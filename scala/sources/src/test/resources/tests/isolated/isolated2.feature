Feature: Isolated 2

  Scenario: Second test
    Given I set the list of values to
      | 10 |
    And I multiply by 2
    Then the list of values is
      | 20 |