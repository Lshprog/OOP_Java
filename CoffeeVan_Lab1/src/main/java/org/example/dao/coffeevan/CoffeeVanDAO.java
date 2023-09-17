package org.example.dao.coffeevan;

import org.example.common.filters.CoffeeFilter;
import org.example.dao.Repository;
import org.example.entities.CoffeeProduct;
import org.example.entities.CoffeeVan;

import java.util.List;

public interface CoffeeVanDAO extends Repository<CoffeeVan, Long> {
    List<CoffeeProduct> getAllCoffeeByVanId(Long vanId);

    List<CoffeeProduct> getAllCoffeeByVanId();

    CoffeeVan getCoffeeVanByName(String name);

    List<CoffeeProduct> getCoffeeByVanIdAndType(Long vanId, List<String> classNames);

    List<CoffeeProduct> getAllCoffeeSortedByParam(Long vanId, String parameter);

    List<CoffeeProduct> getCoffeeBasedOnParameters(Long vanId, CoffeeFilter filter, List<String> classNames);

}
