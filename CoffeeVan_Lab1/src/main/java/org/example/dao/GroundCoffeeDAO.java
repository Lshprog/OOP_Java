package org.example.dao;

import org.example.entities.GroundCoffee;

import java.util.List;

public interface GroundCoffeeDAO extends CoffeeDAO<GroundCoffee> {

    List<GroundCoffee> getByGrindType(String grindType);


}
