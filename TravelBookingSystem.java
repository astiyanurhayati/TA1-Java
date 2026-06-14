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

  private void seedData() {

    flights.add(
        new Flight(
            "GA101",
            "Jakarta",
            "Bali",
            1200000));

    flights.add(
        new Flight(
            "JT202",
            "Bandung",
            "Surabaya",
            900000));

    hotels.add(
        new Hotel(
            1,
            "Grand Bali",
            "Bali",
            800000));

    hotels.add(
        new Hotel(
            2,
            "Sunrise Hotel",
            "Jakarta",
            600000));
  }

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

      int choice = scanner.nextInt();

      switch (choice) {

        case 1:
          searchFlight();
          break;

        case 2:
          searchHotel();
          break;

        case 3:
          showFlights();
          break;

        case 4:
          showHotels();
          break;

        case 5:
          bookFlight();
          break;

        case 6:
          viewReservations();
          break;

        case 7:
          bookHotel();
          break;

        case 8:
          cancelReservation();
          break;

        case 9:
          System.out.println("Thank you!");
          return;
        default:
          System.out.println("Invalid choice!");
      }
    }
  }

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

  public void searchFlight() {

    scanner.nextLine();

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

  public void searchHotel() {

    scanner.nextLine();

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

  public void bookFlight() {

    System.out.println("\n===== AVAILABLE FLIGHTS =====");

    for (int i = 0; i < flights.size(); i++) {

      System.out.println((i + 1) + ". ");

      flights.get(i).displayDetails();

      System.out.println("----------------");
    }

    System.out.print("Choose Flight Number: ");

    int choice = scanner.nextInt();

    if (choice < 1 || choice > flights.size()) {

      System.out.println("Invalid choice!");
      return;
    }

    Flight selectedFlight = flights.get(choice - 1);

    int confirmationNumber = ConfirmationGenerator.generateConfirmationNumber();

    FlightReservation reservation = new FlightReservation(
        confirmationNumber,
        selectedFlight);

    reservations.add(reservation);

    System.out.println(
        "\nFlight booked successfully!");

    System.out.println(
        "Confirmation Number: "
            + confirmationNumber);
  }

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

  public void bookHotel() {

    System.out.println("\n===== AVAILABLE HOTELS =====");

    for (int i = 0; i < hotels.size(); i++) {

      System.out.println((i + 1) + ".");

      hotels.get(i).displayDetails();

      System.out.println("----------------");
    }

    System.out.print("Choose Hotel Number: ");

    int choice = scanner.nextInt();

    if (choice < 1 || choice > hotels.size()) {

      System.out.println("Invalid choice!");
      return;
    }

    Hotel selectedHotel = hotels.get(choice - 1);

    int confirmationNumber = ConfirmationGenerator.generateConfirmationNumber();

    HotelReservation reservation = new HotelReservation(
        confirmationNumber,
        selectedHotel);

    reservations.add(reservation);

    System.out.println(
        "\nHotel booked successfully!");

    System.out.println(
        "Confirmation Number: "
            + confirmationNumber);
  }

  public void cancelReservation() {

    System.out.print(
        "Enter Confirmation Number: ");

    int confirmationNumber = scanner.nextInt();

    boolean removed = reservations.removeIf(
        r -> r.getConfirmationNumber() == confirmationNumber);

    if (removed) {

      System.out.println(
          "Reservation cancelled!");

    } else {

      System.out.println(
          "Reservation not found.");
    }
  }
}