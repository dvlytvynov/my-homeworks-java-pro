import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TestMyMainMap {


    public static void testSizeAndIsEmpty(Map addressMap) {
        System.out.print("Nothing is added: ");
        System.out.print("size() = " + addressMap.size());
        System.out.println(", " + "isEmpty() = " + addressMap.isEmpty());
    }

    public static void testPutAndGet(Map addressMap) {
        System.out.println("\n--- Test put(), get() ---");
        System.out.println("put(\"John\", \"New York\") = " + addressMap.put("John", "New York"));
        System.out.println("get(\"John\") = " + addressMap.get("John"));
        System.out.println("put(\"John\", \"London\") = " + addressMap.put("John", "London"));
        System.out.println("get(\"John\") = " + addressMap.get("John"));
        System.out.print("size() = " + addressMap.size());
        System.out.println(", " + "isEmpty() = " + addressMap.isEmpty());

        System.out.println("put(\"Jennifer\", \"Detroit\") = " + addressMap.put("Jennifer", "Detroit"));
        System.out.println("get(\"Jennifer\") = " + addressMap.get("Jennifer"));
        System.out.print("size() = " + addressMap.size());
        System.out.println(", " + "isEmpty() = " + addressMap.isEmpty());
    }

    public static void putAllElements(Map addressMap) {
        String[] nameArray = new String[]{
                "Olivia", "Emma", "Noah", "Amelia", "Oliver",
                "Ava", "Levi", "Sophia", "Lucas", "Isabella",
                "Mason", "Mia", "Luna", "James", "Ethan",
                "Evelyn", "Leo", "Aria", "Jack", "Ella",
                "Mila", "Hazel"
        };

        String[] cityArray = new String[]{
                "Vienna", "Sofia", "Paris", "Berlin", "Rome",
                "Oslo", "Madrid", "London", "Washington", "New York",
                "Hong Kong", "Tokyo", "Bangkok", "Wellington", "Canberra",
                "Baghdad", "Beijing", "Mexico City", "Havana", "Ottawa",
                "Buenos Aires", "Bogota"
        };

        System.out.println("\n--- Put all Elements ---");
        for (int i = 0; i < nameArray.length; i++) {
            System.out.println("put(\"" + nameArray[i] + "\",\t \"" + cityArray[i] + "\") = "
                    + addressMap.put(nameArray[i], cityArray[i])
                    + ",\t\t size() = " + addressMap.size()
                    + ",\t isEmpty() = " + addressMap.isEmpty());
        }
        MainMap.getElements(addressMap);
    }

    public static void testRemove(Map addressMap) {
        System.out.println("\n--- Test remove() ---");
        System.out.println("remove(Olivia) = " + addressMap.remove("Olivia"));
        System.out.println("remove(Oliver) = " + addressMap.remove("Oliver"));
        System.out.println("remove(Mia) = " + addressMap.remove("Mia"));
        System.out.println("remove(Leo) = " + addressMap.remove("Leo"));
        System.out.println("remove(Max) = " + addressMap.remove("Max"));
        MainMap.getElements(addressMap);
    }

    public static void testContainsKey(Map addressMap) {
        System.out.println("\n--- Test containsKey() ---");
        System.out.println("containsKey(\"Aria\") = " + addressMap.containsKey("Aria"));
        System.out.println("containsKey(\"Jack\") = " + addressMap.containsKey("Jack"));
        System.out.println("containsKey(\"Leo\") = " + addressMap.containsKey("Leo"));
    }

    public static void testContainsValue(Map addressMap) {
        System.out.println("\n--- Test containsValue() ---");
        System.out.println("containsValue(\"Havana\") = " + addressMap.containsValue("Havana"));
        System.out.println("containsValue(\"London\") = " + addressMap.containsValue("London"));
        System.out.println("containsValue(\"Hong Kong\") = " + addressMap.containsValue("Hong Kong"));
        System.out.println("containsValue(\"Olivia\") = " + addressMap.containsValue("Olivia"));
    }

    public static void testKeySet(Map addressMap) {
        System.out.println("\n--- Test keySet() ---");
        Set testSet = new HashSet();
        testSet = addressMap.keySet();
        System.out.println("size() = " + testSet.size());
        Iterator testIter = testSet.iterator();
        System.out.println("--- All elements of keySet() ---");
        while (testIter.hasNext()) {
            System.out.print(testIter.next() + "  ");
        }
        System.out.println();
    }

    public static void testEntrySet(Map addressMap) {
        System.out.println("\n--- Test entrySet() ---");
        Set testEntrySet = new HashSet();
        testEntrySet = addressMap.entrySet();
        System.out.println("size() = " + testEntrySet.size());
        Iterator<Map.Entry> testEntryIter = testEntrySet.iterator();
        System.out.println("--- All elements of entrySet() ---");
        while (testEntryIter.hasNext()) {
            Map.Entry temp = testEntryIter.next();
            System.out.print(temp.getKey() + " = " + temp.getValue() + "  ");
        }
        System.out.println();
    }

    public static void testValues(Map addressMap) {
        System.out.println("\n--- Test values() ---");
        Collection testValues = new ArrayList();
        testValues = addressMap.values();
        System.out.println("size() = " + testValues.size());
        Iterator testValueIter = testValues.iterator();
        System.out.println("--- All elements of value() ---");
        while (testValueIter.hasNext()) {
            System.out.print(testValueIter.next() + "  ");
        }
        System.out.println();
    }

    public static void testClear(Map addressMap) {
        System.out.println("\n--- Test clear() ---");
        addressMap.clear();
        MainMap.getElements(addressMap);
    }

    public static void testPutAll(Map addressMap) {
        String[] nameArray = new String[]{
                "Olivia", "Emma", "Noah", "Amelia", "Oliver",
                "Ava", "Levi", "Sophia", "Lucas", "Isabella",
                "Mason", "Mia", "Luna", "James", "Ethan",
                "Evelyn", "Leo", "Aria", "Jack", "Ella",
                "Mila", "Hazel"
        };

        String[] cityArray = new String[]{
                "Vienna", "Sofia", "Paris", "Berlin", "Rome",
                "Oslo", "Madrid", "London", "Washington", "New York",
                "Hong Kong", "Tokyo", "Bangkok", "Wellington", "Canberra",
                "Baghdad", "Beijing", "Mexico City", "Havana", "Ottawa",
                "Buenos Aires", "Bogota"
        };
        System.out.println("\n--- Test putAll() ---");
        Map<String, String> addressMap1 = new MyHashMap<>();
        Map<String, String> addressMap2 = new MyHashMap<>();
        for (int i = 0; i < nameArray.length; i++) {
            if (i < 4) addressMap1.put(nameArray[i], cityArray[i]);
            if (i > 4) addressMap2.put(nameArray[i], cityArray[i]);
            if (i > 7) break;
        }
        System.out.println("\naddressMap1");
        MainMap.getElements(addressMap1);
        System.out.println("\naddressMap2");
        MainMap.getElements(addressMap2);
        System.out.println("--- Put all Elements into addressMap1 from addressMap2 ---");
        addressMap1.putAll(addressMap2);
        System.out.println("\naddressMap1");
        MainMap.getElements(addressMap1);
        System.out.println("\naddressMap2");
        MainMap.getElements(addressMap2);
    }

    public static void testOverriddenMethods() {
        String[] nameArray = new String[]{
                "Olivia", "Emma", "Noah", "Amelia", "Oliver",
                "Ava", "Levi", "Sophia", "Lucas", "Isabella"
        };

        String[] cityArray = new String[]{
                "Vienna", "Sofia", "Paris", "Berlin", "Rome",
                "Oslo", "Madrid", "London", "Washington", "New York"
        };
        System.out.println("\n--- " +
                "Test class Test.java with overridden methods equals() and hashCode()" +
                "  ---");
        Map<Object, String> testMap = new MyHashMap<>();
        int[] idArray = new int[]{
                2589, 2136, 5874, 9658, 1256,
                1278, 2759, 4591, 6578, 2387
        };
        for (int i = 0; i < 10; i++) {
            TestClass member = new TestClass(nameArray[i], idArray[i]);
            testMap.put(member, cityArray[i]);
        }
        TestClass temp1 = new TestClass("Olivia", 2589);
        System.out.println("remove(\"Olivia\", 2589) = " + testMap.remove(temp1));

        System.out.println("--- All elements of testMap ---");
        Set test = testMap.keySet();
        Iterator<TestClass> testIterator = test.iterator();
        while(testIterator.hasNext()){
            TestClass temp = testIterator.next();
            System.out.println(temp.getName() + " " + temp.getId() + " " + testMap.get(temp));
        }
        System.out.print("\nsize() = " + testMap.size());
        System.out.println(", " + "isEmpty() = " + testMap.isEmpty());
    }
}
