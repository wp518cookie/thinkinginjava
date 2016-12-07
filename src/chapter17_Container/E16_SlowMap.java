package chapter17_Container;

import java.util.*;

/**
 * Created by ping.wu on 2016/12/5.
 */
class SlowMap2<K, V> extends AbstractMap<K, V> {
    private List<K> keys = new ArrayList();
    private List<V> values = new ArrayList();
    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException();
        } else {
            if (!keys.contains(key)) {
                keys.add(key);
                values.add(value);
                return null;
            } else {
                V oldValue = values.get(keys.indexOf(key));
                values.set(keys.indexOf(key), value);
                return oldValue;
            }
        }
    }
    @Override
    public V get(Object key) {
        if (key == null) {
            throw new NullPointerException();
        } else {
            if (!keys.contains(key)) {
                return null;
            } else {
                return values.get(keys.indexOf(key));
            }
        }
    }
    class EntrySet implements AbstractSet<Entry<K, V>> {

    }
}

public class E16_SlowMap {
}
