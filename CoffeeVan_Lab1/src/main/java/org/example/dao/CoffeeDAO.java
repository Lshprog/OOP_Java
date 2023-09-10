package org.example.dao;

import org.example.entities.Coffee;

import java.util.List;

public interface CoffeeDAO<T extends Coffee> {
    void save(T coffee);
    void delete(T coffee);
    T getById(long id);
    List<T> getAll();
    List<T> getAllByVanId(long van_id);
}

