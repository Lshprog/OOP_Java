package org.example.dao.coffee;

import org.example.common.CoffeeProductFactory;
import org.example.common.enums.CoffeeState;
import org.example.dao.coffeesort.CoffeeSortDAO;
import org.example.dao.coffeesort.CoffeeSortDAOImpl;
import org.example.dao.pack.PackDAO;
import org.example.dao.pack.PackDAOImpl;
import org.example.entities.CoffeeBeans;
import org.example.entities.CoffeeSort;
import org.example.entities.Pack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeBeansDAOTest {

    private final CoffeeBeansDAO daoCoffeeBeans = new CoffeeBeansDAOImpl();
    private final PackDAO daoPack = new PackDAOImpl();
    private final CoffeeSortDAO daoCoffeeSort = new CoffeeSortDAOImpl();

    @BeforeEach
    void setUp() {

    }

    @Test
    void testToSaveCoffeeBeansObj(){

        /*CoffeeBeans coffeeBeans = new CoffeeBeans(daoCoffeeSort.findById(1L).get(), daoPack.findById(1L).get());
        daoCoffeeBeans.save(coffeeBeans);*/

        //CoffeeBeans coffeeBeans1 = CoffeeProductFactory.createCoffeeProduct(CoffeeState.BEANS,daoCoffeeSort.findById());



    }



}