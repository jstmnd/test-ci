package starter.admin;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONObject;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class Categories {
    private static String url = "https://altashop-api.fly.dev/api";

    @Step("I set API endpoint for create category")
    public String setPostApiEndpoint() {
        return url + "/categories";
    }

    @Step("I send a POST request with valid category data")
    public void sendCategoryRequest() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("name", "gaming");
        requestBody.put("description", "for gaming purposes");

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(setPostApiEndpoint());
    }

    @Step("I received a valid category data")
    public void receivedValidCategoryData() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.CREATE_CATEGORY_SCHEMA);

        restAssuredThat(response -> response.body("'data'.'ID'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Name'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    @Step("I send a POST request without category data")
    public void sendInvalidCategoryRequest() {
        SerenityRest.given()
                .post(setPostApiEndpoint());
    }

    @Step("I set API endpoint for get category by ID")
    public String setGetCategoryApiEndpoint() {
        return url + "/categories/31972";
    }

    @Step("I send a GET request with valid category ID")
    public void sendCategoryIdRequest() {
        SerenityRest.given()
                .get(setGetCategoryApiEndpoint());
    }

    @Step("I received a valid data from category")
    public void receivedValidDataFromCategory() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_CATEGORY_BY_ID_SCHEMA);

        restAssuredThat(response -> response.body("'data'.'ID'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Name'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Description'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    @Step("I set API endpoint for invalid get category by ID")
    public String setGetApiEndpoint() {
        return url + "/categories/1";
    }

    @Step("I send a GET request with invalid category ID")
    public void sendInvalidCategoryIdRequest() {
        SerenityRest.given()
                .get(setGetApiEndpoint());
    }

    @Step("I set API endpoint for all category")
    public String setGetAllCategoryApiEndpoint() {
        return url + "/categories";
    }

    @Step("I send a GET request for all category")
    public void sendAllCategoryRequest() {
        SerenityRest.given()
                .header("Authorization", "Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJGdWxsbmFtZSI6IkZpcnN0bmFtZSBMYXN0bmFtZSIsIkVtYWlsIjoic29tZW9uZUBtYWlsLmNvbSJ9.bGpZNDg6YHtKlTFw7_yuyn3SAICmfvdIV1yX7mIKrTw")
                .get(setGetAllCategoryApiEndpoint());
    }

    @Step("I received a valid data from all category")
    public void receivedValidDataFromAllCategory() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ALL_CATEGORY_SCHEMA);

        restAssuredThat(response -> response.body("data.ID", notNullValue()));
        restAssuredThat(response -> response.body("data.Name", notNullValue()));
        restAssuredThat(response -> response.body("data.Description", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    @Step("I set API endpoint for delete a category")
    public String setDeleteCategoryApiEndpoint() {
        return url + "/categories/1";
    }

    @Step("I send a DELETE request for delete a category")
    public void sendDeleteCategoryRequest() {
        SerenityRest.given()
                .header("Authorization", "Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJGdWxsbmFtZSI6IkZpcnN0bmFtZSBMYXN0bmFtZSIsIkVtYWlsIjoic29tZW9uZUBtYWlsLmNvbSJ9.bGpZNDg6YHtKlTFw7_yuyn3SAICmfvdIV1yX7mIKrTw")
                .delete(setDeleteCategoryApiEndpoint());
    }

    @Step("Category successfully deleted")
    public void categoryDeleted() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.DELETE_CATEGORY_SCHEMA);

        restAssuredThat(response -> response.body("data", nullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}
