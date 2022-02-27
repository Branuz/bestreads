package bestreads.readingtip;

/** The Tip object contains the data of a single reading tip.
 * @see ReadingTips class is a container for Tip class objects
 */
public class Tip {
    /** The URL of the tip */
    private String url;
    /** The tip title */
    private String title;

    /** Constructor for the Tip class
     *
     * @param url The network adress of the tip
     * @param title The title text for the tip
     */
    public Tip(String url, String title) {
	    this.url = url;
	    this.title = title;
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

    /** Converts a Tip to a String
     * 
     * @return String reprsentation of the tip in format:
     *         "Title text : http://tip.address/"
     */
    // @override
    public String toString() {
	    return this.title + " : " + this.url;
    }
}
