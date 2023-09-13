package org.example.services;

import org.example.common.CoffeeFilter;
import org.example.dao.coffeevan.CoffeeVanDAO;
import org.example.dao.coffeevan.CoffeeVanDAOImpl;
import org.example.entities.CoffeeBeans;
import org.example.entities.CoffeeVan;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleFunction;

public class VanServiceImpl implements VanService{

    private final static CoffeeVanDAO daoCoffeeVan = new CoffeeVanDAOImpl();

    @Override
    public List<CoffeeBeans> sortCoffeeListBasedOnParameter(List<CoffeeBeans> coffeeList, String parameter) {

        Comparator<CoffeeBeans> ratioComparator = Comparator.comparingDouble(new ToDoubleFunction<CoffeeBeans>() {
            @Override
            public double applyAsDouble(CoffeeBeans coffee) {

                switch (parameter){

                    case "price/weight" -> {
                        return coffee.getPrice() / coffee.getWeight();
                    }

                    case "price" -> {
                        return coffee.getPrice();
                    }

                    case "volume" -> {
                        return coffee.getVolume();
                    }

                    case "weight" -> {
                        return coffee.getWeight();
                    }

                    default -> {
                        return 0;
                    }
                }
            }
        });

        coffeeList.sort(ratioComparator.reversed());

        return coffeeList;
    }

    @Override
    public List<CoffeeBeans> sortCoffeeListBasedOnParameters(List<CoffeeBeans> coffeeList, CoffeeFilter filter) {
        return filter.filterCoffee(coffeeList);
    }

    @Override
    public List<CoffeeBeans> getAllCoffeeAvailableToBeAddedToVan() {
        return daoCoffeeVan.getAllCoffeeByVanId(null);
    }


    @Override
    public List<CoffeeBeans> getAllCoffeeInVanBasedOnPriceAndWeightRatio(Long vanId) {

        List<CoffeeBeans> coffeeBeansList = daoCoffeeVan.getAllCoffeeByVanId(vanId);

        Comparator<CoffeeBeans> ratioComparator = Comparator.comparingDouble(coffee -> coffee.getPrice() / coffee.getWeight());

        coffeeBeansList.sort(ratioComparator.reversed());

        return coffeeBeansList;
    }

    @Override
    public List<CoffeeBeans> getAllCoffeeInVan(Long vanId) {
        return daoCoffeeVan.getAllCoffeeByVanId(vanId);
    }

    // Probably need to change names of functions to make code more consistent
    @Override
    public List<CoffeeBeans> getAllCoffeeInVanSortedByParameter(Long vanId, String parameter) {
        return daoCoffeeVan.getAllCoffeeSortedByParam(vanId, parameter);
    }

    @Override
    public List<CoffeeBeans> getCoffeeInVanBasedOnParameters(Long vanId, CoffeeFilter filter, List<String> classNames) {
        return daoCoffeeVan.getCoffeeBasedOnParameters(vanId, filter, classNames);
    }

    // Need to implement 1 function for autoload

    @Override
    public CoffeeVan loadCoffeeVanAutomaticallyBasedOnBudget(CoffeeVan van, Long budget) {
        return null;
    }

    @Override
    public CoffeeVan loadCoffeeProductsByIdToVan(Long vanId, List<Long> idsOfProducts) {

        List<CoffeeBeans> coffeeBeansList = daoCoffeeVan.getAllCoffeeByVanId(null);
        Optional<CoffeeVan> vanOpt = daoCoffeeVan.findById(vanId);
        CoffeeVan van = new CoffeeVan();
        if(vanOpt.isPresent()){
            van = vanOpt.get();
        }
        else {
            return null;
        }

        for (CoffeeBeans coffee : coffeeBeansList) {
            if(van.getMax_volume() - van.getCur_volume() > coffee.getVolume() &&
                    van.getMax_weight() - van.getCur_weight() > coffee.getWeight()) {

                for (Long productId : idsOfProducts) {
                    if(coffee.getId() == productId){
                        coffee.setVan(van);
                        van.setCur_weight(van.getCur_weight() + coffee.getWeight());
                        van.setCur_volume(van.getCur_volume() + coffee.getVolume());
                    }
                }

            }
        }

        return van;

    }


}
