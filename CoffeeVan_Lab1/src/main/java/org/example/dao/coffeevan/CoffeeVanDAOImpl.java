package org.example.dao.coffeevan;

import org.example.common.dboper.DBOperations;
import org.example.entities.CoffeeBeans;
import org.example.entities.CoffeeVan;
import org.hibernate.query.Query;

import java.util.List;

public class CoffeeVanDAOImpl implements CoffeeVanDAO{
    @Override
    public void save(CoffeeVan van) {
        DBOperations.executeTransaction(session -> {
            session.persist(van);
        });
    }

    @Override
    public void delete(CoffeeVan van) {
        DBOperations.executeTransaction(session -> {
            session.remove(van);
        });
    }

    @Override
    public CoffeeVan getById(long id) {
        return DBOperations.executeQuery(session -> session.get(CoffeeVan.class, id));
    }

    @Override
    public List<CoffeeVan> getAll() {
        return null;
    }

    @Override
    public List<CoffeeBeans> getAllCoffeeByVanId(long id) {
        return DBOperations.executeQuery(session -> {
            Query<CoffeeBeans> query = session.createQuery(
                    "SELECT c FROM GroundCoffee c WHERE c.van.id = :vanId " +
                            "UNION " +
                            "SELECT c FROM InstantCoffee c WHERE c.van.id = :vanId " +
                            "UNION " +
                            "SELECT c FROM CoffeeBeans c WHERE c.van.id = :vanId", CoffeeBeans.class);
            query.setParameter("vanId", id);
            return query.list();
        });
    }
}
