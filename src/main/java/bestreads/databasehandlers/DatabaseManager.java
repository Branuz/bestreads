package bestreads.databasehandlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//import bestreads.readingtip.ReadingTips;
import bestreads.readingtip.Tip;

public class DatabaseManager {

    /** Constructor for the DatabaseManager class
     */
    public DatabaseManager() {
	    try {
	        dataBaseCreate(ConnectionManager.getConnection());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
    }
			     
    public void inserIntoDatabase(Connection conn, Tip insert) {
        Statement s  = null;
        String command = "INSERT INTO Tips(Title, Url) VALUES ('" + insert.getTitle() + "','" + insert.getUrl() + "');";

        try {
            s = conn.createStatement();
            s.execute(command); 
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDatabase(Connection conn, int id) {
        Statement s  = null;
        String command = String.format("DELETE FROM Tips WHERE id=%s;", id);

        try {
            s = conn.createStatement();
            s.execute(command); 
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Tip> getAllTipsFromDatabase(Connection conn) {
        ResultSet rs = null;
        Statement s = null;
        ArrayList<Tip> tips = new ArrayList<>();

        try {
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM Tips;");

            while (rs.next()) {
                String title = rs.getString("Title");
                String url = rs.getString("Url");
                int id = rs.getInt("id");
                tips.add(new Tip(url, title, id));
            }

            rs.close();
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tips;
    }

    public void updateInDatabase(Connection conn) {
        //To be made
    }

    public void dataBaseCreate(Connection conn) {
	    String command = "CREATE TABLE  IF NOT EXISTS  Tips (id INTEGER PRIMARY KEY, Title TEXT, Url TEXT);";	
        Statement s  = null;

        try {
            s = conn.createStatement();
            s.execute(command); 

            s.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
