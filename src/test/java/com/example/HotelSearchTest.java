package com.example;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HotelSearchTest {
  private static final String HOTEL_SITE_URL =
      "https://hotel-search-selenium-practice.cdclydesdale.chatgpt.site";

  private WebDriver driver;
  private List<Hotel> scrapedHotels = List.of();

  @BeforeClass
  public void openHotelSite() {
    driver = new ChromeDriver();
    driver.get(HOTEL_SITE_URL);
  }

  @Test
  public void scrapesHotelResults() {
    scrapedHotels = new HotelScraper(driver).scrapeHotels();

    Assert.assertFalse(scrapedHotels.isEmpty(), "Expected the hotel page to contain listings");
  }

  @AfterClass(alwaysRun = true)
  public void closeBrowser() {
    try {
      if (driver != null) {
        driver.quit();
      }
    } finally {
      printScrapedHotels();
    }
  }

  private void printScrapedHotels() {
    Reporter.log("", true);
    Reporter.log("Scraped hotel report (browser closed)", true);
    Reporter.log("-------------------------------------", true);

    for (Hotel hotel : scrapedHotels) {
      Reporter.log(
          String.format(
              "%-30s | $%7.2f per night | Pool: %s",
              hotel.getHotelName(),
              hotel.getHotelPrice(),
              hotel.isHasPool() ? "Yes" : "No"),
          true);
    }

    Reporter.log("-------------------------------------", true);
    Reporter.log("Total hotels: " + scrapedHotels.size(), true);
  }
}
