package org.example.services;

import org.example.entities.CoffeeBeans;

import java.util.Comparator;
import java.util.List;

public class VanServiceImpl implements VanService{

    @Override
    public List<CoffeeBeans> getAllCoffeeBasedOnPriceAndWeightRatio(List<CoffeeBeans> coffeeBeansList) {

        Comparator<CoffeeBeans> ratioComparator = Comparator.comparingDouble(coffee -> coffee.getPrice() / coffee.getWeight());

        coffeeBeansList.sort(ratioComparator.reversed());

        return coffeeBeansList;
    }


}
