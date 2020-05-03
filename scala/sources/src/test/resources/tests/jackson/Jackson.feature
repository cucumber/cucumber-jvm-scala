Feature: As Cucumber Scala, I want to provide a basic DataTable transformer using Jackson

  Scenario: Use the default transformer with a basic case class
    Given I have the following datatable
      | field1 | field2 | field3 |
      | 1.2    | true   | abc    |
      | 2.3    | false  | def    |
      | 3.4    | true   | ghj    |

  Scenario: Use the default transformer with a basic case class and empty values
    Given I have the following datatable, with an empty value
      | field1 | field2 | field3  |
      | 1.2    | true   | abc     |
      | 2.3    | false  | [blank] |
      | 3.4    | true   | ghj     |