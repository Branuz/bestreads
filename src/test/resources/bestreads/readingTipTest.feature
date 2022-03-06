Feature: As a user I want to be able to add a tip

  Scenario: adding a tip is successfull
    Given command "1" is selected
    When title "tira", url "tira.mooc.fi" and tags "tag1" are entered
    Then the program should say "Awesome! You just added a new tip: " and "tira: tira.mooc.fi with tags tag1"