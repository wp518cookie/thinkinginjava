package chapter21_Concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2016/12/23.
 */
public class E22_BusyWait {
    private static volatile boolean flag;
    private static int spins;

    public static void main(String[] args) throws Exception {
        Runnable r1 = new Runnable() {
            public void run() {
                for (; ; ) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        return;
                    }
                    flag = true;
                }
            }
        };

        Runnable r2 = new Runnable() {
            public void run() {
                for (; ; ) {
                    while (!flag && !Thread.currentThread().isInterrupted()) {
                        spins++;
                    }
                    System.out.println("Spun " + spins + " times");
                    spins = 0;
                    flag = false;
                    if (Thread.interrupted()) {
                        return;
                    }
                }
            }
        };

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(r1);
        exec.execute(r2);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdown();
    }
}
