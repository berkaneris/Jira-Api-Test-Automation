Feature: Get An Issue Comment On Jira

  Background:
    Given the API requests are authenticated with system properties for username and token

  Scenario: User get an issue comment successfully
    Given user has a comment information with the "TATP-23" as issue key
    When the client sends a GET request to get a comment
    Then the response status code should be 200
    And the response should contain comment information


  Scenario: User get an issue comment with empty comment id
    When the client sends a GET request to get a comment with "TATP-23" as issue key and " " as comment id
    Then the response status code should be 404


  Scenario: User get an issue comment with empty issue key
    When the client sends a GET request to get a comment with " " as issue key and "10017" as comment id
    Then the response status code should be 404
