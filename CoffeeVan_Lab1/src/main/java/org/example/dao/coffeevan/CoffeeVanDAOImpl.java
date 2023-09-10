package org.example.dao.coffeevan;

import org.example.common.dboper.DBOperations;
import org.example.entities.Coffee;
import org.example.entities.CoffeeVan;
import org.example.entities.GroundCoffee;
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
    public List<Coffee> getAllCoffeeByVanId(long id) {
        return DBOperations.executeQuery(session -> {
            Query<Coffee> query = session.createQuery(
                    "SELECT c FROM GroundCoffee c WHERE c.van.id = :vanId " +
                            "UNION " +
                            "SELECT c FROM InstantCoffee c WHERE c.van.id = :vanId " +
                            "UNION " +
                            "SELECT c FROM CoffeeBeans c WHERE c.van.id = :vanId", Coffee.class);
            query.setParameter("vanId", id);
            return query.list();
        });
    }
}
