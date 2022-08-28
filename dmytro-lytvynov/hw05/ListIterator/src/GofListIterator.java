import java.util.ListIterator;

public class GofListIterator {
    public static void main(String[] args) {

        System.out.println();

        Aggregate mydata = new MyAggregate();
        ListIterator iter = mydata.createIterator();

        // -----------------------------------------

        System.out.println("View Data forward ( next() ):");
        while (iter.hasNext()) {
            System.out.print(iter.next() + " ");
        }
        System.out.println();
        System.out.println();

        // -----------------------------------------

        System.out.println("View Data backward ( previous() ):");
        while (iter.hasPrevious()) {
            System.out.print(iter.previous() + " ");
        }
        System.out.println();
        System.out.println();

        // -----------------------------------------

        System.out.println( "View Data forward with index " +
                            "( nextIndex(), next(), remove(), set() ):");
        while (iter.hasNext()) {
            if (iter.nextIndex() == 5 || iter.nextIndex() == 6 || iter.nextIndex() == 7) {
                iter.remove();
            }
            if (iter.previousIndex() == 10 || iter.previousIndex() == 11) {
                iter.set("---");
            }
            System.out.print(iter.nextIndex() + " ");
            System.out.print(iter.next() + "  ");
        }
        System.out.println();
        System.out.println();

        // -----------------------------------------

        System.out.println( "View Data backward with index " +
                            "( previousIndex(), previous() ):");
        while (iter.hasPrevious()) {
            System.out.print(iter.previousIndex() + " ");
            System.out.print(iter.previous() + "  ");
        }
        System.out.println();
        System.out.println();


    }
}
