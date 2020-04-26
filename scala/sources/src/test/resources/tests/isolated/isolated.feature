Feature: Isolated

  Scenario: First test
    Given I set the list of values to
      | 1 |
      | 2 |
      | 3 |
    And I multiply by 2
    Then the list of values is
      | 2 |
      | 4 |
      | 6 |