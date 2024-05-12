@Products
Feature: Product
  As a admin
  I want to access product feature
  So that I can manage all about product

  @Create-product
  Scenario: As a admin I can create a new product
    Given I set API endpoint for create new product
    When I send a POST request with valid new product data
    Then I receive status code 200
    And I received a valid data from new product

  @Cannot-create-product
  Scenario: As a admin I cannot create a new product without filling in the body data
    Given I set API endpoint for create new product
    When I send a POST request without new product data
    Then I receive status code 400
    And I received an error message

  @Get-id-product
  Scenario: As a admin I can get product by ID
    Given I set API endpoint for get product by ID
    When I send a GET request with valid product ID
    Then I receive status code 200
    And I received a valid data from product

  @Cannot-get-product
  Scenario: As a admin I cannot get product by ID with invalid ID
    Given I set API endpoint for invalid get product by ID
    When I send a GET request with invalid product ID
    Then I receive status code 404
    And I received an error message

  @Get-all-product
  Scenario: As a admin I can get all product
    Given I set API endpoint for get all product
    When I send a GET request for get all product
    Then I receive status code 200
    And I received a valid data from all product

  @Delete-product
  Scenario: As a admin I can delete a product
    Given I set API endpoint for delete a product
    When I send a DELETE request for delete a product
    Then I receive status code 200
    And Product successfully deleted