Feature: As Cucumber Scala, I want to use both a DocString and a DataTable on the same step

  Scenario: DataTable followed by DocString
    Given a step with a data table and a doc string
      | hello |
      """
      world
      """

  Scenario: DocString followed by DataTable
    Given a step with a doc string and a data table
      """
      hello
      """
      | world |

  Scenario: Registered DataTableType followed by DocString
    Given a step with a list of persons and a doc string
      | name  |
      | Alice |
      | Bob   |
      """
      world
      """
