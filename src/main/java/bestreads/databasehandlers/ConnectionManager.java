package bestreads.databasehandlers;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private static String url = "jdbc:sqlite:bestreads.db";
    private static String testUrl = "jdbc:sqlite:bestreadstest.db";
    private static Connection con;

    
    /** Creates a connection between the application and database.
     * @return Connection for the database
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
        con = DriverManager.getConnection(url);
        return con;
    }

    /** Creates a connection between the application and test database.
    * @return Connection for the test database
    * @throws Exception
    */
    public static Connection getTestConnection() throws Exception {
        con = DriverManager.getConnection(testUrl);
        return con;
    }
}
