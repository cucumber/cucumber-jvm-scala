Feature: Optional capture groups are supported

  Scenario: present, using Java's Optional
    Given I have the name: Jack

  Scenario: absent, using Java's Optional
    Given I don't have the name:
