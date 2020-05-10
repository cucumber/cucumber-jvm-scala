Feature: As Cucumber Scala, I want to parse DataTables properly

  Scenario: As datatable
    Given the following table as DataTable
      | key1  | key2  | key3  |
      | val11 | val12 | val13 |
      | val21 | val22 | val23 |
      | val31 | val32 | val33 |

  Scenario: As List of Map
    Given the following table as List of Map
      | key1  | key2  | key3  |
      | val11 | val12 | val13 |
      | val21 | val22 | val23 |
      | val31 | val32 | val33 |

  Scenario: As List of List
    Given the following table as List of List
      | val11 | val12 | val13 |
      | val21 | val22 | val23 |
      | val31 | val32 | val33 |

  Scenario: As Map of Map
    Given the following table as Map of Map
      |      | key1  | key2  | key3  |
      | row1 | val11 | val12 | val13 |
      | row2 | val21 | val22 | val23 |
      | row3 | val31 | val32 | val33 |

  Scenario: As Map of List
    Given the following table as Map of List
      | row1 | val11 | val12 | val13 |
      | row2 | val21 | val22 | val23 |
      | row3 | val31 | val32 | val33 |

  Scenario: As Map
    Given the following table as Map
      | row1 | val11 |
      | row2 | val21 |
      | row3 | val31 |

  Scenario: As List
    Given the following table as List
      | val11 |
      | val21 |
      | val31 |
