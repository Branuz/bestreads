package bestreads.ui;

import java.util.*;

import com.github.freva.asciitable.AsciiTable;
import com.github.freva.asciitable.Column;
import com.github.freva.asciitable.HorizontalAlign;

import bestreads.readingtip.ReadingTips;
import bestreads.readingtip.Tip;

/**
 * Main class for the command line user interface
 *
 * @see IO
 * @see UserInterfaceIO
 * @see ReadingTips
 */
public class UserInterface {
    /** Reading Tips container */
    private ReadingTips tips;
    /** All inputs and outputs are routed via this object */
    private IO io;

    /**
     * Constructor for the UserInterface.
     *
     * @param io   Object for reading user input and writing to user's screen
     * @param tips Object for storing the tips
     */
    public UserInterface(IO io, ReadingTips tips) {
        this.tips = tips;
        this.io = io;
    }

    /**
     * Start the main loop of the user interface
     */
    public void start() {

        io.print("\n@->-->-- @->-->-- @->-->-- @->-->-- @->-->-- @->-->-- @->-->--\n");
        io.print("Welcome to BestReads!");

        while (true) {
            showCommands();
            String input = io.nextLine();
            if (!input.matches("([0-7])")) {
                io.print("Oops! Please choose between 0 and 7.");
                continue;
            }
            int command = Integer.valueOf(input);

            if (command == 0) {
                io.print("\nSee you soon!");
                io.print("\n@->-->-- @->-->-- @->-->-- @->-->-- @->-->-- @->-->-- @->-->--\n");
                break;
            }
            chooseCommand(command);
        }
    }

    /**
     * Print out all the possible choices
     */
    public void showCommands() {
        io.print("\nPlease choose what you wish to do");
        io.print("1 -- Add a reading tip");
        io.print("2 -- Show reading tips");
        io.print("3 -- Delete a reading tip");
        io.print("4 -- Search tips by title");
        io.print("5 -- Search tips by tag");
        io.print("6 -- Export tips");
        io.print("7 -- Import tips from json");
        io.print("0 -- Exit program\n");
    }

    /**
     * Choose correct command
     */
    public void chooseCommand(int command) {
        if (command == 1) {
            addTip();
        }
        if (command == 2) {
            showTips();
        }

        if (command == 3) {
            deleteTip();
        }
        if (command == 4) {
            searchByTitle();
        }
        if (command == 5) {
            searchByTag();
        }
        if (command == 6) {
            exportTips();
        }
        if (command == 7) {
            importTips();
        }
    }

    /**
     * Asks needed information from user and create a new
     * into the database
     */
    public void addTip() {

        io.print("Please give the title for the tip");
        String title = io.nextLine();

        io.print("Please add the url for the tip");
        String url = io.nextLine();

        io.print("Please give tags separated by commas for the tip");
        String tags = io.nextLine().toLowerCase();

        if (!title.isBlank() && !url.isBlank()) {
            this.tips.addTip(url, title, tags);
            io.print("\nAwesome! You just added a new tip: \n" + title + ": " + url + " with tag(s) " + tags);
        } else {
            io.print("Oops! Nothing was added. Both a title and an url are needed.");
        }
    }

    /**
     * Shows all reading tips in the database
     */
    public void showTips() {
        io.print("\nVoilá! All your reading tips:");
        printAsciiTable(this.tips.getTips());
    }

    /**
     * Shows given table data in an Ascii table
     */
    public void printAsciiTable(List<Tip> tips) {
        io.print(AsciiTable.getTable(tips, Arrays.asList(
                new Column().header("Id").headerAlign(HorizontalAlign.CENTER)
                        .with(tip -> Integer.toString(tip.getId())),
                new Column().header("Title").headerAlign(HorizontalAlign.CENTER).dataAlign(HorizontalAlign.CENTER)
                        .with(tip -> tip.getTitle()),
                new Column().header("Url").headerAlign(HorizontalAlign.CENTER).dataAlign(HorizontalAlign.CENTER)
                        .with(tip -> tip.getUrl()),
                new Column().header("Tags").headerAlign(HorizontalAlign.CENTER).dataAlign(HorizontalAlign.CENTER)
                        .with(tip -> tip.getTagsAsString()))));
    }

    /**
     * Asks needed information from user and
     * deletes selected id from the database
     */

    public void deleteTip() {
        showTips();
        io.print("Please give the id of the tip you want to delete:");
        String id = io.nextLine();
        if (id.matches("(^[0-9]+$)")) {
            int idd = Integer.valueOf(id);
            ArrayList<Integer> ids = new ArrayList<Integer>();
            ids = this.tips.getIds();
            if (!ids.contains(idd)) {
                io.print("Oops! Tip with id " + id + " was not found");
            } else {
                io.print("Are you sure you want to delete tip " + id + "? (y/n))");
                String confirmed = io.nextLine().toLowerCase();

                if (confirmed.equals("y")) {
                    this.tips.deleteTip(idd);
                    io.print("Done! Tip with id " + id + " was deleted succesfully");
                } else {
                    io.print("No worries! Nothing was deleted");
                }
            }
        } else {
            io.print("Oops! Please give a number only value for the id");
        }
    }

    /**
     * Searches all tips by a title
     */

    public void searchByTitle() {
        io.print("Please provide a search phrase");
        String searchPhrase = io.nextLine();
        if (!searchPhrase.isBlank()) {
            ArrayList<Tip> result = this.tips.searchByTitle(searchPhrase);
            if (result.size() > 0) {
                io.print("\nTa-da! You have " + result.size() + " search result(s):");
                printAsciiTable(result);
            } else {
                io.print("\nOh no! You have " + result.size() + " search result(s):");
            }
        } else {
            io.print("Oops! Could not complete search with an empty search phrase.");
        }
    }

    /**
     * Searches all tips by a tag
     */
    public void searchByTag() {
        ArrayList<String> tags = this.tips.getAllTags();
        io.print("Tags found: " + tags);
        io.print("Please name the tag you wish to search by");
        String searchTag = io.nextLine();
        if (!searchTag.isBlank()) {
            ArrayList<Tip> result = this.tips.searchByTag(searchTag);
            if (result.size() > 0) {
                io.print("\nTa-da! You have " + result.size() + " search result(s):");
                printAsciiTable(result);
            } else {
                io.print("\nOh no! You have " + result.size() + " search result(s):");
            }
        } else {
            io.print("Oops! Could not complete search without a tag.");
        }
    }

    /**
     * Exports user's reading tips to json format.
     * 
     * @see ReadingTips#exportTips()
     */
    public void exportTips() {
        io.print("Please give the name of the export file or choose default by enter:");
        String fileName = io.nextLine();
        if (fileName.isBlank() | fileName.isEmpty()) {
            fileName = "exportFile.json";
        }
        this.tips.exportTips(fileName);
        io.print("All done! Your reading tips have been exported to " + fileName + ".");
    }

    /**
     * Imports reading tips from exportFile.json.
     * 
     * @see ReadingTips#importTips()
     */
    public void importTips() {
        int initialSize = this.tips.getTips().size();
        io.print("Please give the name of the import file or choose default by enter:");
        String fileName = io.nextLine();
        if (fileName.isBlank() | fileName.isEmpty()) {
            fileName = "exportFile.json";
        }
        this.tips.importTips(fileName);
        int importedTipsSize = this.tips.getTips().size() - initialSize;
        io.print("And... done! Imported " + importedTipsSize + " reading tip(s).");
    }
}
