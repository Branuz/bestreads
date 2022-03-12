package bestreads.exportimport;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import bestreads.databasehandlers.DatabaseManager;
import bestreads.readingtip.Tip;



public class Importer {

    /**
     * Import tips to the datbase from a jsonfile
     *
     * @param DatabaseManager 
     * @param String filename
     *
     * @throws Exception
     */

    public void importFile(DatabaseManager manager, String filename) {

        try {
            Reader reader = Files.newBufferedReader(Paths.get(filename));
            List<Tip> tips = new Gson().fromJson(reader, new TypeToken<List<Tip>>() { }.getType());
            reader.close();

            for (Tip tip : tips) {
                manager.inserIntoDatabase(tip);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}