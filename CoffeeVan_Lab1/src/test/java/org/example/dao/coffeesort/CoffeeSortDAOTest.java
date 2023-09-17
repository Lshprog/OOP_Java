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

        CoffeeSort coffeeSort1 = new CoffeeSort(1L,"Arabica", 40.0, "Brazil");
        CoffeeSort coffeeSort2 = new CoffeeSort(3L,"Robusta", 20.0, "Africa");
        CoffeeSort coffeeSort3 = new CoffeeSort(4L,"Excelsa", 30.0, "Central Africa");
        CoffeeSort coffeeSort4 = new CoffeeSort(5L,"Liberica", 35.0, "Philippines");
        daoCoffeeSort.update(coffeeSort1);
        daoCoffeeSort.update(coffeeSort2);
        daoCoffeeSort.update(coffeeSort3);
        daoCoffeeSort.update(coffeeSort4);

        //assertEquals(daoCoffeeSort.findById(1L).get().getName(), coffeeSort.getName());
    }

}