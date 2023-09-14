package org.example.common;

import org.example.common.enums.CoffeeState;
import org.example.entities.*;

public class CoffeeProductFactory {

        public static CoffeeProduct createCoffeeProduct(CoffeeState state, CoffeeSort coffeeSort, Pack pack) {

        switch (state){

            case BEANS -> {
                return new CoffeeBeans(coffeeSort, pack);
            }

            case INSTANT -> {
                return new InstantCoffee(coffeeSort, pack);
            }

            case GROUND -> {
                return new GroundCoffee(coffeeSort, pack);
            }

            default -> {
                throw new IllegalArgumentException("Unsupported coffee type: " + state.toString());
            }

        }
    }

}
