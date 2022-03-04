package bestreads.readingtip;

import java.util.*;

import bestreads.databasehandlers.DatabaseManager;
import io.cucumber.java.bs.I;

/** The ReadingTips object contains methods to
 *  handle Tips database
 *
 * @see Tip
 */
public class ReadingTips {
    /** Tips are kept in a database */
    private DatabaseManager dbManager;

    /** The constructor of the ReadingTips class
     * @param dbManager Tip database
     */
    public ReadingTips(DatabaseManager dbManager) {
	    this.dbManager = dbManager;
    }

    /** Creates new Tip object and adds it to the collection
     *
     * @param url The network address of the tip
     * @param title The title text for the tip
     * @param tags Tags separated by commas
     */
    public void addTip(String url, String title, String tags) {
	Tip newTip = new Tip(url, title, tags);

	    dbManager.inserIntoDatabase(newTip);
    }

    /** Delete a tip from database
     * @param id Numerical ID of the tip to be deleted
     */
    public void deleteTip(int id) {

	    dbManager.deleteFromDatabase(id);

    }
    
    public ArrayList<Tip> searchByTitle(String searchPhrase) {
        return dbManager.searchByTitle(searchPhrase);
    }

    public ArrayList<Integer> getIds() {
        ArrayList<Integer> ids = new ArrayList<Integer>();
        ArrayList<Tip> tips = new ArrayList<Tip>();

	    tips = dbManager.getAllTipsFromDatabase();

        int i = 0;

        while (tips.size() > i) {
            ids.add(tips.get(i).getId());
            i++;
        }
        return ids;
    }


    /** Produces string representation of all the tips.
     * Each line contains one tip
     *
     * @return Tips as a multiline string
     * @see Tip#toString()
     */
    // @override
    public String toString() {
        String allTips = "";
        ArrayList<Tip> tips = new ArrayList<Tip>();

	    tips = dbManager.getAllTipsFromDatabase();

        int i = 0;

        while (tips.size() > i) {
            allTips += tips.get(i) + "\n";
            i++;
        }

	    return allTips;
    }
}
