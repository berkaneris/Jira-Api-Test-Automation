Feature: Add An Issue Comment On Jira

  Background:
    Given the API requests are authenticated with system properties for username and token

  Scenario: User add an issue comment successfully
    When the client sets the request body to add an issue comment
      | type           | doc                        |
      | version        | 1                          |
      | contentType    | paragraph                  |
      | conContentType | text                       |
      | conContentText | Lorem ipsum dolor sit amet |
    When the client sends a POST request to add an issue comment with "TATP-23" as issue id
    Then the response status code should be 201