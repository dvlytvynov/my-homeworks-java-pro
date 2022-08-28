import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayList<T> implements List<T> {
    //----------------------------------------
    private static final int DEFAULT_CAPACITY = 5;
    private int currentIndex;
    private T[] dataArr;

    //----------------------------------------
    public MyArrayList(int capacity) {

        dataArr = (T[]) new Object[capacity];
        currentIndex = -1;
    }

    public MyArrayList() {
        this(DEFAULT_CAPACITY);
    }

    //----------------------------------------
    @Override
    public int size() {
        return currentIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i <= currentIndex; i++) {
            if (o.equals(dataArr[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {


        return new Iterator<T>() {
            private int index = 0;
            private int maxIndex = currentIndex;
            private T[] data = dataArr;

            @Override
            public boolean hasNext() {
                return index <= maxIndex;
            }

            @Override
            public T next() {
                return data[index++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] temp = new Object[currentIndex + 1];
        for (int i = 0; i <= currentIndex; i++) {
            temp[i] = dataArr[i];
        }
        return temp;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (currentIndex + 1 > a.length) {
            T1[] newarr = (T1[]) new Object[currentIndex + 1];
            a = newarr;
        }
        for (int i = 0; i <= currentIndex; i++) {
            a[i] = (T1) dataArr[i];
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        ensureCapacity(currentIndex + 1 + 1);
        currentIndex++;
        dataArr[currentIndex] = t;
        return true;
    }

    @Override
    public boolean remove(Object obj) {
        int index = this.indexOf(obj);
        if (index >= 0 && index <= currentIndex) {
            T[] newArray = (T[]) new Object[dataArr.length];
            System.arraycopy(dataArr, 0, newArray, 0, index);
            System.arraycopy(dataArr, index + 1, newArray, index, currentIndex - index);
            dataArr = newArray;
            currentIndex--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] temp = c.toArray();
        boolean[] equalsTemp = new boolean[temp.length];
        for (int i = 0; i < temp.length; i++) {
            equalsTemp[i] = false;
            for (int j = 0; j < currentIndex; j++) {
                if (temp[i].equals(dataArr[j])) {
                    equalsTemp[i] = true;
                }
            }
        }
        boolean result = true;
        for (int i = 0; i < equalsTemp.length; i++) {
            result = result & equalsTemp[i];
        }
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean result = true;
        Object[] temp = c.toArray();
        for (int i = 0; i < temp.length; i++) {
            boolean t1 = this.add((T) temp[i]);
            result = result & t1;
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Object[] temp = c.toArray();
        int index1 = index;
        for (int i = 0; i < temp.length; i++) {
            this.add(index1, (T) temp[i]);
            index1++;
        }
        return true;

    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] temp = c.toArray();
        boolean result = false;
        for (int i = 0; i < temp.length; i++) {
            while (this.contains(temp[i])) {
                boolean t1 = this.remove(temp[i]);
                result = result | t1;
            }
        }

        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] temp = c.toArray();
        boolean result = false;
        int temp2 = currentIndex;
        for (int i = temp2; i >= 0; i--) {
            boolean toRetain = false;
            for (int j = 0; j < temp.length; j++) {
                if (dataArr[i].equals(temp[j])) {
                    toRetain = true;
                }
            }
            if (!toRetain) {
                this.remove(i);
                result = true;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        this.currentIndex = -1;
        Arrays.fill(dataArr, null);
    }

    @Override
    public T get(int index) {
        if (index > currentIndex || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return dataArr[index];
    }

    @Override
    public T set(int index, T element) {
        if (index > currentIndex || index < 0) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        T temp = dataArr[index];
        dataArr[index] = element;
        return temp;
    }

    @Override
    public void add(int index, T element) {
        ensureCapacity(currentIndex + 1 + 1);
        T[] newArray = (T[]) new Object[dataArr.length];
        System.arraycopy(dataArr, 0, newArray, 0, index);
        newArray[index] = element;
        currentIndex++;
        System.arraycopy(dataArr, index, newArray, index + 1, currentIndex - index);
        dataArr = newArray;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > currentIndex) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        T temp = this.get(index);
        T[] newArray = (T[]) new Object[dataArr.length];
        System.arraycopy(dataArr, 0, newArray, 0, index);
        System.arraycopy(dataArr, index + 1, newArray, index, currentIndex - index);
        dataArr = newArray;
        currentIndex--;
        return temp;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i <= currentIndex; i++) {
            if (dataArr[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = currentIndex; i >= 0; i--) {
            if (dataArr[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            private int index = 0;
            private T[] data = dataArr;

            @Override
            public boolean hasNext() {
                return index <= currentIndex;
            }

            @Override
            public T next() {
                return data[index++];
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public T previous() {
                return data[(index--) - 1];
            }

            @Override
            public int nextIndex() {
                return index;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                T[] newArray = (T[]) new Object[data.length];
                System.arraycopy(data, 0, newArray, 0, index);
                System.arraycopy(data, index + 1, newArray, index, currentIndex - index);
                currentIndex--;
                for (int i = 0; i <= currentIndex; i++) {
                    data[i] = newArray[i];
                }
            }

            @Override
            public void set(T t) {
                data[index] = t;
            }

            @Override
            public void add(T t) {
                T[] newArray = (T[]) new Object[data.length];
                System.arraycopy(data, 0, newArray, 0, index);
                System.arraycopy(data, index,
                        newArray, index + 1, currentIndex - index + 1);
                newArray[index] = t;
                currentIndex++;
                for (int i = 0; i <= currentIndex; i++) {
                    data[i] = newArray[i];
                }

            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index1) {
        return new ListIterator<T>() {
            private int index = index1;
            private T[] data = dataArr;

            @Override
            public boolean hasNext() {
                return index <= currentIndex;
            }

            @Override
            public T next() {
                return data[index++];
            }

            @Override
            public boolean hasPrevious() {
                return index > 0;
            }

            @Override
            public T previous() {
                return data[(index--) - 1];
            }

            @Override
            public int nextIndex() {
                return index;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                T[] newArray = (T[]) new Object[data.length];
                System.arraycopy(data, 0, newArray, 0, index);
                System.arraycopy(data, index + 1, newArray, index, currentIndex - index);
                currentIndex--;
                for (int i = 0; i <= currentIndex; i++) {
                    data[i] = newArray[i];
                }
            }

            @Override
            public void set(T t) {
                data[index] = t;
            }

            @Override
            public void add(T t) {
                T[] newArray = (T[]) new Object[data.length];
                System.arraycopy(data, 0, newArray, 0, index);
                System.arraycopy(data, index,
                        newArray, index + 1, currentIndex - index + 1);
                newArray[index] = t;
                currentIndex++;
                for (int i = 0; i <= currentIndex; i++) {
                    data[i] = newArray[i];
                }

            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> result = new MyArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            result.add(dataArr[i]);
        }
        return result;
    }

    private void ensureCapacity(int capacity) {

        if (dataArr.length < capacity) {
            T[] newArray = (T[]) new Object[capacity * 3 / 2 + 1];
            System.arraycopy(dataArr, 0, newArray, 0, currentIndex + 1);
            dataArr = newArray;

        }

    }


}
