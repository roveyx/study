package net.xiaosaguo.study.se.thread;


/**
 * description: 线程安全才能保证数据一致
 * 线程安全：多个线程同时读写共享变量，数据是同步的
 * <p>
 * 下面代码期望结果为 0 ，实际可能不为0
 * 原因分析：
 * 1. 两个线程同时执行
 * 2. 执行的代码为： n = n + 1
 * 3. 如果是原子操作，不会出现线程不安全的情况，而 n = n + 1 不是一个原子操作，编译器在执行这句代码时是转换为三条命令逐条执行的
 * -     ILOAD
 * -     IADD
 * -     ISTORE
 * 4. 如果A线程执行完以第一条命令
 * -     A ILOAD    线程A 从主内存（堆）中读取 n 的值，存储在线程A的栈内存中。假设此时 n = 100；
 * 5. 此时线程B被调用，线程A被迫暂停，B依次执行文案三条命令：
 * -     B ILOAD    线程B 从主内存（堆）中读取 n 的值，存储在线程B的栈内存中。，n = 100；
 * -     B IADD     线程B 100 + 1，
 * -     B ISTORE   线程B n = 101，并将 n = 101 写回主内存中
 * 6. B线程执行完毕后，A线程接着执行
 * -     B IADD     线程A 100 + 1，注意：此时主存中的 n 已经为 101，但是线程A栈内存中的 n 依旧是最开始读取的 100
 * -     B ISTORE   线程A n = 101，将 n = 101 写回主内存中
 * 7. 预期结果是 102，实际结果是 101
 * 8. 解决方案，将可能会 造成线程间共享数据不同步（对共享变量进行修改） 的代码变为原子操作
 * 9. 方式 使用 synchronized 对这部分代码进行锁定
 * <p>
 * 理解：
 * 多个线程同时运行，线程调度由操作系统决定，程序本身无法决定，
 * Java 代码又是顺序执行的，所以有多少个线程执行，执行完毕之后，代码的执行顺序是串行的，这个串行顺序是执行中各个线程抢占执行的记录，这个串行顺序是不固定的
 * <p>
 * synchronized:
 * Synchronized是Java中解决并发问题的一种最常用的方法，也是最简单的一种方法。
 * Synchronized的作用主要有三个：
 * （1）确保线程互斥的访问同步代码
 * （2）保证共享变量的修改能够及时可见
 * （3）有效解决重排序问题。
 * <p>
 * Java中每一个对象都可以作为锁，这是synchronized实现同步的基础
 * 对同一个共享数据的修改，应该使用同一个锁。
 * <p>
 * 对 synchronized 的理解：
 * - synchronized(lock | Lock.class){}    第一种
 * - public synchronized void test1(){}   第二种
 * - 第二种实际效果
 * - public void test1(){synchronized(this){}}
 * <p>
 * synchronized 确定的是被锁定的代码范围，传入的对象或Class对象是锁，对象里含有锁信息，记录了锁的状态，线程名等。
 *
 * @author xiaosaguo
 * @date 2020/06/22 14:20
 */
public class Demo06ThreadSync {
    public static void main(String[] args) throws Exception {
        Thread t1 = new AddThread();
        Thread t2 = new DecThread();
        t1.start();
        t2.start();
        // 此时 t1、t2 都在执行
        // 确保 t1 执行完了，接着执行
        t1.join();
        // 确保 t2 也是执行完了的，接着执行
        t2.join();
        System.out.println(SharedVariable.count);

        System.out.println("\n---\n");

        Thread t3 = new SyncAddThread();
        Thread t4 = new SyncDecThread();
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println(SharedVariable.count2);
    }
}

class SharedVariable {
    public static final int LOOP = 1000;
    public static int count = 0;
    public static int count2 = 0;
    public static final Object LOCK = new Object();
}

class AddThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < SharedVariable.LOOP; i++) {
            SharedVariable.count += 1;
        }
    }
}

class DecThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < SharedVariable.LOOP; i++) {
            SharedVariable.count -= 1;
        }
    }
}

class SyncAddThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < SharedVariable.LOOP; i++) {
            synchronized (SharedVariable.LOCK) {
                SharedVariable.count2 += 1;
            }
        }
    }
}

class SyncDecThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < SharedVariable.LOOP; i++) {
            synchronized (SharedVariable.LOCK) {
                SharedVariable.count2 -= 1;
            }
        }
    }
}
