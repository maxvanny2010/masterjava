package ru.javaops.masterjava.service.mail;

import com.typesafe.config.Config;
import lombok.val;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

/**
 * MailSenderTest.
 *
 * @author legion
 * @version 5.0
 * @since 27.09.2021
 */
public class MailSenderTest {
    public static void initEmail() {
        Config config = MailProvider.MailHolder.instConf();
        initEmail(config.getString("mail.host"), config.getInt("mail.port"),
                config.getString("mail.username"), config.getString("mail.password"),
                config.getBoolean("mail.useSSL"), config.getBoolean("mail.useTSL"),
                config.getString("mail.debug"), config.getString("mail.fromName")
        );
    }

    public static void initEmail(
            String host, int port, String username, String password,
            boolean ssl, boolean tsl, String debugs, String fromName
    ) {
        val email = new SimpleEmail();
        email.setHostName(host);
        email.setSmtpPort(port);
        email.setSslSmtpPort(String.valueOf(port));
        email.setAuthenticator(new DefaultAuthenticator(username, password));
        email.setSSLOnConnect(ssl);
        email.setStartTLSEnabled(tsl);
        /*email.getMailSession().getProperties().put("mail.smtp.auth", "true");
        email.getMailSession().getProperties().put("mail.debug", "true");
        email.getMailSession().getProperties().put("mail.smtp.port", "587");
        email.getMailSession().getProperties().put("mail.smtp.socketFactory.port", "587");
        email.getMailSession().getProperties().put("mail.smtp.socketFactory.class",   "javax.net.ssl.SSLSocketFactory");
        email.getMailSession().getProperties().put("mail.smtp.socketFactory.fallback", "false");
        email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");*/
        MailProvider.init(email);
    }
}
