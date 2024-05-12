@Authentication
Feature: Authentication
  As a admin
  I want to perform authentication
  So that I can access all feature

  @Register
  Scenario: As a admin I can register with valid credentials
    Given I set API endpoint for register
    When I send a POST request with valid user credentials
    Then I receive status code 200
    And I successfully create an account

  @Failed-register
  Scenario: As a admin I cannot register without credentials
    Given I set API endpoint for register
    When I send a POST request without user credentials
    Then I receive status code 400
    And I received an error message

  @Login
  Scenario: As a admin I can login with valid credentials
    Given I set API endpoint for login
    When I send a POST request with valid user data
    Then I receive status code 200
    And I successfully logged in to the account

  @Failed-login
  Scenario: As a admin I cannot login without credentials
    Given I set API endpoint for login
    When I send a POST request without valid user data
    Then I receive status code 400
    And I received an error message

  @User-information
  Scenario: As a admin I can get user information
    Given I set API endpoint for get user information
    When I send a GET request for get user information
    Then I receive status code 200
    And I get user information