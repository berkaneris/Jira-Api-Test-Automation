Feature:Get Project On Jira

  Background:
    Given the API requests are authenticated with system properties for username and token

  Scenario Outline: Verification of project details
    When I get details of project whose ID is '<ID of project>'
    Then the response status code should be 200
    And Project ID shouyld matches with '<ID of project>'
    And Given values should match with response
      | Display Name     | <User Created Project> |
      | Activeness       | <Situation Activeness> |
      | ProjectName      | <Project Name>         |
      | Project type Key | <projectTypeKey>       |
    Examples:
      | ID of project | User Created Project | Situation Activeness | Project Name           | projectTypeKey |
      | TATP          | Berkan Eriş          | true                 | Team4-Api-Test-Project | software       |

