package mediator;

import model.Vehicle;
import java.util.List;

public interface Mediator {
    void registerVehicle(Vehicle v);
    List<Vehicle> findClosestAvailable(int clientLocation, int tripDistance);
}
