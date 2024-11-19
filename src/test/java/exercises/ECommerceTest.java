package exercises;

import com.google.gson.Gson;
import exercises.pojo.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ECommerceTest {

    static RequestSpecification reqSpec;
    static ResponseSpecification responseSpec;
    static String tokenId;
    static String userId;
    static String productId;
    static String orderId;
    final static  String userEmail = "kaan.denizay@abc.com";
    final static String userPassword = "Qwerty123456";

    public void loginTest() {
        Gson gson = new Gson();

        LoginRequest loginRequest = new LoginRequest(userEmail, userPassword);
        //   String json = gson.toJson(loginRequest);
        //  loginRequest.setUserEmail("kaan.denizay@abc.com");
        //loginRequest.setUserPassword("Qwerty123456");

        reqSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").
                setContentType(ContentType.JSON)
                .build();
        responseSpec = new ResponseSpecBuilder().expectStatusCode(200).
                expectContentType(ContentType.JSON).build();

        LoginResponse loginResponse =
                given().spec(reqSpec).log().all().
//                body(json).
        body(loginRequest).
                        when().post("api/ecom/auth/login").
                        then().log().all().spec(responseSpec).extract().response().as(LoginResponse.class);

        //LoginResponse loginResponse = gson.fromJson(response, LoginResponse.class);
        tokenId = loginResponse.getToken();
        userId = loginResponse.getUserId();
        System.out.println("tokenId = " + tokenId);
    }

    public void addProduct(){
        Header header = new Header("authorization", tokenId);
        reqSpec.contentType(ContentType.MULTIPART);
        reqSpec.header(header);

        ProductResponse productResponse =
                given().spec(reqSpec).
                        param("productName", "deneme").
                        param("productAddedBy", userId).
                        param("productCategory", "fashion").
                        param("productSubCategory", "shirts").
                        param("productPrice", "3000").
                        param("productDescription", "Addias Originals").
                        param("productFor", "men").
                        multiPart("productImage", new File("src/test/resources/indir.png")).
                when().post("api/ecom/product/add-product").
                then().statusCode(201).log().all().extract().response().as(ProductResponse.class);

        productId = productResponse.getProductId();
        System.out.println("productId = " + productId);

    }

    @Test(priority = 3)
    public void createOrder(){
        reqSpec.contentType(ContentType.JSON);

        Map<String, String> orderDetails = new HashMap<>();
        orderDetails.put("country", "Finland");
        orderDetails.put("productOrderedId", productId);
        OrderRequest orderRequest = new OrderRequest(List.of(orderDetails));

        OrderResponse orderResponse =
                given().spec(reqSpec).log().all().
        body(orderRequest).
                        when().post("api/ecom/order/create-order").
                        then().statusCode(201).log().all().extract().response().as(OrderResponse.class);
        orderId = orderResponse.getOrders().get(0);


    }

    @Test(priority = 4)
    public void compareOrderDetails(){

        OrderDetailsResponse detailsResponse =
                given().spec(reqSpec).queryParam("id",orderId).log().all().
                when().get("api/ecom/order/get-orders-details").
                then().log().all().spec(responseSpec).extract().response().as(OrderDetailsResponse.class);

        assertEquals(detailsResponse.getData().get("_id"), orderId);
        assertEquals(detailsResponse.getData().get("orderById"), userId);
        assertEquals(detailsResponse.getData().get("orderBy"), userEmail);
        assertEquals(detailsResponse.getData().get("productOrderedId"), productId);
        assertEquals(detailsResponse.getData().get("productName"), "deneme");
        assertEquals(detailsResponse.getData().get("country"), "Finland");
        assertEquals(detailsResponse.getData().get("productDescription"), "Addias Originals");
        assertEquals(detailsResponse.getData().get("orderPrice"), "3000");
    }

    @Test(priority = 5)
    public void deleteProduct(){


        String response =
                given().spec(reqSpec).pathParam("productId", productId).log().all().
                when().delete("api/ecom/product/delete-product/{productId}").
                then().log().all().spec(responseSpec).extract().response().asString();
        JsonPath jsonPath = new JsonPath(response);
        String message = jsonPath.get("message");
        assertEquals(message, "Product Deleted Successfully");
    }
}
