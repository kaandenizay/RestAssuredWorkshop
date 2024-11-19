package exercises;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraTest {

    void jiraTest() {
        RestAssured.baseURI = "https://blablabla.atlassian.net/";
        String createIssueResponse =
                given().header("Content-Type", "application/json").
                        header("Authorization", "Basic !!!token_id!!!").
                        body("{\n" +
                                "    \"fields\": {\n" +
                                "        \"project\": {\n" +
                                "            \"key\": \"MY_PROJECT\"\n" +
                                "        },\n" +
                                "        \"summary\": \"REST api test.\",\n" +
                                "        \"description\": [\n" +
                                "            \"value\",\n" +
                                "            \"Creating of an issue using project keys and issue type names using the REST API\"\n" +
                                "        ],\n" +
                                "        \"issuetype\": {\n" +
                                "            \"name\": \"Bug\"\n" +
                                "        },\n" +
                                "    }\n" +
                                "}").
                        log().all().post("rest/api/3/issue").
                        then().log().all().assertThat().statusCode(201).contentType("application/json").extract().response().asString();
        JsonPath js = new JsonPath(createIssueResponse);
        String issueId = js.getString("id");
        System.out.println(issueId);

        given().pathParam("key", issueId).header("X-Atlassian-Token", "no-check").
                header("Authorization", "Basic !!!Token_id!!!").
                multiPart("file", new File("src/test/resources/indir.png")).
                log().all().post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);
        //Add attachment
    }
}
