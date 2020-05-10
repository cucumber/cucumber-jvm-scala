Feature: As Cucumber Scala, I want to parse properly Datatables and use types and transformers

  Scenario: Using a DataTableType for Entry - JList
    Given the following authors as entries
      | name   | surname | famousBook      |
      | Alan   | Alou    | The Lion King   |
      | Robert | Bob     | Le Petit Prince |
    When I concat their names
    Then I get "Alan,Robert"

  Scenario: Using a DataTableType for Entry with empty values - JList
    Given the following authors as entries with empty
      | name    | surname | famousBook      |
      | Alan    | Alou    | The Lion King   |
      | [empty] | Bob     | Le Petit Prince |
    When I concat their names
    Then I get "Alan,"

  Scenario: Using a DataTableType for Entry with empty values - DataTable
    Given the following authors as entries with empty, as table
      | name    | surname | famousBook      |
      | Alan    | Alou    | The Lion King   |
      | [empty] | Bob     | Le Petit Prince |
    When I concat their names
    Then I get "Alan,"

  Scenario: Using a DataTableType for Row - Jlist
    Given the following authors as rows
      | Alan   | Alou | The Lion King   |
      | Robert | Bob  | Le Petit Prince |
    When I concat their names
    Then I get "Alan,Robert"

  Scenario: Using a DataTableType for Row, with empty values - Jlist
    Given the following authors as rows with empty
      | Alan    | Alou | The Lion King   |
      | [empty] | Bob  | Le Petit Prince |
    When I concat their names
    Then I get "Alan,"

  Scenario: Using a DataTableType for Row, with empty values - DataTable
    Given the following authors as rows with empty, as table
      | Alan    | Alou | The Lion King   |
      | [empty] | Bob  | Le Petit Prince |
    When I concat their names
    Then I get "Alan,"

  Scenario: Using a DataTableType for Cell - Jlist[Jlist]
    Given the following authors as cells
      | Alan   | Alou | The Lion King   |
      | Robert | Bob  | Le Petit Prince |
    When I concat their names
    Then I get "Alan,Robert"

  Scenario: Using a DataTableType for Cell, with empty values - Jlist[Jlist]
    Given the following authors as cells with empty
      | Alan    | Alou | The Lion King   |
      | [empty] | Bob  | Le Petit Prince |
    When I concat their names
    Then I get "Alan,"

  Scenario: Using a DataTableType for Cell, with empty values - JList[JMap]
    Given the following authors as cells with empty, as map
      | name    | surname | famousBook      |
      | Alan    | Alou    | The Lion King   |
      | [empty] | Bob     | Le Petit Prince |
    When I concat their names
    Then I get "Alan,"

  Scenario: Using a DataTableType for Cell, with empty values - DataTable -> asMaps
    Given the following authors as cells with empty, as table as map
      | name    | surname | famousBook      |
      | Alan    | Alou    | The Lion King   |
      | [empty] | Bob     | Le Petit Prince |
    When I concat their names
    Then I get "Alan,"

  Scenario: Using a DataTableType for Cell, with empty values - DataTable -> asLists
    Given the following authors as cells with empty, as table as list
      | Alan    | Alou    | The Lion King   |
      | [empty] | Bob     | Le Petit Prince |
    When I concat their names
    Then I get "Alan,"

  Scenario: Using a DataTableType for DataTable - DataTable
    Given the following authors as table
      | name   | surname | famousBook      |
      | Alan   | Alou    | The Lion King   |
      | Robert | Bob     | Le Petit Prince |
    When I concat their names
    Then I get "Alan,Robert"

  Scenario: Using a DataTableType for DataTable with empty values - DataTable
    Given the following authors as table with empty
      | name    | surname | famousBook      |
      | Alan    | Alou    | The Lion King   |
      | [empty] | Bob     | Le Petit Prince |
    When I concat their names
    Then I get "Alan,"
