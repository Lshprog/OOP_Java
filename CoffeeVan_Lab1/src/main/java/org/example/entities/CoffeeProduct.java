package org.example.entities;

import jakarta.persistence.*;
import org.example.common.enums.RoastLevel;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CoffeeProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coffee_seq")
    @SequenceGenerator(name = "coffee_seq", sequenceName = "coffee_seq", allocationSize = 1)
    protected Long id;

    protected double price;

    protected double volume;

    protected double weight;

    protected double density;

    @Enumerated(EnumType.STRING)
    protected RoastLevel roastLevel;

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

    public CoffeeProduct(CoffeeSort sort, Pack pack) {
        this.sort = sort;
        this.pack = pack;
    }

    public CoffeeProduct(CoffeeSort sort, Pack pack, CoffeeVan van) {
        this.sort = sort;
        this.pack = pack;
        this.van = van;
    }

    public CoffeeProduct(Long id, double price, double volume, double weight, double density, RoastLevel roastLevel, CoffeeSort sort, Pack pack, CoffeeVan van) {
        this.id = id;
        this.price = price;
        this.volume = volume;
        this.weight = weight;
        this.density = density;
        this.roastLevel = roastLevel;
        this.sort = sort;
        this.pack = pack;
        this.van = van;
    }

    public CoffeeProduct() {

    }

    public CoffeeProduct(CoffeeProduct coffeeProduct) {
        this.price = coffeeProduct.price;
        this.volume = coffeeProduct.volume;
        this.weight = coffeeProduct.weight;
        this.density = coffeeProduct.density;
        this.roastLevel = coffeeProduct.roastLevel;
        this.sort = coffeeProduct.sort;
        this.pack = coffeeProduct.pack;
        this.van = coffeeProduct.van;
    }

    protected void setUpAttrs(){
        this.volume = this.pack.getVolume();
        this.weight = this.density*this.volume;
        this.price = this.pack.getPrice() + this.weight*this.sort.getPrice_per_kg();
        this.roastLevel = RoastLevel.LIGHT;
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

    public RoastLevel getRoastLevel() {
        return roastLevel;
    }

    public void setRoastLevel(RoastLevel roastLevel) {
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

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public abstract CoffeeProduct clone();

    // To string


    @Override
    public String toString() {
        return "CoffeeProduct{" +
                "id=" + id +
                ", price=" + price +
                ", volume=" + volume +
                ", weight=" + weight +
                ", density=" + density +
                ", roastLevel=" + roastLevel.toString() +
                ", sort=" + sort +
                ", pack=" + pack +
                ", van=" + van +
                '}';
    }
}