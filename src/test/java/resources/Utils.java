package resources;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Utils {

    public static String baseURL = "https://rahulshettyacademy.com";
    public static String keyValue = "qaclick123";

    RequestSpecification request;

    public RequestSpecification requestSpecification() throws FileNotFoundException {

        PrintStream log = new PrintStream(new FileOutputStream("executionLogs.txt"));
        RestAssured.baseURI = baseURL;

        /* RequestSpecBuilder */
        request = new RequestSpecBuilder().setBaseUri(baseURL)
                .addQueryParam("key", keyValue)
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON)
                .build();

        return  request;
    }
}
