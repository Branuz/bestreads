package bestreads;

import bestreads.readingtip.ReadingTips;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class ReadingTipsTest {
    private ReadingTips testTips;

    @Before
    public void setUp() {
	this.testTips = new ReadingTips();
    }

    @Test
    public void tipsCanBeCreated() {
	assertNotNull(new ReadingTips());
    }

    @Test
    public void tipsCanBeAdded() {
	String excepted = "a : A\n";

	testTips.addTip("A", "a");

	assertEquals(excepted, testTips.toString());	
    }
    @Test
    public void tipsToStringWorksWithListOfTips() {
	String excepted = "a : A\nb : B\n";

	testTips.addTip("A", "a");
	testTips.addTip("B", "b");

	assertEquals(excepted, testTips.toString());
    }
}
