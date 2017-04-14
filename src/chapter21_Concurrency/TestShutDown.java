package chapter21_Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/3/6.
 */
class Test implements Runnable {
    public void run() {
        try {
            System.out.println("start");
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("interrupt");
        }
        finally {
            System.out.println("end");
        }
    }
}
public class TestShutDown {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Test());
        TimeUnit.MILLISECONDS.sleep(3000);
        exec.shutdownNow();
    }
}
