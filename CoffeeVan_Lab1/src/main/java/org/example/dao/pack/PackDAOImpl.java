package org.example.dao.pack;

import org.example.common.dboper.DBOperations;
import org.example.dao.ExtraRepos;
import org.example.dao.RepositoryImpl;
import org.example.entities.CoffeeVan;
import org.example.entities.Pack;

import java.util.AbstractMap;

public class PackDAOImpl extends ExtraRepos<Pack> implements PackDAO{
    public PackDAOImpl() {
        super(Pack.class);
    }


    @Override
    public void delete(Pack pack) {

        AbstractMap.SimpleEntry<String, Long> deletePair = new AbstractMap.SimpleEntry<>("pack",pack.getId());

        this.deleteCoffees(deletePair);

        DBOperations.executeTransaction(session -> session.remove(pack));
    }

}
