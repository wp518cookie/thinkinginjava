package chapter18_IO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by ping.wu on 2016/12/15.
 */
public class SortedDirList {
    public static FilenameFilter filter(String regx) {
        return new FilenameFilter() {
            Pattern pattern = Pattern.compile(regx);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }

    public static void main(String[] args) {
        File file = new File(".") {
            @Override
            public String[] list() {
                String[] list = super.list();
                Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
                return list;
            }

            public String[] list(String regx) {
                String[] list = super.list(filter(regx));
                return null;
            }
        };
    }
}
