public class Flight implements Bookable {
  private String flightNumber;
  private String origin;
  private String destination;
  private double price;

  public Flight(String flightNumber, String origin,
      String destination, double price) {

    this.flightNumber = flightNumber;
    this.origin = origin;
    this.destination = destination;
    this.price = price;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public String getOrigin() {
    return origin;
  }

  public String getDestination() {
    return destination;
  }

  public double getPrice() {
    return price;
  }

  @Override
  public void displayDetails() {
    System.out.println("Flight Number : " + flightNumber);
    System.out.println("Origin        : " + origin);
    System.out.println("Destination   : " + destination);
    System.out.println("Price         : " + price);
  }

}
