package ru.javaops.masterjava.service.util;

/**
 * MailResult.
 *
 * @author legion
 * @version 5.0
 * @since 03.09.2021
 */
public class MailResult {
    private static final String OK = "OK";
    private final String email;
    private final String result;

    private MailResult(String email, String cause) {
        this.email = email;
        this.result = cause;
    }

    public static MailResult ok(String email) {
        return new MailResult(email, OK);
    }

    public static MailResult error(String email, String error) {
        return new MailResult(email, error);
    }

    //dummy realization
    public static MailResult sendToUser(String template, String email) {
        if (delay()) return null;
        return Math.random() < 0.7 ? MailResult.ok(email) : MailResult.error(email, "Error");
    }

    private static boolean delay() {
        try {
            Thread.sleep(500);  //delay
        } catch (InterruptedException e) {
            // log cancel;
            return true;
        }
        return false;
    }

    public boolean isOk() {
        return OK.equals(result);
    }

    @Override
    public String toString() {
        return '(' + email + ',' + result + ')';
    }
}
