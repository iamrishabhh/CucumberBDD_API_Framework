package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class StepDefination extends Utils {

    RequestSpecification req2;
    ResponseSpecification res2;
    Response mainResp;

    TestDataBuild data = new TestDataBuild();

    @Given("AddPlace payload")
    public void add_place_payload() {

        req2 = given()
                .spec(requestSpecification())
                .body(data.addPlacePayload());

        /* ResponseSpecBuilder */
        res2 = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

    }

    @When("user calls AddPlace API with POST HTTP request")
    public void user_calls_add_place_api_with_post_http_request() {
        mainResp = req2
                .when()
                .post("/maps/api/place/add/json")
                .then()
                .spec(res2)
                .extract()
                .response();
    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        assertEquals(mainResp.getStatusCode(),200);
    }
    @Then("{string} in response body is {string}")
    public void status_in_response_body_is_ok(String keyValue, String expectedValue) {
        String responseBody = mainResp.asString();
        JsonPath js = new JsonPath(responseBody);
        assertEquals(js.get(keyValue).toString(), expectedValue);
    }

}
