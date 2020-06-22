package net.xiaosaguo.study.se.thread.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class WorkerThread04 extends Thread {
    BlockingQueue<String> taskQueue;

    public WorkerThread04(BlockingQueue<String> taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            String name;
            try {
                name = taskQueue.take();
            } catch (InterruptedException e) {
                break;
            }
            String result = "Hello, " + name + "!";
            System.out.println(result);
        }
    }
}


@SuppressWarnings("all")
public class Demo04ConcurrentBlockingQueue {

    public static void main(String[] args) throws Exception {
        BlockingQueue<String> taskQueue = new ArrayBlockingQueue<>(100);
        WorkerThread04 worker = new WorkerThread04(taskQueue);
        worker.start();
        // add task:
        taskQueue.put("Bob");
        Thread.sleep(1000);
        taskQueue.put("Alice");
        Thread.sleep(1000);
        taskQueue.put("Tim");
        Thread.sleep(1000);
        worker.interrupt();
        worker.join();
        System.out.println("END");
    }
}
