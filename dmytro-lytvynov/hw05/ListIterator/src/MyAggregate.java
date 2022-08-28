import java.util.ListIterator;

public class MyAggregate implements Aggregate{
    private Object[] somedata = { "January", "February", "March",
            "April", "May", "June",
            "July", "August", "September",
            "October", "November", "December", 123, false, true, 2.2345};

    @Override
    public ListIterator createIterator() {
        return new MyListIterator(this.somedata);
    }
}
