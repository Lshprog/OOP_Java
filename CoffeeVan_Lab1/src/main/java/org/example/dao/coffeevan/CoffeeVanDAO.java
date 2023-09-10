package org.example.dao.coffeevan;

import org.example.entities.Coffee;
import org.example.entities.CoffeeVan;

import java.util.List;

public interface CoffeeVanDAO {

    void save(CoffeeVan van);
    void delete(CoffeeVan van);
    CoffeeVan getById(long id);
    List<CoffeeVan> getAll();
    List<Coffee> getAllCoffeeByVanId(long id);

}
