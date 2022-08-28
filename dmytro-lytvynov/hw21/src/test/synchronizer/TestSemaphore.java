package test.synchronizer;

import java.util.concurrent.Semaphore;

public class TestSemaphore {
    private final Semaphore semaphore = new Semaphore(3);

    public void test() {
        System.out.println("- - Test Semaphore - -");
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
            System.out.printf("Available Permits = %d  Queue Length = %d\n",
                    semaphore.availablePermits(), semaphore.getQueueLength());
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
            goSleep(1000);
            System.out.println(prefix + space + "before semaphore");
            try {
                semaphore.acquire();
                goSleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            System.out.println(prefix + space + "after semaphore");
            goSleep(1000);
            System.out.println(prefix + space + "finish");
        });
    }
}
