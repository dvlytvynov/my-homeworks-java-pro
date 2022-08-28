import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainMap {
    public static void main(String[] args) {
        System.out.println("\n* * * * * Test of HashMap * * * * *\n");
        Map<String, String> addressMap = new MyHashMap();
        TestMyMainMap.testSizeAndIsEmpty(addressMap);
        TestMyMainMap.testPutAndGet(addressMap);
        addressMap = new MyHashMap();
        TestMyMainMap.putAllElements(addressMap);
        TestMyMainMap.testRemove(addressMap);
        TestMyMainMap.testContainsKey(addressMap);
        TestMyMainMap.testContainsValue(addressMap);
        TestMyMainMap.testKeySet(addressMap);
        TestMyMainMap.testEntrySet(addressMap);
        TestMyMainMap.testValues(addressMap);
        TestMyMainMap.testClear(addressMap);
        TestMyMainMap.testPutAll(addressMap);
        TestMyMainMap.testOverriddenMethods();
    }

    static <K, V> void getElements(Map<K, V> m) {
        System.out.println("--- Get all elements (Key = Value) ---");
        Set mapSet = m.keySet();
        Iterator setIter = mapSet.iterator();
        while (setIter.hasNext()) {
            K temp = (K) setIter.next();
            System.out.print(temp + " = " + m.get(temp) + ",  ");
        }
        System.out.print("\nsize() = " + m.size());
        System.out.println(", " + "isEmpty() = " + m.isEmpty());
    }
}





