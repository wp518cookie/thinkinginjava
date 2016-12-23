package chapter21_Concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2016/12/23.
 */
class WaitTask {
    public synchronized void sayHello() throws InterruptedException {
        wait();
    }

    public synchronized void sayHelloAgain() throws InterruptedException {
        notifyAll();
    }
}

class WaitTask1 implements Runnable {
    private WaitTask waitTask;

    public WaitTask1(WaitTask waitTask) {
        this.waitTask = waitTask;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                waitTask.sayHello();
                System.out.println("do waitTask1");
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting WaitTask1 via InterruptedException");
        }
        System.out.println("Exiting WaitTask1 on task");
    }
}

class WaitTask2 implements Runnable {
    WaitTask waitTask;

    public WaitTask2(WaitTask waitTask) {
        this.waitTask = waitTask;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(1);
                waitTask.sayHelloAgain();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting WaitTask2 via InterruptedException");
        }
        System.out.println("Exiting WaitTask2 on task");
    }
}

public class E21_WatiTest {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        WaitTask waitTask = new WaitTask();
        exec.execute(new WaitTask1(waitTask));
        exec.execute(new WaitTask2(waitTask));
        TimeUnit.SECONDS.sleep(10);
        System.exit(9);
    }
}
