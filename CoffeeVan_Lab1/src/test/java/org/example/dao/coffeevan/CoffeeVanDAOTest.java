package org.example.dao.coffeevan;

import jakarta.transaction.Transactional;
import org.example.common.enums.Flavor;
import org.example.common.filters.CoffeeFilter;
import org.example.entities.CoffeeProduct;
import org.example.entities.CoffeeVan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeVanDAOTest {

    private final CoffeeVanDAO coffeeVanDAO = new CoffeeVanDAOImpl(CoffeeVan.class);

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSaveCoffeeVan() {

        //CoffeeVan coffeeVan = new CoffeeVan("van1_test",18.1,23.0);
//
        //coffeeVanDAO.save(coffeeVan);

        /*CoffeeVan coffeeVan = coffeeVanDAO.getCoffeeVanByName("van1_test");

        coffeeVanDAO.delete(coffeeVan);*/


    }


    @Test
    void getAllCoffeeByVanId() {

        List<CoffeeProduct> coffeeProducts = coffeeVanDAO.getAllCoffeeByVanId(1L);

        for(CoffeeProduct coffeeProduct : coffeeProducts){
            System.out.println(coffeeProduct.toString());
        }

    }

    @Test
    void getAllCoffeeNotInVan() {

        List<CoffeeProduct> coffeeProducts = coffeeVanDAO.getAllCoffeeByVanId(1L);

        for(CoffeeProduct coffeeProduct : coffeeProducts){
            System.out.println(coffeeProduct.toString());
        }

    }

    @Test
    void getCoffeeVanByName() {

        CoffeeVan coffeeVan = coffeeVanDAO.getCoffeeVanByName("name");

        System.out.println(coffeeVan.getName());

    }

    @Test
    void getCoffeeByVanIdAndType() {
    }

    @Test
    void getAllCoffeeSortedByParam() {

        List<CoffeeProduct> coffeeProducts = coffeeVanDAO.getAllCoffeeSortedByParam(1L, "price");

        for(CoffeeProduct coffeeProduct : coffeeProducts){
            System.out.println(coffeeProduct.toString());
        }

    }

    @Test
    void getCoffeeBasedOnParameters() {
        CoffeeVan coffeeVan = coffeeVanDAO.getCoffeeVanByName("van1_test");

        CoffeeFilter coffeeFilter = new CoffeeFilter();
        //coffeeFilter.setMaxPrice(4.54);
        coffeeFilter.setMaxPrice(5.54);
        coffeeFilter.setMinPrice(5.0);


        List<String> flavors = new ArrayList<>();
        flavors.add(Flavor.HAZELNUT.name());
        coffeeFilter.setFlavor(flavors);


        // Setting sort which by the id 3 which is Robusta, Africa
        List<String> sortids = new ArrayList<>();
        sortids.add("3");
        coffeeFilter.setSortId(sortids);

        List<String> classNames = new ArrayList<>();
        classNames.add("InstantCoffee");
        classNames.add("GroundCoffee");
        classNames.add("CoffeeBeans");


        List<CoffeeProduct> coffeeProducts = coffeeVanDAO.getCoffeeBasedOnParameters(coffeeVan.getId(),coffeeFilter,classNames);

        for(CoffeeProduct coffeeProduct : coffeeProducts){
            System.out.println(coffeeProduct.toString());
        }


    }

}