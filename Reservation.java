public sealed abstract class Reservation
    permits FlightReservation, HotelReservation {

  protected int confirmationNumber;

  public Reservation(int confirmationNumber) {
    this.confirmationNumber = confirmationNumber;
  }

  public int getConfirmationNumber() {
    return confirmationNumber;
  }

  public abstract void displayReservation();
}