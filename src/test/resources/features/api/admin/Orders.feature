@Orders
Feature: Order
  As a admin
  I want to access order feature
  So that I can manage all about order

  @Create-order
  Scenario: As a admin I can create a new order
    Given I set API endpoint for create a new order
    When I send a POST request with valid new order data
    Then I receive status code 200
    And I received a valid data from new order

  @Cannot-create-order
  Scenario: As a admin I cannot create a new order without filling in the body data
    Given I set API endpoint for create a new order
    When I send a POST request without new order data
    Then I receive status code 400
    And I received an error message

  @Get-order
  Scenario: As a admin I can get all orders
    Given I set API endpoint for get all orders
    When I send a GET request for get all orders
    Then I receive status code 200
    And I received a valid data from orders