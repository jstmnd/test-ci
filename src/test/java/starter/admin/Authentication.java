package starter.admin;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONObject;
import starter.utils.JsonSchema;
import starter.utils.JsonSchemaHelper;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.notNullValue;

public class Authentication {
    private static String url = "https://altashop-api.fly.dev/api";

    @Step("I set API endpoint for register")
    public String setRegisterApiEndpoint() {
        return url + "/auth/register";
    }

    @Step("I send a POST request with valid user credentials")
    public void sendRegisterRequest() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("email", "ruanmei@mail.com");
        requestBody.put("password", "123123");
        requestBody.put("fullname", "Firstname Lastname");

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(setRegisterApiEndpoint());
    }

    @Step("I receive status code 200")
    public void receiveStatusCode200() {
        restAssuredThat(response -> response.statusCode(200));
    }

    @Step("I successfully create an account")
    public void successfullyCreateAccount() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.REGISTER_SCHEMA);

        restAssuredThat(response -> response.body("'data'.'ID'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Fullname'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Email'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Password'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    @Step("I send a POST request without user credentials")
    public void sendInvalidRegisterRequest() {
        SerenityRest.given()
                .post(setRegisterApiEndpoint());
    }

    @Step("I receive status code 400")
    public void receiveStatusCode400() {
        restAssuredThat(response -> response.statusCode(400));
    }

    @Step("I set API endpoint for login")
    public String setLoginApiEndpoint() {
        return url + "/auth/login";
    }

    @Step("I send a POST request with valid user data")
    public void sendLoginRequest() {
        JSONObject requestBody = new JSONObject();

        requestBody.put("email", "someone@mail.com");
        requestBody.put("password", "123123");

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .post(setLoginApiEndpoint());
    }

    @Step("I successfully logged in to the account")
    public void successfullyLoggedIn() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.LOGIN_SCHEMA);

        restAssuredThat(response -> response.body("'data'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }

    @Step("I send a POST request without valid user data")
    public void sendInvalidLoginRequest() {
        SerenityRest.given()
                .post(setLoginApiEndpoint());
    }

    @Step("I set API endpoint for get user information")
    public String setGetUserApiEndpoint() {
        return url + "/auth/info";
    }

    @Step("I send a GET request for get user information")
    public void sendUserInfoRequest() {
        SerenityRest.given()
                .header("Authorization", "Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJGdWxsbmFtZSI6IkZpcnN0bmFtZSBMYXN0bmFtZSIsIkVtYWlsIjoic29tZW9uZUBtYWlsLmNvbSJ9.bGpZNDg6YHtKlTFw7_yuyn3SAICmfvdIV1yX7mIKrTw")
                .get(setGetUserApiEndpoint());
    }

    @Step("I get user information")
    public void successfullyGetUserInfo() {
        JsonSchemaHelper helper = new JsonSchemaHelper();
        String schema = helper.getResponseSchema(JsonSchema.USER_INFO_SCHEMA);

        restAssuredThat(response -> response.body("'data'.'ID'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Fullname'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Email'", notNullValue()));
        restAssuredThat(response -> response.body("'data'.'Password'", notNullValue()));

        restAssuredThat(response -> response.body(matchesJsonSchema(schema)));
    }
}
