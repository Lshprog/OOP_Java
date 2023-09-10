package org.example.services;

import org.example.entities.Coffee;

import java.util.List;

public interface VanService {

    List<Coffee> getAllCoffeeBasedOnPriceAndWeightRatio(List<Coffee> coffeeList);


}
