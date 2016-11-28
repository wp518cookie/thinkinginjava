package chapter17_Container;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import static net.mindview.util.Print.*;

/**
 * Created by ping.wu on 2016/11/28.
 */
public class SortedSetDemo {
    public static void main(String[] args) {
        SortedSet<String> sortedSet = new TreeSet<String>();
        Collections.addAll(sortedSet, "one two three four five six seven eight"
                .split(" "));
        print(sortedSet);
        String low = sortedSet.first();
        String high = sortedSet.last();
        print(low);
        print(high);
        Iterator<String> it = sortedSet.iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) {
                low = it.next();
            }
            if (i == 6) {
                high = it.next();
            } else it.next();
        }
        print(low);
        print(high);
        print(sortedSet.subSet(low, high));
        //headset: produces a view of this Set with elements less than toElement
        print(sortedSet.headSet(high));
        //tailset: produces a view of this Set with elements greater than or equal to fromElement
        print(sortedSet.tailSet(low));
    }
}
