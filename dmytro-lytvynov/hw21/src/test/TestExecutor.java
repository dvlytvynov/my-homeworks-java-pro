package test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestExecutor {
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void test() {
        System.out.println("- - Test Executor Service - -");
        Future<?>[] submits = new Future<?>[10];
        for (int i = 0; i < submits.length; i++) {
            submits[i] = executorService.submit(createRunnableTask(i));
        }
        long time = System.currentTimeMillis();
        threadYield(submits);
        for (int i = 0; i < submits.length; i++) {
            try {
                System.out.println("Runnable task " + i + " result: " + submits[i].get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-".repeat(50));
        for (int i = 0; i < submits.length; i++) {
            submits[i] = executorService.submit(createCallableTask(i));
        }
        threadYield(submits);
        for (int i = 0; i < submits.length; i++) {
            try {
                System.out.println("Callable task " + i + " result: " + submits[i].get() + " s");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Time elapsed: " +
                (double) (System.currentTimeMillis() - time) / 1000 + " s");
        executorService.shutdown();
    }

    private void goSleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void threadYield(Future<?>[] submits) {
        for (Future<?> submit : submits) {
            while (!submit.isDone()) {
                Thread.yield();
            }
        }
    }

    private Runnable createRunnableTask(int number) {
        return () -> {
            String[] nameArray = Thread.currentThread().getName().split("-");
            int index = Integer.parseInt(nameArray[nameArray.length - 1]);
            String space = " ".repeat(index * 20);
            String prefix = String.format("%sThread %s\n%sTask %d\n",
                    space, Thread.currentThread().getName(), space, number);
            System.out.println(prefix + space + "start");
            goSleep(1200);
            System.out.println(prefix + space + "task performing");
            goSleep(1000);
            System.out.println(prefix + space + "finish");
        };
    }

    private Callable<Double> createCallableTask(int number) {
        return () -> {
            long time = System.currentTimeMillis();
            String[] nameArray = Thread.currentThread().getName().split("-");
            int index = Integer.parseInt(nameArray[nameArray.length - 1]);
            String space = " ".repeat(index * 20);
            String prefix = String.format("%sThread %s\n%sTask %d\n",
                    space, Thread.currentThread().getName(), space, number);
            System.out.println(prefix + space + "start");
            goSleep(1000);
            System.out.println(prefix + space + "performing task");
            goSleep(1000);
            System.out.println(prefix + space + "finish");
            return (double) (System.currentTimeMillis() - time) / 1000;
        };
    }
}
