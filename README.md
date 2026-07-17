# Hotel Search Selenium Practice

This Java Selenium starter opens the deployed Hotel Search Results practice page
in Chrome.

## Run it

Install Java 17+ and Maven, then run:

```bash
mvn compile exec:java -Dexec.mainClass=com.example.HotelSearchStarter
```

`HotelSearchStarter` uses Selenium Manager to locate or provision a compatible
Chrome driver, then navigates to the practice page with `driver.get()`.
