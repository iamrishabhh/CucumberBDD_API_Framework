Feature: Validating Place APIs

  Scenario Outline: Verify if place is being added successfully using AddPlace API
    Given AddPlace payload with "<name>" "<language>" "<address>"
    When user calls AddPlace API with POST HTTP request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples:
      | name       | language | address  |
      | Tony Stark | English  | Avengers |