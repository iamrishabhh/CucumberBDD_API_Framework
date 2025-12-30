package stepDefinations;

import POJO.AddPlaceBody;
import POJO.Location;
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

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class StepDefination {

    RequestSpecification req2;
    ResponseSpecification res2;
    Response mainResp;


    public static String baseURL = "https://rahulshettyacademy.com";
    public static String keyValue = "qaclick123";

    private static AddPlaceBody getAddPlaceBody() {
        AddPlaceBody ap = new AddPlaceBody();
        ap.setAccuracy(18);
        ap.setAddress("221B Baker Street");
        ap.setLanguage("United Kingdom IN");
        ap.setPhone_number("(+91) 987 654 3210");
        ap.setWebsite("http://google.com");
        ap.setName("Sherlock Holmes");

        List<String> myTypeList = new ArrayList<String>();
        myTypeList.add("Crime Investigation");
        myTypeList.add("Puzzle");
        ap.setTypes(myTypeList);

        Location l = new Location();
        l.setLat(51.523772);
        l.setLng(-0.158538);
        ap.setLocation(l);
        return ap;
    }

    @Given("AddPlace payload")
    public void add_place_payload() {
        RestAssured.baseURI = baseURL;

        AddPlaceBody ap = getAddPlaceBody();

        /* RequestSpecBuilder */
        RequestSpecification req = new RequestSpecBuilder().setBaseUri(baseURL)
                .addQueryParam("key", keyValue)
                .setContentType(ContentType.JSON).build();

        req2 = given().spec(req).body(ap);

        /* ResponseSpecBuilder */
        res2 = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

    }



    @When("user calls AddPlace API with POST HTTP request")
    public void user_calls_add_place_api_with_post_http_request() {
        mainResp = req2.when().post("/maps/api/place/add/json")
                .then().spec(res2).extract().response();
    }
    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code(Integer int1) {
        assertEquals(mainResp.getStatusCode(),200);
        System.out.println("Actual status code: "+mainResp.getStatusCode());
    }
    @Then("{string} in response body is {string}")
    public void status_in_response_body_is_ok(String keyValue, String expectedValue) {
        String responseBody = mainResp.asString();
        System.out.println(responseBody);
        JsonPath js = new JsonPath(responseBody);
        assertEquals(js.get(keyValue).toString(), expectedValue);
    }

}
