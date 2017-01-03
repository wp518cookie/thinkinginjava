package chapter21_Concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2017/1/3.
 */
class Entrance3 implements Runnable {
    private final CountDownLatch latch;
    private static Count count = new Count();
    private static List<Entrance3> entrances = new ArrayList();
    private int number;
    private final int id;
    public static volatile boolean canceled;

    public static void cancel() {
        canceled = true;
    }

    public Entrance3(CountDownLatch ltc, int id) {
        latch = ltc;
        this.id = id;
        entrances.add(this);
    }

    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + " Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
            }
        }
        latch.countDown();
        System.out.println("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }
}

public class E32_OrnamentalGarden3 {
}
