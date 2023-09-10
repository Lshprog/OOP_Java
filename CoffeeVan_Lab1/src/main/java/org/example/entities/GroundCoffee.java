package org.example.entities;

import jakarta.persistence.Entity;

@Entity
public class GroundCoffee extends Coffee {

    private String grindType; // e.g., "Coarse", "Medium", "Fine"
    private String intensity; // e.g., "Mild", "Medium", "Strong"

    // Constructors, getters, setters
    public GroundCoffee() {
        super();
    }

    public GroundCoffee(Long id, double price, double volume, double weight, String roastLevel, CoffeeSort sort, Pack pack, String grindType, String intensity) {
        super(id, price, volume, weight, roastLevel, sort, pack);
        this.grindType = grindType;
        this.intensity = intensity;
    }

    public GroundCoffee(CoffeeSort sort, Pack pack) {
        super(sort, pack);
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