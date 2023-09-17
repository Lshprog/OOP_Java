package org.example.entities;

import jakarta.persistence.Entity;
import org.example.common.enums.RoastLevel;

import java.io.Serializable;

@Entity
public class CoffeeBeans extends CoffeeProduct implements Serializable {


    public CoffeeBeans(CoffeeSort coffeeSort, Pack pack) {
        super(coffeeSort, pack);
        this.setDensity(0.75);
        this.setUpAttrs();
    }

    public CoffeeBeans(CoffeeSort sort, Pack pack, CoffeeVan van) {
        super(sort, pack, van);
        this.setDensity(0.75);
        this.setUpAttrs();
    }

    public CoffeeBeans(Long id, double price, double volume, double weight, double density, RoastLevel roastLevel, CoffeeSort sort, Pack pack, CoffeeVan van) {
        super(id, price, volume, weight, density, roastLevel, sort, pack, van);
    }

    public CoffeeBeans() {

    }

    public CoffeeBeans(CoffeeBeans coffeeBeans) {
        super(coffeeBeans);
    }

    @Override
    public CoffeeProduct clone() {
        return new CoffeeBeans(this);
    }


}
