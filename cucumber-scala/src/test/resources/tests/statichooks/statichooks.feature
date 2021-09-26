Feature: As Cucumber Scala, I want to use beforeAll/afterAll hooks

  Scenario: Scenario A
    Then BeforeAll count is 1
    Then AfterAll count is 0
    When I run scenario "A"
    Then BeforeAll count is 1
    Then AfterAll count is 0

  Scenario: Scenario B
    Then BeforeAll count is 1
    Then AfterAll count is 0
    When I run scenario "B"
    Then BeforeAll count is 1
    Then AfterAll count is 0
