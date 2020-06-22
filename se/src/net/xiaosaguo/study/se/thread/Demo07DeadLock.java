package net.xiaosaguo.study.se.thread;

class SharedObject {

    final Object lockA = new Object();
    final Object lockB = new Object();

    int accountA = 1000;
    int accountB = 2000;

    public void a2b(int balance) {
        synchronized (lockA) {
            accountA -= balance;
            synchronized (lockB) {
                accountB += balance;
            }
        }
    }

    public void b2a(int balance) {
        synchronized (lockB) {
            accountB -= balance;
            synchronized (lockA) {
                accountA += balance;
            }
        }
    }
}

class AThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < Demo07DeadLock.LOOP; i++) {
            Demo07DeadLock.shared.a2b(1);
            if (i % 100 == 0) {
                System.out.println(".");
            }
        }
    }
}

class BThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < Demo07DeadLock.LOOP; i++) {
            Demo07DeadLock.shared.b2a(1);
            if (i % 100 == 0) {
                System.out.println(".");
            }
        }
    }
}

/**
 * description: 避免死锁，将获取的锁对象的顺序保持一致
 *
 * @author xiaosaguo
 * @date 2020/06/22 17:51
 */
public class Demo07DeadLock {

    final static int LOOP = 1000;

    public static SharedObject shared = new SharedObject();

    public static void main(String[] args) throws Exception {
        Thread t1 = new AThread();
        Thread t2 = new BThread();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("END");
    }
}