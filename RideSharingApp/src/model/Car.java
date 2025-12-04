package model;

import strategy.CarTravelStrategy;

public class Car extends Vehicle {

    public Car(String name, int location) {
        super(name, location, new CarTravelStrategy());
    }

    @Override
    public String getType() {
        return "Car";
    }
}
