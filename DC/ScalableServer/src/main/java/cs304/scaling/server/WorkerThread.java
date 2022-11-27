package cs304.scaling.server;

import cs304.scaling.functions.TaskInterface;

import java.util.concurrent.LinkedBlockingQueue;


public class WorkerThread extends Thread {
    private LinkedBlockingQueue<TaskInterface> FIFO_QUEUE; //Thread-safe blocking queue for queueing tasks from which threads can pick a task and execute

    WorkerThread(LinkedBlockingQueue<TaskInterface> FIFO_QUEUE) {
        this.FIFO_QUEUE = FIFO_QUEUE;
    }

    public void run() {
        while (true) {
            synchronized (FIFO_QUEUE) {
                while (FIFO_QUEUE.isEmpty()) {
                    try {
                        FIFO_QUEUE.wait(); //Send thread to waiting state
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                TaskInterface task = FIFO_QUEUE.poll(); //Poll task from blocking queue
                if (task != null) {
                    task.onTask(); //Execute task
                }
            }
        }
    }
}
