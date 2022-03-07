package bestreads;

import java.io.File;

import bestreads.readingtip.ReadingTips;
import bestreads.databasehandlers.DatabaseManager;
import bestreads.ui.UserInterface;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserInterfaceTest {
    private ReadingTips testTips;

    String dbFileName = "bestreadstest.db";    

    @Before
    public void setUp() {
	File dbFile = new File(dbFileName);
	dbFile.delete();
	
	    DatabaseManager dbManager = new DatabaseManager(dbFileName);	
	    this.testTips = new ReadingTips(dbManager);
        dbManager.deleteAllFromDatabase();
    }


    @Test
    public void showTipsCanBeSelected() {
        UserInputsIOStub io = new UserInputsIOStub("2", "0");
        new UserInterface(io, testTips).start();
        assertEquals("\nVoilá! All your reading tips:", io.outputs.get(9));
    }

    @Test
    public void appCanBeClosed() {
        UserInputsIOStub io = new UserInputsIOStub("0");
        new UserInterface(io, testTips).start();
        assertEquals("\nSee you soon!", io.outputs.get(9));
    }

    @Test
    public void invalidCommandNotAccepted() {
        UserInputsIOStub io = new UserInputsIOStub("invalidUserInput", "0");
        new UserInterface(io, testTips).start();
        assertEquals("Oops! Please choose between 0 and 5.", io.outputs.get(9));
    }
    
    @Test
    public void searchBTagCanBeSelected() {
        UserInputsIOStub io = new UserInputsIOStub("5", "tags", "0");
        new UserInterface(io, testTips).start();
        assertTrue(io.outputs.get(9).contains("Tags found:"));
        
    }
        
    @Test
    public void searchByTitleCanBeSelected() {
        UserInputsIOStub io = new UserInputsIOStub("4", "searching", "0");
        new UserInterface(io, testTips).start();
        assertEquals("Please provide a search phrase", io.outputs.get(9));
    }
    
    @Test
    public void searchByTitleResultsArePrinted() {
        String[] testInputs = {
            "1", //Add tip
            "title",
            "url",
            "tags",
            "4", //Search by title
            "ti",
            "0"
        };
        UserInputsIOStub io = new UserInputsIOStub(testInputs);
        new UserInterface(io, testTips).start();
        assertEquals("\nTa-da! You have 1 search result(s)\n", io.outputs.get(21));
        
    }
    
    @Test
    public void searchByEmptyTitlePrintsOops() {
        String[] testInputs = {
            "1", //Add tip
            "title",
            "url",
            "tags",
            "4", //Search by title
            "",
            "0"
        };
        UserInputsIOStub io = new UserInputsIOStub(testInputs);
        new UserInterface(io, testTips).start();
        assertEquals("Oops! Could not complete search with an empty search phrase.", io.outputs.get(21));
        
    }

    @Test
    public void tipCanBeDeleted() {
        String[] testInputs = {
            "1", //Add tip
            "title",
            "url",
            "tags",
            "3", //Delete tip
            "1",
            "y",
            "0"
        };
        UserInputsIOStub io = new UserInputsIOStub(testInputs);
        new UserInterface(io, testTips).start();
        assertEquals("Done! Tip with id 1 was deleted succesfully", io.outputs.get(24));
    }

    @Test
    public void tipIdDeletionWithInvalidInteger() {
        String[] testInputs = {
            "3",
            "notAnInteger",
            "0"
        };
        UserInputsIOStub io = new UserInputsIOStub(testInputs);
        new UserInterface(io, testTips).start();
        assertEquals("Oops! Please give a number only value for the id", io.outputs.get(12));
    }

    @Test
    public void tipDeletionCancelled() {
        String[] testInputs = {
            "1", //Add tip
            "title",
            "url",
            "tags",
            "3", //Delete tip
            "1",
            "n",
            "0"
        };
        UserInputsIOStub io = new UserInputsIOStub(testInputs);
        new UserInterface(io, testTips).start();
        assertEquals("No worries! Nothing was deleted", io.outputs.get(24));
    }

    @Test
    public void tipCantBeDeletedWithInvalidId() {
        String[] testInputs = {
            "1", //Add tip
            "title",
            "url",
            "tags",
            "3", //Delete tip
            "9",
            "y",
            "0"
        };
        UserInputsIOStub io = new UserInputsIOStub(testInputs);
        new UserInterface(io, testTips).start();
        assertEquals("Oops! Tip with id 9 was not found", io.outputs.get(23));
    }
}

