package chapter17_Container.exercise;

import net.mindview.util.Countries;
import static net.mindview.util.Print.*;

import java.util.*;

/**
 * Created by ping.wu on 2016/11/22.
 */
public class Exercise1_ArrayList {
    private static Random rnd = new Random(47);
    public static void main(String[] args) {
        List arrayList = new ArrayList<>(Countries.names(8));
        Collections.sort(arrayList);
        print("sorted: " + arrayList);
        for (int i = 0; i < 5; i++) {
            Collections.shuffle(arrayList, rnd);
            print("shuffled(" + i + "):" + arrayList);
        }
        print("cut-----------------------------------------------------------------");
        List linkedList = new LinkedList<>(Countries.names(8));
        Collections.sort(linkedList);
        print("sorted: " + linkedList);
        for (int i = 0; i < 5; i++) {
            Collections.shuffle(linkedList, rnd);
            print("shuffled(" + i + "):" + linkedList);
        }
    }
}
