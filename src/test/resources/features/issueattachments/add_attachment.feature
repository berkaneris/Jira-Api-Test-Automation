@JiraApi @Attachment
Feature: Add An Attachment In Jira Issue

  Background:
    Given the API requests are authenticated with system properties for username and token

  @PositiveTest
  Scenario: User adds an attachment to jira issue successfully
    When the client sends a POST request to upload file "AttachmentData.jpg"
    Then the response status code should be 200
    And the response should contain attachment details

  @NegativeTest
  Scenario: User adds an attachment to jira issue with in valid issue key
    When the client sends a POST request with "Test" as issue key and "AttachmentData.jpg"
    Then the response status code should be 404

  @NegativeTest
  Scenario: User adds an attachment to jira issue with in empty issue key
    When the client sends a POST request with " " as issue key and "AttachmentData.jpg"
    Then the response status code should be 400
