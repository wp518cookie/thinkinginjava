package chapter17_Container.exercise;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ping.wu on 2016/11/30.
 */
public class E12_MapDemo {
    public static void test(Map map) {
        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("sun", "warm");
        System.out.print(map);
        System.out.println(map.get("ocean"));
        System.out.println();
    }

    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> treeMap = new TreeMap<>();
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        test(hashMap);
        test(treeMap);
        test(linkedHashMap);
    }
}
