package bestreads.ui;

/** Interface for user input and output
 */
public interface IO {
    /** Takes a string and outputs it to the user
     *
     * @param m The string to display
     */
    void print(String m);

    /** Read a string from the user
     *
     * @return String user entered
     */
    String nextLine();
}
