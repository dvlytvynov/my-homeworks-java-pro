package garbage.collector;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainGarbageCollector {
    public static void main(String[] args) {
        System.out.println("- - - - Homework-22 Garbage Collector - - - -");
        long m1, m2, m3;
        Runtime rt = Runtime.getRuntime();
        Scanner scanner = new Scanner(System.in);
        String command = "c";
        while (!"q".equals(command)) {
            for (int i = 0; i < 3; i++) {
                m1 = rt.freeMemory();
                createObject(100000);
                m2 = rt.freeMemory();
                if ("c".equals(command)) {
                    System.gc();
                }
                m3 = rt.freeMemory();
                System.out.println("\nm1=" + m1 + "  m2=" + m2 + "  m3=" + m3 +
                        "\nMemory freed by gc() = " + (m3 - m2));
            }
            System.out.print("Enter \"q\" for Quit, \"c\" for Collect Garbage," +
                    " and any symbol for Continue: ");
            command = scanner.nextLine();
        }
    }

    public static void createObject(int count) {
        for (int i = 0; i < count; i++) {
            List<Integer> list = new ArrayList<>();
            Integer object = i;
            list.add(object);
        }
    }
}
