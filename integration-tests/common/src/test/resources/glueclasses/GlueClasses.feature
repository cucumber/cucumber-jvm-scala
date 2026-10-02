Feature: As Cucumber Scala, I want to select glue classes with cucumber.glue.classes

  Scenario: Steps from a selected class and a selected object
    Given a step defined in a selected class
    And a step defined in a selected object
    And a step that is defined twice but only in one selected class
