package org.example.common.filters;

import org.example.common.enums.Condition;
import org.example.entities.CoffeeProduct;
import org.example.entities.GroundCoffee;
import org.example.entities.InstantCoffee;

import java.util.*;

// Need to improve class to handle multiple variations of flavor, grindType, etc.
// Need to use Builder pattern

public class CoffeeFilter {
    private Double minPrice;
    private Double maxPrice;
    private Double minVolume;
    private Double maxVolume;
    private Double minWeight;
    private Double maxWeight;
    private List<String> roastLevel;
    private Long vanId;
    private List<String> sortId;
    private List<String> packId;

    // Attributes specific to InstantCoffee
    private List<String> dissolvability;
    private List<String> flavor;

    // Attributes specific to GroundCoffee
    private List<String> grindType;
    private List<String> intensity;


    public List<CoffeeProduct> filterCoffee(List<CoffeeProduct> coffeeProductList) {
        List<CoffeeProduct> filteredCoffee = new ArrayList<>();

        for (CoffeeProduct coffee : coffeeProductList) {
            if (matchesFilterCriteria(coffee)) {
                filteredCoffee.add(coffee);
            }
        }

        return filteredCoffee;
    }

    private boolean matchesFilterCriteria(CoffeeProduct coffee) {
        if (minPrice != null && coffee.getPrice() < minPrice) {
            return false;
        }
        if (maxPrice != null && coffee.getPrice() > maxPrice) {
            return false;
        }
        if (minVolume != null && coffee.getVolume() < minVolume) {
            return false;
        }
        if (maxVolume != null && coffee.getVolume() > maxVolume) {
            return false;
        }
        if (minWeight != null && coffee.getWeight() < minWeight) {
            return false;
        }
        if (maxWeight != null && coffee.getWeight() > maxWeight) {
            return false;
        }
        if (roastLevel != null && !roastLevel.contains(coffee.getRoastLevel().name())) {
            return false;
        }
        if (dissolvability != null && coffee instanceof InstantCoffee) {
            InstantCoffee instantCoffee = (InstantCoffee) coffee;
            if (!dissolvability.contains(instantCoffee.getDissolvability().name())) {
                return false;
            }
        }
        if (flavor != null && coffee instanceof InstantCoffee) {
            InstantCoffee instantCoffee = (InstantCoffee) coffee;
            if (!flavor.contains(instantCoffee.getFlavor().name())) {
                return false;
            }
        }
        if (grindType != null && coffee instanceof GroundCoffee) {
            GroundCoffee groundCoffee = (GroundCoffee) coffee;
            if (!grindType.contains(groundCoffee.getGrindType().name())) {
                return false;
            }
        }
        if (intensity != null && coffee instanceof GroundCoffee) {
            GroundCoffee groundCoffee = (GroundCoffee) coffee;
            if (!intensity.contains(groundCoffee.getIntensity().name())) {
                return false;
            }
        }

        return true;
    }

    public List<FilterNode> getFilterRanges() {

        List<FilterNode> criteriaList = new ArrayList<>();

        if (maxPrice != null) {
            criteriaList.add(new FilterNode("price", "maxPrice", Collections.singletonList(String.valueOf(maxPrice)), Condition.MAX, "Double"));
        }
        if (minPrice != null) {
            criteriaList.add(new FilterNode("price", "minPrice", Collections.singletonList(String.valueOf(minPrice)), Condition.MIN, "Double"));
        }
        if (minVolume != null) {
            criteriaList.add(new FilterNode("volume", "minVolume", Collections.singletonList(String.valueOf(minVolume)), Condition.MIN, "Double"));
        }
        if (maxVolume != null) {
            criteriaList.add(new FilterNode("volume", "maxVolume", Collections.singletonList(String.valueOf(maxVolume)), Condition.MAX, "Double"));
        }
        if (minWeight != null) {
            criteriaList.add(new FilterNode("weight", "minWeight", Collections.singletonList(String.valueOf(minWeight)), Condition.MIN, "Double"));
        }
        if (maxWeight != null) {
            criteriaList.add(new FilterNode("weight", "maxWeight", Collections.singletonList(String.valueOf(maxWeight)), Condition.MAX, "Double"));
        }
        if (vanId != null) {
            criteriaList.add(new FilterNode("van.id", "vanId", Collections.singletonList(String.valueOf(vanId)), Condition.EQUAL, "Long"));
        }
//        if (sortId != null) {
//            criteriaList.add(new FilterNode("sort.id", "sortId", Collections.singletonList(String.valueOf(sortId)), Condition.EQUAL, "Long"));
//        }
//        if (packId != null) {
//            criteriaList.add(new FilterNode("pack.id", "packId", Collections.singletonList(String.valueOf(packId)), Condition.EQUAL, "Long"));
//        }
        if (sortId != null) {
            criteriaList.add(new FilterNode("sort.id", "sortId", sortId, Condition.LIST, "Long"));
        }
        if (packId != null) {
            criteriaList.add(new FilterNode("pack.id", "packId", packId, Condition.LIST, "Long"));
        }
        if (roastLevel != null) {
            criteriaList.add(new FilterNode("roastLevel", "roastLevel", roastLevel, Condition.LIST, "RoastLevel"));
        }
        if (dissolvability != null) {
            criteriaList.add(new FilterNode("dissolvability", "dissolvability", dissolvability, Condition.LIST, "Dissolvability"));
        }
        if (flavor != null) {
            criteriaList.add(new FilterNode("flavor", "flavor", flavor, Condition.LIST, "Flavor"));
        }
        if (grindType != null) {
            criteriaList.add(new FilterNode("grindType", "grindType", grindType, Condition.LIST, "GrindType"));
        }
        if (intensity != null) {
            criteriaList.add(new FilterNode("intensity", "intensity", intensity, Condition.LIST, "Intensity"));
        }

        return criteriaList;
    }


    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMinVolume() {
        return minVolume;
    }

    public void setMinVolume(Double minVolume) {
        this.minVolume = minVolume;
    }

    public Double getMaxVolume() {
        return maxVolume;
    }

    public void setMaxVolume(Double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public Double getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(Double minWeight) {
        this.minWeight = minWeight;
    }

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public List<String> getRoastLevel() {
        return roastLevel;
    }

    public void setRoastLevel(List<String> roastLevel) {
        this.roastLevel = roastLevel;
    }

    public List<String> getDissolvability() {
        return dissolvability;
    }

    public void setDissolvability(List<String> dissolvability) {
        this.dissolvability = dissolvability;
    }

    public List<String> getFlavor() {
        return flavor;
    }

    public void setFlavor(List<String> flavor) {
        this.flavor = flavor;
    }

    public List<String> getGrindType() {
        return grindType;
    }

    public void setGrindType(List<String> grindType) {
        this.grindType = grindType;
    }

    public List<String> getIntensity() {
        return intensity;
    }

    public void setIntensity(List<String> intensity) {
        this.intensity = intensity;
    }

    public Long getVanId() {
        return vanId;
    }

    public void setVanId(Long vanId) {
        this.vanId = vanId;
    }

    public List<String> getSortId() {
        return sortId;
    }

    public void setSortId(List<String> sortIds) {
        this.sortId = sortIds;
    }

    public List<String> getPackId() {
        return packId;
    }

    public void setPackId(List<String> packIds) {
        this.packId = packIds;
    }
}
