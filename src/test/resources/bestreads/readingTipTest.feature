Feature: As a user I want to be able to add a tip

  Scenario: adding a tip is successfull
    Given command "1" is selected
    When title "tira" and url "tira.mooc.fi" are entered
    Then the program should say "Tip was added successfully!"