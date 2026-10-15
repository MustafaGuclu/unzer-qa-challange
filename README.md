# Tillhub Shop

## Introduction

This is a test automation project testing "Tillhub.com” webpage on desktop browser(on default chrome)

Only "User successfully orders supplies and validates the Cart" test scenarios is automated.

Automated scenario steps are;
## Test case
**Title:** User successfully orders supplies and validates the Cart  

| Step | Action | Expected result |
|---|---|---|
| 1 | User opens “https://portal.tillhub.com/shop" website  | Tillhub shop page should be successfully opened. Logo should be displayed |
| 2 | User searches for keyword "Rollen" | Search results list should contain desired item "Belegrollen POS Go & Unzer”|
| 3 | User opens item "Belegrollen POS Go & Unzer" | Unit price should be "34,90" Euro|
| 4 | User adds 5 items to the Cart| 5 items should be added |
| 5 | User switches to “Services" tab | Check page title is “Services" |
| 6 | User  adds item "Schulung der Tillhub Kasse" to the cart |  |
| 7 | User switches to “Hardware" tab | Check page title is “Hardware" |
| 8 | Users adds item "Lenovo Tab M" to the cart |  |
| 9 | User opens the Cart | Subtotal should be "472,50" ,Tax should be "89,78" ,Total should be "562,28" Euros on the Cart page|


## Technology Used

* ### Java 21

* ### Selenium

* ### Junit

* ### Cucumber

* ### Maven

## How to Run Tests

    mvn clean test

On default it runs with Chrome but other browsers can be selected for example fro Firefox

    mvn -Dbrowser=firefox test

## Additional Information

- I used page object pattern with page factory.

- On fail scenarios sreenshots are added to screenshots folder and attached to reports on "target/cucumber-report.html/”

- Some custom logs are added to logs folder in html format

- Ideally Chrome, Firefox, Opera, Safari, Edge and Internet Explorer browsers are supported but I tested with only Chrome browser. I don’t guarantee that tests successfully run with all other browsers.

- I added locators in a way that they do not change on different languages but while searching a product in different language, i don't guarantee product can be found. I tested on English only.