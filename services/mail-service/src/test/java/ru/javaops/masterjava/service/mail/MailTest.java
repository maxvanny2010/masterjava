package ru.javaops.masterjava.service.mail;

import org.apache.commons.mail.Email;
import org.junit.BeforeClass;
import org.junit.Test;
import static ru.javaops.masterjava.service.mail.MailProvider.MailHolder;

/**
 * MailTest.
 *
 * @author legion
 * @version 5.0
 * @since 27.09.2021
 */
public class MailTest {

    @BeforeClass
    public static void init() {
        MailSenderTest.initEmail();
    }

    @Test
    public void sendMail() throws Exception {
        Email email = MailHolder.instMail();
        email.setFrom("herokuboot@gmail.com");
        email.setSubject("TestMail");
        email.setMsg("This is a test mail ...");
        email.addTo("maxpraha@gmail.com");
        String send = email.send();
        System.out.println(send);
    }
}
