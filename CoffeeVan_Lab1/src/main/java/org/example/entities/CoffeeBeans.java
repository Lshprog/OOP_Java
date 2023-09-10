package org.example.entities;

import jakarta.persistence.Entity;

@Entity
public class CoffeeBeans extends Coffee{

    public CoffeeBeans(CoffeeSort sort, Pack pack) {
        super(sort, pack);
    }

    public CoffeeBeans(Long id, double price, double volume, double weight, String roastLevel, CoffeeSort sort, Pack pack) {
        super(id, price, volume, weight, roastLevel, sort, pack);
    }

    public CoffeeBeans() {
    }
}
