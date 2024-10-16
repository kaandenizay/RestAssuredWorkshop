import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Basics {


    //Given - when - then

    @Test
    public void addPlaceTest(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given().queryParam("key", "qaclick123")
                .header("Content-Type","application/json");
    }
}
