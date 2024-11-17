import java.util.Scanner;

// Define the CabBooking interface
interface CabBooking {
    double calculateFare(double distance);  // Method for fare calculation based on distance
    double calculateFare(double distance, boolean isPeakHour);  // Overloaded method to include peak hour surcharge
}

// Base class - Cab implementing CabBooking interface
class Cab implements CabBooking {
    String cabType;
    double ratePerKm;

    public Cab(String cabType, double ratePerKm) {
        this.cabType = cabType;
        this.ratePerKm = ratePerKm;
    }

    // Method to calculate fare based on distance
    @Override
    public double calculateFare(double distance) {
        return ratePerKm * distance;
    }

    // Overloaded method to calculate fare with peak hour surcharge
    @Override
    public double calculateFare(double distance, boolean isPeakHour) {
        double fare = ratePerKm * distance;
        if (isPeakHour) {
            fare *= 1.2; // 20% increase during peak hours
        }
        return fare;
    }

    public void displayCabDetails() {
        System.out.println("Cab Type: " + cabType);
        System.out.println("Rate per km: $" + ratePerKm);
    }
}

// Derived class - EconomyCab
class EconomyCab extends Cab {
    public EconomyCab() {
        super("Economy", 1.0);
    }
}

// Derived class - LuxuryCab
class LuxuryCab extends Cab {
    public LuxuryCab() {
        super("Luxury", 2.5);
    }
}

// Main class
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Choose cab type
        System.out.println("Select Cab Type: ");
        System.out.println("1. Economy");
        System.out.println("2. Luxury");
        int choice = scanner.nextInt();

        Cab selectedCab;
        if (choice == 1) {
            selectedCab = new EconomyCab();
        } else if (choice == 2) {
            selectedCab = new LuxuryCab();
        } else {
            System.out.println("Invalid choice. Defaulting to Economy Cab.");
            selectedCab = new EconomyCab();
        }

        // Get distance and peak hour info
        System.out.print("Enter distance in km: ");
        double distance = scanner.nextDouble();

        System.out.print("Is it peak hour? (true/false): ");
        boolean isPeakHour = scanner.nextBoolean();

        // Calculate fare
        double fare = selectedCab.calculateFare(distance, isPeakHour);

        // Display booking details
        System.out.println("\n--- Booking Details ---");
        selectedCab.displayCabDetails();
        System.out.println("Distance: " + distance + " km");
        System.out.println("Peak Hour: " + isPeakHour);
        System.out.println("Total Fare: $" + fare);

        scanner.close();
    }
}
