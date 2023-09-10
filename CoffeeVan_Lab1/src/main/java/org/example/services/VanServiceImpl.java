package org.example.services;

import org.example.entities.Coffee;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VanServiceImpl implements VanService{

    @Override
    public List<Coffee> getAllCoffeeBasedOnPriceAndWeightRatio(List<Coffee> coffeeList) {

        Comparator<Coffee> ratioComparator = Comparator.comparingDouble(coffee -> coffee.getPrice() / coffee.getWeight());

        coffeeList.sort(ratioComparator.reversed());

        return coffeeList;
    }


}
