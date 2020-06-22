package net.xiaosaguo.study.se.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * description: 当前线程调用另一个线程对象的 join() 方法后，当前线程需要等待被调用的线程对象表示的线程执行结束后才能继续执行
 *
 * @author xiaosaguo
 * @date 2020/06/22 02:15
 */
public class Demo02JoinThread {
    public static void main(String[] args) throws Exception {
        Thread t1 = new JoinThread("this is join，等我执行完");
        System.out.println("START");
        t1.start();
        t1.join(); // main线程将等待线程t1执行结束后继续执行
        System.out.println("END");

        System.out.println("\n---\n");

        List<Thread> threads = new ArrayList<>();
        for (String name : Arrays.asList("张三", "李四", "王五")) {
            threads.add(new JoinThread(name));
        }
        System.out.println("START");
        for (Thread t : threads) {
            t.start();
            t.join();
        }
        System.out.println("END");
    }
}

class JoinThread extends Thread {
    String name;

    public JoinThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Hello, " + name + "!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("Goodbye, " + name + "!");
    }
}
