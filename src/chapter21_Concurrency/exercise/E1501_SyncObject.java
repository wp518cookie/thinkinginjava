package chapter21_Concurrency.exercise;

/**
 * Created by ping.wu on 2016/12/20.
 */
class SyncObject1 {
    private Object syncObject = new Object();

    public void f() {
        synchronized (syncObject) {
            for (int i = 0; i < 100; i++) {
                System.out.println("f(n):" + i);
            }
        }
    }

    public void g() {
        synchronized (syncObject) {
            for (int i = 0; i < 100; i++) {
                System.out.println("g(n):" + i);
            }
        }
    }

    public void h() {
        synchronized (syncObject) {
            for (int i = 0; i < 100; i++) {
                System.out.println("h(n):" + i);
            }
        }
    }
}

class SyncObject2 {
    public Object syncObjectg = new Object();
    public Object syncObjecth = new Object();

    public synchronized void f() {
        for (int i = 0; i < 100; i++) {
            System.out.println("f(n):" + i);
        }
    }

    public void g() {
        synchronized (syncObjectg) {
            for (int i = 0; i < 100; i++) {
                System.out.println("g(n):" + i);
            }
        }
    }

    public void h() {
        synchronized (syncObjecth) {
            for (int i = 0; i < 100; i++) {
                System.out.println("h(n):" + i);
            }
        }
    }
}

public class E1501_SyncObject {
    public static void main(String[] args) {
        final SyncObject2 syncObject1 = new SyncObject2();
        new Thread() {
            @Override
            public void run() {
                syncObject1.f();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                syncObject1.g();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                syncObject1.h();
            }
        }.start();
    }
}
