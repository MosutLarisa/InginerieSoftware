import mediator.BoltMediator;
import model.Car;
import model.Client;
import model.Scooter;
import model.Vehicle;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        BoltMediator mediator = new BoltMediator();

        mediator.registerVehicle(new Car("Car A", 10));
        mediator.registerVehicle(new Scooter("Scooter 1", 105, 6));
        mediator.registerVehicle(new Scooter("Scooter 2", 25, 2));
        mediator.registerVehicle(new Scooter("Scooter 3", 65, 6));
        mediator.registerVehicle(new Car("Car B", 50));
        mediator.registerVehicle(new Car("Car C", 95));

        Client client = new Client(40);
        int tripDistanceKm = 3;

        List<Vehicle> options = mediator.findClosestAvailable(client.getLocation(), tripDistanceKm);

        System.out.println("Client is at km: " + client.getLocation() + " | Trip: " + tripDistanceKm + " km");
        System.out.println("Available closest vehicles:");

        for (Vehicle v : options) {
            System.out.println(v.getType() + " → " + v.getName() +
                    " at " + v.getLocation() + " km");
        }
    }
}
