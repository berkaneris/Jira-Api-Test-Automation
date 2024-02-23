@JiraApi @IssueComment
Feature: Update An Issue Comment On Jira

  Background:
    Given the API requests are authenticated with system properties for username and token
  @PositiveTest
  Scenario: User update an issue comment successfully
    When the client sets the request body to update a issue comment
      | type           | doc            |
      | version        | 1              |
      | contentType    | paragraph      |
      | conContentType | text           |
      | conContentText | update comment |
    And the client sends a PUT request to update an issue comment
    Then the response status code should be 200
    And the response should contain updated comment details
  @NegativeTest
  Scenario: User update an issue comment with invalid request body
    When the client sets the request body to update a issue comment
      | type           | png            |
      | version        | 1              |
      | contentType    | deneme         |
      | conContentType | text           |
      | conContentText | update comment |
    And the client sends a PUT request to update an issue comment
    Then the response status code should be 400
