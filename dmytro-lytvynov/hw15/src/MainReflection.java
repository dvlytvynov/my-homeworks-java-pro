import reflection.apps.ReflectionExceptions;
import reflection.apps.ReflectionMapUtils;

import java.util.HashMap;
import java.util.Map;

public class MainReflection {
    public static void main(String[] args) throws ReflectionExceptions {
        System.out.println("- - - - - Homework-15 Reflection - - - - -\n");
        Map<String, Integer> map = ReflectionMapUtils.createMap();

        String[] keys = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December", "Other"};
        Integer[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        TestReflection.putTest(map, keys, values);
        System.out.println("HashMap after call method \"put\":\n" + map);

        String[] keysForRemove = {"January", "February", "December", "Other", "14", "15"};
        System.out.println(TestReflection.removeTest(map, keysForRemove));
        System.out.println("HashMap after call method \"remove\":\n" + map);

        String directory = "resources";
        String fileName = "HashMapFile.txt";
        TestReflection.putTest(map, keys, values);
        TestReflection.outputStreamTest(map, directory, fileName);
        System.out.println("\nHashMap was output to file\"" + fileName + "\"");
    }
}
