Feature: Test Find Users Endpoint


  Background:
    Given the API requests are authenticated with system properties for username and token

  Scenario: Find Users successfully
    When the client sets the request body to create a new issue
      | projectKey         | TATP                                    |
      | summary            | TEST - THIS IS THE SUMMARY OF THE ISSUE |
      | labels             | TEST;POST;CREATE                        |
      | descriptionType    | doc                                     |
      | descriptionVersion | 1                                       |
      | contentType        | paragraph                               |
      | contContentType    | text                                    |
      | contContentText    | THIS IS THE DESCRIPTION OF THE ISSUE    |
      | issueTypeName      | Story                                   |

    When the client sends a POST request to create issue endpoint
    Then the response status code should be 201
    And the response should contain create issue data