package chapter21_Concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ping.wu on 2016/12/19.
 */
public  class E12_Atomicity implements Runnable {
    private int i = 0;

    public synchronized int getValue() {
        return i;
    }

    private synchronized void evenIncrement() {
        i++;
        i++;
    }

    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        E12_Atomicity at = new E12_Atomicity();
        exec.execute(at);
        while (true) {
            int val = at.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
