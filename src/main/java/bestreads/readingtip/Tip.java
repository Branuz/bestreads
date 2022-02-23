package bestreads.readingtip;

    
public class Tip {
    private String url;
    private String title;

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

    // @override
    public String toString() {
	    return this.title + " : " + this.url;
    }
}
