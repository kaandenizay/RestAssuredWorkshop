package exercises;

import com.google.gson.Gson;
import exercises.pojo.CarManufacturers;
import exercises.pojo.PostPlace;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SerializeTest {


    public void serializeTest(){
        Gson gson = new Gson();

        Map<String, Double> location = new HashMap<>();
        location.put("lat", -38.383494);
        location.put("lng", 33.427362);
        PostPlace postPlace = new PostPlace(location, 50,"Frontline house",
                "(+91) 983 893 3937", "29, side layout, cohen 09", List.of("shoe park", "shop"),
                "http://google.com", "French-IN");

        String json = gson.toJson(postPlace);
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response =
                given().
                        queryParam("key", "qaclick123").
                        body(json).
                when().log().all().
                        post("maps/api/place/add/json").
                then().log().all().assertThat().statusCode(200).extract().response().asString();

    }
}
