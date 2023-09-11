package org.example.services;

import org.example.entities.CoffeeBeans;

import java.util.List;

public interface VanService {

    List<CoffeeBeans> getAllCoffeeBasedOnPriceAndWeightRatio(List<CoffeeBeans> coffeeBeansList);


}
