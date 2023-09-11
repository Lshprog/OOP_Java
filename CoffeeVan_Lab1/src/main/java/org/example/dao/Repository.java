package org.example.dao;

import java.util.Optional;

public interface Repository<T, ID> {

    <S extends T> void save(S entity);

    Optional<T> findById(ID primaryKey);

    Iterable<T> findAll();

    void delete(T entity);

    boolean existsById(ID primaryKey);

}
