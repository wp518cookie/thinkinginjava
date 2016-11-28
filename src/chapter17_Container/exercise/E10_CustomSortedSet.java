package chapter17_Container.exercise;

import java.util.*;

import static net.mindview.util.Print.*;

/**
 * Created by ping.wu on 2016/11/28.
 */
class CustomSortedSet<T> implements SortedSet<T> {
    private final List<T> list;

    public CustomSortedSet() {
        list = new LinkedList<T>();
    }

    public CustomSortedSet(List list) {
        this.list = list;
    }

    // method in set
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object element) {
        return list.contains(element);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] t) {
        return list.toArray(t);
    }

    @Override
    public boolean add(T element) {
        checkForNull(element);
        int ip = Collections.binarySearch((List<Comparable<T>>) list, element);
        if (ip < 0) {
            ip = -(ip + 1);
            if (ip == list.size()) {
                list.add(element);
            } else {
                list.add(ip, element);
            }
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        checkForNull(c);
        checkNullElements(c);
        for (T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean equals(Object o) {
        return list.equals(o);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    //extra method in sortedset
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        checkForNull(fromElement);
        checkForNull(toElement);
        checkIndexValid(fromElement);
        checkIndexValid(toElement);
        return new CustomSortedSet<>(list.subList(list.indexOf(fromElement), list.indexOf(toElement)));
    }

    public SortedSet<T> headSet(T toElement) {
        checkForNull(toElement);
        checkIndexValid(toElement);
        return new CustomSortedSet<T>(list.subList(0, list.indexOf(toElement)));
    }

    public SortedSet<T> tailSet(T fromElement) {
        checkForNull(fromElement);
        checkIndexValid(fromElement);
        return new CustomSortedSet<T>(list.subList(list.indexOf(fromElement), size()));
    }

    public T first() {
        if (size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public T last() {
        if (size() > 0) {
            return list.get(size() - 1);
        }
        return null;
    }

    //Utility method
    private void checkForNull(Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
    }

    public void checkNullElements(Collection<?> t) {
        for (Iterator<?> it = t.iterator(); it.hasNext(); ) {
            if (it.next() == null) {
                throw new NullPointerException();
            }
        }
    }

    private void checkIndexValid(Object o) {
        if (!list.contains(o)) {
            throw new IllegalArgumentException();
        }
    }

    public String toString() {
        return list.toString();
    }
}


public class E10_CustomSortedSet<T> {
    public static void main(String[] args) {
        SortedSet<String> sortedSet = new CustomSortedSet<String>();
        Collections.addAll(sortedSet, "one two three four five six seven eight"
                .split(" "));
        print(sortedSet);
        String low = sortedSet.first();
        String high = sortedSet.last();
        print(low);
        print(high);
        Iterator<String> it = sortedSet.iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) low = it.next();
            if (i == 6) high = it.next();
            else it.next();
        }
        print(low);
        print(high);
        print(sortedSet.subSet(low, high));
        print(sortedSet.headSet(high));
        print(sortedSet.tailSet(low));
        print(sortedSet.contains("three"));
        print(sortedSet.contains("eleven"));
        print(sortedSet.addAll(Arrays.asList("three", "eleven")));
        print(sortedSet);
        try {
            sortedSet.addAll(Arrays.asList("zero", null));
        } catch (NullPointerException e) {
            System.out.println(("Null elements not supported!"));
        }
        print(sortedSet);
    }
}
