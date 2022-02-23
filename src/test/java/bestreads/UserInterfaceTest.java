package bestreads;

import bestreads.ui.IO;
import bestreads.ui.UserInterface;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserInterfaceTest {
    
    public UserInterfaceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void appCanBeClosed() {
       IOStub io = new IOStub("3");
       new UserInterface(io).start();
       assertEquals("Closing", io.outputs.get(5));
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