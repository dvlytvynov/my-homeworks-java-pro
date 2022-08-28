package test;

public class TestRunnableClass {
    public void test() {
        System.out.println("- - Test Runnable - -");
        SomeThing thing = new SomeThing();
        Thread myThread1 = new Thread(thing);
        Thread myThread2 = new Thread(() -> {
            String space = " ".repeat(80);
            System.out.println(space + "Another secondary thread started");
            for (int i = 0; i < 11; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(space + "Another secondary thread " + (10 - i));
            }
            System.out.println(space + "Another secondary thread finished");
        });
        myThread1.start();
        myThread2.start();
        System.out.println("The main thread started");
        for (int i = 0; i < 11; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Main thread " + (10 - i));
        }
        System.out.println("The main thread finished");
    }
}

class SomeThing implements Runnable {

    @Override
    public void run() {
        String space = " ".repeat(40);
        System.out.println(space + "The secondary thread started");
        for (int i = 0; i < 11; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(space + "Secondary thread " + (10 - i));
        }
        System.out.println(space + "The secondary thread finished");
    }
}
