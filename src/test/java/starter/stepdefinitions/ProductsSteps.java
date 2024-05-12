package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import starter.admin.Products;

public class ProductsSteps {
    @Steps
    Products product;

    @Given("I set API endpoint for create new product")
    public void setPostApiEndpoint() {
        product.setPostApiEndpoint();
    }

    @When("I send a POST request with valid new product data")
    public void sendNewProductRequest() {
        product.sendNewProductRequest();
    }

    @Then("I received a valid data from new product")
    public void receivedValidDataFromNewProduct() {
        product.receivedValidDataFromNewProduct();
    }

    @When("I send a POST request without new product data")
    public void sendInvalidNewProductRequest() {
        product.sendInvalidNewProductRequest();
    }

    @And("I received an error message")
    public void receivedErrorMessage() {
        product.receivedErrorMessage();
    }

    @Given("I set API endpoint for get product by ID")
    public void setGetProductApiEndpoint() {
        product.setGetProductApiEndpoint();
    }

    @When("I send a GET request with valid product ID")
    public void sendProductIdRequest() {
        product.sendProductIdRequest();
    }

    @Then("I received a valid data from product")
    public void receivedValidDataFromProduct() {
        product.receivedValidDataFromProduct();
    }

    @Given("I set API endpoint for invalid get product by ID")
    public void setGetApiEndpoint() {
        product.setGetApiEndpoint();
    }

    @When("I send a GET request with invalid product ID")
    public void sendProductIdIdRequest() {
        product.sendInvalidProductIdRequest();
    }

    @Then("I receive status code 404")
    public void receiveStatusCode404() {
        product.receiveStatusCode404();
    }

    @Given("I set API endpoint for get all product")
    public void setGetAllProductApiEndpoint() {
        product.setGetAllProductApiEndpoint();
    }

    @When("I send a GET request for get all product")
    public void sendAllProductRequest() {
        product.sendAllProductRequest();
    }

    @And("I received a valid data from all product")
    public void receivedValidDataFromAllProduct() {
        product.receivedValidDataFromAllProduct();
    }

    @Given("I set API endpoint for delete a product")
    public void setDeleteProductApiEndpoint() {
        product.setDeleteProductApiEndpoint();
    }

    @When("I send a DELETE request for delete a product")
    public void sendDeleteProductRequest() {
        product.sendDeleteProductRequest();
    }

    @And("Product successfully deleted")
    public void productDeleted() {
        product.productDeleted();
    }
}
