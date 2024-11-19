package exercises;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

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
        JsonPath jsonPath=new JsonPath(response); //for parsing Json
        String placeId=jsonPath.getString("place_id");
        System.out.println("placeId = " + placeId);


        String newAddress = "Balikesir";
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+ placeId +"\",\n" +
                        "\"address\":\""+ newAddress +"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}\n").
                when().put("maps/api/place/update/json").
                then().assertThat().statusCode(200).
                body("msg", equalTo("Address successfully updated"));



        String getPlaceResponse = given().log().all().
                queryParam("key", "qaclick123").queryParam("place_id", placeId).
                when().get("maps/api/place/get/json").
                then().assertThat().log().all().statusCode(200).extract().response().asPrettyString();
        System.out.println("getPlaceResponse = " + getPlaceResponse);

        jsonPath=new JsonPath(getPlaceResponse); //for parsing Json
        String actualAddress=jsonPath.getString("address");
        System.out.println("actualAddress = " + actualAddress);

        assertEquals(actualAddress, newAddress);
    }
}
