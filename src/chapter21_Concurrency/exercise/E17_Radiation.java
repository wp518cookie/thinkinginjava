package chapter21_Concurrency.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2016/12/21.
 */
class Rad_Count {
    private int count = 0;
    Random rand = new Random(47);

    public  int increment() {
        int temp = count;
        if (rand.nextBoolean()) {
            Thread.yield();
        }
        return (count = ++temp);
    }

    public int value() {
        return count;
    }
}

class Radiation implements Runnable {
    public static Rad_Count radCount = new Rad_Count();
    private int number = 0;
    public static List<Radiation> randiations = new ArrayList();
    private final int id;
    private static volatile boolean canceled = false;
    public Radiation(int id) {
        this.id = id;
        randiations.add(this);
    }

    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            radCount.increment();
        }
    }

    public static void cancel() {
        canceled = true;
    }

    public int getNum() {
        return  number;
    }
}

public class E17_Radiation {
    public static void main(String[] args) throws Exception{
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Radiation(i));
        }
        TimeUnit.SECONDS.sleep(3);
        Radiation.cancel();
        exec.shutdown();
        System.out.println("radCount: " + Radiation.radCount.value());
        int sum = 0;
        for (Radiation radiation : Radiation.randiations) {
            sum += radiation.getNum();
        }
        System.out.println("sum: " + sum);
    }
}
