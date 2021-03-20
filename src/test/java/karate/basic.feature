Feature: Version

  Background:
    Given url baseUrl
    Given path '/trip'

  Scenario: Create a basic trip with one itinerary that incldes one location, one lodging and one transportation
    When method GET
    Then status 200
    And match $ == "1.0"

