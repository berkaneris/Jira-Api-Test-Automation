Feature: Editting issue

  Background:
    Given the API requests are authenticated with system properties for username and token

  Scenario Outline: User create an issue successfully
    When the client sets the request body to create a new issue
      | projectKey         | TATP                                 |
      | summary            | <Issue summary before edit>          |
      | labels             | <Issue labels before edit>           |
      | descriptionType    | doc                                  |
      | descriptionVersion | 1                                    |
      | contentType        | paragraph                            |
      | contContentType    | text                                 |
      | contContentText    | THIS IS THE DESCRIPTION OF THE ISSUE |
      | issueTypeName      | Story                                |
    When the client sends a POST request to create issue endpoint
    Then the response status code should be 201
    When Get the current issue
    When I update the labels
      | add     | <Issue labels for add after edit>    |
      | remove  | <Issue labels for remove after edit> |
      | summary | <Issue summary after edit>           |
    Then the response status code should be 204
    When Get the edited issue
    Then Edited issue paramaters should match with former issue
      | Issue summary before edit | '<Issue summary before edit>' |
      | Issue labels before edit  | '<Issue labels before edit>'  |
      | Issue labels for edit     | '<Issue labels after edit>'   |
      | Issue summary after edit  | '<Issue summary after edit>'  |

    Examples:
      | Issue summary before edit | Issue labels before edit | Issue labels for add after edit | Issue labels for remove after edit | Issue summary after edit | Issue labels after edit |
      | Test Before Edit          | PUT;TEST;                | ADD;GET;CREATE;BUG;             | PUT;                               | TEST AFTER EDİT          | ADD BUG CREATE GET TEST |


