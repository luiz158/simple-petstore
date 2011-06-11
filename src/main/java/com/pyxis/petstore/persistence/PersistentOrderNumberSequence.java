package com.pyxis.petstore.persistence;

import com.pyxis.petstore.Factory;
import com.pyxis.petstore.domain.order.OrderNumber;
import com.pyxis.petstore.domain.order.OrderNumberSequence;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Factory
public class PersistentOrderNumberSequence implements OrderNumberSequence {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersistentOrderNumberSequence(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public OrderNumber nextOrderNumber() {
        Session session = sessionFactory.getCurrentSession();
        session.createSQLQuery("insert into order_number_sequence values(null)").executeUpdate();
        BigInteger nextValue = (BigInteger) session.createSQLQuery("select identity()").
                uniqueResult();
        return new OrderNumber(nextValue);
    }
}
