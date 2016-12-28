package chapter21_Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ping.wu on 2016/12/28.
 */
class WaitPerson3 implements Runnable {
    private Restaurant3 restaurant;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public WaitPerson3(Restaurant3 r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try {
                    while (restaurant.meal == null) {
                        condition.await();
                    }
                } finally {
                    lock.unlock();
                }
                System.out.println("WaitPerson got " + restaurant.meal);
                restaurant.chef.lock.lock();
                try {
                    restaurant.meal = null;
                    restaurant.chef.condition.signalAll();
                } finally {
                    restaurant.chef.lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson3 interrupted!");
        }
    }
}

class Chef3 implements Runnable {
    private Restaurant3 restaurant;
    private int count;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public Chef3(Restaurant3 r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try {
                    if (restaurant.meal != null) {
                        condition.await();
                    }
                } finally {
                    lock.unlock();
                }
                if (++count >= 10) {
                    System.out.println("meal has reached max column");
                    restaurant.exec.shutdownNow();
                }
                System.out.println(restaurant.meal + " has been consumed!");
                restaurant.waitPerson.lock.lock();
                try {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.condition.signalAll();
                } finally {
                    restaurant.waitPerson.lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Chef3 exit via Interrupted");
        }
    }
}

class Restaurant3 {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson3 waitPerson = new WaitPerson3(this);
    Chef3 chef = new Chef3(this);

    public Restaurant3() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }
}

public class E27_Restaurant3 {
    public static void main(String[] args) {
        new Restaurant3();
    }
}
