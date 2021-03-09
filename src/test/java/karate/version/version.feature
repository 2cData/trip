Feature: Version

  Background:
    Given url baseUrl
    Given path '/version'

  Scenario: Correct version
    When method GET
    Then status 200
    And match $ == "1.0"