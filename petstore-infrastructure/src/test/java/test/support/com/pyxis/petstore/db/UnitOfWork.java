package test.support.com.pyxis.petstore.db;

import org.hibernate.Session;

public interface UnitOfWork {

    void work(Session session) throws Exception;
}
