package net.xiaosaguo.study.se.thread.juc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description: 使用 new ReentrantLock().newCondition() 实现 wait() 和 notifyAll()
 *
 * @author xiaosaguo
 * @date 2020/06/23 04:17
 */
class TaskQueue {

    final Queue<String> queue = new LinkedList<>();

    final Lock lock = new ReentrantLock();
    final Condition notEmpty = lock.newCondition();

    public String getTask() throws InterruptedException {
        lock.lock();
        try {
            while (this.queue.isEmpty()) {
                notEmpty.await();
            }
            return queue.remove();
        } finally {
            lock.unlock();
        }
    }

    public void addTask(String name) {
        lock.lock();
        try {
            this.queue.add(name);
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

class WorkerThread03 extends Thread {
    TaskQueue taskQueue;

    public WorkerThread03(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            String name;
            try {
                name = taskQueue.getTask();
            } catch (InterruptedException e) {
                break;
            }
            String result = "Hello, " + name + "!";
            System.out.println(result);
        }
    }
}

@SuppressWarnings("all")
public class Demo03Condition {

    public static void main(String[] args) throws Exception {
        TaskQueue taskQueue = new TaskQueue();
        WorkerThread03 worker = new WorkerThread03(taskQueue);
        worker.start();
        // add task:
        taskQueue.addTask("Bob");
        Thread.sleep(1000);
        taskQueue.addTask("Alice");
        Thread.sleep(1000);
        taskQueue.addTask("Tim");
        Thread.sleep(1000);
        worker.interrupt();
        worker.join();
        System.out.println("END");
    }
}