package bestreads.databasehandlers;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {
    private static String defaultFileName = "bestreads.db";
    private static String fileName;

    private static Connection con;

    /**
     * Creates a connection between the application and database.
     * 
     * @return Connection for the database
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {
	if (fileName == null) {
	    return getConnection(defaultFileName);
	}

	return getConnection(fileName);

    }

    /**
     * Creates a connection between the application and database.
     * 
     * @param dbFileName The database filename
     *
     * @return Connection for the database
     * @throws Exception
     */
    public static Connection getConnection(String dbFileName) throws Exception {
	
	fileName = new String(dbFileName);
	
        String url = "jdbc:sqlite:" + dbFileName;
        con = DriverManager.getConnection(url);

        return con;
    }
}
