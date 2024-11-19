package exercises;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class GraphQLTest {

    @Test
    void queryTest() {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response =
                given().header("Content-Type", "application/json").
                        body("{\"query\":\"query($characterId: Int!, $episodeId: Int!, $locationId: Int!){\\n  character(characterId: $characterId)\\n  {\\n    name\\n    gender\\n    status\\n    id\\n  }\\n  location(locationId: $locationId)\\n  {\\n    name\\n   \\tdimension\\n  }\\n  episode(episodeId: $episodeId){\\n    name\\n    air_date\\n    episode\\n  }\\n  characters(filters: {name: \\\"Kaan\\\"}){\\n    info{\\n      count\\n    }\\n    result{\\n      name\\n      type\\n    }\\n  }\\n  episodes(filters: {episode: \\\"tabii\\\"}){\\n    info{\\n      count\\n    }\\n    result{\\n      id\\n      name\\n      episode\\n      air_date\\n    }\\n  }\\n}\",\"variables\":{\"characterId\":10035,\"episodeId\":1,\"locationId\":14939}}").
                        log().all().post("gq/graphql").
                        then().assertThat().contentType("application/json").extract().response().asString();

        System.out.println("response = " + response);

    }

    @Test
    void mutationTest() {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response =
                given().header("Content-Type", "application/json").
                        body("{\"query\":\"mutation {\\n  createLocation(location: {name: \\\"Finland\\\", type: \\\"Education\\\", dimension: \\\"6\\\"}){\\n    id\\n  }\\n  \\n  createCharacter(character: {name:\\\"Kaan\\\", type: \\\"Perfect\\\", status: \\\"Alive\\\", species:\\\"God\\\", gender:\\\"Male\\\", image:\\\"png\\\", originId: 14938, locationId: 14938})\\n  {\\n    id\\n  }\\n  createEpisode(episode: {name:\\\"Kaan's Life\\\", air_date: \\\"06/06/1996\\\", episode:\\\"tabii\\\"})\\n  {\\n    id\\n  }\\n}\\n\",\"variables\":{\"characterId\":8,\"episodeId\":1}}").
                        log().all().post("gq/graphql").
                        then().assertThat().contentType("application/json").extract().response().asString();

        System.out.println("response = " + response);

    }
}
