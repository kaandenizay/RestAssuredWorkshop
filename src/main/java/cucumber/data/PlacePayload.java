package cucumber.data;

import exercises.pojo.PostPlace;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlacePayload {

    public static PostPlace getPlacePayload(String name, String language, String address){
        Map<String, Double> location = new HashMap<>();
        location.put("lat", -38.383494);
        location.put("lng", 33.427362);
        PostPlace postPlace = new PostPlace(location, 50,name,
                "(+91) 983 893 3937", address, List.of("shoe park", "shop"),
                "http://google.com", language);

        return  postPlace;
    }

    public static String deletePlacePayload(String placeId){
        return "{\r\n\"place_id\": \"" + placeId +"\"\r\n}";
    }
}
