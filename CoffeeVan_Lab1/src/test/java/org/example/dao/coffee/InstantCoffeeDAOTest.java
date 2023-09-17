package org.example.dao.coffee;

import org.example.common.CoffeeProductFactory;
import org.example.common.enums.*;
import org.example.dao.coffeesort.CoffeeSortDAO;
import org.example.dao.coffeesort.CoffeeSortDAOImpl;
import org.example.dao.pack.PackDAO;
import org.example.dao.pack.PackDAOImpl;
import org.example.entities.GroundCoffee;
import org.example.entities.InstantCoffee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstantCoffeeDAOTest {

    private final InstantCoffeeDAO daoInstantCoffee = new InstantCoffeeDAOImpl();
    private final PackDAO daoPack = new PackDAOImpl();
    private final CoffeeSortDAO daoCoffeeSort = new CoffeeSortDAOImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSaveInstantCoffeeDAO() {

        InstantCoffee instantCoffee1 = (InstantCoffee) CoffeeProductFactory.createCoffeeProduct(CoffeeState.INSTANT,daoCoffeeSort.findById(5L).get(),
                daoPack.findById(12L).get());
        instantCoffee1.setRoastLevel(RoastLevel.MEDIUM);
        instantCoffee1.setDissolvability(Dissolvability.MEDIUM);
        instantCoffee1.setFlavor(Flavor.HAZELNUT);


        InstantCoffee instantCoffee2 = (InstantCoffee) instantCoffee1.clone();
        InstantCoffee instantCoffee3 = (InstantCoffee) instantCoffee1.clone();
        InstantCoffee instantCoffee4 = (InstantCoffee) instantCoffee1.clone();
        InstantCoffee instantCoffee5 = (InstantCoffee) instantCoffee1.clone();

        daoInstantCoffee.update(instantCoffee2);
        daoInstantCoffee.update(instantCoffee3);
        daoInstantCoffee.update(instantCoffee4);
        daoInstantCoffee.update(instantCoffee5);

    }
}