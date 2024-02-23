@JiraApi @Users
Feature: Jira User Information Feature
  As a user
  I want to verify user information in Jira
  So that I can ensure that user data is correct
  Background:
    Given the API requests are authenticated with system properties for username and token
  @PositiveTest
  Scenario: Verify user account type and email address
    When the client sends a Get request to get user information as accountId "712020:1c93a5cc-f561-40d9-9574-68cbcb3f8e8f"
    Then the response status code should be 200
    Then I should see the user account type as "atlassian"
    And I should see the user email address as "berkan.eris.10@gmail.com"
  @PositiveTest
  Scenario: Verify user is active and has correct display name
    When the client sends a Get request to get user information as accountId "712020:ce2196d1-51d8-40af-af6a-43c2b90da88f"
    Then the response status code should be 200
    Then I should see the user is active
    And I should see the user display name as "Burak Doğan"