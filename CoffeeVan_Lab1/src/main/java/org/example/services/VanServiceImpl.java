package org.example.services;

import org.example.common.filters.CoffeeFilter;
import org.example.dao.coffeevan.CoffeeVanDAO;
import org.example.dao.coffeevan.CoffeeVanDAOImpl;
import org.example.entities.*;

import java.util.*;
import java.util.function.ToDoubleFunction;

public class VanServiceImpl implements VanService{

    private final static CoffeeVanDAO daoCoffeeVan = new CoffeeVanDAOImpl();

    private final static CoffeeService coffeeService = new CoffeeServiceImpl();


    @Override
    public void saveVan(CoffeeVan coffeeVan) {
        daoCoffeeVan.save(coffeeVan);
    }

    @Override
    public void updateVan(CoffeeVan coffeeVan) {
        daoCoffeeVan.update(coffeeVan);
    }

    @Override
    public void deleteVan(CoffeeVan coffeeVan) {
        daoCoffeeVan.delete(coffeeVan);
    }

    @Override
    public CoffeeVan getCoffeeVanByName(String name) {
        return daoCoffeeVan.getCoffeeVanByName(name);
    }

    @Override
    public List<CoffeeVan> getAllCoffeeVans() {
        return daoCoffeeVan.findAll();
    }


    private List<CoffeeProduct> sortCoffeeListBasedOnParameter(List<CoffeeProduct> coffeeList, String parameter) {

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


    private List<CoffeeProduct> sortCoffeeListBasedOnParameters(List<CoffeeProduct> coffeeList, CoffeeFilter filter) {
        return filter.filterCoffee(coffeeList);
    }

    @Override
    public List<CoffeeProduct> getAllCoffeeAvailableToBeAddedToVan() {
        return daoCoffeeVan.getAllCoffeeByVanId();
    }


    @Override
    public List<CoffeeProduct> getAllCoffeeInVanBasedOnPriceAndWeightRatio(Long vanId) {
        return this.sortCoffeeListBasedOnParameter(daoCoffeeVan.getAllCoffeeByVanId(vanId),"price/weight");
    }

    @Override
    public List<CoffeeProduct> getAllCoffeeInVan(Long vanId) {
        return daoCoffeeVan.getAllCoffeeByVanId(vanId);
    }

    @Override
    public List<CoffeeProduct> getAllCoffeeInVanSortedByParameter(Long vanId, String parameter) {
        return this.sortCoffeeListBasedOnParameter(daoCoffeeVan.getAllCoffeeByVanId(vanId),parameter);
    }

    @Override
    public List<CoffeeProduct> getCoffeeInVanBasedOnParameters(Long vanId, CoffeeFilter filter, List<String> classNames) {
        return daoCoffeeVan.getCoffeeBasedOnParameters(vanId, filter, classNames);
    }

    @Override
    public CoffeeVan getCoffeeVanById(Long vanId) {

        Optional<CoffeeVan> coffeeVanOpt = daoCoffeeVan.findById(vanId);

        if (coffeeVanOpt.isPresent()){
            return coffeeVanOpt.get();
        }
        else{
            System.out.println("No such van in database! ");
        }
        return null;

    }


    @Override
    public CoffeeVan loadCoffeeVanAutomaticallyBasedOnBudget(CoffeeVan van, double budget) {

        List<CoffeeProduct> coffeeProduct = daoCoffeeVan.getAllCoffeeByVanId();
        List<Double> volumeAndWeightList = this.getCurrentVolumeAndWeightOfVan(coffeeProduct);

        List<List<? extends CoffeeProduct>> listOfCoffeeByTypesAvailable = new ArrayList<>();
        listOfCoffeeByTypesAvailable = daoCoffeeVan.getListOfCoffeeByTypesAvailable();

        double cur_volume = volumeAndWeightList.get(0) == null ? 0.0 : volumeAndWeightList.get(0);
        double cur_weight = volumeAndWeightList.get(1) == null ? 0.0 : volumeAndWeightList.get(1);

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
            if(listOfCoffeeByTypesAvailable.get(curList).size() > curInListPos) {
                CoffeeProduct coffee = listOfCoffeeByTypesAvailable.get(curList).get(curInListPos);
                if (coffee != null
                        && coffee.getPrice() + curSum < budget
                        && coffee.getWeight() + cur_weight < van.getMax_weight()
                        && coffee.getVolume() + cur_volume < van.getMax_volume()
                ) {
                    coffee.setVan(van);
                    coffeeService.update(coffee);
                    cur_weight+=coffee.getWeight();
                    cur_volume+= coffee.getVolume();
                    curSum += coffee.getPrice();
                }
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

        List<CoffeeProduct> coffeeProduct = daoCoffeeVan.getAllCoffeeByVanId();
        Optional<CoffeeVan> vanOptional = daoCoffeeVan.findById(vanId);

        CoffeeVan van = new CoffeeVan();


        if(vanOptional.isPresent()){
            van = vanOptional.get();
        }
        else {
            System.out.println("No such van! ");
            return null;
        }

        List<Double> volumeAndWeightList = this.getCurrentVolumeAndWeightOfVan(coffeeProduct);

        double cur_volume = volumeAndWeightList.get(0) == null ? 0.0 : volumeAndWeightList.get(0);
        double cur_weight = volumeAndWeightList.get(1) == null ? 0.0 : volumeAndWeightList.get(1);

        for (CoffeeProduct coffee : coffeeProduct) {
            if(van.getMax_volume() - cur_volume > coffee.getVolume() &&
                    van.getMax_weight() - cur_weight > coffee.getWeight()) {

                for (Long productId : idsOfProducts) {
                    if(Objects.equals(coffee.getId(), productId)){
                        coffee.setVan(van);
                        coffeeService.update(coffee);
                        cur_weight+=coffee.getWeight();
                        cur_volume+=coffee.getVolume();
                    }
                }

            }
        }

        return van;

    }

    private List<Double> getCurrentVolumeAndWeightOfVan(List<CoffeeProduct> coffeeProducts) {

        double volume = 0.0;
        double weight = 0.0;

        for (CoffeeProduct coffeeProduct : coffeeProducts){
            volume+=coffeeProduct.getVolume();
            weight+=coffeeProduct.getWeight();
        }

        List<Double> volumeAndWeightList = new ArrayList<>();
        volumeAndWeightList.add(volume);
        volumeAndWeightList.add(weight);

        return volumeAndWeightList;

    }




}
