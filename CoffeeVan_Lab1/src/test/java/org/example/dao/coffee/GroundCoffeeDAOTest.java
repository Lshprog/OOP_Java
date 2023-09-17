package org.example.dao.coffee;

import jakarta.transaction.Transactional;
import org.example.common.CoffeeProductFactory;
import org.example.common.enums.CoffeeState;
import org.example.common.enums.GrindType;
import org.example.common.enums.Intensity;
import org.example.common.enums.RoastLevel;
import org.example.dao.coffeesort.CoffeeSortDAO;
import org.example.dao.coffeesort.CoffeeSortDAOImpl;
import org.example.dao.pack.PackDAO;
import org.example.dao.pack.PackDAOImpl;
import org.example.entities.CoffeeBeans;
import org.example.entities.CoffeeProduct;
import org.example.entities.GroundCoffee;
import org.example.entities.InstantCoffee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class GroundCoffeeDAOTest {

    private final GroundCoffeeDAO daoGroundCoffee = new GroundCoffeeDAOImpl();
    private final PackDAO daoPack = new PackDAOImpl();
    private final CoffeeSortDAO daoCoffeeSort = new CoffeeSortDAOImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSaveGroundCoffee() {

        /*GroundCoffee groundCoffee1 = (GroundCoffee) CoffeeProductFactory.createCoffeeProduct(CoffeeState.GROUND,daoCoffeeSort.findById(5L).get(),
                daoPack.findById(12L).get());
        groundCoffee1.setRoastLevel(RoastLevel.MEDIUM);
        groundCoffee1.setGrindType(GrindType.MEDIUM);
        groundCoffee1.setIntensity(Intensity.STRONG);


        GroundCoffee groundCoffee2 = (GroundCoffee) groundCoffee1.clone();
        GroundCoffee groundCoffee3 = (GroundCoffee) groundCoffee1.clone();
        GroundCoffee groundCoffee4= (GroundCoffee) groundCoffee1.clone();
        GroundCoffee groundCoffee5 = (GroundCoffee) groundCoffee1.clone();

        daoGroundCoffee.update(groundCoffee2);
        daoGroundCoffee.update(groundCoffee3);
        daoGroundCoffee.update(groundCoffee4);
        daoGroundCoffee.update(groundCoffee5);*/


    }

    @Test
    void testPrintOut() {

        List<GroundCoffee> coffees = daoGroundCoffee.getAllByVanId();

        for (GroundCoffee coffeeProduct : coffees){
            System.out.println(coffeeProduct.toString());
        }



    }


}