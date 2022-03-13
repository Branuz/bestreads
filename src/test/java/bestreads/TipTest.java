/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bestreads;

import bestreads.readingtip.Tip;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author ylireett
 */
public class TipTest {
    private Tip testTip;

    
    @Before
    public void setUp() {
        this.testTip = new Tip("https://ohjelmistotuotanto-hy-avoin.github.io/", "Ohtu", "course");
        this.testTip.setId(0);

    }
    
    @Test
    public void tipCanBeCreated() {
        
        assertEquals("https://ohjelmistotuotanto-hy-avoin.github.io/", testTip.getUrl());
        assertEquals("Ohtu", testTip.getTitle());
    }
    
    @Test
    public void tipUrlCanBeSet() {
        testTip.setUrl("https://google.com");
    
        assertEquals("https://google.com", testTip.getUrl());
    }
    
    @Test
    public void tipTitleCanBeSet() {
        testTip.setTitle("Scrum for Dummies");
        
        assertEquals("Scrum for Dummies", testTip.getTitle());
    }

    @Test
    public void tipTagCanBeSet() {
        ArrayList<String> tags = new ArrayList<>();
        tags.add("course");
        testTip.setTags(tags);
        
        assertEquals(tags, testTip.getTags());
    }
    
    @Test
    public void tipCanBePrintedCorrectly() {
        assertEquals("(id 0) Ohtu, https://ohjelmistotuotanto-hy-avoin.github.io/, tags [course]", testTip.toString());
    }

    @Test
    public void tipIdCanBeSet() {
        testTip.setId(3);
        assertEquals(3, testTip.getId());
    }

    @Test
    public void tagsArePrintedCorrectlyWithoutCommaAtEnd() {
        ArrayList<String> testTags = new ArrayList<>();
        testTags.add("search");
        testTags.add("google");
        testTip.setTags(testTags);
        assertEquals("search, google", testTip.getTagsAsString());
    }

    @Test
    public void tipCanBeCreatedWithTagsAsAList() {
        String[] tags = {"course"};
        Tip tip4 = new Tip("https://ohjelmistotuotanto-hy-avoin.github.io/", "Ohtu", 4, tags);
        assertEquals(4, tip4.getId());
    }
}
