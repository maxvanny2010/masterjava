package ru.javaops.masterjava.service.mail;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.service.dao.AddresseeDao;
import ru.javaops.masterjava.service.dao.MailDao;
import static ru.javaops.masterjava.service.mail.MailProvider.MailHolder.instMail;
import ru.javaops.masterjava.service.model.Addressee;
import ru.javaops.masterjava.service.model.Mail;
import static ru.javaops.masterjava.service.util.Util.createAddressee;
import static ru.javaops.masterjava.service.util.Util.createEmail;
import static ru.javaops.masterjava.service.util.Util.createMail;
import static ru.javaops.masterjava.service.util.Util.transactionAddressee;
import static ru.javaops.masterjava.service.util.Util.transactionMail;
import java.util.*;

@Slf4j
public class MailSender {

    private static final Email email = instMail();
    private static final MailDao MAIL_DAO = DBIProvider.getDao(MailDao.class);
    private static final AddresseeDao ADDRESSEE_DAO = DBIProvider.getDao(AddresseeDao.class);

    static void sendMail(List<Addressee> to, List<Addressee> cc, String subject, String body) {
        log.info("Send mail to \\'" + to + "\\' cc \\'" + cc + "\\' subject \\'"
                + subject + (log.isDebugEnabled() ? "\nbody=" + body : ""));
        val myAddressee = createAddressee("herokuboot@gmail.com", "myName");
        to.forEach(m -> {
                    try {
                        email.getToAddresses().clear();
                        createEmail(email, myAddressee.getEmail(), m.getEmail(), subject, body);
                        String send = email.send();
                        System.out.println(send);
                    } catch (EmailException e) {
                        log.error(e.getMessage());
                    }
                    val addressee = createAddressee(m.getEmail(), m.getName());
                    transactionAddressee(addressee, ADDRESSEE_DAO);
                    log.info("table ADDRESSEE update:" + addressee.getId());
                    Mail mail = createMail(myAddressee, addressee, subject, body);
                    transactionMail(mail, MAIL_DAO);
                    log.info("table MAIL update :" + mail.getId());

                }
        );
    }

}
