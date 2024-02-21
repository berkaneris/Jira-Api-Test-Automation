Feature: Edit An Issue On Jira

  Background:
    Given the API requests are authenticated with system properties for username and token

  Scenario: User edit an issue successfully
    When the client sets the request body to edit an issue
      | add     | PUT               |
      | remove1 | POST              |
      | remove2 | CREATE-ISSUE      |
      | set     | TEST - EDIT ISSUE |

    And the client sends a PUT request to the api
    Then the response status code should be 204