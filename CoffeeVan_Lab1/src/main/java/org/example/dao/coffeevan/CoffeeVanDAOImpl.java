package org.example.dao.coffeevan;

import org.example.common.dboper.DBOperations;
import org.example.dao.RepositoryImpl;
import org.example.entities.CoffeeBeans;
import org.example.entities.CoffeeVan;
import org.hibernate.query.Query;

import java.util.List;

public class CoffeeVanDAOImpl extends RepositoryImpl<CoffeeVan, Long> implements CoffeeVanDAO {

    public CoffeeVanDAOImpl(Class<CoffeeVan> entityClass) {
        super(entityClass);
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

    @Override
    public CoffeeVan getCoffeeVanByName(String name) {

        return DBOperations.executeQuery(session -> {
            Query<CoffeeVan> query = session.createQuery(
                    "FROM CoffeeVan WHERE name = :name ", CoffeeVan.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        });
    }
}
