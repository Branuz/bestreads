package bestreads;

import java.io.File;

import static org.junit.Assert.assertEquals;

import bestreads.readingtip.ReadingTips;
import bestreads.databasehandlers.DatabaseManager;
import bestreads.ui.UserInterface;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefs {
    private UserInterface ui;
    private UserInputsIOStub io;
    private String command;
    private ReadingTips testTips;

    String dbFileName = "bestreadstest.db";

    @Given("command {string} is selected")
    public void commandIsGiven(String command) {
        this.command = command;

        File dbFile = new File(dbFileName);
        dbFile.delete();

        DatabaseManager dbManager = new DatabaseManager(dbFileName);
        this.testTips = new ReadingTips(dbManager);
    }

    @When("title {string}, url {string} and tags {string} are entered")
    public void titleAndUrlAreGiven(String title, String url, String tags) {
        io = new UserInputsIOStub(command, title, url, tags);
        ui = new UserInterface(io, testTips);
        ui.start();
    }

    @Then("the program should say {string} and {string}")
    public void tipAddedSuccessfully(String firstLine, String secondLine) {
        String value = "\n" + firstLine + "\n" + secondLine;
        assertEquals(value, io.outputs.get(12));
    }

    @When("search criteria {string} is entered")
    public void searchTagGiven(String criteria) {
        io = new UserInputsIOStub(command, criteria);
        ui = new UserInterface(io, testTips);
        ui.start();
    }

    @Then("the program should say {string}")
    public void searchByTagSuccessfull(String result) {
        String value = "\n" + result + "\n";
        assertEquals(value, io.outputs.get(11));
    }
    
    @Then("the program should say {string} for search results")
    public void searchByTitleSuccessful(String result) {
        String value = "\n" + result + "\n";
        assertEquals(value, io.outputs.get(10));
    }

    @When("tip {string} is entered with confirmation {string}")
    public void tipIsEnteredWithConfirmation(String string, String string2) {
        io = new UserInputsIOStub(command, string, string2);
        ui = new UserInterface(io, testTips);
        ui.start();
    }

    @Then("the program should confirm deletion with {string}")
    public void theProgramShouldConfirmDeletionWith(String string) {
        String expected = "Done! Tip with id 1 was deleted succesfully";
        String actual = string;
        assertEquals(expected, actual);
    }

    @Then("the program should fail deletion with error message {string}")
    public void theProgramShouldFailDeletionWithErrorMessage(String string) {
        String expected = "Oops! Tip with id 999 was not found";
        String actual = string;
        assertEquals(expected, actual);
    }

}
