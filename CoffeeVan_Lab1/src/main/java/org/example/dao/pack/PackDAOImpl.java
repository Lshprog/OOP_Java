package org.example.dao.pack;

import org.example.dao.ExtraRepos;
import org.example.dao.RepositoryImpl;
import org.example.entities.Pack;

public class PackDAOImpl extends ExtraRepos<Pack> implements PackDAO{
    public PackDAOImpl() {
        super(Pack.class);
    }
}
