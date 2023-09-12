package org.example.services;

import org.example.entities.CoffeeBeans;

import java.util.List;

public interface VanService {

    List<CoffeeBeans> sortAllCoffeeBasedOnPriceAndWeightRatio(Long vanId);

    List<CoffeeBeans> sortCoffeeBasedOnParameter(Long vanId, String parameter);

//    List<CoffeeBeans> sortCoffeeBasedOnParameter(List<CoffeeBeans> coffeeBeansList, String parameter);

//    List<CoffeeBeans> getAllCoffeeBasedOnPrice(List<CoffeeBeans> coffeeBeansList);
//
//    List<CoffeeBeans> getAllCoffeeBasedOnWeight(List<CoffeeBeans> coffeeBeansList);
//
//    List<CoffeeBeans> getAllCoffeeBasedOnVolume(List<CoffeeBeans> coffeeBeansList);

//    List<CoffeeBeans> getAllCoffeeBasedOnParameters();

    List<CoffeeBeans> getCoffeeBasedOnParameters(Long vanId, List<String> parameter);


}
