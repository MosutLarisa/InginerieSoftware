package model;

import strategy.ScooterTravelStrategy;

public class Scooter extends Vehicle {

    private int maxRange;

    public Scooter(String name, int location, int maxRange) {
        super(name, location, new ScooterTravelStrategy(maxRange));
        this.maxRange = maxRange;
    }

    public int getMaxRange() { return maxRange; }

    @Override
    public String getType() {
        return "Scooter";
    }
}
