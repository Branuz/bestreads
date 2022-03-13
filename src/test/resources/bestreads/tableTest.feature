Feature: As an user, I want my result tables to look clean with headers.

  Scenario: Result table looks clean with headers
    Given command "1" is selected
    When title "pretty", url "www.prettytable.com", tags "pretty,table" and command "2" are entered
    Then "+----+--------+---------------------+---------------+" as 1st row
    And "| Id | Title  |         Url         |     Tags      |" as 2nd row
    And "+----+--------+---------------------+---------------+" as 3rd row
    And "|  1 | pretty | www.prettytable.com | pretty, table |" as 4th row
    And "+----+--------+---------------------+---------------+" as final row
    And the program should print the pretty table correctly