package stepDefinitions;

import cucumber.crud.PlaceApi;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlaceStepDefinitions
{
    PlaceApi placeApi = new PlaceApi();
    @Given("Create Place Payload with {string}, {string} and {string}")
    public void createPlacePayload(String name, String language, String address) {
        placeApi.addPayload(name, language, address);
    }
    @When("User calls {string} with Post request")
    public void user_calls_with_post_request(String resource) {
        placeApi.requestPost(resource);
    }

    @When("User calls {string} with Get request")
    public void user_calls_with_get_request(String resource) {
        placeApi.requestGet(resource);
    }

    @When("User calls {string} with Delete request")
    public void userCallsWithDeleteRequest(String resource) {
        placeApi.requestDelete(resource);
    }
    @Then("the API call got success with code {int}")
    public void the_apı_call_got_success_with_code(int statusCode) {
        placeApi.checkStatusCode(statusCode);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String responseKey, String responseValue) {
        placeApi.checkResponseValue(responseKey,responseValue);
    }

    @Then("User should see the {string} is correct after post request")
    public void userShouldSeeTheIsCorrectAfterPostRequest(String field) {
    }


}
