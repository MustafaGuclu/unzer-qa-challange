## Technology Used

* ### Java

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

- On fail scenarios sreenshots are added to screenshots folder and attached to reports on "target/cucumber-html-reports/helpcenter/index.html”

- Some custom logs are added to logs folder in html format

- Ideally Chrome, Firefox, Opera, Safari, Edge and Internet Explorer browsers are supported but I tested with only Chrome and Firefox browser. I don’t guarantee that tests successfully run with all other browsers.