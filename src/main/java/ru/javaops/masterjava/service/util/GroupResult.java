package ru.javaops.masterjava.service.util;

import java.util.List;
import java.util.concurrent.*;

import static ru.javaops.masterjava.service.util.Exit.INTERRUPTED_BY_FAULTS_NUMBER;
import static ru.javaops.masterjava.service.util.Exit.INTERRUPTED_BY_TIMEOUT;
import static ru.javaops.masterjava.service.util.Exit.INTERRUPTED_EXCEPTION;

/**
 * GroupResult.
 *
 * @author legion
 * @version 5.0
 * @since 03.09.2021
 */
public class GroupResult {
    private final int success; // number of successfully sent email
    private final List<MailResult> failed; // failed emails with causes
    private final String failedCause;  // global fail cause

    public GroupResult(int success, List<MailResult> failed, String failedCause) {
        this.success = success;
        this.failed = failed;
        this.failedCause = failedCause;
    }

    public static GroupResult getOrException(final List<CompletableFuture<MailResult>> emailFutures, final Throwable e) {
        GroupResult groupResult = new GroupResult(StoreResult.sizeSuccess(), StoreResult.getFall(), null);
        System.out.println(StoreResult.getFall());
        System.out.println(StoreResult.getSuccess());
        if (e != null) {
            cancelAll(emailFutures);
        }
        if (StoreResult.getSuccess().contains(null)) {
            groupResult = cancelWithFail(INTERRUPTED_BY_TIMEOUT);
        } else if (StoreResult.isFall(2)) {
            groupResult = cancelWithFail(INTERRUPTED_BY_FAULTS_NUMBER);
        } else if (e instanceof InterruptedException) {
            groupResult = cancelWithFail(INTERRUPTED_EXCEPTION);
        } else if (e instanceof RuntimeException || e instanceof ExecutionException) {
            groupResult = cancelWithFail(e.getCause().toString());
        }
        StoreResult.clear();
        return groupResult;
    }

    private static GroupResult cancelWithFail(final String cause) {
        return new GroupResult(StoreResult.sizeSuccess(), StoreResult.getFall(), cause);
    }

    private static void cancelAll(final List<CompletableFuture<MailResult>> emailFutures) {
        emailFutures.forEach(f -> f.cancel(true));
    }

    @Override
    public String toString() {
        return "Success: " + success + '\n' +
                "Failed: " + failed.toString() + '\n' +
                (failedCause == null ? "" : "Failed cause" + failedCause);
    }
}
