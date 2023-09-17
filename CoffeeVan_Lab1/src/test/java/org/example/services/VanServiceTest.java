package org.example.services;

import org.example.entities.CoffeeProduct;
import org.example.entities.CoffeeVan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VanServiceTest {

    private final VanService vanService = new VanServiceImpl();


    @BeforeEach
    void setUp() {

    }

    @Test
    void sortCoffeeListBasedOnParameter() {
        //vanService.sortCoffeeListBasedOnParameter()
    }

    @Test
    void sortCoffeeListBasedOnParameters() {
    }

    @Test
    void getAllCoffeeAvailableToBeAddedToVan() {
    }

    @Test
    void getAllCoffeeInVanBasedOnPriceAndWeightRatio() {
    }

    @Test
    void getAllCoffeeInVan() {

        List<CoffeeProduct> coffees = vanService.getAllCoffeeAvailableToBeAddedToVan();

        for (CoffeeProduct coffeeProduct : coffees){
            System.out.println(coffeeProduct.toString());
        }

    }

    @Test
    void getAllCoffeeInVanSortedByParameter() {
    }

    @Test
    void getCoffeeInVanBasedOnParameters() {
    }

    @Test
    void loadCoffeeVanAutomaticallyBasedOnBudget() {

        CoffeeVan van = vanService.loadCoffeeVanAutomaticallyBasedOnBudget(vanService.getCoffeeVanById(2L),30.0);

        System.out.println(van.toString());

    }

    @Test
    void loadCoffeeProductsByIdToVan() {
    }
}