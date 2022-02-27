package bestreads;

import bestreads.ui.IO;
import bestreads.readingtip.ReadingTips;
import bestreads.ui.UserInterface;
import java.util.ArrayList;
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
        IOStub io = new IOStub("1");
        new UserInterface(io, this.testTips).start();
        assertEquals("Please add the url for the tip", io.outputs.get(7));
    }
    
    @Test
    public void showTipsCanBeSelected() {
        IOStub io = new IOStub("2");
        new UserInterface(io, this.testTips).start();
        assertEquals("Your Reading Tips:", io.outputs.get(6));
    }

    @Test
    public void appCanBeClosed() {
        IOStub io = new IOStub("3");
        new UserInterface(io, this.testTips).start();
        assertEquals("\nSee you soon!", io.outputs.get(6));
    }
    
    @Test
    public void invalidCommandNotAccepted() {
        IOStub io = new IOStub("invalidUserInput");
        new UserInterface(io, this.testTips).start();
        assertEquals("Ops! Please choose between 1 to 3.", io.outputs.get(6));
    }
}

class IOStub implements IO {
    
    String input;
    ArrayList<String> outputs;
    
    public IOStub(String input) {
        this.input = input;
        this.outputs = new ArrayList<String>();
    }

    public void print(String m) {
        outputs.add(m);
    }

    public String nextLine() {
        return this.input;
    }
    
}
