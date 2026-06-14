import java.util.ArrayList;
import java.util.Scanner;

public class TravelBookingSystem {

  private ArrayList<Flight> flights;
  private ArrayList<Hotel> hotels;
  private ArrayList<Reservation> reservations;

  private Scanner scanner;

  public TravelBookingSystem() {

    flights = new ArrayList<>();
    hotels = new ArrayList<>();
    reservations = new ArrayList<>();

    scanner = new Scanner(System.in);

    seedData();
  }

  // =========================
  // INPUT VALIDATION METHOD
  // =========================
  private int getIntInput(String message) {
    System.out.print(message);

    while (true) {
      try {
        int value = scanner.nextInt();
        scanner.nextLine(); // buang newline
        return value;
      } catch (Exception e) {
        System.out.print("Invalid input! Enter a number: ");
        scanner.nextLine();
      }
    }
  }

  // =========================
  // SEED DATA
  // =========================
  private void seedData() {

    flights.add(new Flight("GA101", "Jakarta", "Bali", 1200000));
    flights.add(new Flight("JT202", "Bandung", "Surabaya", 900000));

    hotels.add(new Hotel(1, "Grand Bali", "Bali", 800000));
    hotels.add(new Hotel(2, "Sunrise Hotel", "Jakarta", 600000));
  }

  // =========================
  // MAIN MENU
  // =========================
  public void start() {

    while (true) {

      System.out.println("\n===== Travel Booking System =====");
      System.out.println("1. Search Flight");
      System.out.println("2. Search Hotel");
      System.out.println("3. Show Flights");
      System.out.println("4. Show Hotels");
      System.out.println("5. Book Flight");
      System.out.println("6. View Reservations");
      System.out.println("7. Book Hotel");
      System.out.println("8. Cancel Reservation");
      System.out.println("9. Exit");

      int choice = getIntInput("Choose menu: ");

      switch (choice) {

        case 1 -> searchFlight();
        case 2 -> searchHotel();
        case 3 -> showFlights();
        case 4 -> showHotels();
        case 5 -> bookFlight();
        case 6 -> viewReservations();
        case 7 -> bookHotel();
        case 8 -> cancelReservation();
        case 9 -> {
          System.out.println("Thank you!");
          return;
        }
        default -> System.out.println("Invalid choice!");
      }
    }
  }

  // =========================
  // SHOW DATA
  // =========================
  public void showFlights() {
    System.out.println("\n===== FLIGHTS =====");

    for (Flight flight : flights) {
      flight.displayDetails();
      System.out.println("----------------");
    }
  }

  public void showHotels() {
    System.out.println("\n===== HOTELS =====");

    for (Hotel hotel : hotels) {
      hotel.displayDetails();
      System.out.println("----------------");
    }
  }

  // =========================
  // SEARCH FLIGHT
  // =========================
  public void searchFlight() {

    System.out.print("Origin: ");
    String origin = scanner.nextLine();

    System.out.print("Destination: ");
    String destination = scanner.nextLine();

    boolean found = false;

    for (Flight flight : flights) {

      if (flight.getOrigin().equalsIgnoreCase(origin)
          && flight.getDestination().equalsIgnoreCase(destination)) {

        flight.displayDetails();
        found = true;
        System.out.println("----------------");
      }
    }

    if (!found) {
      System.out.println("No flights found.");
    }
  }

  // =========================
  // SEARCH HOTEL
  // =========================
  public void searchHotel() {

    System.out.print("Location: ");
    String location = scanner.nextLine();

    boolean found = false;

    for (Hotel hotel : hotels) {

      if (hotel.getLocation().equalsIgnoreCase(location)) {

        hotel.displayDetails();
        found = true;
        System.out.println("----------------");
      }
    }

    if (!found) {
      System.out.println("No hotels found.");
    }
  }

  // =========================
  // BOOK FLIGHT
  // =========================
  public void bookFlight() {

    System.out.println("\n===== AVAILABLE FLIGHTS =====");

    for (int i = 0; i < flights.size(); i++) {
      System.out.println((i + 1) + ". ");
      flights.get(i).displayDetails();
      System.out.println("----------------");
    }

    int choice = getIntInput("Choose Flight Number: ");

    if (choice < 1 || choice > flights.size()) {
      System.out.println("Flight not found!");
      return;
    }

    Flight selectedFlight = flights.get(choice - 1);

    int confirmationNumber = ConfirmationGenerator.generateConfirmationNumber();

    FlightReservation reservation = new FlightReservation(confirmationNumber, selectedFlight);

    reservations.add(reservation);

    System.out.println("\nFlight booked successfully!");
    System.out.println("Confirmation Number: " + confirmationNumber);
  }

  // =========================
  // VIEW RESERVATIONS
  // =========================
  public void viewReservations() {

    if (reservations.isEmpty()) {
      System.out.println("No reservations found.");
      return;
    }

    System.out.println("\n===== RESERVATIONS =====");

    for (Reservation reservation : reservations) {
      reservation.displayReservation();
      System.out.println("----------------");
    }
  }

  // =========================
  // BOOK HOTEL
  // =========================
  public void bookHotel() {

    System.out.println("\n===== AVAILABLE HOTELS =====");

    for (int i = 0; i < hotels.size(); i++) {
      System.out.println((i + 1) + ". ");
      hotels.get(i).displayDetails();
      System.out.println("----------------");
    }

    int choice = getIntInput("Choose Hotel Number: ");

    if (choice < 1 || choice > hotels.size()) {
      System.out.println("Invalid choice!");
      return;
    }

    Hotel selectedHotel = hotels.get(choice - 1);

    int confirmationNumber = ConfirmationGenerator.generateConfirmationNumber();

    HotelReservation reservation = new HotelReservation(confirmationNumber, selectedHotel);

    reservations.add(reservation);

    System.out.println("\nHotel booked successfully!");
    System.out.println("Confirmation Number: " + confirmationNumber);
  }

  // =========================
  // CANCEL RESERVATION
  // =========================
  public void cancelReservation() {

    int confirmationNumber = getIntInput("Enter Confirmation Number: ");

    boolean removed = reservations.removeIf(
        r -> r.getConfirmationNumber() == confirmationNumber);

    if (removed) {
      System.out.println("Reservation cancelled successfully!");
    } else {
      System.out.println("Reservation not found!");
    }
  }
}