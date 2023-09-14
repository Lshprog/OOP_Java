package org.example.dao.coffeevan;

import org.example.common.CoffeeFilter;
import org.example.common.dboper.DBOperations;
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

            for(String className : classNames){
                String hqlpart = className + " c WHERE c.van.id = :vanId";

                if (filter.getMinPrice() != null) {
                    hqlpart += " AND c.price >= :minPrice";
                }

                if (filter.getMaxPrice() != null) {
                    hqlpart += " AND c.price <= :maxPrice";
                }

                if (filter.getMinVolume() != null) {
                    hqlpart += " AND c.volume >= :minVolume";
                }

                if (filter.getMaxVolume() != null) {
                    hqlpart += " AND c.volume <= :maxVolume";
                }

                if (filter.getMinWeight() != null) {
                    hqlpart += " AND c.weight >= :minWeight";
                }

                if (filter.getMaxWeight() != null) {
                    hqlpart += " AND c.weight <= :maxWeight";
                }

                if (filter.getRoastLevel() != null) {
                    hqlpart += " AND c.roastLevel = :roastLevel";
                }

                if (filter.getDissolvability() != null && className.equals("InstantCoffee")) {
                    hqlpart += " AND ((type(c) = InstantCoffee AND c.dissolvability = :dissolvability) OR (type(c) = CoffeeBeans)) OR (type(c) == GroundCoffee)";
                }

                if (filter.getFlavor() != null && className.equals("InstantCoffee")) {
                    hqlpart += " AND ((type(c) = InstantCoffee AND c.flavor = :flavor) OR (type(c) = CoffeeBeans)) OR (type(c) == GroundCoffee)";
                }

                if (filter.getGrindType() != null && className.equals("GroundCoffee")) {
                    hqlpart += " AND ((type(c) = GroundCoffee AND c.grindType = :grindType) OR (type(c) = CoffeeBeans)) OR (type(c) == InstantCoffee)";
                }

                if (filter.getIntensity() != null && className.equals("GroundCoffee")) {
                    hqlpart += " AND ((type(c) = GroundCoffee AND c.intensity = :intensity) OR (type(c) = CoffeeBeans)) OR (type(c) == InstantCoffee)";
                }

                if(classNames.indexOf(className) != classNames.size() - 1){
                    hqlpart += " UNION ";
                }

                hql.append(hqlpart);
            }

            Query<CoffeeProduct> query = session.createQuery(hql.toString(), CoffeeProduct.class);

            if (filter.getMinPrice() != null) {
                query.setParameter("minPrice", filter.getMinPrice());
            }

            if (filter.getMaxPrice() != null) {
                query.setParameter("maxPrice", filter.getMaxPrice());
            }

            if (filter.getMinVolume() != null) {
                query.setParameter("minVolume", filter.getMinVolume());
            }

            if (filter.getMaxVolume() != null) {
                query.setParameter("maxVolume", filter.getMaxVolume());
            }

            if (filter.getMinWeight() != null) {
                query.setParameter("minWeight", filter.getMinWeight());
            }

            if (filter.getMaxWeight() != null) {
                query.setParameter("maxWeight", filter.getMaxWeight());
            }

            if (filter.getRoastLevel() != null) {
                query.setParameter("roastLevel", filter.getRoastLevel());
            }

            if (filter.getDissolvability() != null && classNames.contains("InstantCoffee")) {
                query.setParameter("dissolvability", filter.getDissolvability());
            }

            if (filter.getFlavor() != null && classNames.contains("InstantCoffee")) {
                query.setParameter("flavor", filter.getFlavor());
            }

            if (filter.getGrindType() != null && classNames.contains("GroundCoffee")) {
                query.setParameter("grindType", filter.getGrindType());
            }

            if (filter.getIntensity() != null && classNames.contains("GroundCoffee")) {
                query.setParameter("intensity", filter.getIntensity());
            }

            query.setParameter("vanId", vanId);

            return query.list();
        });
    }

}
