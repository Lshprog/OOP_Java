package org.example.services;

import org.example.common.filters.CoffeeFilter;
import org.example.dao.coffee.*;
import org.example.dao.coffeevan.CoffeeVanDAO;
import org.example.dao.coffeevan.CoffeeVanDAOImpl;
import org.example.entities.CoffeeProduct;
import org.example.entities.CoffeeVan;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.ToDoubleFunction;

public class VanServiceImpl implements VanService{

    private final static CoffeeVanDAO daoCoffeeVan = new CoffeeVanDAOImpl();

    private final static CoffeeBeansDAO daoCoffeeBeans = new CoffeeBeansDAOImpl();

    private final static GroundCoffeeDAO daoGroundCoffee = new GroundCoffeeDAOImpl();

    private final static InstantCoffeeDAO daoInstantCoffee = new InstantCoffeeDAOImpl();

    @Override
    public List<CoffeeProduct> sortCoffeeListBasedOnParameter(List<CoffeeProduct> coffeeList, String parameter) {

        Comparator<CoffeeProduct> ratioComparator = Comparator.comparingDouble(new ToDoubleFunction<CoffeeProduct>() {
            @Override
            public double applyAsDouble(CoffeeProduct coffee) {

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
    public List<CoffeeProduct> sortCoffeeListBasedOnParameters(List<CoffeeProduct> coffeeList, CoffeeFilter filter) {
        return filter.filterCoffee(coffeeList);
    }

    @Override
    public List<CoffeeProduct> getAllCoffeeAvailableToBeAddedToVan() {
        return daoCoffeeVan.getAllCoffeeByVanId(null);
    }


    @Override
    public List<CoffeeProduct> getAllCoffeeInVanBasedOnPriceAndWeightRatio(Long vanId) {

        List<CoffeeProduct> coffeeProductList = daoCoffeeVan.getAllCoffeeByVanId(vanId);

        Comparator<CoffeeProduct> ratioComparator = Comparator.comparingDouble(coffee -> coffee.getPrice() / coffee.getWeight());

        coffeeProductList.sort(ratioComparator.reversed());

        return coffeeProductList;
    }

    @Override
    public List<CoffeeProduct> getAllCoffeeInVan(Long vanId) {
        return daoCoffeeVan.getAllCoffeeByVanId(vanId);
    }

    // Probably need to change names of functions to make code more consistent
    @Override
    public List<CoffeeProduct> getAllCoffeeInVanSortedByParameter(Long vanId, String parameter) {
        return daoCoffeeVan.getAllCoffeeSortedByParam(vanId, parameter);
    }

    @Override
    public List<CoffeeProduct> getCoffeeInVanBasedOnParameters(Long vanId, CoffeeFilter filter, List<String> classNames) {
        return daoCoffeeVan.getCoffeeBasedOnParameters(vanId, filter, classNames);
    }

    @Override
    public CoffeeVan loadCoffeeVanAutomaticallyBasedOnBudget(CoffeeVan van, Long budget) {

        List<List<? extends CoffeeProduct>> listOfCoffeeByTypesAvailable = new ArrayList<>();

        listOfCoffeeByTypesAvailable.add(daoCoffeeBeans.getAllByVanIdAndOrderedByPrice(van.getId()));
        listOfCoffeeByTypesAvailable.add(daoGroundCoffee.getAllByVanIdAndOrderedByPrice(van.getId()));
        listOfCoffeeByTypesAvailable.add(daoInstantCoffee.getAllByVanIdAndOrderedByPrice(van.getId()));

        int maxSize = 0;
        int curSum = 0;
        int curList = 0;
        int curInListPos = 0;

        for (List<? extends CoffeeProduct> list : listOfCoffeeByTypesAvailable) {
            if (list.size() > maxSize) {
                maxSize = list.size();
            }
        }
        while (curInListPos < maxSize) {
            curList = curList % 3;
            CoffeeProduct coffee = listOfCoffeeByTypesAvailable.get(curList).get(curInListPos);
            if(coffee != null
                    && coffee.getPrice() + curSum < budget
                    && coffee.getWeight() + van.getCur_weight() < van.getMax_weight()
                    && coffee.getVolume() + van.getCur_volume() < van.getMax_volume()
            ){
                coffee.setVan(van);
                van.setCur_weight(van.getCur_weight() + coffee.getWeight());
                van.setCur_volume(van.getCur_volume() + coffee.getVolume());
                curSum += coffee.getPrice();
            }
            curList++;
            if(curList == 3){
                curInListPos++;
            }
        }

        return van;
    }

    @Override
    public CoffeeVan loadCoffeeProductsByIdToVan(Long vanId, List<Long> idsOfProducts) {

        List<CoffeeProduct> coffeeProductList = daoCoffeeVan.getAllCoffeeByVanId(null);
        Optional<CoffeeVan> vanOpt = daoCoffeeVan.findById(vanId);
        CoffeeVan van = new CoffeeVan();
        if(vanOpt.isPresent()){
            van = vanOpt.get();
        }
        else {
            return null;
        }

        for (CoffeeProduct coffee : coffeeProductList) {
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
