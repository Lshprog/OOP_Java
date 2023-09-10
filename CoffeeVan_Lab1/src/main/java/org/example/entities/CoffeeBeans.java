package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class CoffeeBeans extends Coffee {

    private String roastLevel;

    // Constructors, getters, setters

    public CoffeeBeans() {
        super();
    }

    public CoffeeBeans(CoffeeSort sort, Pack pack) {
        super(sort, pack);
    }

    public String getRoastLevel() {
        return roastLevel;
    }

    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }
}