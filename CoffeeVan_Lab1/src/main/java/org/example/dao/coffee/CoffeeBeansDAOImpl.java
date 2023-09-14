package org.example.dao.coffee;

import org.example.entities.CoffeeBeans;
import org.example.entities.GroundCoffee;
import org.example.entities.InstantCoffee;

public class CoffeeBeansDAOImpl extends CoffeeProductDAOImpl<CoffeeBeans> implements CoffeeBeansDAO{

    public CoffeeBeansDAOImpl() {
        super(CoffeeBeans.class);
    }

}
