package org.example.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class CoffeeVan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String name;

    private double volume;

    private double max_weight;

    // Constructors
    public CoffeeVan() {
    }

    public CoffeeVan(Long id, String name, double volume, double max_weight) {
        this.id = id;
        this.name = name;
        this.volume = volume;
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

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getMax_weight() {
        return max_weight;
    }

    public void setMax_weight(double max_weight) {
        this.max_weight = max_weight;
    }
}
