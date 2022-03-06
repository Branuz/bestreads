package bestreads;

import bestreads.ui.IO;
import java.util.ArrayList;

public class UserInputsIOStub implements IO {
    
    String[] userInputs;
    int inputCount;
    ArrayList<String> outputs;
    
    public UserInputsIOStub(String... input) {
        this.userInputs = input;
        this.outputs = new ArrayList<String>();
    }

    public void print(String m) {
        outputs.add(m);
    }

    public String nextLine() {
        if (userInputs.length == inputCount) {
            return "0";
        }
        String input = this.userInputs[inputCount];
        inputCount++;
        return input;
    }
    
}