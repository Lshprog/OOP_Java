package org.example.dao.coffee;

import org.example.dao.Repository;
import org.example.entities.CoffeeProduct;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public interface CoffeeProductDAO<T extends CoffeeProduct> extends Repository<T, Long> {

    List<T> getAllByVanId(long van_id);

    List<T> getAllByVanId();

    List<T> getAllByVanIdAndOrderedByPrice(long van_id);

    void deleteSafely(AbstractMap.SimpleEntry<String, Long> mapDelete);
}

