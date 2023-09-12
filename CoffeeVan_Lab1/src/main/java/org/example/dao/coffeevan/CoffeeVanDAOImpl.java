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

    public CoffeeVanDAOImpl() {
        super(CoffeeVan.class);
    }

    @Override
    public List<CoffeeBeans> getAllCoffeeByVanId(long vanId) {
        return DBOperations.executeQuery(session -> {
            Query<CoffeeBeans> query = session.createQuery(
                    "SELECT c FROM GroundCoffee c WHERE c.van.id = :vanId " +
                            "UNION " +
                            "SELECT c FROM InstantCoffee c WHERE c.van.id = :vanId " +
                            "UNION " +
                            "SELECT c FROM CoffeeBeans c WHERE c.van.id = :vanId", CoffeeBeans.class);
            query.setParameter("vanId", vanId);
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

    @Override
    public List<CoffeeBeans> getCoffeeByVanIdAndType(long vanId, List<String> classNames) {
        return DBOperations.executeQuery(session -> {
            StringBuilder hql = new StringBuilder("SELECT c FROM ");

            for (String className : classNames) {
                hql.append(className).append(" c");

                if (classNames.indexOf(className) < classNames.size() - 1) {
                    hql.append(" UNION ");
                }
            }

            Query<CoffeeBeans> query = session.createQuery(hql.toString(), CoffeeBeans.class);
            return query.list();
        });
    }

    @Override
    public List<CoffeeBeans> getAllCoffeeSortedByParam(long vanId, String parameter) {
        return DBOperations.executeQuery(session -> {
            Query<CoffeeBeans> query = session.createQuery(
                    "SELECT c FROM GroundCoffee c WHERE c.van.id = :vanId " +
                            "UNION " +
                            "SELECT c FROM InstantCoffee c WHERE c.van.id = :vanId " +
                            "UNION " +
                            "SELECT c FROM CoffeeBeans c WHERE c.van.id = :vanId" +
                            "ORDER BY c." + parameter, CoffeeBeans.class);
            query.setParameter("vanId", vanId);
            return query.list();
        });
    }
}
