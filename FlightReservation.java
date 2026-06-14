public final class FlightReservation
    extends Reservation {

  private Flight flight;

  public FlightReservation(
      int confirmationNumber,
      Flight flight) {

    super(confirmationNumber);
    this.flight = flight;
  }

  @Override
  public void displayReservation() {

    System.out.println(
        "Flight Reservation #" +
            confirmationNumber);

    flight.displayDetails();
  }
}