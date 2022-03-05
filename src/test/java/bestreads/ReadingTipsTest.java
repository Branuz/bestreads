package bestreads;

import bestreads.readingtip.ReadingTips;
import bestreads.databasehandlers.DatabaseManager;
import bestreads.readingtip.Tip;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ReadingTipsTest {
    private ReadingTips testTips;

    String env = "test";

    @Before
    public void setUp() {
	    DatabaseManager dbManager = new DatabaseManager(env);
	    this.testTips = new ReadingTips(dbManager);
        dbManager.deleteAllFromDatabase();
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
    
    // not working at the moment: (test) db should be cleared between test runs
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
}
