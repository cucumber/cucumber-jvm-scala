Feature: As Cucumber Scala, I want to be able to have some step classes depend on another one

  Scenario: Nominal case
    Given a step defined in class DI-A with arg "A"
    And a step defined in class DI-B with arg "B"
    When a step defined in class DI-C uses them both
    Then both values are combined into "AB"
