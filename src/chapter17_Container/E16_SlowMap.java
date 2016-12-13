package chapter17_Container;

import java.util.*;

/**
 * Created by ping.wu on 2016/12/5.
 */
class SlowMap2<K, V> extends AbstractMap<K, V> {
    private List<K> keys = new ArrayList<K>();
    private List<V> values = new ArrayList<V>();

    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        }
        V oldValue = get(key);
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else {
            values.set(keys.indexOf(key), value);
        }
        return oldValue;
    }

    @Override
    public V get(Object key) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (!keys.contains(key)) {
            return null;
        } else {
            return values.get(keys.indexOf(key));
        }
    }

    private EntrySet entrySet = new EntrySet();

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet;
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new Iterator<Map.Entry<K, V>>() {
                private int index = -1;
                private boolean canRemove;

                public boolean hasNext() {
                    return index < keys.size() - 1;
                }

                public Map.Entry<K, V> next() {
                    canRemove = true;
                    ++index;
                    return new MapEntry(keys.get(index), values.get(index));
                }

                public void remove() {
                    if (!canRemove) {
                        throw new IllegalStateException();
                    }
                    canRemove = false;
                    keys.remove(index);
                    values.remove(index--);
                }
            };
        }

        @Override
        public boolean contains(Object o) {
            if (o instanceof MapEntry) {
                MapEntry<K, V> e = (MapEntry<K, V>) o;
                K key = e.getKey();
                if (keys.contains(key)) {
                    return e.equals(new MapEntry<K, V>(key, get(key)));
                }
            }
            return false;
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean remove(Object o) {
            if (contains(o)) {
                MapEntry<K, V> e = (MapEntry<K, V>) o;
                K key = e.getKey();
                V value = e.getValue();
                keys.remove(key);
                values.remove(value);
                return true;
            }
            return false;
        }

        @Override
        public int size() {
            return keys.size();
        }

        @Override
        public void clear() {
            keys.clear();
            values.clear();
        }
    }
}

public class E16_SlowMap {
    public static void printKeys(Map<Integer, String> map) {
        System.out.println("Size = " + map.size() + ". ");
        System.out.print("Keys: ");
        System.out.println(map.keySet());
    }

    public static void test(Map<Integer, String> map) {
        System.out.println(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        map.putAll(new CountingMapData(25));
        printKeys(map);
        System.out.print("Values: ");
        System.out.println(map);
        System.out.println("map.get(11): " + map.get(11));
        System.out.println("map.containsValue(\"F0\"): " + map.containsValue("F0"));
        Integer key = map.keySet().iterator().next();
        System.out.println("First key in map: " + key);
        map.remove(key);
        printKeys(map);
        map.clear();
        System.out.println("map.isEmpty(): " + map.isEmpty());
        map.putAll(new CountingMapData(25));
        map.keySet().removeAll(map.keySet());
        System.out.println("map.isEmpty(): " + map.isEmpty());
    }

    public static void main(String[] args) {
        System.out.println("Testing SlowMap");
        test(new SlowMap<Integer, String>());
        System.out.println("Testing SlowMap2");
        test(new SlowMap2<Integer, String>());
    }
}
