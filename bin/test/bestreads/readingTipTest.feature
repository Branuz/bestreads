Feature: As a user I want to be able to add a tip

  Scenario: adding a tip is successfull
    Given command "1" is selected
    When title "Tietorakenteet ja algoritmit", url "https://tira.mooc.fi" and tags "algorithms" are entered
    Then the program should say "Awesome! You just added a new tip: " and "Tietorakenteet ja algoritmit: https://tira.mooc.fi with tag(s) algorithms"