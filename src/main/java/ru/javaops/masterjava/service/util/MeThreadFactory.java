package ru.javaops.masterjava.service.util;

import java.util.concurrent.*;

/**
 * MeThreadFactory.
 *
 * @author legion
 * @version 5.0
 * @since 03.09.2021
 */
public class MeThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(final Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
