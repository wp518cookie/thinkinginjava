package chapter17_Container.exercise;

import chapter17_Container.MapEntry;
import net.mindview.util.TextFile;

import java.util.*;

import static java.util.Map.*;

/**
 * Created by ping.wu on 2016/12/5.
 */
class SlowMap<K, V> extends AbstractMap<K, V> {
    private ArrayList<K> keys = new ArrayList<>();
    private ArrayList<V> values = new ArrayList<>();
    public V put(K key, V value) {
        if (keys.contains(key)) {
            V oldValue = values.get(keys.indexOf(key));
            values.set(keys.indexOf(key), value);
            return oldValue;
        } else {
            keys.add(key);
            values.add(value);
            return null;
        }
    }

    public V get(Object key) {
        if (keys.contains(key)) {
            return values.get(keys.indexOf(key));
        } else {
            return null;
        }
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K,V>> entrySet = new HashSet();
        Iterator<K> keyIterator = keys.iterator();
        Iterator<V> valueIterator = values.iterator();
        while (keyIterator.hasNext()) {
            entrySet.add(new MapEntry<K, V>(keyIterator.next(), valueIterator.next()));
        }
        return entrySet;
    }
}

public class E15_WordCounter {
    public static void main(String[] args) {
        List<String> words = new TextFile("src/chapter17_Container/exercise/E12_MapDemo.java", "\\W+");
        SlowMap<String, Integer> map = new SlowMap();
        for (String word : words) {
            Integer freq = map.get(word);
            map.put(word, freq == null ? 1 : freq + 1);
        }
        System.out.println(map);
    }
}
