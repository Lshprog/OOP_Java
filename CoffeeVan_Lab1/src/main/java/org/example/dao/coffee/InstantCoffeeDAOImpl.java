package org.example.dao.coffee;

import org.example.entities.InstantCoffee;

public class InstantCoffeeDAOImpl extends CoffeeProductDAOImpl<InstantCoffee> implements InstantCoffeeDAO{
    public InstantCoffeeDAOImpl() {
        super(InstantCoffee.class);
    }

}
