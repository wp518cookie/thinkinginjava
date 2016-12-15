package chapter18_IO.exercise;

import java.io.File;

/**
 * Created by ping.wu on 2016/12/15.
 */
public class E03_DirList {
    public static void main(String[] args) {
        File file = new File(".");
        long fs;
        long total = 0;
        for (String dirItem : file.list()) {
            fs = (new File(file, dirItem)).length();
            total += fs;
            System.out.println(dirItem + ":" + fs);
        }
        System.out.println("==========================");
        System.out.println("file(s):" + total + " bytes");
        System.out.println("file.length():" + file.length() + " bytes");
    }
}
