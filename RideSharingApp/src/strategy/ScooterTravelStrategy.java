package strategy;

public class ScooterTravelStrategy implements TravelStrategy {

    private int maxKm;

    public ScooterTravelStrategy(int maxKm) {
        this.maxKm = maxKm;
    }

    @Override
    public boolean canTravel(int distance) {
        return maxKm >= distance * 0.5;
    }

}
