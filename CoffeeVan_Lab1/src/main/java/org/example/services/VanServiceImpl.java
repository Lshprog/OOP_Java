package org.example.services;

import org.example.dao.coffeevan.CoffeeVanDAO;
import org.example.dao.coffeevan.CoffeeVanDAOImpl;
import org.example.entities.CoffeeBeans;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToDoubleFunction;

public class VanServiceImpl implements VanService{

    private final static CoffeeVanDAO daoCoffeeVan = new CoffeeVanDAOImpl();


    @Override
    public List<CoffeeBeans> sortAllCoffeeBasedOnPriceAndWeightRatio(Long vanId) {

        List<CoffeeBeans> coffeeBeansList = daoCoffeeVan.getAllCoffeeByVanId(vanId);

        Comparator<CoffeeBeans> ratioComparator = Comparator.comparingDouble(coffee -> coffee.getPrice() / coffee.getWeight());

        coffeeBeansList.sort(ratioComparator.reversed());

        return coffeeBeansList;
    }

    // Probably need to change names of functions to make code more consistent
    @Override
    public List<CoffeeBeans> sortCoffeeBasedOnParameter(Long vanId, String parameter) {
        return daoCoffeeVan.getAllCoffeeSortedByParam(vanId, parameter);
    }


    // Need to implement function that takes multiple different parameters then provide optimized
    // way to return the final list.
    @Override
    public List<CoffeeBeans> getCoffeeBasedOnParameters(Long vanId, List<String> parameter) {
        return null;
    }




}
