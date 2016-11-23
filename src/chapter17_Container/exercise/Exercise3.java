package chapter17_Container.exercise;

import net.mindview.util.Countries;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by ping.wu on 2016/11/23.
 */
public class Exercise3 {
    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();
        Set<String> linkedHashSet = new LinkedHashSet<>();
        Set<String> treeSet = new TreeSet<>();
        for (int i = 0; i < 3; i++) {
            hashSet.addAll(Countries.names(5));
            linkedHashSet.addAll(Countries.names(5));
            treeSet.addAll(Countries.names(5));
        }
        System.out.println(hashSet);
        System.out.println(linkedHashSet);
        System.out.println(treeSet);
    }
}
