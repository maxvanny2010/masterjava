package ru.javaops.masterjava.service.util;

import java.util.LinkedList;
import java.util.List;

/**
 * Store.
 *
 * @author legion
 * @version 5.0
 * @since 03.09.2021
 */
public final class StoreResult {
    private static final List<MailResult> success = new LinkedList<>();
    private static final List<MailResult> failed = new LinkedList<>();

    private StoreResult() {
    }

    public static void addFall(final MailResult result) {
        failed.add(result);
    }

    public static void addSuccess(final MailResult result) {
        success.add(result);
    }

    public static int sizeFall() {
        return failed.size();
    }

    public static int sizeSuccess() {
        return success.size();
    }

    public static void clear() {
        failed.clear();
        success.clear();
    }

    public static List<MailResult> getFall() {
        return new LinkedList<>(failed);
    }

    public static List<MailResult> getSuccess() {
        return new LinkedList<>(success);
    }

    public static void save(final MailResult mail) {
        if (mail.isOk()) StoreResult.addSuccess(mail);
        else StoreResult.addFall(mail);
    }

    public static boolean isFall(final int size) {
        return sizeFall() >= size;
    }
}
