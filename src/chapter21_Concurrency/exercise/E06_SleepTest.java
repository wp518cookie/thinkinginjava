package chapter21_Concurrency.exercise;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2016/12/16.
 */
class SleepTest implements Runnable {
    Random random = new Random(47);
    private int count = 5;
    private static int id_count;
    private final int id = id_count++;

    @Override
    public void run() {
        try {
            while (count-- > 0) {
                int sleepTime = random.nextInt(10);
                System.out.println("#" + id + ",count :" + count + ",sleeptime:" + sleepTime);
                TimeUnit.SECONDS.sleep(sleepTime);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

public class E06_SleepTest {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new SleepTest());
        }
    }
}
