package starter.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Steps;
import starter.admin.Categories;

public class CategoriesSteps {
    @Steps
    Categories categories;

    @Given("I set API endpoint for create category")
    public void setPostApiEndpoint() {
        categories.setPostApiEndpoint();
    }

    @When("I send a POST request with valid category data")
    public void sendCategoryRequest() {
        categories.sendCategoryRequest();
    }

    @And("I received a valid category data")
    public void receivedValidCategoryData() {
        categories.receivedValidCategoryData();
    }

    @When("I send a POST request without category data")
    public void sendInvalidCategoryRequest() {
        categories.sendInvalidCategoryRequest();
    }

    @Given("I set API endpoint for get category by ID")
    public void setGetCategoryApiEndpoint() {
        categories.setGetCategoryApiEndpoint();
    }

    @When("I send a GET request with valid category ID")
    public void sendCategoryIdRequest() {
        categories.sendCategoryIdRequest();
    }

    @Then("I received a valid data from category")
    public void receivedValidDataFromCategory() {
        categories.receivedValidDataFromCategory();
    }

    @Given("I set API endpoint for invalid get category by ID")
    public void setGetApiEndpoint() {
        categories.setGetApiEndpoint();
    }

    @When("I send a GET request with invalid category ID")
    public void sendInvalidCategoryIdRequest() {
        categories.sendInvalidCategoryIdRequest();
    }

    @Given("I set API endpoint for all category")
    public void setGetAllCategoryApiEndpoint() {
        categories.setGetAllCategoryApiEndpoint();
    }

    @When("I send a GET request for all category")
    public void sendAllCategoryRequest() {
        categories.sendAllCategoryRequest();
    }

    @And("I received a valid data from all category")
    public void receivedValidDataFromAllCategory() {
        categories.receivedValidDataFromAllCategory();
    }

    @Given("I set API endpoint for delete a category")
    public void setDeleteCategoryApiEndpoint() {
        categories.setDeleteCategoryApiEndpoint();
    }

    @When("I send a DELETE request for delete a category")
    public void sendDeleteCategoryRequest() {
        categories.sendDeleteCategoryRequest();
    }

    @And("Category successfully deleted")
    public void categoryDeleted() {
        categories.categoryDeleted();
    }
}
