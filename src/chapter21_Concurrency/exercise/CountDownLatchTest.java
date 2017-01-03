package chapter21_Concurrency.exercise;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2017/1/3.
 */
class TaskPortion implements Runnable {
    private static int count = 0;
    private final int id = count++;
    private Random rand = new Random(47);
    private CountDownLatch latch;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(200));
        } catch (InterruptedException e) {
            System.out.println("TaskPortion interrupted");
        }
        System.out.println("TaskPortion execute id : " + id);
        latch.countDown();
    }
}

class WaitingTask implements Runnable {
    private static int count = 0;
    private final int id = count++;
    private CountDownLatch latch;

    public WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            System.out.println("WaitingTask interrupted");
        }
        System.out.println("WatingTask execute id : " + id);
    }
}

public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(20);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new WaitingTask(latch));
        }
        for (int i = 0; i < 20; i++) {
            exec.execute(new TaskPortion(latch));
        }
    }
}
