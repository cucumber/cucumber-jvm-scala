Feature: As Cucumber Scala, I want to use DocStringType

  Scenario: Using a DocStringType
    Given the following json text
      """json
      {
        "key": "value"
      }
      """
    Then I have a json text

  Scenario: Using another DocStringType
    Given the following xml text
      """xml
      <xml></xml>
      """
    Then I have a xml text
