package chapter21_Concurrency;

/**
 * Created by ping.wu on 2016/12/20.
 */
class DaulSynch {
    private Object syncObject = new Object();

    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }

    public void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}

public class SyncObject {
    public static void main(String[] args) {
        final DaulSynch ds = new DaulSynch();
        new Thread() {
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();
    }
}
