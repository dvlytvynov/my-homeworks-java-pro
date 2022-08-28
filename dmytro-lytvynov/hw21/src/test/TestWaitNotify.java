package test;

public class TestWaitNotify {
    private int number = 0;
    private final Object o = new Object();

    public void test() {
        System.out.println("- - Test Wait and Notify - -");
        long time;
        Thread[] threads = new Thread[3];
        createAndStartThreads(threads);
        time = System.currentTimeMillis();
        boolean isAllTerminated = false;
        boolean isAllWaiting;
        while (!isAllTerminated) {
            isAllWaiting = true;
            isAllTerminated = true;
            for (Thread thread : threads) {
                Thread.State state = thread.getState();
                printThreadInfo(thread, state.name());
                if (!"WAITING".equals(state.name())) {
                    isAllWaiting = false;
                }
                if (thread.isAlive()) {
                    isAllTerminated = false;
                }
            }
            if (isAllWaiting) {
                synchronized (o) {
                    System.out.println("All threads are waiting");
                    o.notify();
                }
            }
            goSleep(1000);
        }
        System.out.println("Sum = " + number);
        System.out.println("Time elapsed: " +
                (double) (System.currentTimeMillis() - time) / 1000 + " s");
    }

    private void createAndStartThreads(Thread[] threads) {
        for (int t = 0; t < threads.length; t++) {
            threads[t] = new Thread(() -> {
                for (int i = 0; i < 3; i++) {
                    printThreadInfo(Thread.currentThread(), "Before synchronized 1");
                    goSleep(1000);
                    synchronized (o) {
                        try {
                            printThreadInfo(Thread.currentThread(), "Before wait");
                            o.wait();
                            printThreadInfo(Thread.currentThread(), "After wait");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    printThreadInfo(Thread.currentThread(), "Before synchronized 2");
                    goSleep(100);
                    synchronized (o) {
                        printThreadInfo(Thread.currentThread(), "Before notify");
                        o.notify();
                        printThreadInfo(Thread.currentThread(), "After notify");
                    }
                    number++;
                }
            });
            threads[t].setName("Thread-" + (t + 1));
            threads[t].start();
        }
    }

    private void goSleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printThreadInfo(Thread thread, String currentPlace) {
        String threadName = thread.getName();
        String[] stringArray = threadName.split("-");
        int threadNumber = Integer.parseInt(stringArray[stringArray.length - 1]);
        String space = " ".repeat(threadNumber * 20);
        String stringEnd = System.lineSeparator();
        System.out.printf("%s%s%s%s%s%s",
                space, threadName, stringEnd,
                space, currentPlace, stringEnd);
    }
}
