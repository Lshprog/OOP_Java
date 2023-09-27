package org.example.services;

import org.example.entities.CoffeeProduct;
import org.example.entities.CoffeeSort;
import org.example.entities.Pack;

import java.util.List;

public interface CoffeeService {

    List<CoffeeProduct> getAllCoffee();

    List<Pack> getAllPackTypes();

    List<CoffeeSort> getAllCoffeeSorts();

    CoffeeProduct getCoffeeProductById(Long id);

    Pack getPackProductById(Long id);

    CoffeeSort getCoffeeSortProductById(Long id);

    void save(CoffeeProduct coffeeProduct);

    void delete(CoffeeProduct coffeeProduct);

    void update(CoffeeProduct coffeeProduct);

    void save(Pack coffeePack);

    void delete(Pack coffeePack);

    void update(Pack coffeePack);

    void save(CoffeeSort coffeeSort);

    void delete(CoffeeSort coffeeSort);

    void update(CoffeeSort coffeeSort);


}
