package test;

public class TestExtendsThreadClass {
    public int index;
    public void test() {
        System.out.println("- - Test class extends Thread - -");
        int threadQuantity = 4;
        OtherThread[] threads = new OtherThread[threadQuantity];
        for (int i = 0; i < threadQuantity; i++) {
            threads[i] = new OtherThread(i, i * 2 + 5);
            threads[i].start();
        }
        int aliveThreads = 1;
        while (aliveThreads > 0) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            aliveThreads = 0;
            for (Thread t : threads) {
                if (t != null) {
                    if (t.isAlive()) {
                        aliveThreads++;
                    }
                }
            }
            System.out.println("Alive threads quantity = " + aliveThreads);
        }
    }
}

class OtherThread extends Thread {
    private final int number;
    private final int maxValue;

    public OtherThread(int number, int maxValue) {
        this.number = number;
        this.maxValue = maxValue;
    }

    @Override
    public void run() {
        String space = " ".repeat(number * 20);
        for (int i = 0; i < 101; i++) {
            if (i >= maxValue) {
                break;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(space + "Thread-" + number + ": " + i);
        }
    }
}
