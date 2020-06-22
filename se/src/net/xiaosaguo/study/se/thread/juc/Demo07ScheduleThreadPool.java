package net.xiaosaguo.study.se.thread.juc;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
class HelloTask implements Runnable {
    String name;

    public HelloTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Hello, " + name + "! It is " + LocalTime.now());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("Goodbye, " + name + "! It is " + LocalTime.now());
    }
}

@SuppressWarnings("all")
/**
 * description: ScheduledThreadPool 定期调用多个任务
 *
 * @author xiaosaguo
 * @date 2020/06/23 01:51
 */
public class Demo07ScheduleThreadPool {

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
        // 每5秒执行一次任务
        executor.scheduleAtFixedRate(new HelloTask("周期3秒"), 2, 3, TimeUnit.SECONDS);
        // 任务完成5秒后再次执行任务
        executor.scheduleWithFixedDelay(new HelloTask("间隔3秒"), 2, 3, TimeUnit.SECONDS);
    }

}
