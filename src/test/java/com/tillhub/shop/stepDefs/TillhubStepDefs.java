package com.tillhub.shop.stepDefs;

import com.tillhub.shop.base.Base;
import com.tillhub.shop.pages.*;
import com.tillhub.shop.utilities.LogUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class TillhubStepDefs extends Base {

    private ShopPage shopPage = new ShopPage(driver);
    private BelegrollenPage belegrollenPage;
    private CartPage cartPage;
    private HardwarePage hardwarePage;
    private ServicesPage servicesPage;

    @Given("^I open tillhub home page on browser$")
    public void iOpenTillhubHomePageOnBrowser() {
        shopPage.openPage();
        Assertions.assertThat(shopPage.isShopPageLogoVisible()).isTrue();
    }

    @When("^I search for keyword (.*)$")
    public void iSearchText(String text) {
        shopPage = shopPage.searchText(text);
    }

    @Then("^Search results list contains desired item (.*)$")
    public void searchResultsPageShouldHaveZeroResults(String text){
        Assertions.assertThat(shopPage.doSearchResultsContainText(text)).isTrue();
    }

    @When("^I open item (.*)$")
    public void iOpenItem(String text){
        belegrollenPage = shopPage.clickSelectedItem(text);
        Assertions.assertThat(belegrollenPage.getProductInfo().isDisplayed());
    }

    @Then("^I see unit price is (.*) €$")
    public void iSeeUnitPriceIs€(String price) {
        Assertions.assertThat(belegrollenPage.isPriceCorrect(price)).isTrue();
    }

    @When("^I add quantity (.*) to the cart$")
    public void iAddQuantitiyToTheCart(String number) {
    //    LogUtil.log("Adding quantitiy to the cart " + number);
        belegrollenPage.incrementItem(number);
    //    Assertions.assertThat(belegrollenPage.isAmoutCorrect(number)).isTrue();
      //  Assertions.assertThat(belegrollenPage.getAmout()).isEqualTo(number);
        cartPage = belegrollenPage.AddtoChart();
    }

    @When("^I open the Cart$")
    public void iSwitchToCartTab() {
        cartPage = shopPage.switchToCartTab();
    }

    @And("^I switch to (.*) tab$")
    public void iSwitchToTab(String tab) {
        shopPage.switchToTab(tab);
        Assertions.assertThat(shopPage.checkPageisCorrect(tab)).isTrue();
    }

    @And("^I add item (.*) to the cart$")
    public void iAddItemToTheCart(String item) {
        cartPage = shopPage.addItemtoCart(item);
    }

    @Then("^I validate Subtotal:(.*) ,Tax:(.*) ,Total:(.*) Euros$")
    public void iValidateSubtotalTaxTotalEuros(String subtotal, String tax, String total) {
        Assertions.assertThat(cartPage.getSubTotalAmount()).isEqualTo(subtotal);
        Assertions.assertThat(cartPage.getTaxAmount()).isEqualTo(tax);
        Assertions.assertThat(cartPage.getTotalAmount()).isEqualTo(total);
    }
}