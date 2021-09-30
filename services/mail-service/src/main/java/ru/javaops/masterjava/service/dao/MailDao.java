package ru.javaops.masterjava.service.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import one.util.streamex.StreamEx;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.dao.AbstractDao;
import ru.javaops.masterjava.service.model.Mail;
import java.util.*;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class MailDao implements AbstractDao {

    @SqlUpdate("TRUNCATE emails CASCADE ")
    @Override
    public abstract void clean();

    @SqlQuery("SELECT * FROM emails")
    public abstract List<Mail> getAll();

    public List<Mail> getAsList() {
        return StreamEx.of(getAll()).toList();
    }

    @SqlUpdate("INSERT INTO emails (header, body, sender_id, receiver_id) VALUES (:header, :body, :senderId, :receiverId) ")
    @GetGeneratedKeys
    abstract int insertGeneratedId(@BindBean Mail mail);

    public void insert(Mail mail) {
        int id = insertGeneratedId(mail);
        mail.setId(id);
    }
}
