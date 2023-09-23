package org.example.services;

import org.example.entities.CoffeeProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeServiceImplTest {

    private final CoffeeService coffeeService = new CoffeeServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAllCoffee() {

        List<CoffeeProduct> coffeeProducts = coffeeService.getAllCoffee();

        for (CoffeeProduct coffeeProduct : coffeeProducts){
            System.out.println(coffeeProduct.toString());
        }

    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}