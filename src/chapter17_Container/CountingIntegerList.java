package chapter17_Container;

import java.util.AbstractList;

/**
 * Created by ping.wu on 2016/11/22.
 * use flyweight solution
 * so the list doesn't actually have to be populated
 */
public class CountingIntegerList extends AbstractList<Integer> {
    private int size;

    public CountingIntegerList(int size) {
        this.size = size < 0 ? 0 : size;
    }

    public Integer get(int index) {
        return Integer.valueOf(index);
    }

    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        CountingIntegerList countingIntegerList = new CountingIntegerList(30);
//        countingIntegerList.set(0,100);  UnsupportedOperationException, the method set() doesn't defined in abstractList
        System.out.println(new CountingIntegerList(30));
    }
}
