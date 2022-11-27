package cs304.scaling.functions;

import java.util.LinkedList;


public class BatchExecutorTask implements TaskInterface {
    private LinkedList<TaskInterface> batch;

    public BatchExecutorTask(LinkedList<TaskInterface> batch) {
        this.batch = batch;
    }

    @Override
    public void onTask() {
        for (TaskInterface taskInterface : batch) {
            taskInterface.onTask(); //Execute the task for each instance of task
        }
    }
}
