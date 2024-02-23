@JiraApi @Projects
Feature: Get Recent Projects On Jira

  Background:
    Given the API requests are authenticated with system properties for username and token

  @PositiveTest
  Scenario: Retrieving recent projects successfully
    When user send a GET request the recent projects
    Then the response status code should be 200
    And the response should include the following project names
      | Project Name           | Project id | Project Key |
      | Team4-Api-Test-Project | 10002      | TATP        |
      | Team4-P1               | 10000      | T4          |


  @PositiveTest
  Scenario Outline: Retrieve project details with expanded information
    When user send a GET request the recent projects with expanded information "<expandList>"
    Then the response status code should be 200
    Then the response should include project details with expanded information

    Examples:
      | expandList                                                  |
      | description,projectKeys,lead,issueTypes,permissions,insight |
      | description,lead                                            |
      | projectKeys,issueTypes,insight                              |

  @NegativeTest
  Scenario: Send request with wrong api endpoint
    When user send a GET request the recent projects with invalid point "/rest/api/3/proje/recent"
    Then the response status code should be 404
