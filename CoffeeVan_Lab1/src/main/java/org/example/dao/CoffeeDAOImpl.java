package org.example.dao;

import org.example.dao.dboper.DBOperations;
import org.example.entities.Coffee;
import org.example.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CoffeeDAOImpl<T> implements CoffeeDAO<T> {

    private final Class<T> entityClass;

    public CoffeeDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(T coffee) {
        DBOperations.executeTransaction(session -> {
            session.persist(coffee);
        });
    }

    @Override
    public void delete(T coffee) {
        DBOperations.executeTransaction(session -> {
            session.remove(coffee);
        });
    }

    @Override
    public T getById(long id) {
        return DBOperations.executeQuery(session -> session.get(entityClass, id));
    }

    @Override
    public List<T> getAll() {
        return DBOperations.executeQuery(session -> session.createQuery(
                "FROM " + entityClass.getSimpleName(),
                entityClass).list());

    }
}
