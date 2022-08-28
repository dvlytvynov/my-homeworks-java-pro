import test.TestExtendsThreadClass;
import test.TestRunnableClass;
import test.TestThreadPool;
import test.TestVolatileField;

public class MainMultithreading {
    public static void main(String[] args) {
        System.out.println("- - - - Homework-20 Multithreading - - - -");
        TestRunnableClass testRunnable = new TestRunnableClass();
        testRunnable.test();
        TestExtendsThreadClass testExtendsThread = new TestExtendsThreadClass();
        testExtendsThread.test();
        TestVolatileField testVolatile = new TestVolatileField();
        testVolatile.test();
        TestThreadPool testThreadPool = new TestThreadPool();
        testThreadPool.test();
    }
}
