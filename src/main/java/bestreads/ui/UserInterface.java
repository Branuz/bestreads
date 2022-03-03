package bestreads.ui;
import java.util.*;

import bestreads.readingtip.ReadingTips;

/** Main class for the command line user interface
 *
 * @see IO
 * @see UserInterfaceIO
 * @see ReadingTips
 */
public class UserInterface {
    /** Reading Tips container */
    private ReadingTips tips;
    /** All inputs and outputs are routed via this object*/
    private IO io;

    /** Contsructor for the UserInterface.
     *
     * @param io Object for reading user input and writing to user's screen
     * @param tips Object for storing the tips
     */
    public UserInterface(IO io, ReadingTips tips) {
        this.tips = tips;
        this.io = io;
    }

    /** Start the main loop of the user interface
     */
    public void start() {

        io.print("\nnunununununununununununununununununununununununununununununun\n");
        io.print("Welcome to Bestreads!");
        
        while (true) {
            showCommands();
            String input = io.nextLine();
            if (!input.matches("([1-4])")) {
                io.print("Oops! Please choose between 1 and 4.");
                continue;
            }
            int command = Integer.valueOf(input);
            if (command == 1) {
                addTip();

            }
            if (command == 2) {
                showTips();
            }
            if (command == 3) {
                io.print("\nSee you soon!");
                io.print("\nnunununununununununununununununununununununununununununununun\n");
                break;
            }
            if (command == 4) {
                deleteTip();
            }
        }
    }

    /** Print out all the possible choices
     */
    public void showCommands() {
        io.print("\nPlease choose what you wish to do");
        io.print("1 -- Add a reading tip");
        io.print("2 -- Show reading tips");
        io.print("4 -- Delete reading tip");
        io.print("3 -- Exit program\n");
    }

    /** Asks needed information from user and create a new
     *  into the database
     */
    public void addTip() {
        io.print("Please give the title for the tip");
        String title = io.nextLine();
        io.print("Please add the url for the tip");
        String url = io.nextLine();
        if (!title.isBlank() & !url.isBlank()) {
            this.tips.addTip(url, title);
            io.print("Awesome! You just added a new tip - " + title + ": " + url);
        } else {
            io.print("Oops! Nothing was added. Both a title and an url are needed.");
        }
    }

    /** Show all reading tips in the database
     */
    public void showTips() {
        io.print("Your Reading Tips:");
        io.print(this.tips.toString());
    }

    public void deleteTip() {
        showTips();
        io.print("Give the id of the Reading Tip you want to delete:");
        String id = io.nextLine();
        if (id.matches("(^[0-9]+$)")) {
            int idd = Integer.valueOf(id);
            ArrayList<Integer> ids = new ArrayList<Integer>();
            ids = this.tips.getIds();
            if (ids.contains(idd)) {
                this.tips.deleteTip(idd);
                io.print("Tip with id: " + id + " deleted succesfully");
            } else {
                io.print("Tip with id: " + id + " not found");
            }
            
        } else {
            io.print("Oops! Please give an integer value for the id");
        }
    }
}
