package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {

    public static String baseURL = "https://rahulshettyacademy.com";
    public static String keyValue = "qaclick123";

    RequestSpecification request;

    public RequestSpecification requestSpecification(){

        RestAssured.baseURI = baseURL;

        /* RequestSpecBuilder */
        request = new RequestSpecBuilder().setBaseUri(baseURL)
                .addQueryParam("key", keyValue)
                .setContentType(ContentType.JSON)
                .build();

        return  request;
    }
}
