package chapter21_Concurrency.exercise;

/**
 * Created by ping.wu on 2016/12/15.
 */
class Printer implements Runnable {
    private static int taskCount;
    public final int id = taskCount++;
    Printer() {
        System.out.println("Printer started,  ID = " + id );
    }
    public void run() {
        System.out.println("Stage 1..., ID = " + id);
        Thread.yield();
        System.out.println("Stage 2..., ID = " + id);
        Thread.yield();
        System.out.println("Stage 3..., ID = " + id);
        Thread.yield();
        System.out.println("Printer ended, ID = " + id);
    }
}
public class E01_Runnable {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Printer()).start();
        }
    }
}
