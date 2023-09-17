package org.example.dao;

import org.example.common.dboper.DBOperations;

import java.util.Optional;

public class RepositoryImpl<T, ID> implements Repository<T, ID>{

    protected final Class<T> entityClass;

    public RepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }

    @Override
    public <S extends T> void save(S entity) {
        DBOperations.executeTransaction(session -> session.persist(entity));
    }

    @Override
    public Optional<T> findById(ID primaryKey) {
        return  Optional.ofNullable(DBOperations.executeQuery(session -> session.get(entityClass, primaryKey)));
    }

    @Override
    public Iterable<T> findAll() {
        return DBOperations.executeQuery(session -> session.createQuery(
                "FROM " + entityClass.getSimpleName(),
                entityClass).list());
    }

    @Override
    public void delete(T entity) {
        DBOperations.executeTransaction(session -> session.remove(entity));
    }

    @Override
    public void update(T entity) {
        DBOperations.executeTransaction(session -> session.merge(entity));
    }

    @Override
    public boolean existsById(ID primaryKey) {
        return findById(primaryKey).isPresent();
    }
}
