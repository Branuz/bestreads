package bestreads.ui;

import java.util.Scanner;

/** Implementation of an IO object for reading commands
 * and data from user, and printing data to user.

 * @see IO
 */
public class UserInterfaceIO implements IO {
    private Scanner scanner;

    /** The constructor for userInterfaceIO class
     */
    public UserInterfaceIO() {
        scanner = new Scanner(System.in);
    }

    /** Print a given string to the screen
     *
     * @param m The string to be displayed
     */
    public void print(String m) {
        System.out.println(m);
    }

    /** Read a line of user input
     *
     * @return The string user entered
     */
    public String nextLine() {
        return scanner.nextLine();
    }
}
