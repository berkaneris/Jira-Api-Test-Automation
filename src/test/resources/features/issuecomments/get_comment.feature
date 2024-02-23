@IssueComment
Feature: Get An Issue Comment On Jira

  Background:
    Given the API requests are authenticated with system properties for username and token
  @PositiveTest
  Scenario: User get an issue comment successfully
    Given user has a comment information
    When the client sends a GET request to get the comment that user have
    Then the response status code should be 200
    And the response should match with the comment information that user have

  @NegativeTest
  Scenario: User get an issue comment with empty comment id
    When the client sends a GET request to get a comment with "TATP-23" as issue key and " " as comment id
    Then the response status code should be 404

  @NegativeTest
  Scenario: User get an issue comment with empty issue key
    When the client sends a GET request to get a comment with " " as issue key and "10017" as comment id
    Then the response status code should be 404
