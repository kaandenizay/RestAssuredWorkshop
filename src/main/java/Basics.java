import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Basics {


    //Given - all input details
    // When - Submit the API
    // Then - Validate the response


    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.addPlace()).
                when().post("maps/api/place/add/json").
                then().log().all().assertThat().statusCode(200).
                body("scope", equalTo("APP")).
                header("Server", equalTo("Apache/2.4.52 (Ubuntu)")).
                extract().response().asString();

        System.out.println(response);
        JsonPath js=new JsonPath(response); //for parsing Json
        String placeId=js.getString("place_id");
        System.out.println("placeId = " + placeId);

    }
}
