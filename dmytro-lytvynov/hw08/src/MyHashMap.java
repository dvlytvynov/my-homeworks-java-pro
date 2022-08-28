import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MyHashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_BUCKET_COUNT = 10;
    private int count = 0;
    private final MyEntry<K, V>[] bucketArray;

    private class MyEntry<K, V> implements Entry<K, V> {
        private final K key;
        private V value;
        private MyEntry<K, V> next;

        private MyEntry(K key) {
            this.key = key;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V temp = this.value;
            this.value = value;
            return temp;
        }

        public MyEntry<K, V> getNext() {
            return next;
        }

        public void setNext(MyEntry<K, V> next) {
            this.next = next;
        }
    }

    public MyHashMap() {
        this(DEFAULT_BUCKET_COUNT);
    }

    private MyHashMap(int bucketCount) {
        bucketArray = new MyEntry[bucketCount];
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
    public boolean containsKey(Object key) {
        MyEntry<K, V> current = getCurrentEntry((K) key);
        if (current != null) {
            if (key.equals(current.getKey())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < bucketArray.length; i++) {
            MyEntry<K, V> current = bucketArray[i];
            while (current != null) {
                if (value.equals(current.getValue())) {
                    return true;
                }
                current = current.getNext();
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        MyEntry<K, V> current = getCurrentEntry((K) key);
        if (current != null) {
            if (key.equals(current.getKey())) {
                return current.getValue();
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        MyEntry<K, V> current = getCurrentEntry(key);
        if (current != null) {
            if (key.equals(current.getKey())) {
                return current.setValue(value);
            }
        }
        MyEntry<K, V> previous = current;
        current = new MyEntry<>(key);
        count++;
        current.setValue(value);
        if (previous != null) previous.setNext(current);
        if (bucketArray[getBucketIndex(key)] == null) {
            bucketArray[getBucketIndex(key)] = current;
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        int index = getBucketIndex((K) key);
        MyEntry<K, V> current = bucketArray[index];
        MyEntry<K, V> previous = null;
        while (current != null) {
            if (key.equals(current.getKey())) {
                if (previous != null) {
                    previous.setNext(current.getNext());
                } else {
                    bucketArray[index] = current.getNext();
                }
                count--;
                return current.getValue();
            }
            previous = current;
            current = current.getNext();
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Iterator<? extends Entry<? extends K, ? extends V>> setIter = m.entrySet().iterator();
        while (setIter.hasNext()) {
            Map.Entry temp = setIter.next();
            this.put((K) temp.getKey(), (V) temp.getValue());
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < bucketArray.length; i++) {
            bucketArray[i] = null;
            count = 0;
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> result = new HashSet<>();
        for (int i = 0; i < bucketArray.length; i++) {
            MyEntry<K, V> current = bucketArray[i];
            while (current != null) {
                result.add(current.getKey());
                current = current.getNext();
            }
        }
        return result;
    }

    @Override
    public Collection<V> values() {
        Collection<V> result = new ArrayList<>();
        for (int i = 0; i < bucketArray.length; i++) {
            MyEntry<K, V> current = bucketArray[i];
            while (current != null) {
                result.add(current.getValue());
                current = current.getNext();
            }
        }
        return result;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> result = new HashSet<Entry<K, V>>();
        for (int i = 0; i < bucketArray.length; i++) {
            MyEntry<K, V> current = bucketArray[i];
            while (current != null) {
                result.add(current);
                current = current.getNext();
            }
        }
        return result;
    }

    private int getBucketIndex(K key) {
        int hash = Math.abs(key.hashCode() % bucketArray.length);
        return hash;
    }

    private MyEntry<K, V> getCurrentEntry(K key) {
        int index = getBucketIndex(key);
        MyEntry<K, V> current = bucketArray[index];
        MyEntry<K, V> previous = null;
        while (current != null) {
            if (key.equals(current.getKey())) {
                return current;
            }
            previous = current;
            current = current.getNext();
        }
        return previous;
    }
}
