@foo
Feature: Basic Arithmetic

  Scenario: Adding
  # Try to change one of the values below to provoke a failure
    When I add 4.0 and 5.0
    Then the result is 10.0
