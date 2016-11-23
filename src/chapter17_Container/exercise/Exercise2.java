package chapter17_Container.exercise;

import net.mindview.util.Countries;

import java.util.*;

/**
 * Created by ping.wu on 2016/11/22.
 */
public class Exercise2 {
    public static void main(String[] args) {
        TreeMap<String, String> treeMap = new TreeMap<>(Countries.capitals());
        TreeSet<String> treeSet = new TreeSet<>(Countries.names());
        Set<String> key = treeMap.keySet();
        String b = null;
        for (String s : key) {
            if (s.startsWith("B")) {
                b = s;
                break;
            }
        }
        System.out.println(treeMap.headMap(b));
        System.out.println(treeSet.headSet(b));
        System.out.println(treeMap);
        System.out.println(treeSet);
    }
}
