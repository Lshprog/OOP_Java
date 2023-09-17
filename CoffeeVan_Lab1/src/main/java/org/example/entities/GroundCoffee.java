package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.common.enums.GrindType;
import org.example.common.enums.Intensity;
import org.example.common.enums.RoastLevel;

import java.io.Serializable;

@Entity
public class GroundCoffee extends CoffeeProduct implements Serializable {

    @Enumerated(EnumType.STRING)
    private GrindType grindType; // e.g., "Coarse", "Medium", "Fine"

    @Enumerated(EnumType.STRING)
    private Intensity intensity; // e.g., "Mild", "Medium", "Strong"

    // Constructors

    public GroundCoffee(CoffeeSort sort, Pack pack) {
        super(sort, pack);
        this.setDensity(0.308);
        this.setUpAttrs();
    }

    public GroundCoffee(CoffeeSort sort, Pack pack, CoffeeVan van) {
        super(sort, pack, van);
        this.setDensity(0.308);
        this.setUpAttrs();
    }

    public GroundCoffee(CoffeeSort sort, Pack pack, CoffeeVan van, GrindType grindType, Intensity intensity) {
        super(sort, pack, van);
        this.setDensity(0.308);
        this.setUpAttrs();
        this.intensity = intensity;
        this.grindType = grindType;
    }

    public GroundCoffee() {

    }

    public GroundCoffee(GroundCoffee groundCoffee) {
        super(groundCoffee);
        this.grindType = groundCoffee.grindType;
        this.intensity = groundCoffee.intensity;
    }

    public GroundCoffee(Long id, double price, double volume, double weight, double density, RoastLevel roastLevel, CoffeeSort sort, Pack pack, CoffeeVan van, GrindType grindType, Intensity intensity) {
        super(id, price, volume, weight, density, roastLevel, sort, pack, van);
        this.grindType = grindType;
        this.intensity = intensity;
    }

    @Override
    public CoffeeProduct clone() {
        return new GroundCoffee(this);
    }

    //getters, setters

    public GrindType getGrindType() {
        return grindType;
    }

    public void setGrindType(GrindType grindType) {
        this.grindType = grindType;
    }

    public Intensity getIntensity() {
        return intensity;
    }

    public void setIntensity(Intensity intensity) {
        this.intensity = intensity;
    }

    @Override
    public String toString() {
        return "GroundCoffee{" +
                " id=" + id +
                ", price=" + price +
                ", volume=" + volume +
                ", weight=" + weight +
                ", density=" + density +
                ", sort=" + sort +
                ", pack=" + pack +
                ", van=" + van +
                ", roastLevel=" + roastLevel.toString() +
                ", intensity=" + intensity.toString() +
                ", grindType=" + grindType.toString() +
                '}';
    }
}