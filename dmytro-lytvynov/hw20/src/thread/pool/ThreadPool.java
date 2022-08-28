package thread.pool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ThreadPool {
    private static final int DEFAULT_THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 3;
    private int nextThreadNumber;
    private int workThreadCount;
    private final List<Thread> threadList = new ArrayList<>();
    private final Queue<Runnable> runnableQueue = new LinkedList<>();
    private final Object synchronizer = new Object();

    public ThreadPool() {
        this(DEFAULT_THREAD_COUNT);
    }

    public ThreadPool(int threadCount) {
        for (int i = 0; i < threadCount; i++) {
            Thread thread = createThread(i);
            threadList.add(thread);
        }
        nextThreadNumber = threadCount;
        workThreadCount = 0;
        for (Thread thread : threadList) {
            thread.start();
        }
    }

    private Thread createThread(int number) {
        final Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
                boolean isEmpty;
                synchronized (synchronizer) {
                    isEmpty = runnableQueue.isEmpty();
                }
                if (isEmpty) {
                    Thread.yield();
                } else {
                    Runnable obj;
                    synchronized (synchronizer) {
                        obj = runnableQueue.poll();
                    }
                    if (obj != null) {
                        synchronized (synchronizer) {
                            workThreadCount++;
                        }
                        obj.run();
                        synchronized (synchronizer) {
                            workThreadCount--;
                        }
                    }
                }
            }
        });
        thread.setName("ThreadPool-" + number);
        return thread;
    }

    public void stop() {
        for (Thread thread : threadList) {
            thread.interrupt();
        }
    }

    public void run(Runnable runnable) {
        if (threadList.size() - workThreadCount < 2) {
            final Thread newThread = createThread(nextThreadNumber);
            threadList.add(newThread);
            nextThreadNumber++;
            newThread.start();
        }
        synchronized (synchronizer) {
            runnableQueue.add(runnable);
        }
    }
}
