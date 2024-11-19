package exercises.pojo;

import java.util.List;
import java.util.Map;

public class PostPlace {

    private Map<String, Double> location;
    private int accuracy;
    private String name;
    private String phone_number;
    private String address;
    private List<String> types;
    private String website;
    private String language;

    public PostPlace(Map<String, Double> location, int accuracy, String name, String phone_number, String address, List<String> types, String website, String language) {
        this.location = location;
        this.accuracy = accuracy;
        this.name = name;
        this.phone_number = phone_number;
        this.address = address;
        this.types = types;
        this.website = website;
        this.language = language;
    }

}
