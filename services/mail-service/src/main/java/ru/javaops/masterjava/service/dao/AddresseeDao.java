package ru.javaops.masterjava.service.dao;

import com.bertoncelj.jdbi.entitymapper.EntityMapperFactory;
import one.util.streamex.StreamEx;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import ru.javaops.masterjava.persist.dao.AbstractDao;
import ru.javaops.masterjava.service.model.Addressee;
import java.util.*;

@RegisterMapperFactory(EntityMapperFactory.class)
public abstract class AddresseeDao implements AbstractDao {

    @SqlUpdate("TRUNCATE addressee CASCADE ")
    @Override
    public abstract void clean();

    @SqlQuery("SELECT * FROM addressee")
    public abstract List<Addressee> getAll();

    public Map<String, Addressee> getAsMap() {
        return StreamEx.of(getAll()).toMap(Addressee::getName, addressee -> addressee);
    }

    @SqlUpdate("INSERT INTO addressee (email, name) VALUES ( :email, :name)")
    @GetGeneratedKeys
    public abstract int insertGeneratedId(@BindBean Addressee addressee);

    public void insert(Addressee addressee) {
        int id = insertGeneratedId(addressee);
        addressee.setId(id);
    }
}
