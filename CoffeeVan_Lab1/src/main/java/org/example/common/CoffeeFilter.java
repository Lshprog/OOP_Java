package org.example.common;

import org.example.entities.CoffeeProduct;
import org.example.entities.GroundCoffee;
import org.example.entities.InstantCoffee;

import java.util.ArrayList;
import java.util.List;

// Need to improve class to handle multiple variations of flavor, grindType, etc.
// Need to use Builder pattern

public class CoffeeFilter {
    private Double minPrice;
    private Double maxPrice;
    private Double minVolume;
    private Double maxVolume;
    private Double minWeight;
    private Double maxWeight;
    private String roastLevel;

    // Attributes specific to InstantCoffee
    private String dissolvability;
    private String flavor;

    // Attributes specific to GroundCoffee
    private String grindType;
    private String intensity;


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
        if (roastLevel != null && !coffee.getRoastLevel().equalsIgnoreCase(roastLevel)) {
            return false;
        }
        if (dissolvability != null && coffee instanceof InstantCoffee) {
            InstantCoffee instantCoffee = (InstantCoffee) coffee;
            if (!instantCoffee.getDissolvability().equalsIgnoreCase(dissolvability)) {
                return false;
            }
        }
        if (flavor != null && coffee instanceof InstantCoffee) {
            InstantCoffee instantCoffee = (InstantCoffee) coffee;
            if (!instantCoffee.getFlavor().equalsIgnoreCase(flavor)) {
                return false;
            }
        }
        if (grindType != null && coffee instanceof GroundCoffee) {
            GroundCoffee groundCoffee = (GroundCoffee) coffee;
            if (!groundCoffee.getGrindType().equalsIgnoreCase(grindType)) {
                return false;
            }
        }
        if (intensity != null && coffee instanceof GroundCoffee) {
            GroundCoffee groundCoffee = (GroundCoffee) coffee;
            if (!groundCoffee.getIntensity().equalsIgnoreCase(intensity)) {
                return false;
            }
        }

        return true;
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

    public String getRoastLevel() {
        return roastLevel;
    }

    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }

    public String getDissolvability() {
        return dissolvability;
    }

    public void setDissolvability(String dissolvability) {
        this.dissolvability = dissolvability;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getGrindType() {
        return grindType;
    }

    public void setGrindType(String grindType) {
        this.grindType = grindType;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }
}
