package com.tillhub.shop.pages;

import com.tillhub.shop.base.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Objects;

public class ServicesPage extends Base {

    @FindBy(css = "h1.s_title_default")
    private WebElement title;
    @FindBy(css = "a[title='Guest access']")
    private WebElement text;

    public ServicesPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(title);
    }

    public boolean checkTitleIsCorrect(String expectedTitle){
        waitUntilElementVisible(title);
        return Objects.equals(expectedTitle, title.getText());
    }

}
