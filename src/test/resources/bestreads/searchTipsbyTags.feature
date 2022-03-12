Feature: As an user, I want to be able to search tips by tags.

  Scenario: Tips can be found by a tag
    Given command "5" is selected
    When search criteria "tag1" is entered
    Then the program should say "Ta-da! You have 1 search result(s):"

  Scenario: A tag that does not exist gives an error message
    Given command "5" is selected
    When search criteria "tag100" is entered
    Then the program should say "Oh no! You have 0 search result(s):"
