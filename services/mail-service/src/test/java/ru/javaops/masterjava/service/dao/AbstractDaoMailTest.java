package ru.javaops.masterjava.service.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import ru.javaops.masterjava.persist.DBIProvider;
import ru.javaops.masterjava.persist.dao.AbstractDao;
import ru.javaops.masterjava.service.DBITestProvider;

@Slf4j
public abstract class AbstractDaoMailTest<DAO extends AbstractDao> {
    static {
        DBITestProvider.initDBI();
    }

    @Rule
    public TestRule testWatcher = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            log.info("\n\n+++ Start " + description.getDisplayName());
        }

        @Override
        protected void finished(Description description) {
            log.info("\n+++ Finish " + description.getDisplayName() + '\n');
        }
    };

    protected DAO dao;

    protected AbstractDaoMailTest(Class<DAO> daoClass) {
        this.dao = DBIProvider.getDao(daoClass);
    }
}
