package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.io.FileWriter;
import java.io.IOException;

// not importing for some reason?
import com.cedarsoftware.util.io.JsonObject;
import com.cedarsoftware.util.io.JsonWriter;
import com.cedarsoftware.util.io.JsonReader; 


// TODOs
// Data members
// Defeault Constructor
// Copy Constructor??
// Non-default Constructor
// Read in data to JSON object
//      Getters
//      Setters
// Write data to JSON file
// Unit Tests

/**
 * Description...
 * 
 * 
 * @author Brian Clark
 * 
 */
public class JsonOut extends ReportWriter {
    
    // Data Members
    private JsonObject JsonObj = new JsonObject();
    public static final String PRETTY_PRINT;

    /* TODO
     * Default Constructor 
     */
    public JsonOut() {
        JsonObj.put("basePath", " ");
        JsonObj.put("urls", " ");

        JsonObj.put("pages", " ");
        JsonObj.put("path", " ");
        JsonObj.put("imageCount", " ");
        JsonObj.put("jsCount"," ");
        JsonObj.put("cssCount"," ");
        JsonObj.put("imagePaths"," ");
        JsonObj.put("scriptPaths", " ");
        JsonObj.put("cssPaths", " ");
        JsonObj.put("linkCount", " ");

        JsonObj.put("images", "");
        JsonObj.put("path", " ", "pageCount", " ", "usedOn", " ");

        JsonObj.put("files", " ");        
        JsonObj.put("archive", " ");
        JsonObj.put("path", " ", "size", " ");
        JsonObj.put("video", " ");
        JsonObj.put("path", " ", "size", " ");
        JsonObj.put("audio", " ");
        JsonObj.put("path", " ", "size", " ");
        JsonObj.put("other", " ");
        JsonObj.put("path", " ", "size", " ");

    }
    
    /* TODO - implement data member variables
     * Non-Default Constructor
     */
    public JsonOut(JsonObject _JSONObject) {
        JsonObj.put("basePath", );
        JsonObj.put("urls", " ");

        JsonObj.put("pages", "");
        JsonObj.put("path", " ");
        JsonObj.put("imageCount", " ");
        JsonObj.put("jsCount"," ");
        JsonObj.put("cssCount"," ");
        JsonObj.put("imagePaths"," ");
        JsonObj.put("scriptPaths", " ");
        JsonObj.put("cssPaths", " ");
        JsonObj.put("linkCount", " ");

        JsonObj.put("images", "");
        JsonObj.put("path", " ", "pageCount", " ", "usedOn", " ");

        JsonObj.put("files", "");        
        JsonObj.put("archive", "");
        JsonObj.put("path", " ", "size", " ");
        JsonObj.put("video", "");
        JsonObj.put("path", " ", "size", " ");
        JsonObj.put("audio", "");
        JsonObj.put("path", " ", "size", " ");
        JsonObj.put("other", "");
        JsonObj.put("path", " ", "size", " ");
    }


    /** TODO
     * Retrieve JSON Data
     * 
     * @return current JSON data
     */
    public JsonObject getJSONObject() {

    }

    
    /** TODO
     * Update JSON Object
     * 
     * @param _JSONObject replacement JsonObj
     */
    public void setJSONObject(JsonObject _JSONObject) {

    }

    /*
     * Write Json file
     * 
     */
    public void writeJSON() {
        
        try{

            JsonWriter writer = new JsonWriter(nameFile() + ".json");

            writer.write(JsonObj);
            writer.close();

        } catch(IOException e) {
            e.printStackTrace();
        }

    }





}
