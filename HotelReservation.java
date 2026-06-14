public final class HotelReservation
    extends Reservation {

  private Hotel hotel;

  public HotelReservation(
      int confirmationNumber,
      Hotel hotel) {

    super(confirmationNumber);
    this.hotel = hotel;
  }

  @Override
  public void displayReservation() {

    System.out.println(
        "Hotel Reservation #" +
            confirmationNumber);

    hotel.displayDetails();
  }
}