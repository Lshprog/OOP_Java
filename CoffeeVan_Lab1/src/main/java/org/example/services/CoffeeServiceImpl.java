package org.example.services;

import org.example.dao.coffee.*;
import org.example.entities.CoffeeBeans;
import org.example.entities.CoffeeProduct;
import org.example.entities.GroundCoffee;
import org.example.entities.InstantCoffee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CoffeeServiceImpl implements CoffeeService{

    public CoffeeServiceImpl() {
    }

    private final static CoffeeBeansDAO daoCoffeeBeans = new CoffeeBeansDAOImpl();

    private final static GroundCoffeeDAO daoGroundCoffee = new GroundCoffeeDAOImpl();

    private final static InstantCoffeeDAO daoInstantCoffee = new InstantCoffeeDAOImpl();

    @Override
    public List<CoffeeProduct> getAllCoffee() {
        List<CoffeeProduct> allCoffee = new ArrayList<>();
        allCoffee.addAll(daoCoffeeBeans.findAll());
        allCoffee.addAll(daoInstantCoffee.findAll());
        allCoffee.addAll(daoGroundCoffee.findAll());
        return allCoffee;
    }

    @Override
    public void save(CoffeeProduct coffeeProduct) {
        if (coffeeProduct.getClass().getSimpleName().equals("CoffeeBeans")){
            this.save((CoffeeBeans) coffeeProduct);
        }
        else if (coffeeProduct.getClass().getSimpleName().equals("InstantCoffee")) {
            this.save((InstantCoffee) coffeeProduct);
        }
        else {
            this.save((GroundCoffee) coffeeProduct);
        }
    }

    @Override
    public void delete(CoffeeProduct coffeeProduct) {
        if (coffeeProduct.getClass().getSimpleName().equals("CoffeeBeans")){
            this.delete((CoffeeBeans) coffeeProduct);
        }
        else if (coffeeProduct.getClass().getSimpleName().equals("InstantCoffee")) {
            this.delete((InstantCoffee) coffeeProduct);
        }
        else {
            this.delete((GroundCoffee) coffeeProduct);
        }
    }

    @Override
    public void update(CoffeeProduct coffeeProduct) {
        if (coffeeProduct.getClass().getSimpleName().equals("CoffeeBeans")){
            this.update((CoffeeBeans) coffeeProduct);
        }
        else if (coffeeProduct.getClass().getSimpleName().equals("InstantCoffee")) {
            this.update((InstantCoffee) coffeeProduct);
        }
        else {
            this.update((GroundCoffee) coffeeProduct);
        }
    }


    private void save(CoffeeBeans coffeeProduct) {
        daoCoffeeBeans.save(coffeeProduct);
    }


    private void save(InstantCoffee coffeeProduct) {
        daoInstantCoffee.save(coffeeProduct);
    }


    private void save(GroundCoffee coffeeProduct) {
        daoGroundCoffee.save(coffeeProduct);
    }


    private void delete(CoffeeBeans coffeeProduct) {
        daoCoffeeBeans.delete(coffeeProduct);
    }


    private void delete(InstantCoffee coffeeProduct) {
        daoInstantCoffee.delete(coffeeProduct);
    }


    private void delete(GroundCoffee coffeeProduct) {
        daoGroundCoffee.delete(coffeeProduct);
    }


    private void update(CoffeeBeans coffeeProduct) {
        daoCoffeeBeans.update(coffeeProduct);
    }


    private void update(InstantCoffee coffeeProduct) {
        daoInstantCoffee.update(coffeeProduct);
    }


    private void update(GroundCoffee coffeeProduct) {
        daoGroundCoffee.update(coffeeProduct);
    }

}
