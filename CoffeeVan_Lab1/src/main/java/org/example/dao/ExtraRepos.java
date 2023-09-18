package org.example.dao;

import org.example.common.dboper.DBOperations;
import org.example.entities.*;
import org.hibernate.query.Query;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class ExtraRepos<T> extends RepositoryImpl<T, Long>{

    public ExtraRepos(Class<T> entityClass) {
        super(entityClass);
        setUp();
    }

    private final List<Class<? extends CoffeeProduct>> coffeeClasses = new ArrayList<>();

    private void setUp() {
        coffeeClasses.add(GroundCoffee.class);
        coffeeClasses.add(InstantCoffee.class);
        coffeeClasses.add(CoffeeBeans.class);
    }

    public void deleteCoffees(AbstractMap.SimpleEntry<String, Long> deletePair) {

        String key = deletePair.getKey();
        Long id = deletePair.getValue();

        for (Class<? extends CoffeeProduct> coffeeClass : coffeeClasses) {
            DBOperations.executeTransaction(session -> {
                Query<?> query = session.createQuery(
                                "DELETE FROM " + coffeeClass.getSimpleName() + " c WHERE c."+key+".id = :atrid")
                        .setParameter("atrid", id);
                query.executeUpdate();
            });
        }

    }


}
