package bestreads.databasehandlers;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private static String defaultFileName = "bestreads.db";
    
    private static Connection con;

    
    /** Creates a connection between the application and database.
     * @return Connection for the database
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
	
	return getConnection(defaultFileName);
	
    }

    /** Creates a connection between the application and database.
     * @param fileName The database filename
     *
     * @return Connection for the database
     * @throws Exception
     */
    public static Connection getConnection(String fileName) throws Exception {
	
	String url = "jdbc:sqlite:" + fileName;
        con = DriverManager.getConnection(url);
	
        return con;	
    }
}
