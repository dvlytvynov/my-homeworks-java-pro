package test.synchronizer;

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch {
    private final CountDownLatch latch = new CountDownLatch(3);

    public void test() {
        System.out.println("- - Test Count Down Latch - -");
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = createThread();
            threads[i].setName("T-" + i);
        }
        long time = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (int i = 0; i < 15; i++) {
            System.out.printf("Latch Count = %d\n", latch.getCount());
            goSleep(50);
            if (i % 5 == 0) {
                latch.countDown();
            }
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
            System.out.println(prefix + space + "before latch");
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(prefix + space + "after latch");
            goSleep(1000);
            System.out.println(prefix + space + "finish");
        });
    }
}
