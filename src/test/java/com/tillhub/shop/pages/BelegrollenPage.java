package com.tillhub.shop.pages;

import com.tillhub.shop.base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BelegrollenPage extends Base {

    private WebDriver driver;

    @FindBy(className = "input-group-append")
    private WebElement incrementButton;
    @FindBy(className = "input-group-prepend")
    private WebElement decrementButton;
    @FindBy (css = "b.oe_price")
    private WebElement priceTag;
    @FindBy (css = "ol.breadcrumb")
    private WebElement productInfo;
    @FindBy(id = "add_to_cart")
    private WebElement addToCartButton;
    @FindBy(xpath = "[title*='Add']")
    private WebElement increaseButton;
    @FindBy(css = "input.form-control.quantity")
    private WebElement textField;

    public BelegrollenPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilPageIsLoaded(driver);
    }

    public String getPrice() {
        waitUntilElementVisible(addToCartButton);
        return priceTag.getText().trim().split(" ")[0];
    }

    public void incrementItem(String number) {
        waitUntilElementVisible(textField);
        textField.clear();
        textField.click();
        textField.sendKeys(number);
    }

    public String getAmout() {
        return textField.getAttribute("value");
    }

    public CartPage AddtoChart() {
        waitUntilElementVisible(addToCartButton);
        addToCartButton.click();
        return new CartPage(driver);
    }

    public void incrementAmout(int times){
        for(int i = 0; i < times; i++){
            waitUntilElementVisible(increaseButton);
            increaseButton.click();
        }
    }

    public WebElement getProductInfo(){
        waitUntilElementVisible(productInfo);
        return productInfo;
    }
}
