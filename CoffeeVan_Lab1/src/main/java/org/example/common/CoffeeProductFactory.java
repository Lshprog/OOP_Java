package org.example.common;

import org.example.common.enums.CoffeeState;

public class CoffeeFactory {

    public static Coffee createCoffee(CoffeeState type) {

        switch (type){

            case BEANS -> {
                return new CoffeeBeans();
            }

            case INSTANT -> {
                return new InstantCoffee();
            }

            case GROUND -> {
                return new GroundCoffee();
            }

            default -> {
                throw new IllegalArgumentException("Unsupported coffee type: " + type);
            }

        }
    }

}
