package exercises;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OAuthTest {

    @Test
    public void oAuthTest(){
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response =
                given().
                        formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
                        formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
                        formParam("grant_type", "client_credentials").
                        formParam("scope", "trust").
                when().log().all().
                        post("oauthapi/oauth2/resourceOwner/token").
                then().extract().response().asString();

        JsonPath js = new JsonPath(response);
        String accessToken = js.getString("access_token");
        System.out.println(accessToken);

        response =
                given().
//                    queryParam("access_token", accessToken).
                when().log().all().
                    get("oauthapi/getCourseDetails?access_token=" + accessToken ).
                then().extract().response().asString();

    }
}
