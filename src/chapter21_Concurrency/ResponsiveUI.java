package chapter21_Concurrency;

/**
 * Created by ping.wu on 2016/12/19.
 */
class UnresponsiveUI {
    private volatile double d = 1;

    public UnresponsiveUI() throws Exception {
        while (d > 0) {
            d = d + (Math.PI + Math.E) / d;
            System.out.println(d);
        }
        System.in.read();
    }
}

public class ResponsiveUI extends Thread {
    private static volatile double d = 1;

    public ResponsiveUI() {
        setDaemon(true);
        start();
    }

    public void run() {
        try {
            while (true) {
                d = d + (Math.PI + Math.E) / d;
//                System.out.println(d);
                sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println(getName() + " is interrupted!");
        }
    }

    public static void main(String[] args) throws Exception {
//        new UnresponsiveUI();
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);
    }
}
