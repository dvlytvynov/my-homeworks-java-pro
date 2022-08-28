package test.synchronizer;

import java.util.concurrent.Exchanger;

public class TestExchanger {
    private final Exchanger<ExchangeObject> exchanger = new Exchanger<>();

    public void test() {
        System.out.println("- - Test Exchanger - -");
        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = createThread();
            threads[i].setName("T-" + i);
        }
        long time = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
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
            long time = System.currentTimeMillis();
            ExchangeObject object = new ExchangeObject();
            object.setThreadName(Thread.currentThread().getName());
            String[] nameArray = Thread.currentThread().getName().split("-");
            int index = Integer.parseInt(nameArray[nameArray.length - 1]);
            String space = " ".repeat(index * 30);
            String prefix = String.format("%sThread %s\n", space, Thread.currentThread().getName());
            System.out.println(prefix + space + "start");
            goSleep(index * 2000 + 1000);
            System.out.println(prefix + space + "before exchange");
            try {
                object.setWorkPeriod((double) (System.currentTimeMillis() - time) / 1000);
                object = exchanger.exchange(object);
                goSleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(space + "object from " + object.getThreadName());
            System.out.println(space + "its work period " + object.getWorkPeriod() + " s");
            goSleep(1000);
            System.out.println(prefix + space + "finish");
        });
    }
}
