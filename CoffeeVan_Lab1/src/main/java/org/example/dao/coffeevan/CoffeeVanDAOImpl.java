package org.example.dao.coffeevan;

import org.example.common.filters.CoffeeFilter;
import org.example.common.dboper.DBOperations;
import org.example.common.filters.FilterNode;
import org.example.dao.RepositoryImpl;
import org.example.entities.CoffeeProduct;
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
    public List<CoffeeProduct> getAllCoffeeByVanId(Long vanId) {
        return DBOperations.executeQuery(session -> {
            Query<CoffeeProduct> query = session.createQuery(
                    "SELECT c FROM GroundCoffee c WHERE c.van.id = :vanId " +
                            "UNION " +
                            "SELECT c FROM InstantCoffee c WHERE c.van.id = :vanId " +
                            "UNION " +
                            "SELECT c FROM CoffeeBeans c WHERE c.van.id = :vanId", CoffeeProduct.class);
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
    public List<CoffeeProduct> getCoffeeByVanIdAndType(Long vanId, List<String> classNames) {
        return DBOperations.executeQuery(session -> {
            StringBuilder hql = new StringBuilder("SELECT c FROM ");

            for (String className : classNames) {
                hql.append(className).append(" c");

                if (classNames.indexOf(className) < classNames.size() - 1) {
                    hql.append(" UNION ");
                }
            }

            Query<CoffeeProduct> query = session.createQuery(hql.toString(), CoffeeProduct.class);
            return query.list();
        });
    }

    @Override
    public List<CoffeeProduct> getAllCoffeeSortedByParam(Long vanId, String parameter) {
        return DBOperations.executeQuery(session -> {
            Query<CoffeeProduct> query = session.createQuery(
                    "SELECT c FROM GroundCoffee c WHERE c.van.id = :vanId " +
                            "UNION " +
                            "SELECT c FROM InstantCoffee c WHERE c.van.id = :vanId " +
                            "UNION " +
                            "SELECT c FROM CoffeeBeans c WHERE c.van.id = :vanId" +
                            "ORDER BY c." + parameter, CoffeeProduct.class);
            query.setParameter("vanId", vanId);
            return query.list();
        });
    }

   @Override
   public List<CoffeeProduct> getCoffeeBasedOnParameters(Long vanId, CoffeeFilter filter, List<String> classNames) {
       return DBOperations.executeQuery(session -> {
           StringBuilder hql = new StringBuilder("SELECT c FROM ");

           List<FilterNode> criteriaList = filter.getFilterRanges();

           for(String className : classNames){

               hql.append(className).append(" c WHERE c.van.id = :vanId");

               criteriaList.forEach((criteria) -> {

                   switch (criteria.getCondition()) {
                       case MAX -> {
                           hql.append(" AND c.").append(criteria.getAttrEntity()).append(" >= :").append(criteria.getAttrFilter());
                       }
                       case MIN -> {
                           hql.append(" AND c.").append(criteria.getAttrEntity()).append(" <= :").append(criteria.getAttrFilter());
                       }
                       case EQUAL -> {
                           hql.append(" AND c.").append(criteria.getAttrEntity()).append(" = :").append(criteria.getAttrFilter());
                       }
                       case LIST -> {
                           hql.append(" AND c.").append(criteria.getAttrEntity()).append(" IN (:").append(criteria.getAttrFilter()).append(")");
                       }
                   }
               });

               if(classNames.indexOf(className) != classNames.size() - 1){
                   hql.append(" UNION ");
               }

           }

           Query<CoffeeProduct> query = session.createQuery(hql.toString(), CoffeeProduct.class);

           criteriaList.forEach((criteria) -> {
               query.setParameter(criteria.getAttrFilter(), criteria.getValues());
           });

           query.setParameter("vanId", vanId);

           return query.list();
       });
   }

}
