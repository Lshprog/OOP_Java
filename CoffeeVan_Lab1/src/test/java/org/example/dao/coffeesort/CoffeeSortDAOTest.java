package org.example.dao.coffeesort;

import org.example.entities.CoffeeSort;
import org.example.entities.Pack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeSortDAOTest {

    private final CoffeeSortDAO daoCoffeeSort = new CoffeeSortDAOImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("testSaveCoffeeSort successful")
    void testSaveCoffeeSort() {

        CoffeeSort coffeeSort = new CoffeeSort("Arabica", 1.1, "Columbia");

        daoCoffeeSort.save(coffeeSort);

        assertEquals(daoCoffeeSort.findById(1L).get().getName(), coffeeSort.getName());
    }

}