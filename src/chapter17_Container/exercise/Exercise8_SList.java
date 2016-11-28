package chapter17_Container.exercise;

/**
 * Created by ping.wu on 2016/11/25.
 */
public class Exercise8_SList {
    public static void main(String[] args) {
        SList<String> sList = new SList<>();
        SListIterator sListIterator = sList.iterator();
        sListIterator.add("hello");
        sListIterator.add("world");
        sListIterator.add("final");
        System.out.println(sList.toString());
    }
}
