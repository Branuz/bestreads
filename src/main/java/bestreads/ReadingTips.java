package bestreads;

import java.util.*;

public class ReadingTips {
    private ArrayList<ReadingTip> tips;
    
    public ReadingTips() {
	    this.tips = new ArrayList<ReadingTip>();
    }

    public void addTip(String url, String title) {
	    ReadingTip newTip = new ReadingTip(url, title);
	
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
