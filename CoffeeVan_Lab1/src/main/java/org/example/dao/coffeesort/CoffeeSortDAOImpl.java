package org.example.dao.coffeesort;

import org.example.dao.ExtraRepos;
import org.example.dao.RepositoryImpl;
import org.example.dao.pack.PackDAO;
import org.example.entities.CoffeeSort;
import org.example.entities.Pack;

public class CoffeeSortDAOImpl extends ExtraRepos<CoffeeSort> implements CoffeeSortDAO {

    public CoffeeSortDAOImpl() {
        super(CoffeeSort.class);
    }

}
