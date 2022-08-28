package test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestReentrantLock {
    private int number = 0;
    private final Lock lock = new ReentrantLock();

    public void test() {
        System.out.println("- - Test ReentrantLock - -");
        long time;
        Thread[] threads = new Thread[20];
        for (int t = 0; t < threads.length; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    try {
                        lock.lock();
                        int a = number++;
                        if (a % 10 == 0) {
                            System.out.println(a);
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } finally {
                        lock.unlock();
                    }
                }
            });
            threads[t].start();
        }
        time = System.currentTimeMillis();
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Sum = " + number);
        System.out.println("Time elapsed: " +
                (double) (System.currentTimeMillis() - time) / 1000 + " s");
    }
}



