package bestreads.main;

import bestreads.ui.UserInterface;
import bestreads.ui.UserInterfaceIO;
import bestreads.databasehandlers.ConnectionManager;
import bestreads.databasehandlers.DatabaseManager;
import bestreads.readingtip.ReadingTips;

/** Main class for the ReadingTips program.
 *
 * This class is used to create needed objects and launch the program main loop
 */
public class Main {
    public String cucumberTestValue;

    public static void main(String[] args) {
	UserInterfaceIO io = new UserInterfaceIO();
	
	DatabaseManager dbManager = new DatabaseManager();
	
	ReadingTips tips = new ReadingTips(dbManager);
        UserInterface ui = new UserInterface(io, tips);
	
        ui.start();
    }
}
