package org.example.dao.coffee;

import org.example.common.CoffeeProductFactory;
import org.example.common.enums.CoffeeState;
import org.example.common.enums.RoastLevel;
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

        /*CoffeeBeans coffeeBeans1 = (CoffeeBeans) CoffeeProductFactory.createCoffeeProduct(CoffeeState.BEANS,daoCoffeeSort.findById(3L).get(),
                                                                                    daoPack.findById(11L).get());
        coffeeBeans1.setRoastLevel(RoastLevel.MEDIUM);

        CoffeeBeans coffeeBeans2 = (CoffeeBeans) coffeeBeans1.clone();
        CoffeeBeans coffeeBeans3 = (CoffeeBeans) coffeeBeans1.clone();
        CoffeeBeans coffeeBeans4= (CoffeeBeans) coffeeBeans1.clone();
        CoffeeBeans coffeeBeans5 = (CoffeeBeans) coffeeBeans1.clone();

        daoCoffeeBeans.save(coffeeBeans2);
        daoCoffeeBeans.save(coffeeBeans3);
        daoCoffeeBeans.save(coffeeBeans4);
        daoCoffeeBeans.save(coffeeBeans5);*/

        CoffeeBeans coffeeBeans = daoCoffeeBeans.findById(12L).get();
        coffeeBeans.setRoastLevel(RoastLevel.DARK);
        daoCoffeeBeans.update(coffeeBeans);


    }



}