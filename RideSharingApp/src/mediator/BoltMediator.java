package mediator;

import model.Vehicle;
import java.util.*;
import java.util.stream.Collectors;

public class BoltMediator implements Mediator {

    private List<Vehicle> vehicles = new ArrayList<>();

    @Override
    public void registerVehicle(Vehicle v) {
        vehicles.add(v);
    }

    @Override
    public List<Vehicle> findClosestAvailable(int clientLocation, int tripDistance) {

        return vehicles.stream()
                .filter(v -> !v.isBusy())
                .filter(v -> v.canHandleTrip(tripDistance))
                .sorted(Comparator.comparingInt(v ->
                        Math.abs(v.getLocation() - clientLocation)))
                .limit(3)
                .collect(Collectors.toList());
    }
}
