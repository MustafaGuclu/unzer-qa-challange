package com.tillhub.shop.pages;

import com.tillhub.shop.base.Base;
import com.tillhub.shop.utilities.LogUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BelegrollenPage extends Base {

    private WebDriver driver;

    @FindBy(css = "input-group-append")
    private WebElement incrementButton;
    @FindBy(className = "input-group-prepend")
    private WebElement decrementButton;
    @FindBy (className = "oe_currency_value")
    private WebElement priceTag;
    @FindBy (css = "ol.breadcrumb")
    private WebElement productInfo;
    @FindBy(id = "add_to_cart")
    private WebElement addToCartButton;
    @FindBy(css = "[title*='Add']")
    private WebElement addButton;
    @FindBy(css = "input.form-control.quantity")
    private WebElement textField;

    public BelegrollenPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilPageIsLoaded(driver);
    }

    public Boolean isPriceCorrect(String price) {
        LogUtil.log("Price is : "+ price);
        waitUntilElementVisible(addToCartButton);
        return priceTag.getText().equalsIgnoreCase(price);
    }

    public void incrementItem(String number) {
        textField.clear();
        textField.click();
        textField.sendKeys(number);
    }

    public Boolean isAmoutCorrect(String number) {
        return textField.getText().equalsIgnoreCase(number);
    }

    public CartPage AddtoChart() {
        addToCartButton.click();
        return new CartPage(driver);
    }

    public void incrementAmout(int times){
        for(int i = 0; i < times; i++){
            waitUntilElementVisible(addButton);
            LogUtil.log("i is : "+ i);
            addButton.click();
        }
    }

    public WebElement getProductInfo(){
        waitUntilElementVisible(productInfo);
        return productInfo;
    }
}
