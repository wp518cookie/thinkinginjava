package chapter21_Concurrency.exercise;

import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2016/12/21.
 */
class NonTask {
    public static void longMethod() throws InterruptedException {
        TimeUnit.SECONDS.sleep(60);
    }
}

class Task implements Runnable {
    public void run() {
        try {
            NonTask.longMethod();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
        System.out.println("task interrupted");
    }
}

public class E18_Interruption {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Task());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }
}
