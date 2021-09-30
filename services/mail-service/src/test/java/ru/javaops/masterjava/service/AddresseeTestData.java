package ru.javaops.masterjava.service;

import com.google.common.collect.ImmutableMap;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.service.dao.AddresseeDao;
import ru.javaops.masterjava.service.model.Addressee;
import java.util.*;

public class AddresseeTestData {
    public static Addressee ADDRESSEE_01;
    public static Addressee ADDRESSEE_02;
    public static Addressee ADDRESSEE_03;
    public static Addressee ADDRESSEE_04;
    public static Addressee ADDRESSEE_05;
    public static Map<String, Addressee> ADDRESSEE;

    public static int ADDRESSEE_01_ID;
    public static int ADDRESSEE_02_ID;
    public static int ADDRESSEE_03_ID;
    public static int ADDRESSEE_04_ID;
    public static int ADDRESSEE_05_ID;
    public static String ADDRESSEE_01_NAME;
    public static String ADDRESSEE_02_NAME;
    public static String ADDRESSEE_03_NAME;
    public static String ADDRESSEE_04_NAME;
    public static String ADDRESSEE_05_NAME;
    public static String ADDRESSEE_06_NAME;
    public static String ADDRESSEE_01_EMAIL;
    public static String ADDRESSEE_02_EMAIL;
    public static String ADDRESSEE_03_EMAIL;
    public static String ADDRESSEE_04_EMAIL;
    public static String ADDRESSEE_05_EMAIL;


    public static void init() {
        ADDRESSEE_01 = new Addressee("admin@gmail.com", "Admin");
        ADDRESSEE_02 = new Addressee("deleted@gmail.com", "Deleted");
        ADDRESSEE_03 = new Addressee("gmail@gmail.com", "Full Name");
        ADDRESSEE_04 = new Addressee("user1@gmail.com", "User1");
        ADDRESSEE_05 = new Addressee("user2@gmail.com", "User2");
        ADDRESSEE = ImmutableMap.of(
                ADDRESSEE_01.getName(), ADDRESSEE_01,
                ADDRESSEE_02.getName(), ADDRESSEE_02,
                ADDRESSEE_03.getName(), ADDRESSEE_03,
                ADDRESSEE_04.getName(), ADDRESSEE_04,
                ADDRESSEE_05.getName(), ADDRESSEE_05
        );
    }

    public static void setUp() {
        AddresseeDao dao = DBIProvider.getDao(AddresseeDao.class);
        dao.clean();
        DBIProvider.getDBI().useTransaction((conn, status) -> ADDRESSEE.values().forEach(dao::insert));
        ADDRESSEE_01_ID = ADDRESSEE_01.getId();
        ADDRESSEE_02_ID = ADDRESSEE_02.getId();
        ADDRESSEE_03_ID = ADDRESSEE_03.getId();
        ADDRESSEE_04_ID = ADDRESSEE_04.getId();
        ADDRESSEE_05_ID = ADDRESSEE_05.getId();
        ADDRESSEE_01_NAME = ADDRESSEE_01.getName();
        ADDRESSEE_02_NAME = ADDRESSEE_02.getName();
        ADDRESSEE_03_NAME = ADDRESSEE_03.getName();
        ADDRESSEE_04_NAME = ADDRESSEE_04.getName();
        ADDRESSEE_05_NAME = ADDRESSEE_05.getName();
        ADDRESSEE_01_EMAIL = ADDRESSEE_01.getEmail();
        ADDRESSEE_02_EMAIL = ADDRESSEE_02.getEmail();
        ADDRESSEE_03_EMAIL = ADDRESSEE_03.getEmail();
        ADDRESSEE_04_EMAIL = ADDRESSEE_04.getEmail();
        ADDRESSEE_05_EMAIL = ADDRESSEE_05.getEmail();
    }
}
