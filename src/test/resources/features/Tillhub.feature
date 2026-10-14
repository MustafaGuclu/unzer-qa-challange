Feature: Order supplies and validate the cart
  As a merchant running a cafe
  I want to add receipt rolls, cashier training, and a tablet
  So that I can verify the expected cart totals before checkout

  Scenario: User successfully orders supplies and validates the Cart
    Given I open tillhub home page on browser
    When I search for keyword Rollen
    Then Search results list contains desired item Belegrollen POS Go & Unzer
    When I open item Belegrollen POS Go & Unzer
    Then I see unit price is 34,90 €
    When I add quantity 5 to the cart
    And I switch to Services tab
    And I add item Schulung der Tillhub Kasse to the cart
    And I switch to Hardware tab
    And I add item Lenovo Tab M to the cart
    And I open the Cart
    Then I validate Subtotal:472,50 ,Tax:89,78 ,Total:562,28 Euros