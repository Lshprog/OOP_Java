package org.example.dao.coffeevan;

import org.example.common.TypeHelper;
import org.example.common.enums.Condition;
import org.example.common.filters.CoffeeFilter;
import org.example.common.dboper.DBOperations;
import org.example.common.filters.FilterNode;
import org.example.dao.ExtraRepos;
import org.example.entities.*;
import org.hibernate.query.Query;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoffeeVanDAOImpl extends ExtraRepos<CoffeeVan> implements CoffeeVanDAO {

    private final List<Class<? extends CoffeeProduct>> coffeeClasses = new ArrayList<>();

    private void setUp() {
        coffeeClasses.add(GroundCoffee.class);
        coffeeClasses.add(InstantCoffee.class);
        coffeeClasses.add(CoffeeBeans.class);
    }

    public CoffeeVanDAOImpl(Class<CoffeeVan> entityClass) {
        super(entityClass);
        this.setUp();
    }

    public CoffeeVanDAOImpl() {
        super(CoffeeVan.class);
        this.setUp();
    }

    @Override
    public List<CoffeeProduct> getAllCoffeeByVanId(Long vanId) {
        List<CoffeeProduct> finalCoffees = new ArrayList<>();

        for (Class<? extends CoffeeProduct> coffeeClass : coffeeClasses){
            finalCoffees.addAll(Objects.requireNonNull(DBOperations.executeQuery(session -> {
                Query<? extends CoffeeProduct> query = session.createQuery(
                        "SELECT c FROM " + coffeeClass.getSimpleName() + " c WHERE c.van.id = :vanId ", coffeeClass);
                query.setParameter("vanId", vanId);
                return query.list();
            })));
        }


        return finalCoffees;

    }

    @Override
    public List<CoffeeProduct> getAllCoffeeByVanId() {
        List<CoffeeProduct> finalCoffees = new ArrayList<>();

        for (Class<? extends CoffeeProduct> coffeeClass : coffeeClasses){
            finalCoffees.addAll(Objects.requireNonNull(DBOperations.executeQuery(session -> {
                Query<? extends CoffeeProduct> query = session.createQuery(
                        "SELECT c FROM " + coffeeClass.getSimpleName() + " c WHERE c.van.id IS NULL ", coffeeClass);
                return query.list();
            })));
        }


        return finalCoffees;
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
        List<CoffeeProduct> finalCoffees = new ArrayList<>();

        for (Class<? extends CoffeeProduct> coffeeClass : coffeeClasses){
            finalCoffees.addAll(Objects.requireNonNull(DBOperations.executeQuery(session -> {
                Query<? extends CoffeeProduct> query = session.createQuery(
                        "SELECT c FROM " + coffeeClass.getSimpleName() + " c WHERE c.van.id = :vanId "+ "ORDER BY c." + parameter, coffeeClass);
                query.setParameter("vanId", vanId);
                return query.list();
            })));
        }


        return finalCoffees;
    }

   @Override
   public List<CoffeeProduct> getCoffeeBasedOnParameters(Long vanId, CoffeeFilter filter, List<String> classNames) {

       List<CoffeeProduct> finalCoffees = new ArrayList<>();

       for(String className : classNames) {
           finalCoffees.addAll(Objects.requireNonNull(DBOperations.executeQuery(session -> {
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
                       case LIST -> {
                           if(TypeHelper.validateCombinationClasses(criteria.getDatatype(),className)){
                               hql.append(" AND c.").append(criteria.getAttrEntity()).append(" IN (:").append(criteria.getAttrFilter()).append(")");
                           }
                       }
                   }
               });


               Query<CoffeeProduct> query = session.createQuery(hql.toString(), CoffeeProduct.class);

               criteriaList.forEach((criteria) -> {
                   if(TypeHelper.validateCombinationClasses(criteria.getDatatype(),className)){
                       query.setParameter(criteria.getAttrFilter(), TypeHelper.convertToList(criteria.getValues(), criteria.getDatatype()));
                   }
               });

               query.setParameter("vanId", vanId);

               return query.list();
           })));
       }

       return finalCoffees;
   }

    @Override
    public List<List<? extends CoffeeProduct>> getListOfCoffeeByTypesAvailable() {
        List<List<? extends CoffeeProduct>> listOfCoffeeByTypesAvailable = new ArrayList<>();

        for (Class<? extends CoffeeProduct> coffeeClass : coffeeClasses){
            List<? extends CoffeeProduct> coffeeList = DBOperations.executeQuery(session -> {
                Query<? extends CoffeeProduct> query = session.createQuery(
                        "SELECT c FROM " + coffeeClass.getSimpleName() + " c WHERE c.van.id IS NULL ", coffeeClass);

                return query.list();
            });

            listOfCoffeeByTypesAvailable.add(coffeeList);
        }

        return listOfCoffeeByTypesAvailable;
    }


    @Override
    public List<CoffeeProduct> getAllCoffeeInVanBasedOnPriceAndWeightRatio() {
        List<CoffeeProduct> finalCoffees = new ArrayList<>();

        for (Class<? extends CoffeeProduct> coffeeClass : coffeeClasses) {
            finalCoffees.addAll(Objects.requireNonNull(DBOperations.executeQuery(session -> {
                Query<CoffeeProduct> query = session.createQuery(
                        "SELECT c FROM " + coffeeClass.getSimpleName() + " c WHERE c.van.id IS NULL " +
                                "ORDER BY c.price / c.weight", CoffeeProduct.class);
                return query.list();
            })));
        }

        return finalCoffees;
    }

    @Override
    public void delete(CoffeeVan coffeeVan) {

        AbstractMap.SimpleEntry<String, Long> deletePair = new AbstractMap.SimpleEntry<>("van",coffeeVan.getId());

        this.deleteCoffees(deletePair);

        DBOperations.executeTransaction(session -> session.remove(coffeeVan));
    }

}
