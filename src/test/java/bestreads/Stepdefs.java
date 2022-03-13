package bestreads;

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
    private String[] prettyTable;
    private DatabaseManager dbManager;

    String dbFileName = "bestreadstest.db";

    @Given("command {string} is selected")
    public void commandIsGiven(String command) {
        this.command = command;
        this.dbManager = new DatabaseManager(dbFileName);
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
        assertEquals(value, io.outputs.get(14));
        this.dbManager.deleteAllFromDatabase();
    }

    @When("search criteria {string} is entered")
    public void searchTagGiven(String criteria) {
        testTips.deleteAllRows();
        testTips.addTip("ListApart", "https://alistapart.com/", "news");
        io = new UserInputsIOStub(command, criteria);
        ui = new UserInterface(io, testTips);
        ui.start();
    }

    @Then("the program should say {string}")
    public void searchByTagSuccessfull(String result) {
        String value = "\n" + result;
        assertEquals(value, io.outputs.get(13));
    }

    @Then("the program should say {string} for search results")
    public void searchByTitleSuccessful(String result) {
        String value = "\n" + result;
        assertEquals(value, io.outputs.get(12));
    }

    @When("tip {string} is entered with confirmation {string}")
    public void tipIsEnteredWithConfirmation(String string, String string2) {
        io = new UserInputsIOStub(command, string, string2);
        ui = new UserInterface(io, testTips);
        ui.start();
    }

    @When("tip {string} is entered")
    public void tipIsEntered(String string) {
        io = new UserInputsIOStub(command, string);
        ui = new UserInterface(io, testTips);
        ui.start();
    }

    @Then("the program should confirm deletion with {string}")
    public void theProgramShouldConfirmDeletionWith(String string) {
        String expected = "Done! Tip with id 1 was deleted succesfully";
        String actual = string;
        assertEquals(expected, actual);
        this.dbManager.deleteAllFromDatabase();
    }

    @Then("the program should fail deletion with error message {string}")
    public void theProgramShouldFailDeletionWithErrorMessage(String string) {
        String expected = "Oops! Tip with id 999 was not found";
        String actual = string;
        assertEquals(expected, actual);
    }

    @When("user has created reading tips and gives the export file name {string}")
    public void exportCommandIsEntered(String fileName) {
        testTips.deleteAllRows();
        testTips.addTip("SlashDot", "https://slashdot.org/", "blogs, news");
        testTips.addTip("tuts+", "https://code.tutsplus.com/", "tutorials");
        io = new UserInputsIOStub(command, fileName);
        ui = new UserInterface(io, testTips);
        ui.start();
    }

    @Then("the program should confirm the export with {string}")
    public void exportingTipsSuccessfull(String confirmation) {
        assertEquals(confirmation, io.outputs.get(12));
        this.dbManager.deleteAllFromDatabase();
    }

    @When("user imports a json file named {string} located in project root including two tips")
    public void importCommandIsEntered(String fileName) {
        testTips.deleteAllRows();
        testTips.addTip("SlashDot", "https://slashdot.org/", "blogs, news");
        testTips.addTip("tuts+", "https://code.tutsplus.com/", "tutorials");
        testTips.exportTips(fileName);
        io = new UserInputsIOStub(command, fileName);
        ui = new UserInterface(io, testTips);
        ui.start();
    }

    @Then("the program should confirm the import and the correct imported amount")
    public void importingTipsSuccessfull() {
        String expected = "And... done! Imported 2 reading tip(s).";
        assertEquals(expected, io.outputs.get(12));
        this.dbManager.deleteAllFromDatabase();
    }

    @When("title {string}, url {string}, tags {string} and command {string} are entered")
    public void titleUrlTagsAndCommandAreEntered(String title, String url, String tags, String show) {
        testTips.deleteAllRows();
        io = new UserInputsIOStub(command, title, url,  tags, show);
        ui = new UserInterface(io, testTips);
        ui.start();
    }

    @Then("{string} as 1st row")
    public void as1stRow(String expected) {
        prettyTable = new String [] {"","","","",""};
        prettyTable[0] = expected;
    }

    @Then("{string} as 2nd row")
    public void as2ndRow(String expected) {
        prettyTable[1] = expected;
    }

    @Then("{string} as 3rd row")
    public void as3rdRow(String expected) {
        prettyTable[2] = expected;
    }

    @Then("{string} as 4th row")
    public void as4thRow(String expected) {
        prettyTable[3] = expected;
    }

    @Then("{string} as final row")
    public void asFinalRow(String expected) {
        prettyTable[4] = expected;
    }

    @Then("the program should print the pretty table correctly")
    public void theProgramShouldPrintThePrettyTableCorrectly() {
        String expected = String.join(System.lineSeparator(),
            prettyTable[0],
            prettyTable[1],
            prettyTable[2],
            prettyTable[3],
            prettyTable[4]);
        assertEquals(expected, io.outputs.get(25));
        this.dbManager.deleteAllFromDatabase();
    }

}
