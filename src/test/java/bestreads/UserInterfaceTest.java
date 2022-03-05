package bestreads;

import bestreads.readingtip.ReadingTips;
import bestreads.databasehandlers.DatabaseManager;
import bestreads.ui.UserInterface;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserInterfaceTest {
    private ReadingTips testTips;

    String env = "prod";

    @Before
    public void setUp() {
	    DatabaseManager dbManager = new DatabaseManager(env);	
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
    public void searchByTitleCanBeSelected() {
        UserInputsIOStub io = new UserInputsIOStub("4", "searching", "0");
        new UserInterface(io, testTips).start();
        assertEquals("Please provide a search phrase", io.outputs.get(9));
    }
}

