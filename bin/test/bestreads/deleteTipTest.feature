Feature: As an user, I want to be able to delete tips with id.

  Scenario: Tips can be deleted with an id
    Given command "3" is selected
    When tip "1" is entered with confirmation "y"
    Then the program should confirm deletion with "Done! Tip with id 1 was deleted succesfully"

  Scenario: Tips can't be deleted without valid id
    Given command "3" is selected
    When tip "999" is entered
    Then the program should fail deletion with error message "Oops! Tip with id 999 was not found"