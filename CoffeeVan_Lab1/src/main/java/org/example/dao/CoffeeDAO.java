package org.example.dao;

public interface CoffeeDAO<T> {
    void save(T coffee);
    void delete(T coffee);
    T getById(long id);
}

