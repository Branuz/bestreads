Feature: As a user I want to be able to import tips

  Scenario: importing tips succeeds 
    Given command "7" is selected
    When user imports a json file named "testExport.json" located in project root including two tips
    Then the program should confirm the import and the correct imported amount 

