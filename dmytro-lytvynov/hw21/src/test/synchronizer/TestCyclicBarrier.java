package test.synchronizer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
    private final CyclicBarrier barrier = new CyclicBarrier(3,
            () -> System.out.println("Parties have gathered"));

    public void test() {
        System.out.println("- - Test Cyclic Barrier - -");
        Thread[] threads = new Thread[9];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = createThread();
            threads[i].setName("T-" + i);
        }
        long time = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (int i = 0; i < 15; i++) {
            System.out.printf("Number Waiting = %d  Parties = %d  Is Broken = %b\n",
                    barrier.getNumberWaiting(), barrier.getParties(), barrier.isBroken());
            goSleep(400);
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

    private Thread createThread() {
        return new Thread(() -> {
            String[] nameArray = Thread.currentThread().getName().split("-");
            int index = Integer.parseInt(nameArray[nameArray.length - 1]);
            String space = " ".repeat(index * 10);
            String prefix = String.format("%sThread %s\n", space, Thread.currentThread().getName());
            System.out.println(prefix + space + "start");
            goSleep(index * 100 + 200);
            System.out.println(prefix + space + "before barrier");
            try {
                barrier.await();
                goSleep(1000);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(prefix + space + "after barrier");
            goSleep(1000);
            System.out.println(prefix + space + "finish");
        });
    }
}
