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
    private double price_per_kg;
    private String origin_country;

    // Constructors

    public CoffeeSort(Long id, String name, double price_per_kg, String origin_country) {
        this.id = id;
        this.name = name;
        this.price_per_kg = price_per_kg;
        this.origin_country = origin_country;
    }

    public CoffeeSort(String name, double price_per_kg, String origin_country) {
        this.name = name;
        this.price_per_kg = price_per_kg;
        this.origin_country = origin_country;
    }

    public CoffeeSort() {
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

    public double getPrice_per_kg() {
        return price_per_kg;
    }

    public void setPrice_per_kg(double price_per_kg) {
        this.price_per_kg = price_per_kg;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    // To string


    @Override
    public String toString() {
        return "CoffeeSort{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price_per_kg=" + price_per_kg +
                ", origin_country='" + origin_country + '\'' +
                '}';
    }
}