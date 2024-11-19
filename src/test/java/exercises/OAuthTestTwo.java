package exercises;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class OAuthTestTwo {


    public void getAuthCode(){
        RestAssured.baseURI = "https://www.googleapis.com/";
        String response =
                given().
                        queryParam("code", "4%2F0AVG7fiRmeLsFirH3mbkSmxvd008io0noo2-Mf2uM7SlnqCuBMvGHG8KFCuDJafDXmP-1fQ").
                        queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
                        queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").
                        queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php").
                        queryParam("grant_type", "authorization_code").
                        when().log().all().
                        post("oauth2/v4/token").
                        then().extract().response().asString();

        JsonPath js = new JsonPath(response);
        System.out.println(response);
        String accessToken = js.getString("access_token");
        System.out.println(accessToken);

    }

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
                    urlEncodingEnabled(false).
                    queryParam("access_token", accessToken).
                when().log().all().
                    get("oauthapi/getCourseDetails").
                then().extract().response().asString();

    }
}
