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

    private static Email createEmailByConfig(final Config config) {
        Email email = new SimpleEmail();
        email.setHostName(config.getString("mail.host"));
        email.setSmtpPort(config.getInt("mail.port"));
        email.setSslSmtpPort(String.valueOf(config.getInt("mail.port")));
        email.setAuthenticator(new DefaultAuthenticator(
                config.getString("mail.username"),
                config.getString("mail.password")));
        email.setSSLOnConnect(config.getBoolean("mail.useSSL"));
        email.setStartTLSEnabled(config.getBoolean("mail.useTSL"));
        return email;
    }

    public static class MailHolder {
        private static final Config CONFIG;
        private static Email EMAIL;

        static {
            CONFIG = Configs.getConfig("mail.conf", "mail");
            MailProvider.init(createEmailByConfig(CONFIG));
        }


        public static Email instMail() {
            return MailHolder.EMAIL;
        }

        public static Config instConf() {
            return MailHolder.CONFIG;
        }
    }
}
