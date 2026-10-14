package com.tillhub.shop.pages;

import com.tillhub.shop.base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class HardwarePage extends Base {
    private final WebDriver driver;

    @FindBy(className = "navbar-brand")
    private List<WebElement> logo;

    public HardwarePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        waitUntilPageIsLoaded(driver);
    }
}
