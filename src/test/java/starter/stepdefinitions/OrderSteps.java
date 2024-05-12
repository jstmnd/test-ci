package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import starter.admin.Orders;

public class OrderSteps {
    @Steps
    Orders orders;

    @Given("I set API endpoint for create a new order")
    public void setPostOrderApiEndpoint() {
        orders.setPostOrderApiEndpoint();
    }

    @When("I send a POST request with valid new order data")
    public void sendNewOrderRequest() {
        orders.sendNewOrderRequest();
    }

    @And("I received a valid data from new order")
    public void receivedValidDataFromNewOrder() {
        orders.receivedValidDataFromNewOrder();
    }

    @When("I send a POST request without new order data")
    public void sendInvalidNewOrderRequest() {
        orders.sendInvalidNewOrderRequest();
    }

    @Given("I set API endpoint for get all orders")
    public void setGetOrdersApiEndpoint() {
        orders.setGetOrdersEndpoint();
    }

    @When("I send a GET request for get all orders")
    public void sendGetOrdersRequest() {
        orders.sendGetAllOrdersRequest();
    }

    @Then("I received a valid data from orders")
    public void receiveOrdersData() {
        orders.receivedValidDataFromOrders();
    }
}
