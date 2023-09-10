package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
public class CoffeeSort implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    protected double price_per_kg;
    protected String origin_country;

    // Constructors, getters, setters

    public CoffeeSort(Long id, String name, double price_per_kg, String origin_country) {
        this.id = id;
        this.name = name;
        this.price_per_kg = price_per_kg;
        this.origin_country = origin_country;
    }

    public CoffeeSort() {
    }
}