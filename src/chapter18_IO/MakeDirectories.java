package chapter18_IO;

import java.io.File;

/**
 * Created by ping.wu on 2016/12/15.
 */
public class MakeDirectories {
    private static void fileData(File f) {
        System.out.println(
                "Absolute path:" + f.getAbsolutePath() +
                        "\n Can read:" + f.canRead() +
                        "\n Can write:" + f.canWrite() +
                        "\n getName:" + f.getName() +
                        "\n getParent:" + f.getParent() +
                        "\n getPath:" + f.getPath() +
                        "\n length:" + f.length() +
                        "\n lastModified:" + f.lastModified());
    }

    public static void main(String[] args) {
        File old = new File(".");
        fileData(old);
    }
}
