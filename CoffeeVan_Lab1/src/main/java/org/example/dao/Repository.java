package org.example.dao;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    <S extends T> void save(S entity);

    Optional<T> findById(ID primaryKey);

    List<T> findAll();

    void delete(T entity);

    void update(T entity);

    boolean existsById(ID primaryKey);

}
