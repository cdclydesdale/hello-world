package com.example;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Blueprint for extracting Hotel objects from the Hotel Search Results page.
 */
public class HotelScraper {
  private static final By HOTEL_LISTINGS = By.cssSelector(".hotel-listing");
  private static final By HOTEL_NAME = By.cssSelector(".hotel-name");
  private static final By PRICE = By.cssSelector(".price");
  private static final By POOL_ICON = By.cssSelector(".pool-icon");

  private final WebDriver driver;

  public HotelScraper(WebDriver driver) {
    this.driver = driver;
  }

  public List<Hotel> scrapeHotels() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    List<WebElement> hotelCards =
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(HOTEL_LISTINGS, 0));

    return hotelCards.stream()
        .map(card -> {
          String hotelName = card.findElement(HOTEL_NAME).getText();
          String priceText = card.findElement(PRICE).getText().replace("$", "").trim();
          boolean hasPool = !card.findElements(POOL_ICON).isEmpty();
          double price = Double.parseDouble(priceText);
          return new Hotel(hotelName, price, hasPool);
        })
        .toList();
  }
}
