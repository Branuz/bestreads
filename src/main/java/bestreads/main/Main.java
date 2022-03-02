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
        UserInterface ui = new UserInterface(new UserInterfaceIO(), new ReadingTips());
        createDataBaseTables();
        ui.start();
    }

    private static void createDataBaseTables() {
        DatabaseManager dbManager = new DatabaseManager();
        String tipTable = "CREATE TABLE  IF NOT EXISTS  Tips (id INTEGER PRIMARY KEY, Title TEXT, Url TEXT);";

        try {
            dbManager.dataBaseCreate(ConnectionManager.getConnection(), tipTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
