package org.example.dao.pack;

import org.example.dao.RepositoryImpl;
import org.example.entities.Pack;

public class PackDAOImpl extends RepositoryImpl<Pack, Long> implements PackDAO{
    public PackDAOImpl() {
        super(Pack.class);
    }
}
