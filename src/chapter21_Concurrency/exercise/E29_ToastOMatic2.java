package chapter21_Concurrency.exercise;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2016/12/29.
 */
class Toast {
    public enum Status {
        DRY,
        BUTTERED,
        JAMMED,
        READY {
            public String toString() {
                return BUTTERED.toString() + " & " + JAMMED.toString();
            }
        }
    }

    private Status status = Status.DRY;
    private final int id;

    public Toast(int idn) {
        id = idn;
    }

    public void butter() {
        status = (status == Status.DRY) ? Status.BUTTERED : Status.READY;
    }

    public void jam() {
        status = (status == Status.DRY) ? Status.JAMMED : Status.READY;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Toast " + id + ": " + status;
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {

}

class Toaster implements Runnable {
    private ToastQueue toastQueue;
    private int count;
    private Random rand = new Random(47);

    public Toaster(ToastQueue tq) {
        toastQueue = tq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                Toast t = new Toast(count++);
                System.out.println(t);
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toaster off");
    }
}

class Butterer implements Runnable {
    private ToastQueue inQueue, butterQueue;

    public Butterer(ToastQueue in, ToastQueue buttered) {
        inQueue = in;
        butterQueue = buttered;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = inQueue.take();
                t.butter();
                System.out.println(t);
                butterQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer off");
        }
    }
}

class Jammer implements Runnable {
    private ToastQueue inQueue, jammedQueue;

    public Jammer(ToastQueue in, ToastQueue jammed) {
        inQueue = in;
        jammedQueue = jammed;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = inQueue.take();
                t.jam();
                System.out.println(t);
                jammedQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Jammed off");
        }
    }
}

class Eater implements Runnable {
    private ToastQueue finishedQueue;

    public Eater(ToastQueue finished) {
        finishedQueue = finished;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = finishedQueue.take();
                if (t.getStatus() != Toast.Status.READY) {
                    System.out.println(">>>> Error: " + t);
                    System.exit(1);
                } else {
                    System.out.println("Chomp!  " + t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}

class Alternator implements Runnable {
    private ToastQueue inQueue, out1Queue, out2Queue;
    private boolean outTo2;

    public Alternator(ToastQueue in, ToastQueue out1, ToastQueue out2) {
        inQueue = in;
        out1Queue = out1;
        out2Queue = out2;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = inQueue.take();
                if (!outTo2) {
                    out1Queue.put(t);
                } else {
                    out2Queue.put(t);
                }
                outTo2 = !outTo2;
            }
        } catch (InterruptedException e) {
            System.out.println("Alternator interrupted");
        }
        System.out.println("Alternator off");
    }
}

class Merger implements Runnable {
    private ToastQueue in1Queue, in2Queue, toBeButteredQueue, toBeJammedQueue, finishedQueue;

    public Merger(ToastQueue in1, ToastQueue in2, ToastQueue toBeButtered, ToastQueue toBeJammed, ToastQueue finished) {
        in1Queue = in1;
        in2Queue = in2;
        toBeButteredQueue = toBeButtered;
        toBeJammedQueue = toBeJammed;
        finishedQueue = finished;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = null;
                while (t == null) {
                    t = in1Queue.poll(50, TimeUnit.MILLISECONDS);
                    if (t != null) {
                        break;
                    }
                    t = in2Queue.poll(50, TimeUnit.MILLISECONDS);
                }
                switch (t.getStatus()) {
                    case BUTTERED:
                        toBeJammedQueue.put(t);
                        break;
                    case JAMMED:
                        toBeJammedQueue.put(t);
                        break;
                    default:
                        finishedQueue.put(t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Merger interrupt");
        }
        System.out.println("Merge off");
    }
}

public class E29_ToastOMatic2 {
    public static void main(String[] args) throws Exception {
        ToastQueue dryQueue = new ToastQueue(),
                butteredQueue = new ToastQueue(),
                toBeButteredQueue = new ToastQueue(),
                jammedQueue = new ToastQueue(),
                toBeJammedQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Alternator(dryQueue, toBeButteredQueue, toBeJammedQueue));
        exec.execute(new Butterer(toBeButteredQueue, butteredQueue));
        exec.execute(new Jammer(toBeJammedQueue, jammedQueue));
        exec.execute(new Merger(butteredQueue, jammedQueue, toBeButteredQueue, toBeJammedQueue, finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
