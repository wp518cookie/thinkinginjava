package chap16_Array;

/**
 * Created by ping.wu on 2017/4/14.
 */
public class BerylliumSphere {
    private static long counter;
    private final long id = counter++;
    public String toString() {
        return "Sphere " + id;
    }
}
