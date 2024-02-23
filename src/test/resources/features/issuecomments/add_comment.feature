@IssueComment
Feature: Add An Issue Comment On Jira

  Background:
    Given the API requests are authenticated with system properties for username and token
  @PositiveTest
  Scenario: User add an issue comment successfully
    When the client sets the request body to add an issue comment
      | type           | doc                        |
      | version        | 1                          |
      | contentType    | paragraph                  |
      | conContentType | text                       |
      | conContentText | Lorem ipsum dolor sit amet |
    When the client sends a POST request to add an issue comment
    Then the response status code should be 201
    And the response should contain comment details

  @NegativeTest
  Scenario: User add an issue comment with invalid issue key
    When the client sets the request body to add an issue comment
      | type           | doc                        |
      | version        | 1                          |
      | contentType    | paragraph                  |
      | conContentType | text                       |
      | conContentText | Lorem ipsum dolor sit amet |
    When the client sends a POST request to add an issue comment with "Test" as issue id
    Then the response status code should be 404
