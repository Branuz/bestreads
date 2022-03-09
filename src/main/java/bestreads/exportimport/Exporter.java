package bestreads.exportimport;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import bestreads.readingtip.Tip;

public class Exporter {

    private JSONArray exportArray;

    public Exporter() {
        exportArray = new JSONArray();;
    }

    public void createNewJsonFile(ArrayList<Tip> tips) {

        try { 
            FileWriter file = new FileWriter("exportFile.json");
            JSONObject jsonObject;

            for (Tip tip : tips) {
                jsonObject = new JSONObject();
                jsonObject.put("id", tip.getId());
                jsonObject.put("title", tip.getTitle());
                jsonObject.put("url", tip.getUrl());
                jsonObject.put("tags", tip.getTags());

                exportArray.add(jsonObject);
            }
            
            file.write(exportArray.toJSONString());
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
