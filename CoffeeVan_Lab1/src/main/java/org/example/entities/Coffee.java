package org.example.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public abstract class Coffee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected double price;

    protected double volume;

    protected double weight;

    private String roastLevel;

    @ManyToOne
    @JoinColumn(name = "sort_id")
    protected CoffeeSort sort;

    @ManyToOne
    @JoinColumn(name = "pack_id")
    protected Pack pack;

    @ManyToOne
    @JoinColumn(name = "van_id")
    protected CoffeeVan van;


    // Constructors

    public Coffee(CoffeeSort sort, Pack pack) {
        this.sort = sort;
        this.pack = pack;
    }

    public Coffee(CoffeeSort sort, Pack pack, CoffeeVan van) {
        this.sort = sort;
        this.pack = pack;
        this.van = van;
    }

    public Coffee(Long id, double price, double volume, double weight, String roastLevel, CoffeeSort sort, Pack pack, CoffeeVan van) {
        this.id = id;
        this.price = price;
        this.volume = volume;
        this.weight = weight;
        this.roastLevel = roastLevel;
        this.sort = sort;
        this.pack = pack;
        this.van = van;
    }

    public Coffee() {
    }

    // Getters and Setters


    public CoffeeVan getVan() {
        return van;
    }

    public void setVan(CoffeeVan van) {
        this.van = van;
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

    public String getRoastLevel() {
        return roastLevel;
    }

    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }

    public CoffeeSort getSort() {
        return sort;
    }

    public void setSort(CoffeeSort sort) {
        this.sort = sort;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }
}