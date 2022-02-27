package bestreads;

import bestreads.readingtip.ReadingTips;
import bestreads.ui.UserInterface;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserInterfaceTest {
    private ReadingTips testTips;

    @Before
    public void setUp() {
	this.testTips = new ReadingTips();
    }

    @Test
    public void aTipCanBeSelected() {
        UserInputsIOStub io = new UserInputsIOStub("1", "title", "url", "3");
        new UserInterface(io, testTips).start();
        assertEquals("Please add the url for the tip", io.outputs.get(7));
    }

    @Test
    public void showTipsCanBeSelected() {
        UserInputsIOStub io = new UserInputsIOStub("2", "3");
        new UserInterface(io, testTips).start();
        assertEquals("Your Reading Tips:", io.outputs.get(6));
    }

    @Test
    public void appCanBeClosed() {
        UserInputsIOStub io = new UserInputsIOStub("3");
        new UserInterface(io, testTips).start();
        assertEquals("\nSee you soon!", io.outputs.get(6));
    }

    @Test
    public void invalidCommandNotAccepted() {
        UserInputsIOStub io = new UserInputsIOStub("invalidUserInput", "3");
        new UserInterface(io, testTips).start();
        assertEquals("Ops! Please choose between 1 and 3.", io.outputs.get(6));
    }
}

