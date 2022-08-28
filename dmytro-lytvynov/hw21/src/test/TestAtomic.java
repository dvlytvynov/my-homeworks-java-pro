package test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAdder;

public class TestAtomic {
    private final AtomicInteger numberSum;
    private final DoubleAdder timeSum;
    private int numberSumNonAtomic = 0;
    private double timeSumNonAtomic = 0;

    public TestAtomic() {
        numberSum = new AtomicInteger();
        numberSum.set(0);
        timeSum = new DoubleAdder();
        timeSum.reset();
    }

    public void test() {
        System.out.println("- - Test Atomic - -");
        long time = System.currentTimeMillis();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(createRunnableTask());
            threads[i].setName("T-" + (i + 1));
            threads[i].start();
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
        System.out.println("Time sum = " + timeSum.doubleValue());
        System.out.println("Time sum nonatomic = " + timeSumNonAtomic);
        System.out.println("Number sum = " + numberSum.get());
        System.out.println("Number sum nonatomic = " + numberSumNonAtomic);

    }

    private void goSleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Runnable createRunnableTask() {
        return () -> {
            long time = System.currentTimeMillis();
            String[] nameArray = Thread.currentThread().getName().split("-");
            int index = Integer.parseInt(nameArray[nameArray.length - 1]);
            String space = " ".repeat(index * 10);
            String prefix = String.format("%sThread %s\n", space, Thread.currentThread().getName());
            System.out.println(prefix + space + "start");
            goSleep(500);
            System.out.println(prefix + space + "task performing");
            for (int i = 0; i < 10; i++) {
                goSleep(200);
                numberSumNonAtomic = numberSumNonAtomic + index;
                numberSum.addAndGet(index);
            }
            long longWorkTime = (System.currentTimeMillis() - time);
            double doubleWorkTime = ((double) longWorkTime) / 1000;
            timeSumNonAtomic = timeSumNonAtomic + doubleWorkTime;
            timeSum.add(doubleWorkTime);
            System.out.println(prefix + space + "finish for " + doubleWorkTime + " s");
        };
    }
}
