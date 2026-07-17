package com.example;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public final class HotelSearchStarter {
  private static final String HOTEL_SITE_URL =
      "https://hotel-search-selenium-practice.cdclydesdale.chatgpt.site";

  private HotelSearchStarter() {}

  public static void main(String[] args) throws IOException {
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();

    try {
      driver.get(HOTEL_SITE_URL);

      HotelScraper hotelScraper = new HotelScraper(driver);
      List<Hotel> hotels = hotelScraper.scrapeHotels();

      System.out.println("Scraped hotels:");
      for (Hotel hotel : hotels) {
        System.out.printf(
            "%s | $%.2f per night | Pool: %s%n",
            hotel.getHotelName(),
            hotel.getHotelPrice(),
            hotel.isHasPool());
      }

      List<String> budgetPoolHotelNames = findBudgetPoolHotelNames(hotels);
      System.out.println("Budget hotels with pools: " + budgetPoolHotelNames);

      System.out.println("The hotel site is open. Press Enter to close Chrome.");
      System.in.read();

      List<String> premiumPoolHotels = findPremiumHotelNames(hotels);
      System.out.println("Premium hotels with pools: " + premiumPoolHotels);
      System.in.read();
    } finally {
      driver.quit();
    }
  }

  private static List<String> findBudgetPoolHotelNames(List<Hotel> scrapedHotels) {
    // TODO Start one stream pipeline with scrapedHotels.stream().
    // TODO Keep hotels where hotel.isHasPool() is true.
    // TODO Keep hotels whose getHotelPrice() is less than 150.00.
    // TODO Map each remaining Hotel to getHotelName(), then collect into List<String>.

   List<String> budgetHotel = scrapedHotels.stream()
           .filter(Hotel::isHasPool)
           .filter(h -> h.getHotelPrice() < 150)
           .map(Hotel::getHotelName)
           .toList();

    return budgetHotel;
  }

  private static List<String> findPremiumHotelNames(List<Hotel> scrapedHotels) {
    // Find all the premium hotel that cost > $200 and has a pool

    List<String> premiumHotel = scrapedHotels.stream()
            .filter(Hotel::isHasPool)
            .filter(h -> h.getHotelPrice() > 200)
            .map(Hotel::getHotelName)
            .toList();

    return premiumHotel;
  }
}
