package exercises;

import io.restassured.path.json.JsonPath;

public class NestedJSONExample {

    public static void main(String[] args) {

        String response = "{\n" +
                "\n" +
                "\"dashboard\": {\n" +
                "\n" +
                "\"purchaseAmount\": 910,\n" +
                "\n" +
                "\"website\": \"rahulshettyacademy.com\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\"courses\": [\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Selenium Python\",\n" +
                "\n" +
                "\"price\": 50,\n" +
                "\n" +
                "\"copies\": 6\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Cypress\",\n" +
                "\n" +
                "\"price\": 40,\n" +
                "\n" +
                "\"copies\": 4\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"RPA\",\n" +
                "\n" +
                "\"price\": 45,\n" +
                "\n" +
                "\"copies\": 10\n" +
                "\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "\n" +
                "}";

        JsonPath jsonPath=new JsonPath(response); //for parsing Json
        int size = jsonPath.getInt("courses.size()");
        System.out.println("size = " + size);
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println("purchaseAmount = " + purchaseAmount);
        int total = 0;
        for (int i = 0; i<size; i++){
            String courseTitle = jsonPath.getString("courses[" + i +  "].title");
            int coursePrice = jsonPath.getInt("courses[" + i +  "].price");
            int copies = jsonPath.getInt("courses[" + i +  "].copies");
            System.out.println("courseTitle = " + courseTitle + " price " + coursePrice + " copies " + copies);
            total = total + (coursePrice * copies);
        }
        System.out.println("total = " + total);
    }
}
