import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainArrayList {
    public static void main(String[] args) {
        System.out.println("* * * * * Test of ArrayList * * * * *");

        Object[] array1 = {1, "Emma", "Noah",
                2, "Amelia", "Liam",
                3, "Ava", "Elijah",
                2, "Olivia", "Oliver",
                8, "Charlotte", "Grayson"};

        Object[] arrayTest;

        List<Object> nameList = new MyArrayList(12);


        System.out.println();
        System.out.println("--- Test: size(), isEmpty(), add() ---");

        System.out.print("Nothing is added: ");
        System.out.print("size() = " + nameList.size());
        System.out.println(", isEmpty() = " + nameList.isEmpty());

        System.out.print("First element is added: ");
        System.out.print(nameList.add(123) + ", ");
        System.out.print("size() = " + nameList.size());
        System.out.println(", isEmpty() = " + nameList.isEmpty());

        System.out.print("Second element is added: ");
        System.out.print(nameList.add(true) + ", ");
        System.out.print("size() = " + nameList.size());
        System.out.println(", isEmpty() = " + nameList.isEmpty());


        System.out.println();
        System.out.println("--- Test: contains() ---");
        nameList.add("qwer");
        nameList.add(123456);
        nameList.add(2.36);
        nameList.add("12s");
        PrintData(nameList);
        System.out.println("qwer " + nameList.contains("qwer"));
        System.out.println("qwer1 " + nameList.contains("qwer1"));
        System.out.println("12s " + nameList.contains("12s"));
        System.out.println("123456 " + nameList.contains(123456));


        System.out.println();
        System.out.println("--- Test: toArray() ---");
        arrayTest = nameList.toArray();
        PrintArray(arrayTest);


        System.out.println();
        System.out.println("--- Test: toArray(T1[] a) ---");
        System.out.println("Before:");
        arrayTest = new Object[4];
        PrintArray(arrayTest);
        System.out.println("After:");
        arrayTest = nameList.toArray(arrayTest);
        PrintArray(arrayTest);


        System.out.println();
        System.out.println("--- Test: clear(), add() with ensureCapacity() ---");
        nameList.clear();
        for (int i = 0; i < array1.length; i++) {
            nameList.add(array1[i]);
        }
        PrintData(nameList);


        System.out.println();
        System.out.println("--- Test: set() ---");
        System.out.println("12 before: " + nameList.set(12, "---") + ";  set ---");
        System.out.println("10 before: " + nameList.set(10, "+++") + ";  set +++");
        PrintData(nameList);


        System.out.println();
        System.out.println("--- Test: indexOf() ---");
        System.out.println("Ava; indexOf() = " + nameList.indexOf("Ava"));
        System.out.println("Lucas; indexOf() = " + nameList.indexOf("Lucas"));
        System.out.println("John; indexOf() = " + nameList.indexOf("John"));
        System.out.println("1; indexOf() = " + nameList.indexOf(1));
        System.out.println("Grayson; indexOf() = " + nameList.indexOf("Grayson"));
        System.out.println("2; indexOf() = " + nameList.indexOf(2));


        System.out.println();
        System.out.println("--- Test: lastIndexOf() ---");
        System.out.println("Ava; lastIndexOf() = " + nameList.lastIndexOf("Ava"));
        System.out.println("Lucas; lastIndexOf() = " + nameList.lastIndexOf("Lucas"));
        System.out.println("John; lastIndexOf() = " + nameList.lastIndexOf("John"));
        System.out.println("1; lastIndexOf() = " + nameList.lastIndexOf(1));
        System.out.println("Grayson; lastIndexOf() = " + nameList.lastIndexOf("Grayson"));
        System.out.println("2; lastIndexOf() = " + nameList.lastIndexOf(2));


        System.out.println();
        System.out.println("--- Test: remove() ---");
        PrintData(nameList);
        System.out.println("Ava; remove() = " + nameList.remove("Ava"));
        PrintData(nameList);
        System.out.println("2; remove() = " + nameList.remove((Integer) 2));
        PrintData(nameList);
        System.out.println("1; remove() = " + nameList.remove((Integer) 1));
        PrintData(nameList);
        System.out.println("Grayson; remove() = " + nameList.remove("Grayson"));
        PrintData(nameList);
        System.out.println("JJ; remove() = " + nameList.remove("JJ"));
        PrintData(nameList);


        System.out.println();
        System.out.println("--- Test: add(index, element) ---");
        PrintData(nameList);
        System.out.println("add(5, newElem-5)");
        nameList.add(5, "newElem-5");
        PrintData(nameList);
        System.out.println("add(0, newElem-0)");
        nameList.add(0, "newElem-0");
        PrintData(nameList);
        System.out.println("add(lastElem, newElem-last)");
        nameList.add(nameList.size() - 1, "newElem-last");
        PrintData(nameList);


        System.out.println();
        System.out.println("--- Test: remove(index) ---");
        PrintData(nameList);
        System.out.println("remove(10): " + nameList.remove(10));
        PrintData(nameList);
        System.out.println("remove(0): " + nameList.remove(0));
        PrintData(nameList);
        System.out.println("remove(lastElem): " + nameList.remove(nameList.size() - 1));
        PrintData(nameList);


        System.out.println();
        System.out.println("--- Test: iterator() ---");
        Object MyIterator = nameList.iterator();
        for (int i = 0; ((Iterator<?>) MyIterator).hasNext(); i++) {
            System.out.print(((Iterator<?>) MyIterator).next() + "  ");
        }
        System.out.println();


        System.out.println();
        System.out.println("--- Test: containsAll(Collection<?> c) ---");
        List<Object> testList = new MyArrayList<>(4);
        testList.add("Emma");
        testList.add("Noah");
        testList.add((Integer) 3);
        testList.add("+++");
        PrintData(testList);
        System.out.println("nameList.containsAll(testList) = " + nameList.containsAll(testList));


        System.out.println();
        System.out.println("--- Test: addAll(Collection<? extends T> c) ---");
        testList = new MyArrayList<>(5);
        testList.add("Sofia");
        testList.add("Maverick");
        testList.add((Integer) 180);
        testList.add("Violet");
        testList.add("Aurora");
        PrintData(testList);
        System.out.println("nameList.addAll(testList) = " + nameList.addAll(testList));
        PrintData(nameList);


        System.out.println();
        System.out.println("--- Test: addAll(int index, Collection<? extends T> c) ---");
        nameList.clear();
        for (int i = 0; i < array1.length; i++) {
            nameList.add(array1[i]);
        }
        PrintData(nameList);
        PrintData(testList);
        System.out.println("nameList.addAll(6, testList) = " + nameList.addAll(6, testList));
        PrintData(nameList);


        System.out.println();
        System.out.println("--- Test: removeAll(Collection<?> c) ---");
        nameList.add("Aurora");
        PrintData(nameList);
        PrintData(testList);
        System.out.println("nameList.removeAll(testList) = " + nameList.removeAll(testList));
        PrintData(nameList);
        System.out.println("nameList.removeAll(testList) = " + nameList.removeAll(testList));


        System.out.println();
        System.out.println("--- Test: subList() ---");
        PrintData(nameList);
        testList = nameList.subList(4, 10);
        System.out.println("testList = nameList.subList(4, 10)");
        PrintData(testList);


        System.out.println();
        System.out.println("--- Test: retainAll(Collection<?> c) ---");
        testList = new MyArrayList<>(5);
        testList.add("Emma");
        testList.add("Noah");
        testList.add("Amelia");
        testList.add("Elijah");
        testList.add("Peter");
        PrintData(nameList);
        PrintData(testList);
        System.out.println("nameList.retainAll(testList) = " + nameList.retainAll(testList));
        PrintData(nameList);


        System.out.println();
        System.out.println("--- Test: listIterator() ---");
        Object MyListIterator = nameList.listIterator();
        PrintData(nameList);
        System.out.println("hasNext() = " + ((ListIterator<?>) MyListIterator).hasNext());
        System.out.println("next() = " + ((ListIterator<?>) MyListIterator).next() + "  ");
        System.out.println("hasNext() = " + ((ListIterator<?>) MyListIterator).hasNext());
        System.out.println("next() = " + ((ListIterator<?>) MyListIterator).next() + "  ");
        System.out.println("hasPrevious() = " + ((ListIterator<?>) MyListIterator).hasPrevious());
        System.out.println("previous() = " + ((ListIterator<?>) MyListIterator).previous() + "  ");
        System.out.println("nextIndex() = " + ((ListIterator<?>) MyListIterator).nextIndex() + "  ");
        System.out.println("previousIndex() = " + ((ListIterator<?>) MyListIterator).previousIndex() + "  ");
        System.out.println("remove()");
        ((ListIterator<?>) MyListIterator).remove();
        PrintData(nameList);
        System.out.println("set()");
        ((ListIterator<Object>) MyListIterator).set("NewData");
        PrintData(nameList);
        System.out.println("add()");
        ((ListIterator<Object>) MyListIterator).add("Sofia");
        System.out.println("add()");
        ((ListIterator<Object>) MyListIterator).add("John");
        PrintData(nameList);
        System.out.println("next() = " + ((ListIterator<?>) MyListIterator).next() + "  ");
        System.out.println("add()");
        ((ListIterator<Object>) MyListIterator).add("Nik");
        PrintData(nameList);
        System.out.println();







    }

    static void PrintData(List obj) {

        System.out.println("ArrayList elements:");
        System.out.print("[ ");
        for (int i = 0; i < obj.size(); i++) {
            System.out.print(i + ": ");
            System.out.print(obj.get(i) + "  ");

        }
        System.out.println("]");
    }

    static <T> void PrintArray(T[] arr) {

        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + ": " + arr[i] + "  ");
        }
        System.out.println("]");
    }


}




