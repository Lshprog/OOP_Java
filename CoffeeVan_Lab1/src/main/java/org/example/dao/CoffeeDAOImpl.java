package org.example.dao;

import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CoffeeDAOImpl<T> implements CoffeeDAO<T> {

    @Override
    public void save(T coffee) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.persist(coffee);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(T coffee) {

    }

    @Override
    public T getById(long id) {
        return null;
    }
}
