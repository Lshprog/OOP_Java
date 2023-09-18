package org.example.dao.coffee;

import org.example.common.dboper.DBOperations;
import org.example.dao.RepositoryImpl;
import org.example.entities.CoffeeProduct;


import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class CoffeeProductDAOImpl<T extends CoffeeProduct> extends RepositoryImpl<T, Long> implements CoffeeProductDAO<T> {

    public CoffeeProductDAOImpl(Class<T> entityClass) {
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

    @Override
    public List<T> getAllByVanId() {
        return DBOperations.executeQuery(session -> session.createQuery(
                        "FROM " + entityClass.getSimpleName() + " c WHERE c.van.id IS NULL",
                        entityClass)
                .list());
    }

    @Override
    public List<T> getAllByVanIdAndOrderedByPrice(long van_id) {
        return DBOperations.executeQuery(session -> session.createQuery(
                        "FROM " + entityClass.getSimpleName() + " c WHERE c.van.id = :van_id ORDER BY c.price",
                        entityClass)
                .setParameter("van_id", van_id)
                .list());
    }

    @Override
    public void deleteSafely(AbstractMap.SimpleEntry<String, Long> deletePair) {

        String key = deletePair.getKey();
        Long id = deletePair.getValue();

        DBOperations.executeQuery(session -> session.createQuery(
                        "DELETE " + entityClass.getSimpleName() + " c WHERE c."+ key +".id = :par_id",
                        entityClass)
                .setParameter("par_id", id)
                .list());

    }


}
