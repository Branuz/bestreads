package bestreads;

import static bestreads.fuzzymatch.FuzzyMatch.fuzzyMatch;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

public class FuzzyMatchTest {
    @Before
    public void setUp() {
    }

    @Test
    public void matchExactString() {
	assertTrue(fuzzyMatch("feline", "Cats are felines"));
    }
    
    @Test
    public void matchMissingLetter() {
	assertTrue(fuzzyMatch("fline", "Cats are felines"));
    }
    
    @Test
    public void matchChangedLetter() {
	assertTrue(fuzzyMatch("faline", "Cats are felines"));
    }

    @Test
    public void matchAdditionalLetter() {
	assertTrue(fuzzyMatch("felinee", "Cats are felines"));
    }
    
    @Test
    public void doNotMatchTwoMissingLetters() {
	assertFalse(fuzzyMatch("fene", "Cats are felines"));
    }

    @Test
    public void doNotMatchTwoAdditionalLetters() {
	assertFalse(fuzzyMatch("efelinese", "Cats are felines"));
    }

    @Test
    public void doNotMatchTwoChangedLetters() {
	assertFalse(fuzzyMatch("falinas", "Cats are felines"));
    }    
}

