package org.example.entities;

import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class InstantCoffee extends Coffee{

    private String dissolvability; // e.g., "Fast", "Medium", "Slow"
    private String flavor; // e.g., "Vanilla", "Hazelnut"

    public InstantCoffee() {
        super();
    }

    public InstantCoffee(CoffeeSort sort, Pack pack) {
        super(sort, pack);
    }

    public InstantCoffee(CoffeeSort sort, Pack pack, CoffeeVan van) {
        super(sort, pack, van);
    }

    public String getDissolvability() {
        return dissolvability;
    }

    public InstantCoffee(Long id, double price, double volume, double weight, String roastLevel, CoffeeSort sort, Pack pack, CoffeeVan van, String dissolvability, String flavor) {
        super(id, price, volume, weight, roastLevel, sort, pack, van);
        this.dissolvability = dissolvability;
        this.flavor = flavor;
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
}
