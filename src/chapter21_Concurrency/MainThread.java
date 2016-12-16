package chapter21_Concurrency;

/**
 * Created by ping.wu on 2016/12/15.
 */
public class MainThread {
    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
    }
}
