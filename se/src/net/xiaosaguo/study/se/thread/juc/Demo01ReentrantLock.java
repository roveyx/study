package net.xiaosaguo.study.se.thread.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Counter01 {

    private Lock lock = new ReentrantLock();

    private int value = 0;

    public void add(int m) {
        lock.lock();
        try {
            this.value += m;
        } finally {
            lock.unlock();
        }
    }

    public void dec(int m) {
        lock.lock();
        try {
            this.value -= m;
        } finally {
            lock.unlock();
        }
    }

    public int get() {
        lock.lock();
        try {
            return this.value;
        } finally {
            lock.unlock();
        }
    }
}

@SuppressWarnings("all")
public class Demo01ReentrantLock {

    final static int LOOP = 100;

    public static void main(String[] args) throws Exception {
        Counter01 counter = new Counter01();
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