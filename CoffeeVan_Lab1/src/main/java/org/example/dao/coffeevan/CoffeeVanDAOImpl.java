package org.example.dao.coffeevan;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.common.TypeHelper;
import org.example.common.enums.Condition;
import org.example.common.filters.CoffeeFilter;
import org.example.common.dboper.DBOperations;
import org.example.common.filters.FilterNode;
import org.example.dao.ExtraRepos;
import org.example.entities.*;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.*;
import java.util.function.Function;

public class CoffeeVanDAOImpl extends ExtraRepos<CoffeeVan> implements CoffeeVanDAO {

    public CoffeeVanDAOImpl(Class<CoffeeVan> entityClass) {
        super(entityClass);
    }

    public CoffeeVanDAOImpl() {
        super(CoffeeVan.class);
    }

    @Override
    public List<CoffeeProduct> getAllCoffeeByVanId(Long vanId) {
        Optional<CoffeeVan> coffeeVanOpt = this.findById(vanId);

        if(coffeeVanOpt.isPresent())
            return this.getListOfCoffees("", new AbstractMap.SimpleEntry<>("van",vanId));
        else {
            return null;
        }
    }

    @Override
    public List<CoffeeProduct> getAllCoffeeByVanId() {
        return this.getListOfCoffees("", new AbstractMap.SimpleEntry<>("van",null));
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
    public List<CoffeeProduct> getAllCoffeeSortedByParam(Long vanId, String parameter) {
        return this.getListOfCoffees("ORDER BY c." + parameter, new AbstractMap.SimpleEntry<>("van",vanId));

    }

    @Override
    public List<CoffeeProduct> getCoffeeBasedOnParameters(Long vanId, CoffeeFilter filter, List<String> classNames) {

        List<CoffeeProduct> finalCoffees = new ArrayList<>();

        List<Function<Session, List<CoffeeProduct>>> databaseOperations = new ArrayList<>();

        for (String className : classNames) {
            databaseOperations.add(session -> {
                StringBuilder hql = new StringBuilder("SELECT c FROM ");

                List<FilterNode> criteriaList = filter.getFilterRanges();

                hql.append(className).append(" c WHERE c.van.id = :vanId");

                criteriaList.forEach((criteria) -> {

                    switch (criteria.getCondition()) {
                        case MAX -> {
                            hql.append(" AND c.").append(criteria.getAttrEntity()).append(" <= :").append(criteria.getAttrFilter());
                        }
                        case MIN -> {
                            hql.append(" AND c.").append(criteria.getAttrEntity()).append(" >= :").append(criteria.getAttrFilter());
                        }
                        case EQUAL -> {
                            hql.append(" AND c.").append(criteria.getAttrEntity()).append(" IN (:").append(criteria.getAttrFilter()).append(")");
                        }
                        case LIST -> {
                            if (TypeHelper.validateCombinationClasses(criteria.getDatatype(), className)) {
                                hql.append(" AND c.").append(criteria.getAttrEntity()).append(" IN (:").append(criteria.getAttrFilter()).append(")");
                            }
                        }
                    }
                });

                Query<CoffeeProduct> query = session.createQuery(hql.toString(), CoffeeProduct.class);

                criteriaList.forEach((criteria) -> {
                    if (TypeHelper.validateCombinationClasses(criteria.getDatatype(), className)) {
                        query.setParameter(criteria.getAttrFilter(), TypeHelper.convertToList(criteria.getValues(), criteria.getDatatype()));
                    }
                });

                query.setParameter("vanId", vanId);

                return query.list();
            });
        }

        List<List<CoffeeProduct>> results = DBOperations.executeQueries(databaseOperations);


        if (results!=null){
            results.forEach(finalCoffees::addAll);
        }

        return finalCoffees;
    }


    @Override
    public List<List<? extends CoffeeProduct>> getListOfCoffeeByTypesAvailable() {
        List<List<? extends CoffeeProduct>> listOfCoffeeByTypesAvailable = new ArrayList<>();

        List<Function<Session, List<? extends CoffeeProduct>>> databaseOperations = new ArrayList<>();

        for (Class<? extends CoffeeProduct> coffeeClass : coffeeClasses) {
            databaseOperations.add(session -> {
                Query<? extends CoffeeProduct> query = session.createQuery(
                        "SELECT c FROM " + coffeeClass.getSimpleName() + " c WHERE c.van.id IS NULL ", coffeeClass);

                return query.list();
            });
        }

        List<List<? extends CoffeeProduct>> results = DBOperations.executeQueries(databaseOperations);

        if (results!=null){
            listOfCoffeeByTypesAvailable.addAll(results);
        }

        return listOfCoffeeByTypesAvailable;
    }



    @Override
    public List<CoffeeProduct> getAllCoffeeInVanBasedOnPriceAndWeightRatio(Long vanId) {
        return this.getListOfCoffees("ORDER BY c.price / c.weight", new AbstractMap.SimpleEntry<>("van",vanId));
    }

    @Override
    public void delete(CoffeeVan coffeeVan) {

        AbstractMap.SimpleEntry<String, Long> deletePair = new AbstractMap.SimpleEntry<>("van",coffeeVan.getId());

        this.deleteCoffees(deletePair);

        DBOperations.executeTransaction(session -> session.remove(coffeeVan));
    }



}
