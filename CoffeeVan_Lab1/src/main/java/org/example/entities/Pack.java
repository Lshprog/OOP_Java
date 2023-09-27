package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Pack implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;
    private double volume;
    private double weight;
    private String description;

    // Constructors, getters, setters

    public Pack(double price, double volume, double weight, String description) {

        this.price = price;
        this.volume = volume;
        this.weight = weight;
        this.description = description;
    }

    public Pack(Long id, double price, double volume, double weight, String description) {
        this.id = id;
        this.price = price;
        this.volume = volume;
        this.weight = weight;
        this.description = description;
    }

    public Pack() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Pack{" +
                "id=" + id +
                ", price=" + price +
                ", volume=" + volume +
                ", weight=" + weight +
                ", description='" + description + '\'' +
                '}';
    }
}