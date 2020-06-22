package net.xiaosaguo.study.se.thread;

/**
 * description: 使用 interrupt() 中断线程
 * <p>
 * 如果线程出等待状态时，被中断了，该线程会立即捕获到 InterruptedException
 *
 * @author xiaosaguo
 * @date 2020/06/22 02:26
 */
public class Demo03InterruptedThread {

    public static void main(String[] args) throws Exception {
        InterruptedThread t = new InterruptedThread();
        t.start();
        System.out.println("Main  睡11秒");
        Thread.sleep(11000);
        System.out.println("Main  睡了11秒，醒了");
        System.out.println("Main  无聊，干它丫的");
        t.interrupt();
        System.out.println("Main  狗呆");
    }
}

class InterruptedThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread  开搞");
        while (!isInterrupted()) {
            System.out.println("Thread  吃点");
            try {
                System.out.println("Thread  好饱，睡5秒，不要再我睡觉的时候中断我");
                Thread.sleep(5000);
                System.out.println("Thread  睡了5秒，醒了，还没被中断，继续执行: isInterrupted()=" + isInterrupted());
                System.out.println("Thread  我被输出了吗？如果在睡觉的时候被中断了，" +
                        "就睡不醒了，会立刻 catch 到 InterruptedException，sleep() 方法后的代码是不会被执行的");
            } catch (InterruptedException e) {
                System.out.println("Thread  Interrupted! Fuck the dog! 刚睡1秒就被干了！不能愉快的吃睡循环了。。。");
                break;
            }
        }
        System.out.println("Thread  狗呆");
    }
}

