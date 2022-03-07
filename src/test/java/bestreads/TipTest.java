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
        this.testTip = new Tip("https://ohjelmistotuotanto-hy-avoin.github.io/", "New Tip", "Tag");
        this.testTip.setId(0);
    }
    
    @Test
    public void tipCanBeCreated() {
        
        assertEquals("https://ohjelmistotuotanto-hy-avoin.github.io/", testTip.getUrl());
        assertEquals("New Tip", testTip.getTitle());
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
        tags.add("tag1");
        testTip.setTags(tags);
        
        assertEquals(tags, testTip.getTags());
    }
    
    @Test
    public void tipCanBePrintedCorrectly() {
        assertEquals("(id 0) New Tip, https://ohjelmistotuotanto-hy-avoin.github.io/, tags [tag]", testTip.toString());
    }

    @Test
    public void tipIdCanBeSet() {
        testTip.setId(3);
        assertEquals(3, testTip.getId());
    }
}
