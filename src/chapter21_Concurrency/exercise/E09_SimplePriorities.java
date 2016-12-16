package chapter21_Concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by ping.wu on 2016/12/16.
 */
class E09_ThreadFactory implements ThreadFactory {
    private static int count = 0;
    private static final int[] PRIORITIES = {Thread.MIN_PRIORITY, Thread.NORM_PRIORITY, Thread.MAX_PRIORITY};

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setPriority(PRIORITIES[count++ % 3]);
        System.out.println("ThreadFactory count :" + count);
        return thread;
    }
}

class E09_Thread implements Runnable {
    @Override
    public void run() {
    }
}

public class E09_SimplePriorities {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new E09_ThreadFactory());
        for (int i = 0; i < 5; i++) {
            exec.execute(new E09_Thread());
        }
        exec.shutdown();
    }
}
