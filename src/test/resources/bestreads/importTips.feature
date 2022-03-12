Feature: As a user I want to be able to import tips

  Scenario: importing tips succeeds
    Given command "7" is selected
    When user has a suitable json file existing in the root folder and import is executed
    Then the program should confirm the import and the correct imported amount 