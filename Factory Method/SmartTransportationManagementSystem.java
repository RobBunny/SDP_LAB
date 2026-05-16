import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/*
 * =========================================================
 * SMART TRANSPORTATION MANAGEMENT SYSTEM
 * =========================================================
 */

/* =========================================================
 * PRODUCT INTERFACE
 * =========================================================
 */
interface Vehicle {
    void startTrip();

    double calculateFare(double distance);

    void assignRoute(String route);
    
    void getVehicleInfo();
    
    String getVehicleType();
}

/* =========================================================
 * CONCRETE PRODUCTS
 * =========================================================
 */

class Bus implements Vehicle {

    private final int capacity = 40;
    private final double farePerKm = 15;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Bus trip started.");
        assignRoute(route);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Bus assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle: Bus");
        System.out.println("Capacity: " + capacity);
        System.out.println("Fare per KM: " + farePerKm);
    }

    @Override
    public String getVehicleType() {
        return "Bus";
    }
}

class Taxi implements Vehicle {

    private final int capacity = 4;
    private final double farePerKm = 30;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Taxi trip started.");
        assignRoute(route);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Taxi assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle: Taxi");
        System.out.println("Capacity: " + capacity);
        System.out.println("Fare per KM: " + farePerKm);
    }

    @Override
    public String getVehicleType() {
        return "Taxi";
    }
}

class MotorcycleDelivery implements Vehicle {

    private final int capacity = 1;
    private final double farePerKm = 10;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Motorcycle delivery started.");
        assignRoute(route);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Motorcycle assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle: Motorcycle Delivery");
        System.out.println("Capacity: " + capacity);
        System.out.println("Fare per KM: " + farePerKm);
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle Delivery";
    }
}

class ElectricScooter implements Vehicle {

    private final int capacity = 1;
    private final double farePerKm = 8;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Electric scooter trip started.");
        assignRoute(route);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Scooter assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle: Electric Scooter");
        System.out.println("Capacity: " + capacity);
        System.out.println("Fare per KM: " + farePerKm);
    }

    @Override
    public String getVehicleType() {
        return "Electric Scooter";
    }
}

class Truck implements Vehicle {

    private final int capacity = 40;
    private final double farePerKm = 15;
    private String route;

    @Override
    public void startTrip() {
        System.out.println("Truck trip started.");
        assignRoute(route);
    }

    @Override
    public double calculateFare(double distance) {
        return distance * farePerKm;
    }

    @Override
    public void assignRoute(String route) {
        this.route = route;
        System.out.println("Truck assigned to route: " + route);
    }

    @Override
    public void getVehicleInfo() {
        System.out.println("Vehicle: Truck");
        System.out.println("Capacity: " + capacity);
        System.out.println("Fare per KM: " + farePerKm);
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }
}

/* =========================================================
 * FACTORY CLASS
 * =========================================================
 */

abstract class VehicleFactory {
    public Vehicle chooseVehicle() {
        Vehicle vehicle = createVehicle();
        return vehicle;
    }

    public abstract Vehicle createVehicle();
}

/* =========================================================
 * FACTORY CLASSES
 * =========================================================
*/

class BusFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Bus();
    }
}

class TaxiFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Taxi();
    }
}

class MotorcycleFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new MotorcycleDelivery();
    }
}

class ElectricScooterFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new ElectricScooter();
    }
}

class TruckFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Truck();
    }
}

/* =========================================================
 * MAIN APPLICATION
 * =========================================================
 */

public class SmartTransportationManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<VehicleFactory> vehicles = new ArrayList<>();

        System.out.println("Welcome to the Smart Transportation Management System");

        vehicles.add(new BusFactory());
        vehicles.add(new TaxiFactory());
        vehicles.add(new MotorcycleFactory());
        vehicles.add(new ElectricScooterFactory());
        vehicles.add(new TruckFactory());

        System.out.println("Select a vehicle type:");
        for (int i = 0; i < vehicles.size(); i++) {
            System.out.println((i + 1) + ". " + vehicles.get(i).createVehicle().getVehicleType());
        }

        int choice = scanner.nextInt();
        VehicleFactory vehicleFactory = vehicles.get(choice - 1);
        Vehicle vehicle = vehicleFactory.chooseVehicle();
        System.out.println("Enter the distance:");
        double distance = scanner.nextDouble();
        double fare = vehicle.calculateFare(distance);
        System.out.println("Enter the route:");
        String route = scanner.next();
        vehicle.assignRoute(route);
        vehicle.startTrip();
        vehicle.getVehicleInfo();
        System.out.println("Fare: " + fare);

        scanner.close();
    }
}