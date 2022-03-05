package bestreads;

import bestreads.readingtip.ReadingTips;
import bestreads.databasehandlers.DatabaseManager;
import bestreads.readingtip.Tip;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReadingTipsTest {
    private ReadingTips testTips;
    private DatabaseManager dbManager;

    String env = "test";

    @Before
    public void setUp() {
	    this.dbManager = new DatabaseManager(env);
	    this.testTips = new ReadingTips(dbManager);
    }

    @After
    public void cleadUp() {
	    this.dbManager.deleteAllFromDatabase();
    }


    @Test
    public void tipsCanBeCreated() {
	    DatabaseManager dbManager = new DatabaseManager(env);
	    assertNotNull(new ReadingTips(dbManager));
    }

    @Test
    public void tipsCanBeAdded() {
	    testTips.addTip("A", "a", "tag1");
	    assertTrue(testTips.toString().contains("a, A"));
    }

    @Test
    public void tipsToStringWorksWithListOfTips() {

	    testTips.addTip("A", "a", "tag1");
	    testTips.addTip("B", "b", "tag2");

	    assertTrue(testTips.toString().contains("a, A"));
	    assertTrue(testTips.toString().contains("b, B"));

    }
    
 @Test
    public void searchByTitleGivesCorrectResults() {
        testTips.addTip("A", "first result", "tag1");
        testTips.addTip("B", "second result", "tag2");
        testTips.addTip("C", "this should not be included", "tag3");

        
        // !! Should also test that tip w/ title "this should not be included" is not included in search results
        ArrayList<Tip> searchResults = testTips.searchByTitle("result");
        assertEquals(2, searchResults.size());
        
    }
    
    @Test
    public void searchByTitleReturnsEmptyIfNoResults() {
        testTips.addTip("A", "first result","tag1");
        testTips.addTip("B", "second result", "tag2");
        
        ArrayList<Tip> searchResults = testTips.searchByTitle("find");
        assertEquals(0, searchResults.size());
    }

    @Test
    public void searchByTagGivesCorrectResults() {
        testTips.addTip("D", "first result", "tag0");
        testTips.addTip("E", "second result", "tag0");
        testTips.addTip("F", "this should not be included", "tag2");
        ArrayList<Tip> searchResults = testTips.searchByTag("tag0");
        assertEquals(2, searchResults.size());
        
    }

    @Test
    public void searchByTagReturnsEmptyIfNoResults() {
        testTips.addTip("A", "first result","tag1");
        testTips.addTip("B", "second result", "tag2");
        
        ArrayList<Tip> searchResults = testTips.searchByTag("tag3");
        assertEquals(0, searchResults.size());
    }

    @Test
    public void deleteAllTipsWorks() {
        testTips.addTip("A", "first result","tag1");
        testTips.addTip("B", "second result", "tag2");
        ArrayList<Integer> tips = testTips.getIds();
        assertEquals(2, tips.size());
        testTips.deleteAllRows();
        ArrayList<Integer> noTips = testTips.getIds();
        assertEquals(0, noTips.size());
    }

}
