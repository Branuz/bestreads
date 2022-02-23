package bestreads.readingtip;

import java.util.*;

public class ReadingTips {
    private ArrayList<Tip> tips;
    
    public ReadingTips() {
	    this.tips = new ArrayList<Tip>();
    }

    public void addTip(String url, String title) {
	    Tip newTip = new Tip(url, title);
	
	    this.tips.add(newTip);    
    }

    // @override
    public String toString() {
	    String allTips = "";
	
	    int i = 0;
	
	    while (this.tips.size() > i) {
	        allTips += this.tips.get(i) + "\n";
	        i++;
	    }

	    return allTips;
    }
}
