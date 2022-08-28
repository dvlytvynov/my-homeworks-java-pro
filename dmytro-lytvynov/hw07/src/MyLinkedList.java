import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements List<T> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        private Node() {
        }

        private Node(T data) {
            this.data = data;
        }

    }

    private Node<T> head;
    private Node<T> tail;
    private int count;

    public MyLinkedList() {
        head = null;
        tail = null;
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> current;
        current = head;
        while (current.next != null) {
            if (o.equals(current.data)) {
                return true;
            }
            current = current.next;
        }
        if (current == tail && o.equals(current.data)) {
            return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException("End of Linked List");
                }
                Object temp = current.data;
                current = current.next;
                return (T) temp;
            }
        };

    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size()];
        Node<T> current;
        current = head;
        int index = 0;
        while (current != null) {
            result[index] = current.data;
            current = current.next;
            index++;
        }
        return result;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        T1[] result = (T1[]) new Object[a.length];
        Node<T> current;
        current = head;
        int index = 0;
        while (current != null && index < a.length) {
            result[index] = (T1) current.data;
            current = current.next;
            index++;
        }
        return result;
    }

    @Override
    public boolean add(T t) {
        final Node<T> currentNode = new Node<>(t);
        Node<T> current;
        current = head;
        if (head == null) {
            head = currentNode;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            currentNode.previous = current;
            current.next = currentNode;
        }
        count++;
        tail = currentNode;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> current;
        current = head;
        while (current != null) {
            if (o.equals(current.data)) {
                if (current.previous != null) {
                    current.previous.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.previous = current.previous;
                } else {
                    tail = current.previous;
                }
                count--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] temp = c.toArray();
        boolean[] equalsTemp = new boolean[temp.length];
        for (int i = 0; i < temp.length; i++) {
            equalsTemp[i] = this.contains(temp[i]);
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
        boolean t1 = true;
        for (int i = 0; i < temp.length; i++) {
            t1 = this.add((T) temp[i]);
            result = result & t1;
        }
        return result;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > this.size() - 1 || index < 0) {
            throw new NoSuchElementException("Index out of range");
        }

        Node<T> current;
        current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        boolean result = true;
        Object[] temp = c.toArray();
        boolean t1 = true;
        for (int i = 0; i < temp.length; i++) {
            final Node<T> currentNode = new Node<T>((T) temp[i]);
            currentNode.previous = current.previous;
            currentNode.next = current;
            if (current.previous != null) {
                current.previous.next = currentNode;
            } else {
                head = currentNode;
            }
            current.previous = currentNode;

            result = result & t1;
            count++;
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = true;
        Object[] temp = c.toArray();
        boolean t1;
        for (int i = 0; i < temp.length; i++) {
            t1 = this.remove(temp[i]);
            result = result & t1;
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Node<T> current;
        current = head;
        Object[] temp = c.toArray();
        boolean result = false;
        boolean needRetain = false;
        while (current != null) {
            for (int i = 0; i < temp.length; i++) {
                if (temp[i].equals(current.data)) {
                    needRetain = true;
                }
            }
            if (needRetain == false) {
                result = result | this.remove(current.data);
            }
            needRetain = false;
            current = current.next;
        }

        return result;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        count = 0;
    }

    @Override
    public T get(int index) {
        if (index > this.size() || index < 0) {
            throw new NoSuchElementException("Index out of range");
        }
        Node<T> current;
        current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public T set(int index, T element) {
        if (index > this.size() || index < 0) {
            throw new NoSuchElementException("Index out of range");
        }
        Node<T> current;
        Object temp;
        current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        temp = current.data;
        current.data = element;
        return (T) temp;
    }

    @Override
    public void add(int index, T element) {
        if (index > this.size() || index < 0 || (index == this.size() && index != 0)) {
            throw new NoSuchElementException("Index out of range");
        }
        final Node<T> currentNode = new Node<>(element);
        Node<T> current;
        current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        if (head == null) {
            head = currentNode;
            tail = currentNode;
        } else {
            currentNode.next = current;
            currentNode.previous = current.previous;
            if (current.previous != null) {
                current.previous.next = currentNode;
            } else {
                head = currentNode;
            }
            current.previous = currentNode;
        }
        count++;
    }

    @Override
    public T remove(int index) {
        if (index >= this.size() || index < 0) {
            throw new NoSuchElementException("Index out of range");
        }
        Node<T> current, temp;
        current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        temp = current;
        if (current.previous != null) {
            current.previous.next = current.next;
        } else {
            head = current.next;
        }

        if (current.next != null) {
            current.next.previous = current.previous;
        } else {
            tail = current.previous;
        }
        count--;

        return temp.data;
    }

    @Override
    public int indexOf(Object o) {
        Node<T> current;
        current = head;
        int index = 0;
        while (current != null) {
            if (o.equals(current.data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<T> current;
        current = tail;
        int index = this.size() - 1;
        while (current != null) {
            if (o.equals(current.data)) {
                return index;
            }
            current = current.previous;
            index--;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            Node<T> current = head;
            int index = 0;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null) {
                    throw new NoSuchElementException("End of Linked List");
                }
                Object temp = current.data;
                current = current.next;
                index++;
                return (T) temp;
            }

            @Override
            public boolean hasPrevious() {
                return current.previous != null;
            }

            @Override
            public T previous() {
                if (current.previous == null) {
                    throw new NoSuchElementException("End of Linked List");
                }
                Object temp = current.previous.data;
                current = current.previous;
                index--;
                return (T) temp;
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
                if (current.previous != null) {
                    current.previous.next = current.next;
                } else {
                    head = current.next;
                }

                if (current.next != null) {
                    current.next.previous = current.previous;
                } else {
                    tail = current.previous;
                }
                count--;
                current = current.next;
            }

            @Override
            public void set(T t) {
                current.data = t;
            }

            @Override
            public void add(T t) {
                final Node<T> currentNode = new Node<>(t);
                if (head == null) {
                    head = currentNode;
                    tail = currentNode;
                } else {
                    currentNode.next = current;
                    currentNode.previous = current.previous;
                    if (current.previous != null) {
                        current.previous.next = currentNode;
                    } else {
                        head = currentNode;
                    }
                    current.previous = currentNode;
                }
                count++;
            }
        };
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        ListIterator result = this.listIterator();
        for (int i = 0; i <= index; i++) {
            if (result.hasNext()) {
                result.next();
            }
        }
        return result;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        List<T> result = new MyLinkedList<T>();
        Node<T> current;
        current = head;
        int index = 0;
        while (current != null) {
            if (index >= fromIndex && index <= toIndex) {
                result.add(current.data);
            }
            current = current.next;
            index++;
        }
        return result;
    }

    // Method printAll() is created for test of LinkedList
    public void printAll() {
        Node<T> current;
        current = head;
        int index = 0;
        System.out.print("size = " + this.size() + "  ");
        while (current != null) {
            System.out.print(index + ": " + current.data + "  ");
            current = current.next;
            index++;
        }
        System.out.println();


    }
}
