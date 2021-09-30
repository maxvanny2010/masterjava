package ru.javaops.masterjava.service.mail;

import com.typesafe.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import ru.javaops.masterjava.config.Configs;

/**
 * MailProvider.
 *
 * @author legion
 * @version 5.0
 * @since 26.09.2021
 */
@Slf4j
public final class MailProvider {

    private MailProvider() {
    }

    public static void init(Email email) {
        MailHolder.EMAIL = email;
    }

    public static class MailHolder {
        private static final Config CONFIG;
        private static Email EMAIL;

        static {
            Email email = new SimpleEmail();
            CONFIG = Configs.getConfig("mail.conf", "mail");
            email.setHostName(CONFIG.getString("mail.host"));
            email.setSmtpPort(CONFIG.getInt("mail.port"));
            email.setSslSmtpPort(String.valueOf(CONFIG.getInt("mail.port")));
            email.setAuthenticator(new DefaultAuthenticator(
                    CONFIG.getString("mail.username"),
                    CONFIG.getString("mail.password")));
            email.setSSLOnConnect(CONFIG.getBoolean("mail.useSSL"));
            email.setStartTLSEnabled(CONFIG.getBoolean("mail.useTSL"));
            MailProvider.init(email);
        }

        public static Email instMail() {
            return MailHolder.EMAIL;
        }

        public static Config instConf() {
            return MailHolder.CONFIG;
        }
    }
}
