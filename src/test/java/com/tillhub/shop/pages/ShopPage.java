package com.tillhub.shop.pages;

import com.tillhub.shop.base.Base;
import com.tillhub.shop.utilities.LogUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.NoSuchElementException;

public class ShopPage extends Base {

    private static final Logger log = LoggerFactory.getLogger(ShopPage.class);
    private WebDriver driver;
    private static final String url = "https://portal.tillhub.com/shop";

    @FindBy(className = "search-query")
    private WebElement searchBox;
    @FindBy(className = "input-group-append")
    private WebElement searchButton;
    @FindBy(className = "img-fluid")
    private WebElement logo;
    @FindBy(id = "cookiescript_reject")
    private WebElement declineButton;
    @FindBy(className = "oe_product_cart")
    private List<WebElement> searchResults;
    @FindBy(xpath = "//a[@href='/shop/category/services-5']")
    private WebElement servicesTab;
    @FindBy(xpath = "//a[@href='/shop/category/hardware-4']")
    private WebElement hardwareTab;
    @FindBy(xpath = "//a[@href='/shop/cart']")
    private WebElement cartTab;
    @FindBy(className = "s_title_default")
    private WebElement title;
    private final By itemImage = By.className("oe_product_image");
    private final By addToChartButton = By.className("o_wsale_product_btn");

    public ShopPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilPageIsLoaded(driver);
    }

    public void openPage(){
        driver.get(url);
        try {
            if(declineButton.isDisplayed()) declineButton.click();
        } catch (Exception ignored) {
            LogUtil.errorLog("Cookies can't be declined");
            log.debug(ignored.getMessage());
        }
        waitUntilElementVisible(searchButton);
    }

    public boolean isShopPageLogoVisible() {
        waitUntilElementVisible(logo);
        return logo.isDisplayed();
    }

    public ShopPage searchText(String text){
        searchBox.click();
        searchBox.sendKeys(text);
        searchButton.click();
        return new ShopPage(driver);
    }

    public List<WebElement> getSearchResults(){
        return searchResults;
    }

    public void switchToTab(String tab) {
        switch (tab) {
            case "Services" -> servicesTab.click();
            case "Hardware" -> hardwareTab.click();
            case "Cart" -> cartTab.click();
            case null, default -> LogUtil.errorLog("Tab " + tab + " not found");
        }
    }

    public CartPage switchToCartTab() {
        cartTab.click();
        return new CartPage(driver);
    }

    public String getPageTitle(){
        waitUntilElementVisible(logo);
        scrollDown();
        title.isDisplayed();
        return title.getText();
    }

    public WebElement returnSearchResultsContainText(String text){
        scrollDown();
        List<WebElement> results = getSearchResults();
        return results.stream().filter(element -> element.getText().contains(text)).
                findFirst().orElseThrow(NoSuchElementException::new);
    }

    public BelegrollenPage clickSelectedItem(String text) {
        returnSearchResultsContainText(text).findElement(itemImage).click();
        waitUntilElementVisible(logo);
        return new BelegrollenPage(driver);
    }

    public CartPage addItemtoCart(String item) {
        returnSearchResultsContainText(item).findElement(addToChartButton).click();
        return new CartPage(driver);
    }
}
