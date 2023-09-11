package org.example.dao.coffeevan;

import jakarta.transaction.Transactional;
import org.example.entities.CoffeeVan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeVanDAOTest {

    private final CoffeeVanDAO coffeeVanDAO = new CoffeeVanDAOImpl(CoffeeVan.class);

    @BeforeEach
    void setUp() {
    }

    @Test
    @Transactional
    void testSaveCoffeeVan() {

        CoffeeVan coffeeVan = new CoffeeVan("name1",18.1,23.0);

        coffeeVanDAO.save(coffeeVan);

        CoffeeVan coffeeVan1 = coffeeVanDAO.getCoffeeVanByName("name1");


    }



}