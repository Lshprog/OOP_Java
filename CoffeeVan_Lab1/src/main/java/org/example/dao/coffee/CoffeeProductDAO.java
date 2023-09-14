package org.example.dao.coffee;

import org.example.dao.Repository;
import org.example.entities.CoffeeProduct;

import java.util.List;

public interface CoffeeProductDAO<T extends CoffeeProduct> extends Repository<T, Long> {

    List<T> getAllByVanId(long van_id);

    List<T> getAllByVanIdAndOrderedByPrice(long van_id);
}

