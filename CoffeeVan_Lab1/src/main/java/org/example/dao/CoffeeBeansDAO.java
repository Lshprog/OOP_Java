package org.example.dao;

import org.example.entities.CoffeeBeans;

import java.util.List;

public interface CoffeeBeansDAO<T extends CoffeeBeans> extends Repository<T, Long> {

    List<T> getAllByVanId(long van_id);
}

