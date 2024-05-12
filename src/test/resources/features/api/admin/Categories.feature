@Categories
Feature: Categories
  As a admin
  I want to access categories feature
  So that I can manage all about categories

  @Create-category
  Scenario: As a admin I can create a category
    Given I set API endpoint for create category
    When I send a POST request with valid category data
    Then I receive status code 200
    And I received a valid category data

  @Cannot-create-category
  Scenario: As a admin I cannot create a category without filling in the body data
    Given I set API endpoint for create category
    When I send a POST request without category data
    Then I receive status code 400
    And I received an error message

  @Get-id-category
  Scenario: As a admin I can get category by ID
    Given I set API endpoint for get category by ID
    When I send a GET request with valid category ID
    Then I receive status code 200
    And I received a valid data from category

  @Cannot-get-category
  Scenario: As a admin I cannot get category by ID with invalid ID
    Given I set API endpoint for invalid get category by ID
    When I send a GET request with invalid category ID
    Then I receive status code 404
    And I received an error message

  @Get-all-category
  Scenario: As a admin I can get all category
    Given I set API endpoint for all category
    When I send a GET request for all category
    Then I receive status code 200
    And I received a valid data from all category

  @Delete-category
  Scenario: As a admin I can delete a category
    Given I set API endpoint for delete a category
    When I send a DELETE request for delete a category
    Then I receive status code 200
    And Category successfully deleted