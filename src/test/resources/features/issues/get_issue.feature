Feature: Get An Issue From Jira

  Background:
    Given the API requests are authenticated with system properties for username and token

  Scenario: User get a created issue successfully
      When the client sends a GET request to get an issue with "TATP-23git chekout main" as issue key
      Then the response status code should be 200
      And the response should contain the issue data

  Scenario: User get a created issue with invalid issue key
    When the client sends a GET request to get an issue with "Test" as issue key
    Then the response status code should be 404

  Scenario: User get a created issue with empty issue key
    When the client sends a GET request to get an issue with " " as issue key
    Then the response status code should be 404

