package chapter17_Container.exercise;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * Created by ping.wu on 2016/11/29.
 */
class Item implements Comparable<Item> {
    private static final Random ran = new Random(47);
    private Integer rand_item = ran.nextInt(101);
    @Override
    public int compareTo(Item arg) {
        if (rand_item > arg.rand_item) {
            return 1;
        } else if (rand_item == arg.rand_item) {
            return 0;
        } else {
            return -1;
        }
    }
    @Override
    public String toString() {
        return rand_item.toString();
    }
}

public class E11_PriorityQueue {
    public static void main(String[] args) {
        Queue<Item> queue = new PriorityQueue<Item>();
        for (int i = 0; i < 10; i++) {
            queue.add(new Item());
        }
        while(!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
