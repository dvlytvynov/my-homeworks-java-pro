import java.util.Iterator;

public class MyIterator implements Iterator<String> {

    private int index = 0;
    private String[] data;

    public MyIterator(String[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return index < this.data.length;
    }

    @Override
    public String next() {
        return this.data[index++];
    }
}
