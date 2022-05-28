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

  Scenario: Using no content type
    Given the following raw text
      """
      something raw
      """
    Then I have a raw text

  Scenario: Generic type - string
    Given the following string list
      """
      item 1
      item 2
      """
    Then I have a string list "item 1,item 2"

  Scenario: Generic type - int
    Given the following int list
      """
      1
      2
      """
    Then I have a int list "1,2"
