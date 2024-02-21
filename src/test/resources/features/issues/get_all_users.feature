Feature:bilal

  Background:
    Given the API requests are authenticated with system properties for username and token

  Scenario Outline: bilal
    When I set the start point to '<Start Point>' and maximum account number to '<Max Account Number>'
    Then the response status code should be '<Status code>'
    And Number of account should be '<Expected Max Account Number>'
    And Content of accounts should match with given data
    Examples:
      | Start Point | Max Account Number | Status code | Expected Max Account Number |
      | 0           |                    | 200         | 60                          |