package bestreads;

import bestreads.readingtip.ReadingTips;
import bestreads.databasehandlers.DatabaseManager;
import bestreads.readingtip.Tip;

import java.util.ArrayList;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReadingTipsTest {
    private ReadingTips testTips;
    private DatabaseManager dbManager;

    String dbFileName = "bestreadstest.db";

    @Before
    public void setUp() {
        File dbFile = new File(dbFileName);
        dbFile.delete();
        
	    this.dbManager = new DatabaseManager(dbFileName);
	    this.testTips = new ReadingTips(dbManager);
    }

    @After
    public void cleadUp() {
	    this.dbManager.deleteAllFromDatabase();
    }


    @Test
    public void tipsCanBeCreated() {
	    DatabaseManager dbManager = new DatabaseManager(dbFileName);
	    assertNotNull(new ReadingTips(dbManager));
    }

    @Test
    public void tipsCanBeAdded() {
	    testTips.addTip("SlashDot", "https://slashdot.org/", "blogs");
	    assertTrue(testTips.toString().contains("https://slashdot.org/, SlashDot"));
    }

    @Test
    public void tipsToStringWorksWithListOfTips() {

	    testTips.addTip("SlashDot", "https://slashdot.org/", "blogs");
	    testTips.addTip("tuts+", "https://code.tutsplus.com/", "tutorials");

	    assertTrue(testTips.toString().contains("https://slashdot.org/, SlashDot"));
	    assertTrue(testTips.toString().contains("https://code.tutsplus.com/, tuts+"));

    }
    
    @Test
    public void searchByTitleGivesCorrectResults() {
        testTips.addTip("SlashDot", "https://slashdot.org/", "blogs");
        testTips.addTip("SlashDot", "https://slashdot.org/", "news");
        testTips.addTip("A List Apart", "https://alistapart.com/", "news");

        
        // !! Should also test that tip w/ title "this should not be included" is not included in search results
        ArrayList<Tip> searchResults = testTips.searchByTitle("SlashDot");
        assertEquals(2, searchResults.size());
        
    }
    
    @Test
    public void searchByTitleReturnsEmptyIfNoResults() {
        testTips.addTip("SlashDot", "https://slashdot.org/", "blogs");
        testTips.addTip("tuts+", "https://code.tutsplus.com/", "tutorials");
        
        ArrayList<Tip> searchResults = testTips.searchByTitle("find");
        assertEquals(0, searchResults.size());
    }

    @Test
    public void creatingTipWithOneTagWorks() {
	    testTips.addTip("SlashDot", "https://slashdot.org/", "blogs");

	    assertTrue(testTips.toString().contains("blogs"));	
    }

    @Test
    public void creatingTipWithTwoTagsWorks() {
        testTips.addTip("SlashDot", "https://slashdot.org/", "blogs, news");

        assertTrue(testTips.toString().contains("blogs"));
        assertTrue(testTips.toString().contains("news"));	
    }
    
    @Test
    public void searchByTagGivesCorrectResults() {
        testTips.addTip("SlashDot", "https://slashdot.org/", "blogs, news");
        testTips.addTip("tuts+", "https://code.tutsplus.com/", "tutorials");
        testTips.addTip("A List Apart", "https://alistapart.com/", "news");
        ArrayList<Tip> searchResults = testTips.searchByTag("news");
        assertEquals(2, searchResults.size());
        
    }

    @Test
    public void searchByTagReturnsEmptyIfNoResults() {
        testTips.addTip("SlashDot", "https://slashdot.org/", "blogs");
        testTips.addTip("tuts+", "https://code.tutsplus.com/", "tutorials");
        
        ArrayList<Tip> searchResults = testTips.searchByTag("news");
        assertEquals(0, searchResults.size());
    }



    @Test
    public void deleteAllTipsWorks() {
        testTips.addTip("SlashDot", "https://slashdot.org/", "blogs, news");
        testTips.addTip("tuts+", "https://code.tutsplus.com/", "tutorials");
        ArrayList<Integer> tips = testTips.getIds();
        assertEquals(2, tips.size());
        testTips.deleteAllRows();
        ArrayList<Integer> noTips = testTips.getIds();
        assertEquals(0, noTips.size());
    }


    @Test
    public void dataBaseManagerCanBeCreated() {
	    assertNotNull(new DatabaseManager());
    }

    @Test
    public void tipsCanBeSearchedByTitle() {
        DatabaseManager dbManager = new DatabaseManager(dbFileName);
        dbManager.searchByTitle("result");
        testTips.addTip("SlashDot", "https://slashdot.org/", "blogs, news");
	    assertNotNull(dbManager.searchByTitle("SlashDot"));
    }

}
