package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import starter.admin.Authentication;

public class AuthenticationSteps {
    @Steps
    Authentication authentication;

    @Given("I set API endpoint for register")
    public void setRegistercApiEndpoint() {
        authentication.setRegisterApiEndpoint();
    }

    @When("I send a POST request with valid user credentials")
    public void sendRegisterRequest() {
        authentication.sendRegisterRequest();
    }

    @Then("I receive status code 200")
    public void receiveStatusCode200() {
        authentication.receiveStatusCode200();
    }

    @And("I successfully create an account")
    public void successfullyCreateAccount() {
        authentication.successfullyCreateAccount();
    }

    @When("I send a POST request without user credentials")
    public void sendInvalidRegisterRequest() {
        authentication.sendInvalidRegisterRequest();
    }

    @Then("I receive status code 400")
    public void receiveStatusCode400() {
        authentication.receiveStatusCode400();
    }

    @Given("I set API endpoint for login")
    public void setLoginApiEndpoint() {
        authentication.setLoginApiEndpoint();
    }

    @When("I send a POST request with valid user data")
    public void sendLoginRequest() {
        authentication.sendLoginRequest();
    }

    @And("I successfully logged in to the account")
    public void successfullyLoggedIn() {
        authentication.successfullyLoggedIn();
    }

    @When("I send a POST request without valid user data")
    public void sendInvalidLoginRequest() {
        authentication.sendInvalidLoginRequest();
    }

    @Given("I set API endpoint for get user information")
    public void setGetUserApiEndpoint() {
        authentication.setGetUserApiEndpoint();
    }

    @When("I send a GET request for get user information")
    public void sendUserInfoRequest() {
        authentication.sendUserInfoRequest();
    }

    @Then("I get user information")
    public void successfullyGetUserInfo() {
        authentication.successfullyGetUserInfo();
    }
}
