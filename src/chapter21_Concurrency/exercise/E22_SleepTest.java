package chapter21_Concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2016/12/23.
 */
class UseSleepTest {
    boolean flag = false;
    public long time;

    public synchronized void set(boolean flag) {
        this.flag = flag;
        System.out.println("set userSleepTest: " + flag);
    }

    public synchronized boolean get() {
        return flag;
    }

    public synchronized void setTime() {
        time = System.currentTimeMillis();
    }

    public synchronized long getTime() {
        return time;
    }
}

class UseSleepTask1 implements Runnable {
    private UseSleepTest useSleepTest;

    public UseSleepTask1(UseSleepTest useSleepTest) {
        this.useSleepTest = useSleepTest;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                useSleepTest.setTime();
                TimeUnit.SECONDS.sleep(2);
                useSleepTest.set(true);
            }
        } catch (InterruptedException e) {
            System.out.println("UseSleepTask1 interrupted");
        }
        System.out.println("UseSleepTask1 ended");
    }
}

class UseSleepTask2 implements Runnable {
    private UseSleepTest useSleepTest;

    public UseSleepTask2(UseSleepTest useSleepTest) {
        this.useSleepTest = useSleepTest;
    }

    public void run() {
        while (!Thread.interrupted()) {
            if (useSleepTest.get()) {
//                System.out.println(System.currentTimeMillis() - useSleepTest.getTime());
                useSleepTest.set(false);
            }
        }
        System.out.println("UseSleepTask2 ended");
    }
}

public class E22_SleepTest {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        UseSleepTest useSleepTest = new UseSleepTest();
        exec.execute(new UseSleepTask1(useSleepTest));
        exec.execute(new UseSleepTask2(useSleepTest));
        TimeUnit.SECONDS.sleep(10);
        System.exit(0);
    }
}
