@IssueComment
Feature: Delete An Issue Comment On Jira

  Background:
    Given the API requests are authenticated with system properties for username and token

  @PositiveTest
  Scenario: User delete an issue comment successfully
    Given User has an issue comment to delete
    When the client sends a DELETE request to delete an issue comment
    Then the response status code should be 204

  @NegativeTest
  Scenario: User delete an issue comment with empty issue key
    When the client sends a DELETE request to delete an issue comment with " " as issue id and "10008" as comment id
    Then the response status code should be 404

  @NegativeTest
  Scenario: User delete an issue comment with empty comment id
    When the client sends a DELETE request to delete an issue comment with "TATP-23" as issue id and " " as comment id
    Then the response status code should be 404
