package org.example.dao;

import org.example.common.dboper.DBOperations;
import org.example.entities.GroundCoffee;
import org.hibernate.query.Query;

import java.util.List;

public class GroundCoffeeDAOImpl extends CoffeeDAOImpl<GroundCoffee> implements GroundCoffeeDAO {

    private GroundCoffeeDAOImpl() {
        super(GroundCoffee.class);
    }

    public static GroundCoffeeDAOImpl create() {
        return new GroundCoffeeDAOImpl();
    }

    @Override
    public List<GroundCoffee> getByGrindType(String grindType) {
        return DBOperations.executeQuery(session -> {
            Query<GroundCoffee> query = session.createQuery(
                    "FROM GroundCoffee WHERE grindType = :grindType", GroundCoffee.class);
            query.setParameter("grindType", grindType);
            return query.list();
        });
    }
}
