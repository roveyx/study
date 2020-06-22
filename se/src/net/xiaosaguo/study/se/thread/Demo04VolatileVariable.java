package net.xiaosaguo.study.se.thread;

/**
 * description: 线程之间共享变量需要使用 volatile 关键字标记，能确保线程读取到的是更新后的变量值
 * <p>
 * 使用 volatile 关键字标记
 * 1. 每次访问变量时，总是获取主内存的最新值
 * 2. 每次修改变量后，立刻写回到主内存
 *
 * @author xiaosaguo
 * @date 2020/06/22
 */
public class Demo04VolatileVariable {
    public static void main(String[] args) throws InterruptedException {
        VolatileVariableThread t = new VolatileVariableThread();
        t.start();
        Thread.sleep(1);
        t.running = false;
    }

}

class VolatileVariableThread extends Thread {
    volatile boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Hello!");
        }
    }

}
