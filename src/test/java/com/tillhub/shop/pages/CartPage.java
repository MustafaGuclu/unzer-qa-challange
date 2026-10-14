package com.tillhub.shop.pages;

import com.tillhub.shop.base.Base;
import com.tillhub.shop.utilities.LogUtil;
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

    public Boolean isTotalCorrect(String inTotal) {
        LogUtil.log(" inTotalText.getText() : "+  inTotalText.getText());

        waitUntilElementVisible(inTotalText);
        return inTotalText.getText().contains(inTotal);
    }

    public Boolean isSubtotalCorrect(String subTotal) {
        waitUntilElementVisible(subtotalText);
        return subtotalText.getText().contains(subTotal);
    }

    public Boolean isTaxCorrect(String tax) {
        waitUntilElementVisible(steerText);
        return steerText.getText().contains(tax);
    }
}
