package exercises;

import com.google.gson.Gson;
import io.restassured.parsing.Parser;
import exercises.pojo.CarManufacturers;

import static io.restassured.RestAssured.given;

public class DeserialTest {

    public void deserializeTest(){
        Gson gson = new Gson();

        // Serialize Java object to JSON
//        MyClass obj = new MyClass("John", 30);
//        String json = gson.toJson(obj);
//        System.out.println("Serialized JSON: " + json);
//        Output :  Serialized JSON: {"name":"John","age":30}

        String response =
                given().
                        queryParam("format", "json").expect().defaultParser(Parser.JSON).
                        when().
                        get("https://vpic.nhtsa.dot.gov/api/vehicles/getallmanufacturers").asString();

        System.out.println("json = " + response);

        CarManufacturers carManufacturers = gson.fromJson(response, CarManufacturers.class);
        System.out.println("Name = " + carManufacturers.getResults().get(1).getVehicleTypes().get(0).getName());
        System.out.println("Mfr_Name = " + carManufacturers.getResults().get(1).getMfr_Name());
    }
}
