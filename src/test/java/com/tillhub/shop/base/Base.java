package com.tillhub.shop.base;

import com.tillhub.shop.utilities.LogUtil;
import com.tillhub.shop.utilities.WebDriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base {

    private static final int TIME_OUT = 10;
    protected static WebDriver driver;

    protected void waitUntilElementVisible(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
        ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(webElement);
        wait.until(condition);
    }

    public void waitUntilPageIsLoaded(WebDriver driver) {
        new WebDriverWait(driver, TIME_OUT).until(
                webDriver -> "complete".equals(
                        ((JavascriptExecutor) webDriver)
                                .executeScript("return document.readyState")
                )
        );
    }

    public void scrollDown() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0, 500);");
    }

    void setWebDriver(String browser) {
        try {
            driver = WebDriverFactory.setBrowser(browser);
            LogUtil.log("Browser is " + browser);
            driver.manage().window().maximize();
        } catch (Exception e) {
            LogUtil.errorLog("Browser can not be set");
            e.printStackTrace();
        }
    }
}
