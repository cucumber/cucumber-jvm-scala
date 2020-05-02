Feature: As Cucumber Scala, I want to handle ParameterType definitions

  Scenario: define parameter type with single argument
    Given string builder parameter, defined by lambda

  Scenario: define parameter type with two arguments
    Given balloon coordinates 123,456, defined by lambda

  Scenario: define parameter type with three arguments
    Given kebab made from mushroom, meat and veg, defined by lambda

  Scenario: define default parameter transformer
    Given kebab made from anonymous meat, defined by lambda

  Scenario: define default data table cell transformer - DataTable
    Given default data table cells, defined by lambda
      | Kebab   |
      | [empty] |

  Scenario: define default data table cell transformer - JList[Jlist]
    Given default data table cells, defined by lambda, as rows
      | Kebab   |
      | [empty] |

  Scenario: define default data table entry transformer - DataTable
    Given default data table entries, defined by lambda
      | dinner  |
      | Kebab   |
      | [empty] |

  Scenario: define default data table entry transformer - JList
    Given default data table entries, defined by lambda, as rows
      | dinner  |
      | Kebab   |
      | [empty] |