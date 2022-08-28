import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyListIterator implements ListIterator {

    private Object[] Data;
    private int index;

    public MyListIterator(Object[] aggr) {
        this.index = 0;
        this.Data = aggr;

    }

    @Override
    public boolean hasNext() {
        return this.index < this.Data.length;
    }

    @Override
    public Object next() {
        if (this.index >= this.Data.length) {
            throw new NoSuchElementException("End of data");
        }
        return this.Data[this.index++];
    }

    @Override
    public boolean hasPrevious() {
        return this.index > 0;
    }

    @Override
    public Object previous() {
        if (this.index <= 0) {
            throw new NoSuchElementException("End of data");
        }
        return this.Data[(this.index--)-1];
    }

    @Override
    public int nextIndex() {
        return this.index;
    }

    @Override
    public int previousIndex() {
        return this.index-1;
    }

    @Override
    public void remove() {
        this.Data[this.index] = null;
    }

    @Override
    public void set(Object o) {
        this.Data[this.index] = o;
    }

    @Override
    public void add(Object o) {

    }
}
