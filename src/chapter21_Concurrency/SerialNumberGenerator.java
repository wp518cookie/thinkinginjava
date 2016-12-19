package chapter21_Concurrency;

/**
 * Created by ping.wu on 2016/12/19.
 */
public class SerialNumberGenerator {
    private static volatile int serialNumber = 0;

    public static synchronized int newSerialNumber() {
        return serialNumber++;
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println(newSerialNumber());
        }
    }
}
