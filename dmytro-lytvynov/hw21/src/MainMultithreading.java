import test.fork.join.TestForkJoin;
import test.TestReentrantLock;
import test.TestReentrantReadWriteLock;
import test.TestStreamParallel;
import test.TestThreadLocal;
import test.TestWaitNotify;
import test.TestAtomic;
import test.TestConcurrentCollection;
import test.TestExecutor;
import test.synchronizer.TestCountDownLatch;
import test.synchronizer.TestCyclicBarrier;
import test.synchronizer.TestExchanger;
import test.synchronizer.TestSemaphore;

public class MainMultithreading {
    public static void main(String[] args) {
        System.out.println("- - - - Homework-21 MainMultithreading - - - -");
        new TestReentrantLock().test();
        new TestReentrantReadWriteLock().test();
        new TestThreadLocal().test();
        new TestWaitNotify().test();
        new TestSemaphore().test();
        new TestCountDownLatch().test();
        new TestCyclicBarrier().test();
        new TestExchanger().test();
        new TestExecutor().test();
        new TestAtomic().test();
        new TestStreamParallel().test();
        new TestConcurrentCollection().test();
        new TestForkJoin().test();
    }
}
