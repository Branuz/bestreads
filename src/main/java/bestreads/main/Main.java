package bestreads.main;

import bestreads.readingtip.ReadingTips;
import bestreads.ui.UserInterface;
import bestreads.ui.UserInterfaceIO;

/** Main class for the ReadingTips program.
 *
 * This class is used to create needed objects and launch the program main loop
 */
public class Main {
    public String cucumberTestValue;

    public static void main(String[] args) {
        UserInterface ui = new UserInterface(new UserInterfaceIO());
        ui.start();
        Main test = new Main();
        test.testMethod();
	    System.out.println(test.cucumberTestValue);

	    ReadingTips tips = new ReadingTips();

	    tips.addTip("http://foo.bar", "First tip");
	    tips.addTip("http://bar.baz", "Second tip");

	    System.out.println(tips);
    }

    //Just for setting up the testing will be deleted later
    public void testMethod() {
        this.cucumberTestValue = "Hello world!";
    }
}
