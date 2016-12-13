package chapter17_Container.exercise;

import chapter17_Container.MapEntry;
import chapter17_Container.SimpleHashMap;
import net.mindview.util.Countries;

import java.util.*;

/**
 * Created by ping.wu on 2016/12/12.
 */
public class E20_SimpleHashMap<K, V> extends AbstractMap<K, V> {
    static final int SIZE = 997;
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    public V put(K key, V value) {
        V oldValue = null;
        MapEntry<K, V> pair = new MapEntry<>(key, value);
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            buckets[index] = new LinkedList<MapEntry<K, V>>();
        } else {
            System.out.println("Collision while adding\n" + pair + "\nBucket already contains: ");
            Iterator<MapEntry<K, V>> it = buckets[index].iterator();
            while (it.hasNext()) {
                System.out.println(it.next());
            }
        }
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        boolean found = false;
        while (it.hasNext()) {
            MapEntry<K, V> ipair = it.next();
            if (ipair.getKey().equals(pair.getKey())) {
                oldValue = ipair.getValue();
                it.set(pair);
                found = true;
                break;
            }
        }
        if (!found) {
            buckets[index].add(pair);
        }
        return oldValue;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<Map.Entry<K, V>>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) {
                continue;
            }
            for (MapEntry<K, V> mpair : bucket) {
                set.add(mpair);
            }
        }
        return set;
    }

    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            return null;
        }
        ListIterator<MapEntry<K, V>> it = buckets[index].listIterator();
        while (it.hasNext()) {
            MapEntry<K, V> pair = it.next();
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    @Override
    public void clear() {
        buckets = new LinkedList[SIZE];
    }

    @Override
    public V remove(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) {
            return null;
        }
        Iterator<MapEntry<K, V>> it = buckets[index].listIterator();
        while (it.hasNext()) {
            MapEntry<K, V> pair = it.next();
            if (pair.getKey().equals(key)) {
                V oldValue = pair.getValue();
                it.remove();
                return oldValue;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        E20_SimpleHashMap<String, String> map = new E20_SimpleHashMap();
        map.put("hello", "world");
        map.put("hello", "world");
        System.out.println(map);
    }
}
