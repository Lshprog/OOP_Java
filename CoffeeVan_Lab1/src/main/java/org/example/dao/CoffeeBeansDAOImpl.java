package org.example.dao;

import org.example.common.dboper.DBOperations;
import org.example.entities.CoffeeBeans;


import java.util.List;

public class CoffeeBeansDAOImpl<T extends CoffeeBeans> extends RepositoryImpl<T, Long> implements CoffeeBeansDAO<T> {

    public CoffeeBeansDAOImpl(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public List<T> getAllByVanId(long van_id) {
        return DBOperations.executeQuery(session -> session.createQuery(
                        "FROM " + entityClass.getSimpleName() + " c WHERE c.van.id = :van_id",
                        entityClass)
                .setParameter("van_id", van_id)
                .list());
    }

}
