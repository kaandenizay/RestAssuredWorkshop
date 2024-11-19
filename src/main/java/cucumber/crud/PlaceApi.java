package cucumber.crud;

import com.google.gson.Gson;
import cucumber.data.PlacePayload;
import cucumber.utils.Utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


import java.lang.reflect.InvocationTargetException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static cucumber.config.ConfigurationManager.configuration;

public class PlaceApi {

    static RequestSpecification reqSpec = Utils.getReqSpec();
    static ResponseSpecification responseSpec = Utils.getResponseSpec();
    static String json;
    static Response response;
    static String placeId;

    public void addPayload(String name, String language, String address){
        Gson gson = new Gson();
        json = gson.toJson(PlacePayload.getPlacePayload(name, language, address));
    }

    private String getEndpoint(String endpoint){
        try {
            return (String) configuration().getClass().getMethod(endpoint).invoke(configuration());
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void requestPost(String endpoint){
        response = given().spec(reqSpec).body(json).
                when().
                post(getEndpoint(endpoint)).
                then().extract().response();
    }

    public void requestGet(String endpoint){
        response = given().spec(reqSpec).queryParam("place_id", placeId).
                when().
                get(getEndpoint(endpoint)).
                then().extract().response();
    }

    public void requestDelete(String endpoint){
        response = given().spec(reqSpec).body(PlacePayload.deletePlacePayload(placeId)).
                when().
                post(getEndpoint(endpoint)).
                then().extract().response();
    }


    public void checkStatusCode(int statusCode){
        assertEquals(response.getStatusCode(), statusCode);
        fetchPlaceId();
    }

    public void checkResponseValue(String responseKey, String responseValue){
        JsonPath jsonPath=new JsonPath(response.asString()); //for parsing Json
        System.out.println("RESPONSE = " + response.asString());
        assertEquals(jsonPath.get(responseKey), responseValue);
    }

    private void fetchPlaceId(){
        JsonPath jsonPath=new JsonPath(response.asString()); //for parsing Json
        placeId = jsonPath.getString("place_id");
        System.out.println("placeId = " + placeId);
    }
}
