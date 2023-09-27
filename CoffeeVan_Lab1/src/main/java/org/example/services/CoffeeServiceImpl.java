package org.example.services;

import org.example.dao.coffee.*;
import org.example.dao.coffeesort.CoffeeSortDAO;
import org.example.dao.coffeesort.CoffeeSortDAOImpl;
import org.example.dao.pack.PackDAO;
import org.example.dao.pack.PackDAOImpl;
import org.example.entities.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class CoffeeServiceImpl implements CoffeeService{

    public CoffeeServiceImpl() {
    }

    private final static CoffeeBeansDAO daoCoffeeBeans = new CoffeeBeansDAOImpl();

    private final static GroundCoffeeDAO daoGroundCoffee = new GroundCoffeeDAOImpl();

    private final static InstantCoffeeDAO daoInstantCoffee = new InstantCoffeeDAOImpl();

    private final static PackDAO daoPack = new PackDAOImpl();

    private final static CoffeeSortDAO daoCoffeeSort = new CoffeeSortDAOImpl();

    @Override
    public List<CoffeeProduct> getAllCoffee() {
        List<CoffeeProduct> allCoffee = new ArrayList<>();
        allCoffee.addAll(daoCoffeeBeans.findAll());
        allCoffee.addAll(daoInstantCoffee.findAll());
        allCoffee.addAll(daoGroundCoffee.findAll());
        return allCoffee;
    }

    @Override
    public List<Pack> getAllPackTypes() {
        return daoPack.findAll();
    }

    @Override
    public List<CoffeeSort> getAllCoffeeSorts() {
        return daoCoffeeSort.findAll();
    }

    @Override
    public CoffeeProduct getCoffeeProductById(Long id) {
        Optional<? extends CoffeeProduct> coffeeOPT = daoGroundCoffee.findById(id);
        if(coffeeOPT.isPresent()){
            return coffeeOPT.get();
        }
        coffeeOPT = daoCoffeeBeans.findById(id);
        if(coffeeOPT.isPresent()){
            return coffeeOPT.get();
        }
        coffeeOPT = daoInstantCoffee.findById(id);
        return coffeeOPT.orElse(null);

    }

    @Override
    public Pack getPackProductById(Long id) {
        return daoPack.findById(id).orElse(null);
    }

    @Override
    public CoffeeSort getCoffeeSortProductById(Long id) {
        return daoCoffeeSort.findById(id).orElse(null);
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

    @Override
    public void save(Pack coffeePack) {
        daoPack.save(coffeePack);
    }

    @Override
    public void delete(Pack coffeePack) {
        daoPack.delete(coffeePack);
    }

    @Override
    public void update(Pack coffeePack) {
        daoPack.update(coffeePack);
    }

    @Override
    public void save(CoffeeSort coffeeSort) {
        daoCoffeeSort.save(coffeeSort);
    }

    @Override
    public void delete(CoffeeSort coffeeSort) {
        daoCoffeeSort.delete(coffeeSort);
    }

    @Override
    public void update(CoffeeSort coffeeSort) {
        daoCoffeeSort.update(coffeeSort);
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
