package bestreads.databasehandlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
			     
    public void inserIntoDatabase(Tip insert) {
        Statement s  = null;
        String command = "INSERT INTO Tips(Title, Url) VALUES ('" + insert.getTitle() + "','" + insert.getUrl() + "');";

        try {
	        Connection conn = ConnectionManager.getConnection();
            s = conn.createStatement();
            s.execute(command); 
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDatabase(int id) {
        Statement s  = null;
        String command = String.format("DELETE FROM Tips WHERE id=%s;", id);

        try {
	        Connection conn = ConnectionManager.getConnection();	    
            s = conn.createStatement();
            s.execute(command); 
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public ArrayList<Tip> getAllTipsFromDatabase() {
        ResultSet rs = null;
        Statement s = null;
        ArrayList<Tip> tips = new ArrayList<>();

        try {
	        Connection conn = ConnectionManager.getConnection();	    
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
    
    public ArrayList<Tip> searchByTitle(String searchPhrase) {
        ResultSet rs = null;
        Statement s = null;
        ArrayList<Tip> tips = new ArrayList<>();
        
        try {
            Connection conn = ConnectionManager.getConnection();
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM Tips WHERE Title LIKE '%" + searchPhrase + "%';");
            
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

    /** Add a tag to Tags table
     *
     * THE METHOD IS NOT SQL INJECTION SAFE! (AND NEITHER ARE OTHERS)
     *
     * If the tag doesn't already exist in the database, it will be added.
     *
     * @param tag The tag to be added should contain only letters and numbers
     */
    public void addTag(String tag) {
	try {
	    Connection conn = ConnectionManager.getConnection();	    
            Statement s = conn.createStatement();
            s.execute("INSERT INTO Tags (Tag) VALUES ('" + tag +"' );");

            s.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Get all tag strings from the database
     *
     * @return Tag strings
     */
    public ArrayList<String> listTags() {
        ResultSet rs = null;
        Statement s = null;
        ArrayList<String> tags = new ArrayList<>();

        try {
	    Connection conn = ConnectionManager.getConnection();	    
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM Tags;");

            while (rs.next()) {
                String tag = rs.getString("Tag");
                tags.add(tag);
            }

            rs.close();
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tags;
    }

    /** Get Tag ID
     *
     * @param tag
     * @return Integer ID of the tag
     */
    public Integer getTagID(String tag) {
	ResultSet rs = null;
        Statement s = null;
        ArrayList<String> tags = new ArrayList<>();
	Integer id = null;
	
        try {
	    Connection conn = ConnectionManager.getConnection();	    
            s = conn.createStatement();
            rs = s.executeQuery("SELECT id FROM Tags WHERE tag = '" + tag + "';");

	    rs.next();
	    id = Integer.parseInt(rs.getString("id"));

            rs.close();
            s.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;

    }


    /** Add tag for a Tip
     *
     *
     * @param Tip to which tag will be added
     * @param Tag string
     */
    public void connectTagToTip(Tip tip, String tag) {
	addTag(tag);
	Integer tag_id = getTagID(tag);

	try {
	    Connection conn = ConnectionManager.getConnection();	    
            Statement s = conn.createStatement();
	    
            s.execute("INSERT INTO Tagmap (tip_id, tag_id) VALUES ('" + tip.getId() + "', '" + tag_id + "');");

            s.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
	
    }

    /** Return all Tips which have given tag
     *
     * @param tag
     *
     * @return List of Tips
     */
    public ArrayList<Tip> searchByTag(String tag) {
        ResultSet rs = null;
        Statement s = null;
        ArrayList<Tip> tips = new ArrayList<>();
        
        try {
            Connection conn = ConnectionManager.getConnection();
            s = conn.createStatement();
            rs = s.executeQuery("SELECT * FROM Tips WHERE id IN (SELECT tip_id FROM Tagmap WHERE tag_id = (SELECT Tags.id FROM Tags WHERE Tag = '" + tag + "');");

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
    
    /** Create tables to database, if they don't exist
     */
    public void dataBaseCreate(Connection conn) {
        try {
            Statement s = conn.createStatement();
            s.execute("CREATE TABLE  IF NOT EXISTS  Tips (id INTEGER PRIMARY KEY, Title TEXT, Url TEXT);");
            s.execute("CREATE TABLE  IF NOT EXISTS  Tags (id INTEGER PRIMARY KEY, Tag TEXT NOT NULL UNIQUE ON CONFLICT IGNORE);");
	    s.execute("CREATE TABLE  IF NOT EXISTS  Tagmap (tip_id INTEGER NOT NULL, tag_id INTEGER NOT NULL);"); 	    	    

            s.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
