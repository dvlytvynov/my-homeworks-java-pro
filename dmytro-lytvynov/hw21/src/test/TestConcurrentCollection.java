package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TestConcurrentCollection {
    private final List<Integer> list;
    private final List<Integer> synchronizedList;
    private final Map<String, Integer> map;
    private final Map<String, Integer> synchronizedMap;
    private final Map<String, Integer> concurrentMap;
    private final Queue<Integer> queue;
    private final Queue<Integer> concurrentQueue;

    public TestConcurrentCollection() {
        list = new ArrayList<>();
        synchronizedList = Collections.synchronizedList(new ArrayList<>());
        map = new HashMap<>();
        synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        concurrentMap = new ConcurrentHashMap<>();
        queue = new LinkedList<>();
        concurrentQueue = new ConcurrentLinkedQueue<>();
    }

    public void test() {
        System.out.println("- - Test Concurrent Collections - -");
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
        compareLists();
        compareMaps();
        compareQueues();
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
            String[] nameArray = Thread.currentThread().getName().split("-");
            int index = Integer.parseInt(nameArray[nameArray.length - 1]);
            String space = " ".repeat(index * 10);
            String prefix = String.format("%sThread %s\n", space, Thread.currentThread().getName());
            System.out.println(prefix + space + "start");
            goSleep(150);
            System.out.println(prefix + space + "task performing");
            for (int i = 0; i < 100; i++) {
                queue.add(i);
                concurrentQueue.add(i);
                list.add(i);
                synchronizedList.add(i);
                map.put(Thread.currentThread().getName() + ":" + i, i);
                synchronizedMap.put(Thread.currentThread().getName() + ":" + i, i + 100);
                concurrentMap.put(Thread.currentThread().getName() + ":" + i, i + 1000);
                goSleep(100);
            }
        };
    }

    private void compareLists() {
        System.out.println("------------------------- Compare Lists -------------------------");
        Collections.sort(list);
        Collections.sort(synchronizedList);
        boolean isEqual = true;
        int listSize = Math.min(list.size(), synchronizedList.size());
        if (list.size() != synchronizedList.size()) {
            isEqual = false;
            System.out.println("List != Synchronized List because of size");
        }
        for (int i = 0; i < listSize; i++) {
            int value1 = list.get(i);
            int value2 = synchronizedList.get(i);
            if (value1 != value2) {
                isEqual = false;
                System.out.println("\nList != Synchronized List because of values: "
                        + value1 + " " + value2);
            }
        }
        System.out.println("List == Synchronized List: " + isEqual);
        System.out.println("List = " + list);
        System.out.println("List size = " + list.size());
        System.out.println("Synchronized List = " + synchronizedList);
        System.out.println("Synchronized List size = " + synchronizedList.size());
    }

    private void compareMaps() {
        System.out.println("------------------------- Compare Maps -------------------------");
        Set<String> synchronizedMapKeySet = synchronizedMap.keySet();
        System.out.println("map size = " + map.size());
        System.out.println("synchronized Map size = " + synchronizedMap.size());
        System.out.println("concurrent Map size = " + concurrentMap.size());
        boolean isEqual1 = true;
        boolean isEqual2 = true;
        for (String key : synchronizedMapKeySet) {
            int mapValue;
            try {
                mapValue = map.get(key);
            } catch (NullPointerException e) {
                System.out.println("Key = " + key + " is absent in Map");
                mapValue = 0;
                isEqual1 = false;
            }
            int synchronizedMapValue = synchronizedMap.get(key);
            int concurrentMapValue = concurrentMap.get(key);
            if (mapValue != synchronizedMapValue - 100) {
                isEqual1 = false;
                System.out.println("\nMap != Synchronized Map because of values: "
                        + mapValue + " " + (synchronizedMapValue - 100) + " of key " + key);
            }
            if (concurrentMapValue - 1000 != synchronizedMapValue - 100) {
                isEqual2 = false;
                System.out.println("\nConcurrent Map != Synchronized Map because of values: "
                        + (concurrentMapValue - 1000) + " "
                        + (synchronizedMapValue - 100) + " of key " + key);
            }
        }
        System.out.println("Map == Synchronized Map: " + isEqual1);
        System.out.println("Concurrent Map == Synchronized Map: " + isEqual2);
    }

    private void compareQueues() {
        System.out.println("------------------------- Compare Queues -------------------------");
        System.out.println("Queue size = " + queue.size());
        System.out.println("Concurrent Queue size = " + concurrentQueue.size());
        Integer head = 0;
        System.out.println("Queue:");
        while (head != null) {
            head = queue.poll();
            System.out.print(head + " ");
        }
        System.out.println();
        head = 0;
        System.out.println("Concurrent Queue:");
        while (head != null) {
            head = concurrentQueue.poll();
            System.out.print(head + " ");
        }
    }
}
