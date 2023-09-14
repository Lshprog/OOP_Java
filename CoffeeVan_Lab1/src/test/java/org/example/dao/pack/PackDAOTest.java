package org.example.dao.pack;

import jakarta.transaction.Transactional;
import org.example.entities.CoffeeVan;
import org.example.entities.Pack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PackDAOTest {

    private final PackDAO daoPack = new PackDAOImpl();

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("testSaveCoffeeVan successful")
    void testSaveCoffeeVan() {

        Pack pack = new Pack(1.1, 2.2, 3.3, "Perfect");

        daoPack.save(pack);

        assertEquals(daoPack.findById(1L).get().getPrice(), pack.getPrice());
    }

    @Test
    void testCheckCoffeeVan() {



    }



}