package cs304.scaling.server;

import cs304.scaling.functions.BatchExecutorTask;
import cs304.scaling.functions.TaskInterface;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;


class ThreadPool {
    private int numThreads;
    private int batchSize;
    private double batchTime;
    private long batchTimekeeper; //Timestamp containing the last batch execution time.
    private LinkedList<LinkedList<TaskInterface>> allTasks; //LinkedList containing lists of tasks. Each list will be executed once it reaches a size of batchSize
    private LinkedBlockingQueue<TaskInterface> executionUnitTasks; //Blocking queue that is subscribed to by the executor threads

    ThreadPool(int batchSize, double batchTime, int numThreads) {
        this.numThreads = numThreads;
        this.batchSize = batchSize;
        this.batchTime = batchTime;
        batchTimekeeper = System.currentTimeMillis();
        allTasks = new LinkedList<>();
        allTasks.add(new LinkedList<>());
        executionUnitTasks = new LinkedBlockingQueue<>();
    }

    /**
     * Initialize numThreads number of threads
     */
    void initiateThreads() {
        int tNum = 0;
        while (tNum < numThreads) {
            new WorkerThread(executionUnitTasks).start();
            tNum++;
        }
    }

    /**
     * This function takes the parameter task and tries to add it into the task list. While doing so it will check if any
     * task list has reached batchSize. If they have, it will take appropriate steps, detailed within function
     * @param task
     */
    void addTaskToBatch(TaskInterface task) {
        synchronized (allTasks) {
            LinkedList<TaskInterface> list = allTasks.remove(); //Remove the head(also a list) of the list
            list.add(task); //Add task to the list that is at the head of the nested linked list(list of list of tasks)
            allTasks.add(list); //Add new list to the linked list of linked lists

            // The subsequent block of code checks whether or not the topmost list in the list of lists has reached a size of batch size
            // or if the last batch was executed before batchTime milliseconds. If any of those conditions are true, hen the first list
            // from the list of lists is added to a wrapper Batch executor task and that task is added to the Linked Blocking queue that
            // all the executor threads(worker threads) subscribe to.

            if (allTasks.peek().size() >= batchSize || (double) (System.currentTimeMillis() - batchTimekeeper) >= batchTime) {
                BatchExecutorTask batch = new BatchExecutorTask(allTasks.remove());
                batchTimekeeper = System.currentTimeMillis(); //Set execution time of last batch to now
                allTasks.add(new LinkedList<TaskInterface>());
                notifyAndExecuteImmediate(batch); //Add batch task to Linked Blocking queue
            }
        }
    }

    /**
     * Function that adds the batch task to the FIFO_QUEUE(linked blocking queue) so that it can be executed by an available thread.
     * @param batch
     */
    void notifyAndExecuteImmediate(TaskInterface batch) {
        synchronized (executionUnitTasks) {
            executionUnitTasks.add(batch);
            executionUnitTasks.notify();
        }
    }
}
