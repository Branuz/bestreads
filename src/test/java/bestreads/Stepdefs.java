package bestreads;

import static org.junit.Assert.assertEquals;

import bestreads.readingtip.ReadingTips;
import bestreads.databasehandlers.DatabaseManager;
import bestreads.ui.UserInterface;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Stepdefs{
    private UserInterface ui;
    private UserInputsIOStub io;
    private String command;
    private ReadingTips testTips;

    @Given("command {string} is selected")
    public void commandIsGiven(String command) {
        this.command = command;

	DatabaseManager dbManager = new DatabaseManager();	
        this.testTips = new ReadingTips(dbManager);
    }

    @When("title {string} and url {string} are entered")
    public void titleAndUrlAreGiven(String title, String url) {
        io = new UserInputsIOStub(command, title, url, "0");
        ui = new UserInterface(io, testTips);
        ui.start();
    }

    @Then("the program should say {string}")
    public void tipAddedSuccessfully(String value) {

        assertEquals(value, io.outputs.get(9));
    }

}
