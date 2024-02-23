@JiraApi @UserSearch
Feature: Test Find Users Endpoint

  Background:
    Given the API requests are authenticated with system properties for username and token
  @PositiveTest
  Scenario: Find Users with query params successfully
    When the client sets the request body to find user with query "Altuntaş"
    Then the response status code should be 200
    And the response should contain users data
  @PositiveTest
  Scenario: Find Users with query params successfully
    When the client sets the request body to find user with maxResult "2"
    Then the response status code should be 200
    And the response should contain "2" users

  @NegativeTest
  Scenario: Find Users with invalid max result query param
    When the client sets the request body to find user with maxResult "-1500"
    Then the response status code should be 500



