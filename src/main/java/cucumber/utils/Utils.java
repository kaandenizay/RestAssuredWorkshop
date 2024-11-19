package cucumber.utils;

import cucumber.config.Configuration;
import cucumber.config.ConfigurationManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static cucumber.config.ConfigurationManager.configuration;

public class Utils {

    static RequestSpecification reqSpec;
    static ResponseSpecification responseSpec;

    public static RequestSpecification getReqSpec(){
        PrintStream log = null;

        try {
            log = new PrintStream(new FileOutputStream("target/logging.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        reqSpec = new RequestSpecBuilder().setBaseUri(configuration().baseURI()).
                addQueryParam("key", "qaclick123").
                addFilter(RequestLoggingFilter.logRequestTo(log)).
                addFilter(ResponseLoggingFilter.logResponseTo(log)).
                setContentType(ContentType.JSON)
                .build();
        return reqSpec;
    }

    public static ResponseSpecification getResponseSpec(){
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).
                expectContentType(ContentType.JSON).build();
        return responseSpec;
    }
}
