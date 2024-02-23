@Attachment
Feature: Delete An Attachment In Jira Issue

  Background:
    Given the API requests are authenticated with system properties for username and token
  @PositiveTest
  Scenario: User delete an issue attachment in jira issue successfully
    Given user has an attachment to delete
    When user send a DELETE request to endpoint to delete attachment
    Then the response status code should be 204
    And check the attachment is deleted

  @NegativeTest
  Scenario: User delete an issue attachment in jira issue with invalid attachment id
    When user send a DELETE request to endpoint with "Test" as attachment id
    Then the response status code should be 404