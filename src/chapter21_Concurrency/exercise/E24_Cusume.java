package chapter21_Concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ping.wu on 2016/12/27.
 */
class Product {
    private final int productId;

    public Product(int productId) {
        this.productId = productId;
    }

    public String toString() {
        return "product " + productId;
    }
}

class Consumer implements Runnable {
    E24_Cusume e24_cusume;

    public Consumer(E24_Cusume e24_cusume) {
        this.e24_cusume = e24_cusume;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    if (e24_cusume.product == null) {
                        wait();
                    }
                }
                System.out.println("consumer consumed");
                synchronized (e24_cusume.producer) {
                    e24_cusume.product = null;
                    e24_cusume.producer.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Consumer Interrupted ");
        }
    }
}

class Producer implements Runnable {
    int count = 0;
    E24_Cusume e24_cusume;

    public Producer(E24_Cusume e24_cusume) {
        this.e24_cusume = e24_cusume;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    if (e24_cusume.product != null) {
                        wait();
                    }
                }
                System.out.println("Producer produce");
                if (++count >= 10) {
                    System.out.println("out of product, shut down");
                    e24_cusume.exec.shutdownNow();
                }
                synchronized (e24_cusume.consumer) {
                    e24_cusume.product = new Product(count);
                    System.out.println(e24_cusume.product + " has produced!");
                    e24_cusume.consumer.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Producer Interrupted! ");
        }
    }
}

public class E24_Cusume {
    Product product;
    ExecutorService exec = Executors.newCachedThreadPool();
    Consumer consumer = new Consumer(this);
    Producer producer = new Producer(this);

    public E24_Cusume() {
        exec.execute(producer);
        exec.execute(consumer);
    }

    public static void main(String[] args) {
        E24_Cusume e24_cusume = new E24_Cusume();
    }
}
