package bestreads.fuzzymatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Static class for matching strings against each other considering
 *  there might be spelling errors
 */
public class FuzzyMatch {

    /** Test if first argument is a substring of the second argument
     * Example exactMatch("a", "abc") returns True
     *
     * @param a String to be searched
     * @param b String to be searched from
     *
     * @return If first string is found with in second, true
     */
    private static Boolean exactMatch(String a, String b) {
	    b = b.toLowerCase();
	    a = a.toLowerCase();	

	    String regex = a;
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(b);

	    return matcher.find();
    }

     /** Test if first argument is a substring of the second argument,
     *even if the first string is missing one matching character
     *
     * Example missingChar("ac", "aaabccc") returns True
     *
     * @param a String to be searched
     * @param b String to be searched from
     *
     * @return If first string is found with in second in fuzzy way, true
     */    
    private static Boolean missingChar(String a, String b) {
	    b = b.toLowerCase();
	    a = a.toLowerCase();	
	
	    for(int i = 1; i < a.length(); i++) {
	        String aleft = a.substring(0, i);
	        String aright = a.substring(i, a.length());
	    
	        String regex = aleft + "." + aright;
	        Pattern pattern = Pattern.compile(regex);

	        Matcher matcher = pattern.matcher(b);
	        if (matcher.find()) {
		    return true;
		}	
	    }
	    return false;
    }
    
    /** Test if first argument is a substring of the second argument
     * Example additionalChar("abc", "aaaccc") returns True
     *
     * @param a String to be searched
     * @param b String to be searched from
     *
     * @return If first string is found with in second in fuzzy way, true
     */    
    private static Boolean additionalChar(String a, String b) {
	    b = b.toLowerCase();
	    a = a.toLowerCase();	

	    for(int i = 1; i <= a.length(); i++) {
	        String aleft = a.substring(0, i-1);
	        String aright = a.substring(i, a.length());
	    
	        String regex = aleft + aright;
	        Pattern pattern = Pattern.compile(regex);

	        Matcher matcher = pattern.matcher(b);
	        if (matcher.find()) {
		    return true;
		}	
	    }

	    return false;
    }

    /** Test if first argument is a substring of the second argument
     * Example flippedChar("adc", "aaabccc") returns True
     *
     * @param a String to be searched
     * @param b String to be searched from
     *
     * @return If first string is found with in second in fuzzy way, true
     */    
    private static Boolean flippedChar(String a, String b) {
	    b = b.toLowerCase();
	    a = a.toLowerCase();	
	
	    for(int i = 1; i <= a.length(); i++) {
	        String aleft = a.substring(0, i-1);
	        String aright = a.substring(i, a.length());
	    
	        String regex = aleft + "." + aright;
	        Pattern pattern = Pattern.compile(regex);

	        Matcher matcher = pattern.matcher(b);
	        if (matcher.find()) {
		    return true;
		}	
	    }

	    return false;
    }

    /** Test if first argument is a substring of the second argument
     * Example additionalChar("abc", "aaaccc") returns True
     *
     * @param a String to be searched
     * @param b String to be searched from
     *
     * @return If first string is found with in second in fuzzy way, true
     */    
    public static Boolean fuzzyMatch(String a, String b) {
	
	    return exactMatch(a, b) ||
	        missingChar(a, b) ||
	        additionalChar(a, b) ||
	        flippedChar(a, b);
    }
}
