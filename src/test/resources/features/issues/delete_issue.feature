@JiraApi @Issue
Feature: Delete an issue

  Background:
    Given the API requests are authenticated with system properties for username and token

  @PositiveTest
  Scenario: User create an issue successfully
    When the client sets the request body to create a new issue
      | projectKey         | TATP                                 |
      | summary            | TEST - Lets try another one          |
      | labels             | TEST;POST;CREATE                     |
      | descriptionType    | doc                                  |
      | descriptionVersion | 1                                    |
      | contentType        | paragraph                            |
      | contContentType    | text                                 |
      | contContentText    | THIS IS THE DESCRIPTION OF THE ISSUE |
      | issueTypeName      | Story                                |
    When the client sends a POST request to create issue endpoint
    Then the response status code should be '201'

    When Get ID of cretead of issue and delete the issue
    Then the response status code should be 204
    When Get ID of cretead of issue and delete the issue

  @PositiveTest
  Scenario: User create an issue successfully
    Given user has an issue to delete
    When user send a DELETE request to endpoint to delete issue
    Then the response status code should be 204
    And check the issue is deleted

  @NegativeTest
  Scenario: User try to delete an issue non-exist

    When I give an invalid issue ID and post DELETE method
    Then the response status code should be 404
    And I should take an error message



