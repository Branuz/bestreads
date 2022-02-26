package bestreads;

import bestreads.ui.IO;
import bestreads.ui.UserInterface;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserInterfaceTest {
    
    @Test
    public void aTipCanBeSelected() {
        IOStub io = new IOStub("1");
        new UserInterface(io).start();
        assertEquals("Reading Tip Title?", io.outputs.get(5));
    }
    
    @Test
    public void showTipsCanBeSelected() {
        IOStub io = new IOStub("2");
        new UserInterface(io).start();
        assertEquals("Reading Tips:", io.outputs.get(5));
    }

    @Test
    public void appCanBeClosed() {
        IOStub io = new IOStub("3");
        new UserInterface(io).start();
        assertEquals("Closing", io.outputs.get(5));
    }
    
    @Test
    public void invalidCommandNotAccepted() {
        IOStub io = new IOStub("invalidUserInput");
        new UserInterface(io).start();
        assertEquals("Invalid command", io.outputs.get(5));
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