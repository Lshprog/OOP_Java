package org.example.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class CoffeeVan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String name;

    private double max_volume;

    private double max_weight;



//    @OneToMany(cascade = CascadeType.ALL,
//            mappedBy = "van")
//    private List<CoffeeBeans> coffeeProducts = new ArrayList<>();

    // Constructors
    public CoffeeVan() {

    }

    public CoffeeVan(Long id, String name, double max_volume, double max_weight) {
        this.id = id;
        this.name = name;
        this.max_volume = max_volume;
        this.max_weight = max_weight;

    }

    public CoffeeVan(String name, double max_volume, double max_weight) {
        this.name = name;
        this.max_volume = max_volume;
        this.max_weight = max_weight;

    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMax_weight() {
        return max_weight;
    }

    public void setMax_weight(double max_weight) {
        this.max_weight = max_weight;
    }

    public double getMax_volume() {
        return max_volume;
    }

    public void setMax_volume(double max_volume) {
        this.max_volume = max_volume;
    }

    @Override
    public String toString() {
        return "CoffeeVan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", max_volume=" + max_volume +
                ", max_weight=" + max_weight +
                '}';
    }
}
