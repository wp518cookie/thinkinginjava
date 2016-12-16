package chapter21_Concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2016/12/16.
 */
class E08_Thread implements Runnable {
    public int count;

    public void run() {
        try {
            while (true) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(count++);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}

class E08_ThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}

public class E08_SimpleThread {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool(new E08_ThreadFactory());
        exec.execute(new E08_Thread());
        TimeUnit.SECONDS.sleep(5);
    }
}
