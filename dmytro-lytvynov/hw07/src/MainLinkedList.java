import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainLinkedList {
    public static void main(String[] args) {
        System.out.println("* * * * * Test of LinkedList * * * * *");

        List carList = new MyLinkedList();


        System.out.println();
        System.out.println("--- Test: size(), isEmpty(), add() ---");
        System.out.print("Nothing is added: ");
        System.out.print("size() = " + carList.size());
        System.out.println(", isEmpty() = " + carList.isEmpty());
        System.out.print("add(\"Toyota\") = " + carList.add("Toyota"));
        System.out.print(", size() = " + carList.size());
        System.out.println(", isEmpty() = " + carList.isEmpty());
        System.out.print("add(\"Audi\") = " + carList.add("Audi"));
        System.out.print(", size() = " + carList.size());
        System.out.println(", isEmpty() = " + carList.isEmpty());


        carList.add("Mercedes");

        System.out.println("--- All carList from first to last: ");
        ((MyLinkedList<?>) carList).printAll();


        System.out.println();
        System.out.println("--- Test: contains() ---");
        System.out.println("contains(\"Toyota\") = " + carList.contains("Toyota"));
        System.out.println("contains(\"Mercedes\") = " + carList.contains("Mercedes"));
        System.out.println("contains(\"Audi\") = " + carList.contains("Audi"));
        System.out.println("contains(\"Suzuki\") = " + carList.contains("Suzuki"));


        System.out.println();
        System.out.println("--- Test: iterator() ---");
        Object MyLinkedIterator = carList.iterator();
        System.out.print("hasNext() = " + ((Iterator<?>) MyLinkedIterator).hasNext());
        System.out.println("  next() = " + ((Iterator<?>) MyLinkedIterator).next());
        System.out.print("hasNext() = " + ((Iterator<?>) MyLinkedIterator).hasNext());
        System.out.println("  next() = " + ((Iterator<?>) MyLinkedIterator).next());
        System.out.print("hasNext() = " + ((Iterator<?>) MyLinkedIterator).hasNext());
        System.out.println("  next() = " + ((Iterator<?>) MyLinkedIterator).next());
        System.out.print("hasNext() = " + ((Iterator<?>) MyLinkedIterator).hasNext());
        System.out.println();


        System.out.println();
        System.out.println("--- Test: toArray() ---");
        carList.add("Nissan");
        ((MyLinkedList<?>) carList).printAll();
        Object[] testArray = new Object[carList.size()];
        testArray = carList.toArray();
        for (int i = 0; i < testArray.length; i++) {
            System.out.println(i + " :  " + testArray[i]);
        }


        System.out.println();
        System.out.println("--- Test: toArray(T1[]) ---");
        Object[] newTestArray = new Object[10];
        newTestArray = carList.toArray(newTestArray);
        for (int i = 0; i < newTestArray.length; i++) {
            System.out.println(i + " :  " + newTestArray[i]);
        }


        System.out.println();
        System.out.println("--- Test: remove() ---");
        ((MyLinkedList<?>) carList).printAll();
        System.out.println("remove(\"Audi\") = " + carList.remove("Audi"));
        System.out.println("remove(\"Subaru\") = " + carList.remove("Subaru"));
        ((MyLinkedList<?>) carList).printAll();


        System.out.println();
        System.out.println("--- Test: containsAll() ---");
        carList.add("Lincoln");
        carList.add("Mazda");
        ((MyLinkedList<?>) carList).printAll();
        List<Object> testList = new MyLinkedList<>();
        testList.add("Lincoln");
        testList.add("Mazda");
        testList.add("Toyota");
        ((MyLinkedList<?>) testList).printAll();
        System.out.println("containsAll() = " + carList.containsAll(testList));
        testList.add("Audi");
        ((MyLinkedList<?>) carList).printAll();
        ((MyLinkedList<?>) testList).printAll();
        System.out.println("containsAll() = " + carList.containsAll(testList));


        testList.add("Ford");
        testList.add("Lexus");
        testList.remove("Toyota");
        testList.remove("Mazda");
        System.out.println();
        System.out.println("--- Test: addAll() ---");
        ((MyLinkedList<?>) carList).printAll();
        ((MyLinkedList<?>) testList).printAll();
        System.out.println("addAll() = " + carList.addAll(testList));
        ((MyLinkedList<?>) carList).printAll();


        System.out.println();
        System.out.println("--- Test: addAll(index) ---");
        testList = new MyLinkedList<>();
        testList.add("Hyundai");
        testList.add("Jeep");
        testList.add("Opel");
        ((MyLinkedList<?>) testList).printAll();
        ((MyLinkedList<?>) carList).printAll();
        System.out.println("addAll(1, testList) = " + carList.addAll(5, testList));
        ((MyLinkedList<?>) carList).printAll();


        System.out.println();
        System.out.println("--- Test: removeAll() ---");
        ((MyLinkedList<?>) testList).printAll();
        ((MyLinkedList<?>) carList).printAll();
        System.out.println("addAll(1, testList) = " + carList.removeAll(testList));
        ((MyLinkedList<?>) carList).printAll();


        System.out.println();
        System.out.println("--- Test: get() ---");
        System.out.println("get(0) = " + carList.get(0));
        System.out.println("get(1) = " + carList.get(1));
        System.out.println("get(4) = " + carList.get(4));
        System.out.println("get(8) = " + carList.get(8));


        System.out.println();
        System.out.println("--- Test: set() ---");
        System.out.println("set(4, \"Skoda\") = " + carList.set(4, "Skoda"));
        System.out.println("set(6, \"Mitsubishi\") = " + carList.set(6, "Mitsubishi"));
        ((MyLinkedList<?>) carList).printAll();


        System.out.println();
        System.out.println("--- Test: retainAll() ---");
        testList = new MyLinkedList<>();
        testList.add("Audi");
        testList.add("Lincoln");
        testList.add("Nissan");
        testList.add("Lexus");
        testList.add("Toyota");
        ((MyLinkedList<?>) testList).printAll();
        ((MyLinkedList<?>) carList).printAll();
        System.out.println("addAll(1, testList) = " + carList.retainAll(testList));
        ((MyLinkedList<?>) carList).printAll();


        System.out.println();
        System.out.println("--- Test: clear() ---");
        carList.clear();
        ((MyLinkedList<?>) carList).printAll();


        System.out.println();
        System.out.println("--- Test: add(index, element) ---");
        System.out.println("carList.add(0,\"Ford\")");
        carList.add(0, "Ford");
        System.out.println("carList.add(0,\"Lexus\")");
        carList.add(0, "Lexus");
        System.out.println("carList.add(1,\"Ferrari\")");
        carList.add(1, "Ferrari");
        System.out.println("carList.add(2,\"Fiat\")");
        carList.add(2, "Fiat");
        System.out.println("carList.add(1,\"Kia\")");
        carList.add(1, "Kia");
        ((MyLinkedList<?>) carList).printAll();


        System.out.println();
        System.out.println("--- Test: remove(index) ---");
        System.out.println("carList.remove(2) = " + carList.remove(2));
        System.out.println("carList.remove(0) = " + carList.remove(0));
        ((MyLinkedList<?>) carList).printAll();


        System.out.println();
        carList.add(1, "Nissan");
        carList.add(1, "Ford");
        carList.add(1, "Mazda");
        System.out.println("--- Test: indexOf() ---");
        ((MyLinkedList<?>) carList).printAll();
        System.out.println("indexOf(\"Nissan\") = " + carList.indexOf("Nissan"));
        System.out.println("indexOf(\"Nissan+\") = " + carList.indexOf("Nissan+"));
        System.out.println("indexOf(\"Kia\") = " + carList.indexOf("Kia"));
        System.out.println("indexOf(\"Ford\") = " + carList.indexOf("Ford"));


        System.out.println();
        System.out.println("--- Test: lastIndexOf() ---");
        ((MyLinkedList<?>) carList).printAll();
        System.out.println("lastIndexOf(\"Nissan\") = " + carList.lastIndexOf("Nissan"));
        System.out.println("lastIndexOf(\"Nissan+\") = " + carList.lastIndexOf("Nissan+"));
        System.out.println("lastIndexOf(\"Kia\") = " + carList.lastIndexOf("Kia"));
        System.out.println("lastIndexOf(\"Ford\") = " + carList.lastIndexOf("Ford"));


        System.out.println();
        System.out.println("--- Test: subList() ---");
        ((MyLinkedList<?>) carList).printAll();
        System.out.println("subList(1,3)");
        List testSubList = carList.subList(1,3);
        ((MyLinkedList<?>) testSubList).printAll();


        System.out.println();
        System.out.println("--- Test: listIterator() ---");
        ((MyLinkedList<?>) carList).printAll();
        Object MyLinkedListIterator = carList.listIterator();
        System.out.print("hasNext() = " + ((ListIterator<?>) MyLinkedListIterator).hasNext());
        System.out.print("  nextIndex() = " + ((ListIterator<?>) MyLinkedListIterator).nextIndex());
        System.out.println("  next() = " + ((ListIterator<?>) MyLinkedListIterator).next());
        System.out.println("set(\"Mitsubishi\")");
        ((ListIterator<String>) MyLinkedListIterator).set("Mitsubishi");
        System.out.print("hasNext() = " + ((ListIterator<?>) MyLinkedListIterator).hasNext());
        System.out.print("  nextIndex() = " + ((ListIterator<?>) MyLinkedListIterator).nextIndex());
        System.out.println("  next() = " + ((ListIterator<?>) MyLinkedListIterator).next());
        System.out.print("hasNext() = " + ((ListIterator<?>) MyLinkedListIterator).hasNext());
        System.out.print("  nextIndex() = " + ((ListIterator<?>) MyLinkedListIterator).nextIndex());
        System.out.println("  next() = " + ((ListIterator<?>) MyLinkedListIterator).next());
        System.out.print("hasNext() = " + ((ListIterator<?>) MyLinkedListIterator).hasNext());
        System.out.print("  nextIndex() = " + ((ListIterator<?>) MyLinkedListIterator).nextIndex());
        System.out.println();
        System.out.println("remove()");
        ((ListIterator<?>) MyLinkedListIterator).remove();
        ((MyLinkedList<?>) carList).printAll();
        System.out.print("hasNext() = " + ((ListIterator<?>) MyLinkedListIterator).hasNext());
        System.out.print("  nextIndex() = " + ((ListIterator<?>) MyLinkedListIterator).nextIndex());
        System.out.println("  next() = " + ((ListIterator<?>) MyLinkedListIterator).next());
        System.out.print("hasPrevious() = " + ((ListIterator<?>) MyLinkedListIterator).hasPrevious());
        System.out.print("  previousIndex() = " + ((ListIterator<?>) MyLinkedListIterator).previousIndex());
        System.out.println("  previous() = " + ((ListIterator<?>) MyLinkedListIterator).previous());
        System.out.print("hasPrevious() = " + ((ListIterator<?>) MyLinkedListIterator).hasPrevious());
        System.out.print("  previousIndex() = " + ((ListIterator<?>) MyLinkedListIterator).previousIndex());
        System.out.println("  previous() = " + ((ListIterator<?>) MyLinkedListIterator).previous());
        System.out.print("\nnextIndex() = " + ((ListIterator<?>) MyLinkedListIterator).nextIndex());
        System.out.println(";   add(\"Chevrolet\"), add(\"Chrysler\"), add(\"Hummer\")");
        ((ListIterator<String>) MyLinkedListIterator).add("Chevrolet");
        ((ListIterator<String>) MyLinkedListIterator).add("Chrysler");
        ((ListIterator<String>) MyLinkedListIterator).add("Hummer");
        ((MyLinkedList<?>) carList).printAll();

        System.out.println("\nlistIterator(index = 3)");
        MyLinkedListIterator=carList.listIterator(3);
        System.out.print("hasNext() = " + ((ListIterator<?>) MyLinkedListIterator).hasNext());
        System.out.print("  nextIndex() = " + ((ListIterator<?>) MyLinkedListIterator).nextIndex());
        System.out.println("  next() = " + ((ListIterator<?>) MyLinkedListIterator).next());
    }
}



