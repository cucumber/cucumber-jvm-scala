Feature: Passing feature

  Scenario: Simple scenario
    Given a passing step
    When another passing step
    Then a final step

  Scenario Outline: Outline with <value>
    Given a passing step
    Then the value is <value>

    Examples:
      | value |
      | 1     |
      | 2     |
