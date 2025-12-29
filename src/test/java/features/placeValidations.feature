Feature: Validating Place APIs

  Scenario: Verify if place is being added successfully using AddPlace API
    Given AddPlace payload
    When user calls AddPlace API with POST HTTP request
    Then the API call is success with status code 200
    And status in reponse body is OK