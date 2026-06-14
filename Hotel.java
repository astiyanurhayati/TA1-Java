public class Hotel implements Bookable {
  private int hotelId;
  private String hotelName;
  private String location;
  private double price;

  public Hotel(int hotelId, String hotelName,
      String location, double price) {

    this.hotelId = hotelId;
    this.hotelName = hotelName;
    this.location = location;
    this.price = price;
  }

  public int getHotelId() {
    return hotelId;
  }

  public String getHotelName() {
    return hotelName;
  }

  public String getLocation() {
    return location;
  }

  public double getPrice() {
    return price;
  }

  @Override
  public void displayDetails() {

    System.out.println("Hotel ID   : " + hotelId);
    System.out.println("Hotel Name : " + hotelName);
    System.out.println("Location   : " + location);
    System.out.println("Price      : " + price);

  }
}