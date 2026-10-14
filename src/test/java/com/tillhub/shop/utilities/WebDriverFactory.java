package com.tillhub.shop.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {

    private static final String OPERA = "opera";
    private static final String FIREFOX = "firefox";
    private static final String SAFARI = "safari";
    private static final String EDGE = "edge";
    private static final String INTERNET_EXPLORER = "ie";

    public static WebDriver setBrowser(String browser) {

        WebDriver webDriver = null;

        switch (browser) {
            case OPERA:
                WebDriverManager.operadriver().setup();
                webDriver = new OperaDriver();
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case SAFARI:
                webDriver = new SafariDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver();
                break;
            case INTERNET_EXPLORER:
                WebDriverManager.iedriver().setup();
                webDriver = new InternetExplorerDriver();
                break;
            default:	//Set default browser to Chrome
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
        }
        return webDriver;
    }
}
