Feature: As Cucumber Scala, I want to be able to use steps defined in objects even though they will persist their state across scenarios

  Scenario: First scenario
    Given I have a calculator
    When I do 2 + 2
    Then I got 4

  Scenario: Second scenario
    Given I have a calculator
    When I do 5 + 6
    Then I got 11
