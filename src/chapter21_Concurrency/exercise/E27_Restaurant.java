package chapter21_Concurrency.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ping.wu on 2016/12/28.
 * 不正确，shutdownNow()留着待以后琢磨,无法关闭
 */
class E27_Meal {
    private int orderNum;

    public E27_Meal(int num) {
        orderNum = num;
    }

    public String toString() {
        return "E27_Meal: " + orderNum;
    }
}

class E27_WaitPerson implements Runnable {
    private E27_Restaurant e27_restaurant;

    public E27_WaitPerson(E27_Restaurant e27_restaurant) {
        this.e27_restaurant = e27_restaurant;
    }

    public void run() {
        while (!Thread.interrupted()) {
            try {
                e27_restaurant.lock.lock();
                while (e27_restaurant.e27_meal == null) {
                    e27_restaurant.condititon.await();
                }
                System.out.println("meal is ready, consume :" + e27_restaurant.e27_meal);
                TimeUnit.MILLISECONDS.sleep(500);
                e27_restaurant.e27_meal = null;
                e27_restaurant.condititon.signalAll();
            } catch (InterruptedException e) {
                System.out.println("E27_WaitPerson exit via Interrupted");
            } finally {
                e27_restaurant.lock.unlock();
            }
        }
        System.out.println("E27_WaitPerson exit via task");
    }
}

class E27_Chef implements Runnable {
    private int count = 0;
    private E27_Restaurant e27_restaurant;

    public E27_Chef(E27_Restaurant e27_restaurant) {
        this.e27_restaurant = e27_restaurant;
    }

    public void run() {
        while (!Thread.interrupted()) {
            try {
                e27_restaurant.lock.lock();
                while (e27_restaurant.e27_meal != null) {
                    e27_restaurant.condititon.await();
                }
                if (++count >= 5) {
                    System.out.println("have made " + count + " meal, shutdown");
                    e27_restaurant.exec.shutdownNow();
                }
                System.out.println( e27_restaurant.e27_meal + " is consumed");
                TimeUnit.MILLISECONDS.sleep(400);
                e27_restaurant.e27_meal = new E27_Meal(count);
                e27_restaurant.condititon.signalAll();
            } catch (InterruptedException e) {
                System.out.println("E27_Chef exit via Interrupted");
            } finally {
                e27_restaurant.lock.unlock();
            }
        }
    }
}

public class E27_Restaurant {
    E27_WaitPerson e27_waitPerson = new E27_WaitPerson(this);
    E27_Chef e27_chef = new E27_Chef(this);
    E27_Meal e27_meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    Lock lock = new ReentrantLock();
    Condition condititon = lock.newCondition();

    public E27_Restaurant() {
        exec.execute(e27_chef);
        exec.execute(e27_waitPerson);
    }

    public static void main(String[] args) {
        new E27_Restaurant();
    }
}
