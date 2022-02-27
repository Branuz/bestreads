package bestreads.readingtip;

import java.util.*;

/** The ReadingTips object contains a collection of
 *  reading tips of Tip class objects.
 *
 * @see Tip
 */
public class ReadingTips {
    /** Container for the tips */
    private ArrayList<Tip> tips;

    /** The constructor of the ReadingTips class
     */
    public ReadingTips() {
	    this.tips = new ArrayList<Tip>();
    }

    /** Creates new Tip object and adds it to the cllection
     *
     * @param url The network adress of the tip
     * @param title The title text for the tip
     */
    public void addTip(String url, String title) {
	    Tip newTip = new Tip(url, title);
	
	    this.tips.add(newTip);    
    }

    /** Produces string representation of all the tips.
     * Each line contains one tip
     *
     * @return Tips as a multiline string
     * @see Tip#toString()
     */
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
