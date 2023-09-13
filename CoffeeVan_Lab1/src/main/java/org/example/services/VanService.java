package org.example.services;

import org.example.common.CoffeeFilter;
import org.example.entities.CoffeeBeans;
import org.example.entities.CoffeeVan;

import java.util.List;

public interface VanService {


    // Sorting functions : which take list of coffee as parameters
    List<CoffeeBeans> sortCoffeeListBasedOnParameter(List<CoffeeBeans> coffeeList, String parameter);

    List<CoffeeBeans> sortCoffeeListBasedOnParameters(List<CoffeeBeans> coffeeList, CoffeeFilter filter);

    // Getter functions : call dao functions to get data from db based on specific criteria
    List<CoffeeBeans> getAllCoffeeAvailableToBeAddedToVan();

    List<CoffeeBeans> getAllCoffeeInVanBasedOnPriceAndWeightRatio(Long vanId);

    List<CoffeeBeans> getAllCoffeeInVan(Long vanId);

    List<CoffeeBeans> getAllCoffeeInVanSortedByParameter(Long vanId, String parameter);

    List<CoffeeBeans> getCoffeeInVanBasedOnParameters(Long vanId, CoffeeFilter filter, List<String> classNames);

    // Load functions : load the particular van with products based on something

    CoffeeVan loadCoffeeVanAutomaticallyBasedOnBudget(CoffeeVan van, Long budget);

    CoffeeVan loadCoffeeProductsByIdToVan(Long vanId, List<Long> idsOfProducts);


}
