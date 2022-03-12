package bestreads.readingtip;

import java.util.*;

import bestreads.databasehandlers.DatabaseManager;
import bestreads.exportimport.Exporter;
import bestreads.exportimport.Importer;
import bestreads.fuzzymatch.FuzzyMatch;

/**
 * The ReadingTips object contains methods to
 * handle Tips database
 *
 * @see Tip
 */
public class ReadingTips {
    /** Tips are kept in a database */
    private DatabaseManager dbManager;

    /**
     * The constructor of the ReadingTips class
     * 
     * @param dbManager Tip database
     */
    public ReadingTips(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    /**
     * Creates new Tip object and adds it to the collection
     *
     * @param url   The network address of the tip
     * @param title The title text for the tip
     * @param tags  Tags separated by commas
     */
    public void addTip(String url, String title, String tags) {
        Tip newTip = new Tip(url, title, tags);

        dbManager.inserIntoDatabase(newTip);
    }

    /**
     * Delete a tip from database
     * 
     * @param id Numerical ID of the tip to be deleted
     */
    public void deleteTip(int id) {

        dbManager.deleteFromDatabase(id);

    }

    /**
     * Delete all rows from database
     * 
     */
    public void deleteAllRows() {

        dbManager.deleteAllFromDatabase();

    }

    /**
     * Get all used tags from database
     */
    public ArrayList<String> getAllTags() {
        return dbManager.listTags();
    }

    /**
     * Search tips by title from database using fuzzy string matching
     * 
     * @param searchPhrase string used for search
     * @return Tips as an array
     */
    public ArrayList<Tip> searchByTitle(String searchPhrase) {
        ArrayList<Tip> tips = new ArrayList<Tip>();
	    ArrayList<Tip> returnTips = new ArrayList<Tip>();
	
	    tips = dbManager.getAllTipsFromDatabase();

	    for (Tip tip: tips) {
		    if (FuzzyMatch.fuzzyMatch(searchPhrase, tip.getTitle())) {
			    returnTips.add(tip);
		    }
	    }
	
	    return returnTips;
    }

    /**
     * Search tips by tag from database
     * 
     * @param searchTag string used for search
     * @return Tips as an array
     */
    public ArrayList<Tip> searchByTag(String searchTag) {
        return dbManager.searchByTag(searchTag);
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

    /**
     * Produces string representation of all the tips.
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

    public ArrayList<Tip> getTips() {
        ArrayList<Tip> tips = new ArrayList<Tip>();

        tips = dbManager.getAllTipsFromDatabase();

        return tips;
    }
    
    /**
     * Exports users's reading tips to json.
     */
    public void exportTips(String fileName) {
        Exporter ex = new Exporter();
        ex.createNewJsonFile(this.getTips(), fileName);
    }
    
    /**
     * Imports the contents of exportFile.json containing reading tips.
     */
    public void importTips(String fileName) {
        Importer imp = new Importer();
        imp.importFile(dbManager, fileName);
    }
}
