package bestreads.readingtip;

import java.util.ArrayList;

/**
 * The Tip object contains the data of a single reading tip.
 * 
 * @see ReadingTips class is a container for Tip class objects
 */
public class Tip {

    /** The URL of the tip */
    private String url;

    /** The tip title */
    private String title;

    /** The tip id */
    private int id;

    /** Tags */
    private ArrayList<String> tags;

    /**
     * Constructor for the Tip class
     *
     * @param url   The network adress of the tip
     * @param title The title text for the tip
     */
    public Tip(String url, String title) {
        this.url = url;
        this.title = title;

        this.tags = new ArrayList<>();
    }

    /**
     * Constructor for the Tip class
     *
     * @param url   The network adress of the tip
     * @param title The title text for the tip
     * @param tags  Tags separated by comma (',')
     */
    public Tip(String url, String title, String tags) {

        this(url, title);
        addTagsFromString(tags);
    }

    public Tip(String url, String title, int id, String[] tags) {

        this(url, title);
        this.id = id;
        
        for (String tag : tags) {
            addTag(tag);
        }
    }

    public Tip(String url, String title, int id, String tags) {

        this(url, title);
        this.id = id;
        addTagsFromString(tags);
    }

    public Tip(String url, String title, int id) {
        this.url = url;
        this.title = title;
        this.id = id;
    }

    

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /** Add a tag to the tip
     * No duplicate tags are created, and if there's 5 or more tags in the tip
     * allready, it will not be added.
     *
     * @param tag The tag
     */
    public void addTag(String tag) {
	    tag = tag.toLowerCase();
	
        if (this.tags.contains(tag)) {
            return;
        }

	    if (this.tags.size() >= 5) {
	        return;
	    }

        this.tags.add(tag);
	
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getTags() {
        return this.tags;
    }

    /** Add all the tags in a string, separated by comma, to a tag
     *
     * @param tag String containing tags: "firsttag, secondtag, third"
     */
    public void addTagsFromString(String tags) {
        String[] arrayOfStrings = tags.split(",");

        for (String s : arrayOfStrings) {
            addTag(cleanUpTag(s));
        }
    }

    /**
     * Clean up string from illegal characters
     *
     * @param tag
     *
     * @return Tag string where all non-numeric and non-alphabetic characters
     *         has been removed
     */
    private String cleanUpTag(String tag) {
        String s = "";

        for (int i = 0; i < tag.length(); i++) {
            Character c = tag.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                s += c;
            }
        }

        return s;
    }

    /**
     * Converts a Tip to a String
     * 
     * @return String reprsentation of the tip in format:
     *         "Title text : http://tip.address/ [tag]"
     */
    // @override
    public String toString() {
        return "(id " + this.id + ") " + this.title + ", " + this.url + ", tags " + this.tags;
    }
}
