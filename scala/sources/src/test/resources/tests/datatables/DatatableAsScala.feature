Feature: As Cucumber Scala, I want to parse DataTables to Scala types properly

  # Scenarios with Strings

  Scenario: As datatable
    Given the following table as Scala DataTable
      | key1  | key2  | key3  |
      | val11 | val12 | val13 |
      | val21 |       | val23 |
      | val31 | val32 | val33 |

  Scenario: As List of Map
    Given the following table as Scala List of Map
      | key1  | key2  | key3  |
      | val11 | val12 | val13 |
      | val21 |       | val23 |
      | val31 | val32 | val33 |

  Scenario: As List of List
    Given the following table as Scala List of List
      | val11 | val12 | val13 |
      | val21 |       | val23 |
      | val31 | val32 | val33 |

  Scenario: As Map of Map
    Given the following table as Scala Map of Map
      |      | key1  | key2  | key3  |
      | row1 | val11 | val12 | val13 |
      | row2 | val21 |       | val23 |
      | row3 | val31 | val32 | val33 |

  Scenario: As Map of List
    Given the following table as Scala Map of List
      | row1 | val11 | val12 | val13 |
      | row2 | val21 |       | val23 |
      | row3 | val31 | val32 | val33 |

  Scenario: As Map
    Given the following table as Scala Map
      | row1 | val11 |
      | row2 |       |
      | row3 | val31 |

  Scenario: As List
    Given the following table as Scala List
      | val11 |
      |       |
      | val31 |

    # Scenarios with other basic types (Int)

  Scenario: As datatable of integers
    Given the following table as Scala DataTable of integers
      | 1  | 2  | 3  |
      | 11 | 12 | 13 |
      | 21 |    | 23 |
      | 31 | 32 | 33 |

  Scenario: As List of Map of integers
    Given the following table as Scala List of Map of integers
      | 1  | 2  | 3  |
      | 11 | 12 | 13 |
      | 21 |    | 23 |
      | 31 | 32 | 33 |

  Scenario: As List of List of integers
    Given the following table as Scala List of List of integers
      | 11 | 12 | 13 |
      | 21 |    | 23 |
      | 31 | 32 | 33 |

  Scenario: As Map of Map of integers (partial)
    Given the following table as Scala Map of Map of integers
      |    | key1  | key2  | key3  |
      | 10 | val11 | val12 | val13 |
      | 20 | val21 |       | val23 |
      | 30 | val31 | val32 | val33 |

  Scenario: As Map of List of integers (partial)
    Given the following table as Scala Map of List of integers
      | 10 | val11 | val12 | val13 |
      | 20 | val21 |       | val23 |
      | 30 | val31 | val32 | val33 |

  Scenario: As Map of integers
    Given the following table as Scala Map of integers
      | 10 | 11 |
      | 20 |    |
      | 30 | 31 |

  Scenario: As List of integers
    Given the following table as Scala List of integers
      | 11 |
      |    |
      | 31 |