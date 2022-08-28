package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReentrantReadWriteLock {
    private int number = 0;
    private int lastListItem;
    private final List<Integer> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void test() {
        System.out.println("- - Test ReentrantReadWriteLock - -");
        long time;
        Thread[] writeThreads = new Thread[10];
        Thread[] raedThreads = new Thread[10];
        createAndStartWriteThreads(writeThreads);
        createAndStartReadThreads(raedThreads);
        time = System.currentTimeMillis();
        joinThreads(writeThreads);
        joinThreads(raedThreads);
        System.out.println("Sum = " + number);
        System.out.println("List size = " + list.size());
        System.out.println("The last list item = " + list.get(list.size() - 1));
        System.out.println("Time elapsed: " +
                (double) (System.currentTimeMillis() - time) / 1000 + " s");
    }

    private void goSleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void joinThreads(Thread[] threads) {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createAndStartWriteThreads(Thread[] threads) {
        for (int t = 0; t < threads.length; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    final Lock wLock = lock.writeLock();
                    try {
                        wLock.lock();
                        int a = ++number;
                        list.add(a);
                        System.out.println("Write: " + a);
                        goSleep(100);
                    } finally {
                        wLock.unlock();
                    }
                }
            });
            threads[t].start();
        }
    }

    private void createAndStartReadThreads(Thread[] threads) {
        for (int t = 0; t < threads.length; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    final Lock rLock = lock.readLock();
                    try {
                        rLock.lock();
                        if (list.size() > 0) {
                            lastListItem = list.get(list.size() - 1);
                            System.out.println("Read the last list item: " + lastListItem);
                        }
                        goSleep(150);
                    } finally {
                        rLock.unlock();
                    }
                }
            });
            threads[t].start();
        }
    }
}



