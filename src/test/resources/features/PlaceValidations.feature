@firstScenario
Feature: Validating Place Api's

#  Scenario: Verify Place is being successfully added
#    Given Add Place Payload
#    When User calls "api-place-add" with Post http request
#    Then the API call got success with code 200
#    And  "status" in response body is "OK"
#    And  "scope" in response body is "APP"

  Scenario Outline:
    Given Create Place Payload with "<name>", "<language>" and "<address>"
    When User calls "addPlaceAPI" with Post request
    Then the API call got success with code 200
    And  "status" in response body is "OK"
    And  "scope" in response body is "APP"
    # GET Functionality scenario
    When User calls "getPlaceAPI" with Get request
    Then "name" in response body is "<name>"
    # DELETE Functionality scenario
    When User calls "deletePlaceAPI" with Delete request
    Then "status" in response body is "OK"

    Examples:
      | name      | language | address            |
      | AAhouse   | English  | World cross center |
      | New house | Finnish  | Helsinki           |
