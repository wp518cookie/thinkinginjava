package chap16_Array.exercise;

import chap16_Array.BerylliumSphere;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/4/14.
 */
class A {
    public String toString() {
        return "A Object";
    }
}

public class Ex1 {
    public static void test(BerylliumSphere[] a) {
        System.out.println("1:" + Arrays.asList(a));
    }

    public static <T> void test(T[] t) {
        System.out.println("2:" + Arrays.toString(t));
    }

    public static void test(int[] ia) {
        System.out.println("3:" + Arrays.asList(ia));
    }

    public static void main(String[] args) {
        test(new BerylliumSphere[3]);
        test(new BerylliumSphere[]{new BerylliumSphere(), new BerylliumSphere()});
        BerylliumSphere[] a = {new BerylliumSphere(), new BerylliumSphere(), new BerylliumSphere()};
        test(a);
        BerylliumSphere[] bsa = new BerylliumSphere[2];
        test(bsa);
        bsa = a;
        test(bsa);
        test(new int[]{new Integer(0), new Integer(0)});
        test(new int[2]);
        int[] ia = {1, 2, 3};
        test(ia);
    }
}
