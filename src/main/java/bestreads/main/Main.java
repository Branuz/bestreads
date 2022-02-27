package bestreads.main;

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
    }
}
