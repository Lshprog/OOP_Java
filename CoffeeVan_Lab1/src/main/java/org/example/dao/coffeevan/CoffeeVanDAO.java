package org.example.dao.coffeevan;

import org.example.common.CoffeeFilter;
import org.example.dao.Repository;
import org.example.entities.CoffeeBeans;
import org.example.entities.CoffeeVan;

import java.util.List;

public interface CoffeeVanDAO extends Repository<CoffeeVan, Long> {
    List<CoffeeBeans> getAllCoffeeByVanId(Long vanId);

    CoffeeVan getCoffeeVanByName(String name);

    List<CoffeeBeans> getCoffeeByVanIdAndType(Long vanId, List<String> classNames);

    List<CoffeeBeans> getAllCoffeeSortedByParam(Long vanId, String parameter);

    List<CoffeeBeans> getCoffeeBasedOnParameters(Long vanId, CoffeeFilter filter, List<String> classNames);

}
