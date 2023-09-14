package org.example.dao.coffee;

import org.example.common.dboper.DBOperations;
import org.example.entities.GroundCoffee;
import org.hibernate.query.Query;

import java.util.List;

public class GroundCoffeeDAOImpl extends CoffeeProductDAOImpl<GroundCoffee> implements GroundCoffeeDAO {

    public GroundCoffeeDAOImpl() {
        super(GroundCoffee.class);
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
