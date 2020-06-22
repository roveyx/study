package net.xiaosaguo.study.se.thread.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * description: 读写锁能够提高读并发，适合读多写少的情景
 *
 * @author xiaosaguo
 * @date 2020/06/23 04:13
 */
class Counter02 {

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock rlock = lock.readLock();
    private Lock wlock = lock.writeLock();

    private int value = 0;

    public void add(int m) {
        wlock.lock();
        try {
            this.value += m;
        } finally {
            wlock.unlock();
        }
    }

    public void dec(int m) {
        wlock.lock();
        try {
            this.value -= m;
        } finally {
            wlock.unlock();
        }
    }

    public int get() {
        rlock.lock();
        try {
            return this.value;
        } finally {
            rlock.unlock();
        }
    }
}

@SuppressWarnings("all")
public class Demo02ReadWriteLock {

    final static int LOOP = 100;

    public static void main(String[] args) throws Exception {
        Counter02 counter = new Counter02();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < LOOP; i++) {
                    counter.add(1);
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < LOOP; i++) {
                    counter.dec(1);
                }
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter.get());
    }
}
