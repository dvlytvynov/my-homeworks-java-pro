package test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThreadLocal {
    private int number = 0;
    private final ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
    private final Lock lock = new ReentrantLock();

    public void test() {
        System.out.println("- - Test ThreadLocal - -");
        long time;
        Thread[] threads = new Thread[10];
        createAndStartThreads(threads);
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

    private void createAndStartThreads(Thread[] threads) {
        for (int t = 0; t < threads.length; t++) {
            threads[t] = new Thread(() -> {
                Integer oldValue;
                int currentValue;
                for (int i = 0; i < 10; i++) {
                    try {
                        lock.lock();
                        number++;
                        oldValue = threadLocal.get();
                        currentValue = number;
                        threadLocal.set(currentValue);
                        printThreadLocal(oldValue, currentValue);
                        goSleep();
                    } finally {
                        lock.unlock();
                    }
                }
            });
            threads[t].setName("Thread-" + t);
            threads[t].start();
        }
    }

    private void goSleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printThreadLocal(Integer oldValue, int currentValue) {
        String threadName = Thread.currentThread().getName();
        String[] stringArray = threadName.split("-");
        int threadNumber = Integer.parseInt(stringArray[stringArray.length - 1]);
        String space = " ".repeat(threadNumber * 10);
        String stringEnd = System.lineSeparator();
        System.out.printf("%s%s%s%sOld=%d%s%sCur=%d%s",
                space, threadName, stringEnd,
                space, oldValue, stringEnd,
                space, currentValue, stringEnd);
    }
}
