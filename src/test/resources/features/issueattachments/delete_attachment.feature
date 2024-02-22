Feature: Delete An Attachment In Jira Issue

  Background:
    Given the API requests are authenticated with system properties for username and token

  Scenario: User delete an issue attachment in jira issue successfully
    Given user has an attachment to delete
    When user send a DELETE request to endpoint
    Then the response status code should be 204