package exercises;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class JSONFileTest {


    public void addBook() throws IOException {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type", "application/json").
                body(Files.readAllBytes(Paths.get("C:FSDFAFASDFAF"))).
                when().post("/Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).
                extract().response().asString();

        JsonPath jsonPath = new JsonPath(response);
        String id = jsonPath.get("ID");
        System.out.println("id = " + id);

    }
}
