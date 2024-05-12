package starter.admin;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.*;

public class Orders {
    private static String url = "https://altashop-api.fly.dev/api";

    @Step("I set API endpoint for create a new order")
    public String setPostOrderApiEndpoint() {
        return url + "/orders";
    }

    @Step("I send a POST request with valid new order data")
    public void sendNewOrderRequest() {
        JSONArray jsonArray = new JSONArray();

        JSONObject productObj = new JSONObject();
        productObj.put("product_id", 2);
        productObj.put("quantity", 1);

        jsonArray.put(productObj);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJGdWxsbmFtZSI6IkZpcnN0bmFtZSBMYXN0bmFtZSIsIkVtYWlsIjoic29tZW9uZUBtYWlsLmNvbSJ9.bGpZNDg6YHtKlTFw7_yuyn3SAICmfvdIV1yX7mIKrTw")
                .body(jsonArray.toString())
                .post(setPostOrderApiEndpoint());
    }

    @Step("I received a valid data from new order")
    public void receivedValidDataFromNewOrder() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.CREATE_ORDER_SCHEMA);

        restAssuredThat(response -> response.body("data[0].ID", Matchers.notNullValue()));
        restAssuredThat(response -> response.body("data[0].User.ID", Matchers.equalTo(31506)));
        restAssuredThat(response -> response.body("data[0].User.Fullname", Matchers.equalTo("Firstname Lastname")));
        restAssuredThat(response -> response.body("data[0].User.Email", Matchers.equalTo("someone@mail.com")));
        restAssuredThat(response -> response.body("data[0].User.Password", Matchers.equalTo("123123")));
        restAssuredThat(response -> response.body("data[0].Product", Matchers.nullValue()));
        restAssuredThat(response -> response.body("data[0].Quantity", Matchers.equalTo(1)));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    @Step("I send a POST request without new order data")
    public void sendInvalidNewOrderRequest() {
        SerenityRest.given()
                .header("Authorization", "Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJGdWxsbmFtZSI6IkZpcnN0bmFtZSBMYXN0bmFtZSIsIkVtYWlsIjoic29tZW9uZUBtYWlsLmNvbSJ9.bGpZNDg6YHtKlTFw7_yuyn3SAICmfvdIV1yX7mIKrTw")
                .post(setPostOrderApiEndpoint());
    }

    @Step("I set API endpoint for get all orders")
    public String setGetOrdersEndpoint() {
        return url + "/orders";
    }

    @Step("I send a GET request for get all orders")
    public void sendGetAllOrdersRequest() {
        SerenityRest.given()
                .header("Authorization", "Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJGdWxsbmFtZSI6IkZpcnN0bmFtZSBMYXN0bmFtZSIsIkVtYWlsIjoic29tZW9uZUBtYWlsLmNvbSJ9.bGpZNDg6YHtKlTFw7_yuyn3SAICmfvdIV1yX7mIKrTw")
                .get(setGetOrdersEndpoint());
    }

    @Step("I received a valid data from orders")
    public void receivedValidDataFromOrders() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.GET_ALL_ORDER_SCHEMA);

        restAssuredThat(response -> response.body("'data'.'Product'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Price'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Quantity'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Subtotal'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}