package ru.javaops.masterjava.service.dao;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.javaops.masterjava.service.MailTestData;
import static ru.javaops.masterjava.service.MailTestData.MAILS;
import ru.javaops.masterjava.service.model.Mail;
import java.util.*;

public class MailDaoTest extends AbstractDaoMailTest<MailDao> {

    public MailDaoTest() {
        super(MailDao.class);
    }

    @BeforeClass
    public static void init() {
        MailTestData.init();
    }

    @Before
    public void setUp() {
        MailTestData.setUp();
    }

    @Test
    public void getAll() {
        final List<Mail> mails = dao.getAsList();
        assertEquals(MAILS, mails);
        System.out.println(mails);
    }
}