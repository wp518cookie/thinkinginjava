package chapter17_Container;

import java.util.Random;

/**
 * Created by ping.wu on 2016/11/30.
 */
public class Prediction {
    private static Random ran = new Random(47);
    private boolean shadow = ran.nextDouble() > 0.5;
    public String toString() {
        if (shadow) {
            return "Six more weeks of Winter!";
        } else {
            return "Early Spring";
        }
    }
}
