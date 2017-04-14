package chapter21_Concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2016/12/16.
 */
class ADaemon implements Runnable {
    public void run() {
        try {
            System.out.println("Starting ADaemon");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("Exiting vai InterruptedException");
        } finally {
            System.out.println("This should always run?");
        }
    }
}

public class DaemonsDontRunFinally {
    public static void main(String[] args) {
        try{
            System.exit(0);
        } finally {
            System.out.println("end!");
        }
    }
}


