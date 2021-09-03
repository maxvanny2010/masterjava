package ru.javaops.masterjava.service.util;

import java.util.TimerTask;

import static ru.javaops.masterjava.service.util.Exit.INTERRUPTED_BY_TIMEOUT;

/**
 * MeTimer.
 *
 * @author legion
 * @version 5.0
 * @since 03.09.2021
 */
public class MeTimer extends TimerTask {
    private final MailResult mail;

    public MeTimer(final MailResult mail) {
        this.mail = mail;
    }

    @Override
    public void run() {
        if (mail == null) {
            throw new RuntimeException(INTERRUPTED_BY_TIMEOUT);
        }
    }
}
