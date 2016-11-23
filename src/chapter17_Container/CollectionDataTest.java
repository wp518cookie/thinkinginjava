package chapter17_Container;

import net.mindview.util.Generator;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by ping.wu on 2016/11/21.
 */
class Government implements Generator<String> {
    String[] foundation = ("strange women lying in ponds " +
            "distributing swords is no basis for a system of " + "government").split(" ");
    private int index;
    public String next() {
        return foundation[index++];
    }
}

public class CollectionDataTest {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<String>(new CollectionData<String>(new Government(), 15));
        System.out.println(set);
    }
}
