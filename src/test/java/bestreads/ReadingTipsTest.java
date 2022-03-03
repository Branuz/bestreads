package bestreads;

import bestreads.main.Main;
import bestreads.readingtip.ReadingTips;
import bestreads.databasehandlers.DatabaseManager;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class ReadingTipsTest {
    private ReadingTips testTips;

    @Before
    public void setUp() {
	DatabaseManager dbManager = new DatabaseManager();
	this.testTips = new ReadingTips(dbManager);
    }

    @Test
    public void tipsCanBeCreated() {
	DatabaseManager dbManager = new DatabaseManager();
	assertNotNull(new ReadingTips(dbManager));
    }

    @Test
    public void tipsCanBeAdded() {
	testTips.addTip("A", "a");

	assertTrue(testTips.toString().contains("a : A"));
    }

    @Test
    public void tipsToStringWorksWithListOfTips() {
	String excepted = "a : A\nb : B\n";

	testTips.addTip("A", "a");
	testTips.addTip("B", "b");

	assertTrue(testTips.toString().contains("a : A"));
	assertTrue(testTips.toString().contains("b : B"));
    }
}
