package chapter18_IO;

import net.mindview.util.PPrint;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by ping.wu on 2016/12/15.
 */
public final class Directory {
    public static File[] local(File dir, String regx) {
        return dir.listFiles(
                new FilenameFilter() {
                    Pattern pattern = Pattern.compile(regx);

                    @Override
                    public boolean accept(File dir, String name) {
                        return pattern.matcher(name).matches();
                    }
                }
        );
    }

    public static File[] local(String path, String regx) {
        return local(new File(path), regx);
    }

    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<File>();
        public List<File> dirs = new ArrayList<File>();

        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
        }

        public String toString() {
            return "dirs: " + PPrint.pformat(dirs) + "\n\nfiles: " + PPrint.pformat(files);
        }
    }
}
