package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.example.common.enums.Dissolvability;
import org.example.common.enums.Flavor;
import org.example.common.enums.RoastLevel;

import java.io.Serializable;

@Entity
public class InstantCoffee extends CoffeeProduct implements Serializable {

    @Enumerated(EnumType.STRING)
    private Dissolvability dissolvability; // e.g., "Fast", "Medium", "Slow"
    @Enumerated(EnumType.STRING)
    private Flavor flavor; // e.g., "Vanilla", "Hazelnut"

    // Constructors

    public InstantCoffee() {
        super();
    }

    public InstantCoffee(CoffeeSort sort, Pack pack) {
        super(sort, pack);
        this.setDensity(0.22);
        this.setUpAttrs();
    }

    public InstantCoffee(CoffeeSort sort, Pack pack, Dissolvability dissolvability, Flavor flavor) {
        super(sort, pack);
        this.setDensity(0.22);
        this.setUpAttrs();
        this.dissolvability = dissolvability;
        this.flavor = flavor;
    }

    public InstantCoffee(CoffeeSort sort, Pack pack, CoffeeVan van) {
        super(sort, pack, van);
        this.setDensity(0.22);
        this.setUpAttrs();
    }

    public InstantCoffee(CoffeeSort sort, Pack pack, CoffeeVan van, Dissolvability dissolvability, Flavor flavor) {
        super(sort, pack, van);
        this.setDensity(0.22);
        this.setUpAttrs();
        this.dissolvability = dissolvability;
        this.flavor = flavor;
    }

    public InstantCoffee(Long id, double price, double volume, double weight, double density, RoastLevel roastLevel, CoffeeSort sort, Pack pack, CoffeeVan van, Dissolvability dissolvability, Flavor flavor) {
        super(id, price, volume, weight, density, roastLevel, sort, pack, van);
        this.dissolvability = dissolvability;
        this.flavor = flavor;
    }

    public InstantCoffee(InstantCoffee instantCoffee) {
        super(instantCoffee);
        this.dissolvability = instantCoffee.dissolvability;
        this.flavor = instantCoffee.flavor;
    }

    @Override
    public CoffeeProduct clone() {
        return new InstantCoffee(this);
    }

    // getters, setters
    public Dissolvability getDissolvability() {
        return dissolvability;
    }

    public void setDissolvability(Dissolvability dissolvability) {
        this.dissolvability = dissolvability;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return "InstantCoffee{" +
                " id=" + id +
                ", price=" + price +
                ", volume=" + volume +
                ", weight=" + weight +
                ", density=" + density +
                ", sort=" + sort +
                ", pack=" + pack +
                ", van=" + van +
                ", roastLevel=" + roastLevel.toString() +
                ", dissolvability=" + dissolvability.toString() +
                ", flavor=" + flavor.toString() +
                '}';
    }
}
