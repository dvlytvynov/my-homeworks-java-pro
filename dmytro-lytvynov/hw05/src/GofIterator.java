
public class GofIterator {
    public static void main(String[] args) {

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String[] months = { "January", "February", "March",
                            "April", "May", "June",
                            "July", "August", "September",
                            "October", "November", "December"};

        MyIterator SomeIterator;

        SomeIterator = new MyIterator(days);
        ViewData(SomeIterator);

        SomeIterator = new MyIterator(months);
        ViewData(SomeIterator);


    }

    public static void ViewData(MyIterator myit) {
        while (myit.hasNext()) {
            System.out.print(myit.next() + " ");

        }
        System.out.println(" ");
    }

}

