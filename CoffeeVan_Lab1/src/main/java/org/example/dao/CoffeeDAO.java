package org.example.dao;

import java.util.List;

public interface CoffeeDAO<T> {
    void save(T coffee);
    void delete(T coffee);
    T getById(long id);
    List<T> getAll();
}

