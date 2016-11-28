package chapter17_Container.exercise;

import chapter17_Container.CollectionData;
import net.mindview.util.RandomGenerator;

import java.util.TreeSet;

/**
 * Created by ping.wu on 2016/11/28.
 */
public class E09_RandTreeSet {
    public static void main(String[] args) {
        TreeSet<String> ts = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        ts.addAll(CollectionData.list(new RandomGenerator.String(),10));
        System.out.println("ts=" + ts);
    }
}
