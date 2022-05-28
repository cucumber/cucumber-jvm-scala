Feature: As Cucumber Scala, I want to use beforeAll/afterAll hooks

  Scenario: Scenario C
    Then BeforeAll count is 1
    Then AfterAll count is 0
    When I run scenario "C"
    Then BeforeAll count is 1
    Then AfterAll count is 0

  Scenario: Scenario D
    Then BeforeAll count is 1
    Then AfterAll count is 0
    When I run scenario "D"
    Then BeforeAll count is 1
    Then AfterAll count is 0
