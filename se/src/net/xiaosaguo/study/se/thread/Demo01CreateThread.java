package net.xiaosaguo.study.se.thread;

/**
 * description: 实现多线程的两种方式
 * <p>
 * 第一种：定义一个线程类
 * 1. extends Thread
 * 2. 重写 run() 方法
 * <p>
 * 第二种：定义一个线程类
 * 1. implements Runnable
 * 2. 重写 run() 方法
 * 3. 使用时 new Thread(runnable)
 * <p>
 * 多线程使用方式：
 * 1. 创建一个线程对象
 * 2. 启动一个线程
 * <p>
 * 线程由操作系统调度，程序本身无法控制，所以无法保证多线程下的代码执行顺序
 *
 * @author xiaosaguo
 * @date 2020/06/22 01:37
 */
public class Demo01CreateThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new ExtendsThread("foo");
        t1.start();
        Runnable runnable = new ImplementsRunnable("bar");
        Thread t2 = new Thread(runnable);
        t2.start();
        for (int i = 0; i < 3; i++) {
            System.out.println("Main!");
            Thread.sleep(200);
        }
        /* out: 每次执行输出结果不一致，执行顺序是无法保证的、随机的
         * Hello, foo!
         * Main!
         * Hello, bar!
         * Main!
         * Hello, foo!
         * Hello, bar!
         * Main!
         * Hello, bar!
         * Hello, foo!
         */
    }
}


class ExtendsThread extends Thread {
    String name;

    public ExtendsThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hello, " + name + "!");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("线程被中断异常");
                e.printStackTrace();
            }
        }
    }
}

class ImplementsRunnable implements Runnable {
    String name;

    public ImplementsRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hello, " + name + "!");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("线程被中断异常");
                e.printStackTrace();
            }
        }
    }
}

