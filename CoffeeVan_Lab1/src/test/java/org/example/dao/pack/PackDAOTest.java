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

        //Pack pack = new Pack(1.1, 2.2, 3.3, "Perfect");

        /*Pack pack1 = new Pack(2.0, 0.250,0.030, "250g package, tight");
        Pack pack2 = new Pack(3.0, 0.250, 0.040, "250g package, ultra-tight");
        Pack pack3 = new Pack(1.8, 0.250, 0.025, "250g package, thin");

        daoPack.update(pack1);
        daoPack.update(pack2);
        daoPack.update(pack3);*/

        //assertEquals(daoPack.findById(1L).get().getPrice(), pack.getPrice());
    }

    @Test
    void testCheckCoffeeVan() {



    }



}