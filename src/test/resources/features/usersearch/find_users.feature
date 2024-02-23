Feature: Test Find Users Endpoint


  Background:
    Given the API requests are authenticated with system properties for username and token

  Scenario: Find Users successfully
    When the client sets the request body to find user with "Altuntaş"
    Then the response status code should be 200
    And the response should contain users data

  Scenario: Making a valid query
    When a user search query is made
    And a query string and property are specified
    And startAt and maxResults are specified

    Then the API should be called successfully
    And a JSON response should be received
    And there should be a successful list of users in the response
    And privacy controls should be applied based on users' preferences


























