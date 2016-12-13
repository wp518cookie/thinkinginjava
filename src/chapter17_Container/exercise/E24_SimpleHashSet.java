package chapter17_Container.exercise;

import java.util.*;

/**
 * Created by ping.wu on 2016/12/13.
 */
public class E24_SimpleHashSet<K> extends AbstractSet<K> {
    private final static int SIZE = 997;
    private LinkedList<K>[] buckets = new LinkedList[SIZE];

    public boolean add(K key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<K>();
        }
        LinkedList<K> bucket = buckets[index];
        ListIterator<K> it = bucket.listIterator();
        while (it.hasNext()) {
            if (it.next().equals(key)) {
                return false;
            }
        }
        it.add(key);
        return true;
    }

    public boolean contains(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            return false;
        }
        ListIterator<K> it = buckets[index].listIterator();
        while (it.hasNext()) {
            if (it.next().equals(key)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        int size = 0;
        for (LinkedList<K> linkedList : buckets) {
            if (linkedList != null) {
                size += linkedList.size();
            }
        }
        return size;
    }

    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int count;
            private boolean canRemove;
            private int index1, index2;

            public boolean hasNext() {
                return count < size();
            }

            public K next() {
                if (hasNext()) {
                    canRemove = true;
                    ++count;
                    for (; ; ) {
                        while(buckets[index1] == null) {
                            ++index1;
                        }
                        try {
                            return buckets[index1].get(index2++);
                        } catch (IndexOutOfBoundsException e) {
                            ++index1;
                            index2 = 0;
                        }
                    }
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                if (canRemove) {
                    canRemove = false;
                    buckets[index1].remove(--index2);
                    if (buckets[index1].isEmpty()) {
                        buckets[index1++] = null;
                        --count;
                    }
                } else
                throw new IllegalStateException();
            }
        };
    }
}
