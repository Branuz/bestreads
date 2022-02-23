package bestreads;

import java.util.*;

public class ReadingTips {
    private ArrayList<Tip> tips;
    
    class Tip {
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
    
    public ReadingTips() {
	this.tips = new ArrayList<Tip>();
    }

    public void addTip(String url, String title) {
	Tip new_tip = new Tip(url, title);
	
	this.tips.add(new_tip);    
    }

    // @override
    public String toString() {
	String all_tips = "";
	
	int i = 0;
	
	while(this.tips.size() > i) {
	    all_tips += this.tips.get(i) + "\n";
	    i++;
	}

	return all_tips;
    }
}
