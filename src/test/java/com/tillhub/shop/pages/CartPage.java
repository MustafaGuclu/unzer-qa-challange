package com.tillhub.shop.pages;

import com.tillhub.shop.base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends Base {

    private WebDriver driver;

    @FindBy(id = "order_total")
    private WebElement inTotalText;
    @FindBy(id = "order_total_taxes")
    private WebElement steerText;
    @FindBy(id = "order_total_untaxed")
    private WebElement subtotalText;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        waitUntilElementVisible(inTotalText);
    }

    public String getTotalAmount() {
        waitUntilElementVisible(inTotalText);
        return inTotalText.getText().replaceAll("[^0-9,]", "").trim();
    }

    public String getSubTotalAmount() {
        waitUntilElementVisible(subtotalText);
        return subtotalText.getText().replaceAll("[^0-9,]", "").trim();
    }

    public String getTaxAmount() {
        waitUntilElementVisible(steerText);
        return steerText.getText().replaceAll("[^0-9,]", "").trim();
    }
}