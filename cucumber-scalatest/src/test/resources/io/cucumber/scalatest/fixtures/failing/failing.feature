Feature: Failing feature

  @passing
  Scenario: Passing scenario
    Given a passing step

  @failing
  Scenario: Failing step
    Given a passing step
    When a failing step
    Then a skipped step

  @failing
  Scenario: Undefined step
    Given a passing step
    When a step nobody defined
