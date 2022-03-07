Feature: As a user I want to be able to search for tips by their title

  Scenario: searching for an existing tip is successful
    Given command "4" is selected
    When search criteria "tir" is entered
    Then the program should say "Ta-da! You have 1 search result(s)" for search results

  Scenario: searching with a title not found in tips return zero results
    Given command "4" is selected
    When search criteria "nonexistent" is entered
    Then the program should say "Oh no! You have 0 search result(s)" for search results