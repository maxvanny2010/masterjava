package ru.javaops.masterjava.service.dao;

import static java.util.stream.Collectors.*;
import lombok.val;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.javaops.masterjava.service.AddresseeTestData;
import static ru.javaops.masterjava.service.AddresseeTestData.ADDRESSEE;
import ru.javaops.masterjava.service.model.Addressee;
import java.util.*;
import java.util.Map.*;

public class AddresseeDaoTest extends AbstractDaoMailTest<AddresseeDao> {

    public AddresseeDaoTest() {
        super(AddresseeDao.class);
    }

    @BeforeClass
    public static void init() {
        AddresseeTestData.init();
    }

    @Before
    public void setUp() {
        AddresseeTestData.setUp();
    }

    @Test
    public void getAll() {
        final Map<String, Addressee> addressee = dao.getAsMap();
        val map = addressee.entrySet()
                .stream()
                .sorted(Entry.comparingByKey())
                .collect(toMap(Entry::getKey, Entry::getValue,
                        (old, newValue) -> old, LinkedHashMap::new));
        assertEquals(map, ADDRESSEE);
        assertEquals(addressee, ADDRESSEE);

    }
}