@Issue
Feature: Delete An Issue On Jira

  Background:
    Given the API requests are authenticated with system properties for username and token
  @PositiveTest
  Scenario: User create an issue successfully
    Given user has an issue to delete
    When user send a DELETE request to endpoint to delete issue
    Then the response status code should be 204
    And check the issue is deleted