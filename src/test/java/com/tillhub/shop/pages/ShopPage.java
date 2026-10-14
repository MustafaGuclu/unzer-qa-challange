package com.tillhub.shop.pages;

import com.tillhub.shop.base.Base;
import com.tillhub.shop.utilities.LogUtil;
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
    @FindBy(className = "a.nav-link")
    private List<WebElement> topMenu;
    @FindBy(xpath = "//a[@href='/shop/category/services-5']")
    private WebElement servicesTab;
    @FindBy(className = "o_wsale_product_btn")
    private List<WebElement> addToChartButtons;
    @FindBy(xpath = "//a[@href='/shop/category/hardware-4']")
    private WebElement hardwareTab;
    @FindBy(xpath = "//a[@href='/shop/cart']")
    private WebElement cartTab;
    @FindBy(className = "o_wsale_products_item_title")
    private List<WebElement> itemTitles;
    @FindBy(className = "oe_product_image")
    private List<WebElement> itemImages;
    @FindBy(className = "s_title_default")
    private WebElement title;

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

    public List<WebElement> getTopMenu(){
        return topMenu;

    }
    public boolean checkPageisCorrect(String expectedTitle){
        waitUntilElementVisible(logo);
        scrollDown();
        title.isDisplayed();
        return title.getText().equals(expectedTitle);
    }

    public int searchResultsContainText(String text){
        scrollDown();
        List<WebElement> results = getSearchResults();
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i).getText().contains(text)) {
                LogUtil.log("i the index is: " + i);
                return i;
            }
        }
        return -1;
    }

    public Boolean doSearchResultsContainText(String text) {
        return searchResultsContainText(text)!=-1;
    }

    public BelegrollenPage clickSelectedItem(String text) {
        int index = searchResultsContainText(text);
        List<WebElement> results = getSearchResults();
        if (index < 0) {
            throw new NoSuchElementException(
                    "Could not find item in search results: " + text
            );
        }
        itemImages.get(index).click();
        LogUtil.log("image is clicked with index: " + index);
        waitUntilElementVisible(logo);

        return new BelegrollenPage(driver);
    }

    public CartPage addItemtoCart(String item) {
        int index = searchResultsContainText(item);
        addToChartButtons.get(index).click();
        LogUtil.log("add to char clicked, index is:" + index);
        return new CartPage(driver);
    }
}
