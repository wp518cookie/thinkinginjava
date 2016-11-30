package chapter17_Container;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ping.wu on 2016/11/30.
 */
public class Maps {
    public static void printKeys(Map<Integer, String> map) {
        System.out.print("Size = " + map.size() + ", ");
        System.out.print("Keys: " + map.keySet());
        System.out.println();
    }
    public static void test(Map<Integer, String> map) {
        System.out.println(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        map.putAll(new CountingMapData(25));
        printKeys(map);
        System.out.println("values: " + map.values());
        System.out.println(map);
        System.out.println("map.containsKey(11): " + map.containsKey(11));
        System.out.println("map.get(11): " + map.get(11));
        System.out.println("map.containsValue(\"F0\"): " + map.containsValue("F0"));
        Integer key = map.keySet().iterator().next();
        System.out.println("Frist key in map: " + key);
        map.remove(key);
        printKeys(map);
        map.clear();
        System.out.println("map.isEmpty(): " + map.isEmpty());
        map.putAll(new CountingMapData(25));
        map.keySet().removeAll(map.keySet());
        System.out.println("map.isEmpty(): " + map.isEmpty());
    }

    public static void main(String[] args) {
        System.out.println("------hashmap-----");
        test(new HashMap<Integer, String>());
        System.out.println("--------treemap------");
        test(new TreeMap<Integer, String>());
        System.out.println("-------linkedHashMap-------");
        test(new LinkedHashMap<Integer, String>());
        System.out.println("-------identityHashMap-------");
        test(new IdentityHashMap<Integer, String>());
        System.out.println("------conCurrentHashMap------");
        test(new ConcurrentHashMap<Integer, String>());
        System.out.println("------weakHashMap------");
        test(new WeakHashMap<Integer, String>());
    }
}
