Feature: As a user I want to be able to export my tips to a json file

  Scenario: exporting tips succeeds 
    Given command "6" is selected
    When user has created reading tips and gives the export file name "testExport.json"
    Then the program should confirm the export with "All done! Your reading tips have been exported to testExport.json."

