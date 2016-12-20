package chapter21_Concurrency.exercise;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ping.wu on 2016/12/19.
 */
public class E14_TimerTest {
    public static int id = 0;
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    int i = id++;
                    while (true) {
                        System.out.println("timetask : " + i);
                    }
                }
            }, 500);
        }
    }
}
