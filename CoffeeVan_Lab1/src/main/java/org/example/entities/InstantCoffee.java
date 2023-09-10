package org.example.entities;

import jakarta.persistence.Entity;

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
}
