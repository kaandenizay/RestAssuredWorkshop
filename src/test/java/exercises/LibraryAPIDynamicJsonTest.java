package exercises;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LibraryAPIDynamicJsonTest {


    @Test(dataProvider = "booksData")
    public void addBook(String isbn, String aisle) {

        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type", "application/json").
                body(addPayload(isbn,aisle)).
                when().post("/Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).
                extract().response().asString();

        JsonPath jsonPath = new JsonPath(response);
        String id = jsonPath.get("ID");
        System.out.println("id = " + id);

    }

    public static String addPayload(String isbn, String aisle) {
        String payload = "{\n" +
                "\"name\":\"Learn Appium Automation with Java\",\n" +
                "\"isbn\":\""+isbn+"\",\n" +
                "\"aisle\":\""+aisle+"\",\n" +
                "\"author\":\"John foe\"\n" +
                "}\n";
        return payload;
    }

    @DataProvider(name = "booksData")
    public Object[][] getData() {
        return new Object[][] { { "firstOne", "1234" },{ "secondOne", "4567"},{"kaan", "543"}};
    }
}
