package ru.javaops.masterjava.service.util;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.service.dao.AddresseeDao;
import ru.javaops.masterjava.service.dao.MailDao;
import ru.javaops.masterjava.service.model.Addressee;
import ru.javaops.masterjava.service.model.Mail;

/**
 * Util.
 *
 * @author legion
 * @version 5.0
 * @since 30.09.2021
 */
public final class Util {
    private Util() {
    }

    public static Mail createMail(final Addressee myAddressee, final Addressee addressee,
                                  final String subject, final String body
    ) {
        Mail mail = new Mail();
        mail.setHeader(subject);
        mail.setBody(body);
        mail.setSenderId(myAddressee.getId());
        mail.setReceiverId(addressee.getId());
        return mail;
    }

    public static void transactionAddressee(final Addressee model, AddresseeDao dao) {
        DBIProvider.getDBI().useTransaction(((conn, status) -> dao.insert(model)));
    }

    public static void transactionMail(final Mail model, MailDao dao) {
        DBIProvider.getDBI().useTransaction(((conn, status) -> dao.insert(model)));
    }

    public static Addressee createAddressee(final String mail, final String name) {
        Addressee addressee = new Addressee();
        addressee.setEmail(mail);
        addressee.setName(name);
        return addressee;
    }

    public static void createEmail(final Email email,
                                   final String emailFrom, final String emailTO,
                                   String header, String body) throws EmailException {
        email.setFrom(emailFrom);
        email.addTo(emailTO);
        email.setSubject(header);
        email.setMsg(body);
    }
}
