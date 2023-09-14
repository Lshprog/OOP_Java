package org.example.entities;

import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class CoffeeBeans extends CoffeeProduct implements Serializable {


    public CoffeeBeans(CoffeeSort coffeeSort, Pack pack) {
        super(coffeeSort, pack);
    }

    public CoffeeBeans() {

    }
}
