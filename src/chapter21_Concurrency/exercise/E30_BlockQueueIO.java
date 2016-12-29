package chapter21_Concurrency.exercise;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2016/12/29.
 */
class Letter {
    char letter;

    public Letter(char letter) {
        this.letter = letter;
    }

    public String toString() {
        return String.valueOf(letter);
    }
}

class LetterQueue extends LinkedBlockingQueue<Letter> {

}

class Sender implements Runnable {
    LetterQueue letterQueue;
    Random rand = new Random(47);

    public Sender(LetterQueue queue) {
        letterQueue = queue;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                for (char c = 'A'; c < 'z'; c++) {
                    Letter letter = new Letter(c);
                    System.out.println("letterQueue add: " + letter);
                    letterQueue.put(letter);
                    TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(400));
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Sender exit via interrupt");
        }
        System.out.println("Sender exit via task");
    }
}

class Receiver implements Runnable {
    LetterQueue letterQueue;
    Random rand = new Random(47);

    public Receiver(LetterQueue queue) {
        letterQueue = queue;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Letter letter = letterQueue.take();
                System.out.println("letterQueue read: " + letter);
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(400));
            }
        } catch (InterruptedException e) {
            System.out.println("Receiver exit via interrupt");
        }
        System.out.println("Receiver exit via task");
    }
}

public class E30_BlockQueueIO {
    public static void main(String[] args) throws Exception {
        LetterQueue letterQueue = new LetterQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Sender(letterQueue));
        exec.execute(new Receiver(letterQueue));
        TimeUnit.SECONDS.sleep(5);
        System.exit(1);
    }
}
