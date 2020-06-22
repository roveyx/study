package net.xiaosaguo.study.se.thread.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
class PrintTask implements Runnable {
    String name;

    public PrintTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hello, " + name + "!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

@SuppressWarnings("all")
public class Demo06ThreadPool {

    public static void main(String[] args) throws Exception {
        // 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，
        // 这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。

        // 创建一个指定数量个线程的线程池
        // ExecutorService executor = Executors.newFixedThreadPool(3);

        // 创建一个单线程线程池
        // ExecutorService executor = Executors.newSingleThreadExecutor();

        // 根据任务数量动态创建线程数量
        // ExecutorService executor = Executors.newCachedThreadPool();

        // 指定线程池线程数量上限
        ExecutorService executor = new ThreadPoolExecutor(
                3,
                10,
                60L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        executor.submit(new PrintTask("Bob"));
        executor.submit(new PrintTask("Alice"));
        executor.submit(new PrintTask("Tim"));
        executor.submit(new PrintTask("Robot"));
        Thread.sleep(10000);
        executor.shutdown();
    }
}
