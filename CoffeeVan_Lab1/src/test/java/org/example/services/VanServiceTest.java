package org.example.services;

import org.example.common.enums.Flavor;
import org.example.common.enums.RoastLevel;
import org.example.common.filters.CoffeeFilter;
import org.example.entities.CoffeeBeans;
import org.example.entities.CoffeeProduct;
import org.example.entities.CoffeeVan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

        CoffeeVan coffeeVan = vanService.getCoffeeVanByName("van1_test");

        CoffeeFilter coffeeFilter = new CoffeeFilter();
        //coffeeFilter.setMaxPrice(4.54);
        coffeeFilter.setMaxPrice(5.54);
        coffeeFilter.setMinPrice(5.0);


        List<String> flavors = new ArrayList<>();
        flavors.add(Flavor.HAZELNUT.name());
        coffeeFilter.setFlavor(flavors);


        // Setting sort which by the id 3 which is Robusta, Africa
        coffeeFilter.setSortId(3L);

        List<String> classNames = new ArrayList<>();
        classNames.add("InstantCoffee");
        classNames.add("GroundCoffee");
        classNames.add("CoffeeBeans");


        List<CoffeeProduct> coffeeProducts = vanService.getCoffeeInVanBasedOnParameters(coffeeVan.getId(),coffeeFilter,classNames);

        for(CoffeeProduct coffeeProduct : coffeeProducts){
            System.out.println(coffeeProduct.toString());
        }

    }

    @Test
    void loadCoffeeVanAutomaticallyBasedOnBudget() {

        CoffeeVan van = vanService.loadCoffeeVanAutomaticallyBasedOnBudget(vanService.getCoffeeVanById(4L),30.0);


    }

    @Test
    void loadCoffeeProductsByIdToVan() {

    }


}