package org.example.services;

import org.example.entities.CoffeeProduct;

import java.util.List;

public interface CoffeeService {

    List<CoffeeProduct> getAllCoffee();

    void save(CoffeeProduct coffeeProduct);

    void delete(CoffeeProduct coffeeProduct);




}
