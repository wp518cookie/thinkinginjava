package chapter21_Concurrency.exercise;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2016/12/30.
 */
class Chopstick {
    private final int id;
    private boolean taken;

    public Chopstick(int ident) {
        id = ident;
    }

    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }

    public String toString() {
        return "Chopstick " + id;
    }
}

class ChopstickQueue extends LinkedBlockingQueue<Chopstick> {
}

class ChopstickBin {
    private ChopstickQueue bin = new ChopstickQueue();

    public Chopstick get() throws InterruptedException {
        return bin.take();
    }

    public void put(Chopstick stick) throws InterruptedException {
        bin.put(stick);
    }
}

class Philosopher implements Runnable {
    private static Random rand = new Random(47);
    private final int id;
    private final int ponderFactor;
    private ChopstickBin bin;

    private void pause() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    public Philosopher(ChopstickBin bin, int ident, int ponder) {
        this.bin = bin;
        id = ident;
        ponderFactor = ponder;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " " + "thinking");
                pause();
                Chopstick c1 = bin.get();
                System.out.println(this + " has " + c1 + " waiting for another one");
                Chopstick c2 = bin.get();
                System.out.println(this + " has " + c2);
                System.out.println(this + " eating");
                pause();
                bin.put(c1);
                bin.put(c2);
            }
        } catch (InterruptedException e) {
            System.out.println(this + " " + "exiting via interrupt");
        }
    }

    public String toString() {
        return "Philosopher " + id;
    }
}

public class E31_DiningPhilosophers2 {
    public static void main(String[] args) {

    }
}
