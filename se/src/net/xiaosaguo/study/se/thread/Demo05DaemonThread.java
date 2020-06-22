package net.xiaosaguo.study.se.thread;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * description: 守护线程
 * <p>
 * 将线程变为守护线程
 * t.setDaemon(true);
 * <p>
 * 守护线程的特性：
 * 1. 守护线程就是个弟弟，是为其他线程提供服务的线程
 * 2. 当所有非守护线程执行完毕后，虚拟机就会退出，即虚拟机退出时，可不管守护线程是否执行完毕，会直接退出，相当于把守护线程强制干掉
 * 3. 守护线程不能持有资源，因为虚拟机退出时可不会给守护线程释放资源的机会
 *
 * @author xiaosaguo
 * @date 2020/06/22 04:17
 */
public class Demo05DaemonThread {
    public static void main(String[] args) throws Exception {
        System.out.println("Main start");
        TimerThread t = new TimerThread();
        t.setDaemon(true);
        t.start();
        Thread.sleep(5000);
        System.out.println("Main end");
    }
}

class TimerThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}