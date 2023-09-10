package org.example.entities;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected double price;

    protected double volume;

    protected double weight;

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

    @ManyToOne
    @JoinColumn(name = "sort_id")
    protected CoffeeSort sort;

    @ManyToOne
    @JoinColumn(name = "pack_id")
    protected Pack pack;

    public Coffee(double price, double volume, double weight, CoffeeSort sort, Pack pack) {
        this.price = price;
        this.volume = volume;
        this.weight = weight;
        this.sort = sort;
        this.pack = pack;
    }

    public Coffee() {
    }

    public Coffee(CoffeeSort sort, Pack pack) {
        this.sort = sort;
        this.pack = pack;
    }


}
