package chapter21_Concurrency;

import java.util.concurrent.ThreadFactory;

/**
 * Created by ping.wu on 2016/12/16.
 */
public class DaemonThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
