package org.example.dao.coffeevan;

import org.example.dao.Repository;
import org.example.entities.CoffeeBeans;
import org.example.entities.CoffeeVan;

import java.util.List;

public interface CoffeeVanDAO extends Repository<CoffeeVan, Long> {
    List<CoffeeBeans> getAllCoffeeByVanId(long id);

}
