package org.example.services;

import org.example.common.filters.CoffeeFilter;
import org.example.entities.CoffeeProduct;
import org.example.entities.CoffeeVan;

import java.util.List;

public interface VanService {

    void saveVan(CoffeeVan coffeeVan);

    void updateVan(CoffeeVan coffeeVan);

    void deleteVan(CoffeeVan coffeeVan);

    CoffeeVan getCoffeeVanByName(String name);

    CoffeeVan getCoffeeVanById(Long vanId);

    List<CoffeeVan> getAllCoffeeVans();

    // Sorting functions : which take list of coffee as parameters
//    List<CoffeeProduct> sortCoffeeListBasedOnParameter(List<CoffeeProduct> coffeeList, String parameter);
//
//    List<CoffeeProduct> sortCoffeeListBasedOnParameters(List<CoffeeProduct> coffeeList, CoffeeFilter filter);

    // Getter functions : call dao functions to get data from db based on specific criteria
    List<CoffeeProduct> getAllCoffeeAvailableToBeAddedToVan();

    List<CoffeeProduct> getAllCoffeeInVanBasedOnPriceAndWeightRatio(Long vanId);

    List<CoffeeProduct> getAllCoffeeInVan(Long vanId);

    List<CoffeeProduct> getAllCoffeeInVanSortedByParameter(Long vanId, String parameter);

    List<CoffeeProduct> getCoffeeInVanBasedOnParameters(Long vanId, CoffeeFilter filter, List<String> classNames);

    // Load functions : load the particular van with products based on something

    CoffeeVan loadCoffeeVanAutomaticallyBasedOnBudget(CoffeeVan van, double budget);

    CoffeeVan loadCoffeeProductsByIdToVan(Long vanId, List<Long> idsOfProducts);


}
