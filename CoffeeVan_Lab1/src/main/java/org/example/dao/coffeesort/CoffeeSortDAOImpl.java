package org.example.dao.coffeesort;

import org.example.common.dboper.DBOperations;
import org.example.dao.ExtraRepos;
import org.example.dao.RepositoryImpl;
import org.example.dao.pack.PackDAO;
import org.example.entities.CoffeeSort;
import org.example.entities.Pack;

import java.util.AbstractMap;

public class CoffeeSortDAOImpl extends ExtraRepos<CoffeeSort> implements CoffeeSortDAO {

    public CoffeeSortDAOImpl() {
        super(CoffeeSort.class);
    }


    @Override
    public void delete(CoffeeSort sort) {

        AbstractMap.SimpleEntry<String, Long> deletePair = new AbstractMap.SimpleEntry<>("sort",sort.getId());

        this.deleteCoffees(deletePair);

        DBOperations.executeTransaction(session -> session.remove(sort));
    }

}
