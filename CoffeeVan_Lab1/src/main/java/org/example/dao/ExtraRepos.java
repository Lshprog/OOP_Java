package org.example.dao;

import org.example.common.dboper.DBOperations;
import org.example.entities.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class ExtraRepos<T> extends RepositoryImpl<T, Long>{

    public ExtraRepos(Class<T> entityClass) {
        super(entityClass);
        setUp();
    }

    protected final List<Class<? extends CoffeeProduct>> coffeeClasses = new ArrayList<>();

    protected void setUp() {
        coffeeClasses.add(GroundCoffee.class);
        coffeeClasses.add(InstantCoffee.class);
        coffeeClasses.add(CoffeeBeans.class);
    }

    protected List<CoffeeProduct> getListOfCoffees(String ordering,AbstractMap.SimpleEntry<String, Long> parameterPair) {
        List<CoffeeProduct> finalCoffees = new ArrayList<>();

        List<Function<Session, List<? extends CoffeeProduct>>> databaseOperations = new ArrayList<>();

        for (Class<? extends CoffeeProduct> coffeeClass : coffeeClasses) {
            databaseOperations.add(session -> {

                StringBuilder hql = new StringBuilder("SELECT c FROM ");
                hql.append(coffeeClass.getSimpleName());
                hql.append(" c WHERE c.");

                String parameter = parameterPair.getKey();
                Long id = parameterPair.getValue();

                if (id == null) {
                    hql.append(parameter);
                    hql.append(".id IS NULL ");
                    hql.append(ordering);
                } else {
                    hql.append(parameter);
                    hql.append(".id = :");
                    hql.append(parameter);
                    hql.append(" ");
                    hql.append(ordering);
                }

                Query<? extends CoffeeProduct> query = session.createQuery(hql.toString(), coffeeClass);

                if (id != null) {
                    query.setParameter(parameter, id);
                }

                return query.list();
            });
        }

        List<List<? extends CoffeeProduct>> results = DBOperations.executeQueries(databaseOperations);


        if (results!=null){
            results.forEach(finalCoffees::addAll);
        }

        return finalCoffees;


    }

    public void deleteCoffees(AbstractMap.SimpleEntry<String, Long> deletePair) {

        String key = deletePair.getKey();
        Long id = deletePair.getValue();

        List<Consumer<Session>> databaseOperations = new ArrayList<>();

        for (Class<? extends CoffeeProduct> coffeeClass : coffeeClasses) {
            databaseOperations.add(session -> {
                Query<?> query = session.createQuery(
                                "DELETE FROM " + coffeeClass.getSimpleName() + " c WHERE c."+key+".id = :atrid")
                        .setParameter("atrid", id);
                query.executeUpdate();
            });
        }

        DBOperations.executeTransactions(databaseOperations);
    }


}
