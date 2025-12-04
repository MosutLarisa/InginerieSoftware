package model;

import strategy.TravelStrategy;

public abstract class Vehicle {
    protected String name;
    protected int location;
    protected boolean busy = false;
    protected TravelStrategy strategy;

    public Vehicle(String name, int location, TravelStrategy strategy) {
        this.name = name;
        this.location = location;
        this.strategy = strategy;
    }

    public String getName() { return name; }
    public int getLocation() { return location; }
    public boolean isBusy() { return busy; }
    public void setBusy(boolean busy) { this.busy = busy; }

    public boolean canHandleTrip(int distance) {
        return strategy.canTravel(distance);
    }

    public abstract String getType();
}
