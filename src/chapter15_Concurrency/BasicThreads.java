package chapter15_Concurrency;

/**
 * Created by ping.wu on 2016/12/15.
 */
public class BasicThreads {
    public static void main(String[] args) {
        Thread t = new Thread(new LiftOff());
        t.start();
        System.out.println("wating for LiftOff");
    }
}
