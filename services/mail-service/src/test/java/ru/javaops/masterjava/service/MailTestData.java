package ru.javaops.masterjava.service;

import com.google.common.collect.ImmutableList;
import ru.javaops.masterjava.persist.DBIProvider;
import static ru.javaops.masterjava.service.AddresseeTestData.ADDRESSEE_01_ID;
import static ru.javaops.masterjava.service.AddresseeTestData.ADDRESSEE_02_ID;
import static ru.javaops.masterjava.service.AddresseeTestData.ADDRESSEE_03_ID;
import static ru.javaops.masterjava.service.AddresseeTestData.ADDRESSEE_04_ID;
import static ru.javaops.masterjava.service.AddresseeTestData.ADDRESSEE_05_ID;
import ru.javaops.masterjava.service.dao.MailDao;
import ru.javaops.masterjava.service.model.Mail;
import java.util.*;

public class MailTestData {
    public static Mail MAIL_1;
    public static Mail MAIL_2;
    public static Mail MAIL_3;
    public static Mail MAIL_4;
    public static Mail MAIL_5;
    public static List<Mail> MAILS;

    public static void init() {
        AddresseeTestData.init();
        AddresseeTestData.setUp();
        MAIL_1 = new Mail("mail_1", "mail_1", ADDRESSEE_01_ID, ADDRESSEE_05_ID);
        MAIL_2 = new Mail("mail_2", "mail_2", ADDRESSEE_02_ID, ADDRESSEE_04_ID);
        MAIL_3 = new Mail("mail_3", "mail_3", ADDRESSEE_03_ID, ADDRESSEE_01_ID);
        MAIL_4 = new Mail("mail_4", "mail_4", ADDRESSEE_04_ID, ADDRESSEE_02_ID);
        MAIL_5 = new Mail("mail_5", "mail_5", ADDRESSEE_05_ID, ADDRESSEE_01_ID);
        MAILS = ImmutableList.of(MAIL_1, MAIL_2, MAIL_3, MAIL_4, MAIL_5);
    }

    public static void setUp() {
        MailDao dao = DBIProvider.getDao(MailDao.class);
        dao.clean();
        DBIProvider.getDBI().useTransaction((conn, status) -> MAILS.forEach(dao::insert));
    }
}
