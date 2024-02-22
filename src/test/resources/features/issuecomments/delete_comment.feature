Feature: Delete An Issue Comment On Jira

  Background:
    Given the API requests are authenticated with system properties for username and token

  Scenario: User delete an issue comment successfully
    When the client sends a DELETE request to delete an issue comment with "TATP-23" as issue id and "10008" as comment id
    Then the response status code should be 204

  Scenario: User delete an issue comment with empty issue key
    When the client sends a DELETE request to delete an issue comment with " " as issue id and "10008" as comment id
    Then the response status code should be 404

  Scenario: User delete an issue comment with empty comment id
    When the client sends a DELETE request to delete an issue comment with "TATP-23" as issue id and " " as comment id
    Then the response status code should be 404
