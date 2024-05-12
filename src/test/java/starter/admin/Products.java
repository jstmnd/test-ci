package starter.admin;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONArray;
import org.json.JSONObject;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class Products {
    private static String url = "https://altashop-api.fly.dev/api";

    @Step("I set API endpoint for create new product")
    public String setPostApiEndpoint() {
        return url + "/products";
    }

    @Step("I send a POST request with valid new product data")
    public void sendNewProductRequest() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("name", "Sony PS5");
        requestBody.put("description", "play has no limits");
        requestBody.put("price", 299);

        JSONArray categoriesArray = new JSONArray();
        categoriesArray.put(1);

        requestBody.put("categories", categoriesArray);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(setPostApiEndpoint());
    }

    @Step("I received a valid data from new product")
    public void receivedValidDataFromNewProduct() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.CREATE_PRODUCT_SCHEMA);

        restAssuredThat(response -> response.body("'data'.'ID'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Name'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Description'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Price'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Ratings'", notNullValue()));

        restAssuredThat(response -> response.body("'data'.'Categories'.'ID'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Categories'.'Name'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Categories'.'Description'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    @Step("I send a POST request without new product data")
    public void sendInvalidNewProductRequest() {
        SerenityRest.given()
                .post(setPostApiEndpoint());
    }

    @Step("I received an error message")
    public void receivedErrorMessage() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.ERROR_HANDLING_SCHEMA);

        restAssuredThat(response -> response.body("'error'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    @Step("I set API endpoint for get product by ID")
    public String setGetProductApiEndpoint() {
        return url + "/products/88721";
    }

    @Step("I send a GET request with valid product ID")
    public void sendProductIdRequest() {
        SerenityRest.given()
                .get(setGetProductApiEndpoint());
    }

    @Step("I received a valid data from product")
    public void receivedValidDataFromProduct() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_PRODUCT_BY_ID_SCHEMA);

        restAssuredThat(response -> response.body("'data'.'ID'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Name'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Description'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Price'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Ratings'", notNullValue()));

        restAssuredThat(response -> response.body("'data'.'Categories'.'ID'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Categories'.'Name'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Categories'.'Description'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    @Step("I set API endpoint for invalid get product by ID")
    public String setGetApiEndpoint() {
        return url + "/products/1";
    }

    @Step("I send a GET request with invalid product ID")
    public void sendInvalidProductIdRequest() {
        SerenityRest.given()
                .get(setGetApiEndpoint());
    }

    @Step("I receive status code 404")
    public void receiveStatusCode404() {
        restAssuredThat(response -> response.statusCode(404));
    }

    @Step("I set API endpoint for get all product")
    public String setGetAllProductApiEndpoint() {
        return url + "/products";
    }

    @Step("I send a GET request for get all product")
    public void sendAllProductRequest() {
        SerenityRest.given()
                .header("Authorization", "Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJGdWxsbmFtZSI6IkZpcnN0bmFtZSBMYXN0bmFtZSIsIkVtYWlsIjoic29tZW9uZUBtYWlsLmNvbSJ9.bGpZNDg6YHtKlTFw7_yuyn3SAICmfvdIV1yX7mIKrTw")
                .get(setGetAllProductApiEndpoint());
    }

    @Step("I received a valid data from all product")
    public void receivedValidDataFromAllProduct() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ALL_PRODUCT_SCHEMA);

        restAssuredThat(response -> response.body("'data'.'ID'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Name'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Description'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Price'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Ratings'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Categories'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    @Step("I set API endpoint for delete a product")
    public String setDeleteProductApiEndpoint() {
        return url + "/products/1";
    }

    @Step("I send a DELETE request for delete a product")
    public void sendDeleteProductRequest() {
        SerenityRest.given()
                .header("Authorization", "Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJGdWxsbmFtZSI6IkZpcnN0bmFtZSBMYXN0bmFtZSIsIkVtYWlsIjoic29tZW9uZUBtYWlsLmNvbSJ9.bGpZNDg6YHtKlTFw7_yuyn3SAICmfvdIV1yX7mIKrTw")
                .delete(setDeleteProductApiEndpoint());
    }

    @Step("Product successfully deleted")
    public void productDeleted() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.DELETE_PRODUCT_SCHEMA);

        restAssuredThat(response -> response.body("data", nullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}