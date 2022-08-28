package test;

public class TestVolatileField {
    public void test() {
        System.out.println("- - Test Volatile modifier - -");
        NumberChanger changer1 = new NumberChanger(1);
        Thread t1 = new Thread(changer1);
        t1.start();
        NumberChanger changer2 = new NumberChanger(2);
        Thread t2 = new Thread(changer2);
        t2.start();
        waiter(t1);
        waiter(t2);
        System.out.println(Example.number);
    }

    private void waiter(Thread thread) {
        if (thread.isAlive()) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class NumberChanger implements Runnable {
    private final int threadNumber;

    NumberChanger(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Example.number = Example.number + 1000010000;
            System.out.println("Thread number = " + threadNumber +
                    "    Example.number = " + Example.number);
        }
    }
}

class Example {
    public static volatile long number = 10000000000000000L;
}