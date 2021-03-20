Feature: Version

  Background:
    Given url baseUrl
    Given path '/trip'

  Scenario: Add basic location to trip
    When method GET
    Then status 200
    And match $ == "1.0"

  Scenario: Add location to trip
    When method GET
    Then status 200
    And match $ == "1.0"