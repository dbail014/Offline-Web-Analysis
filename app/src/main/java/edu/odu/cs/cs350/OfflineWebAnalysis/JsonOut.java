package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;
import java.util.stream.Stream;

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
        JsonObj.put("path", " ");

        JsonObj.put("files", " ");        
        JsonObj.put("archive", " ");
        JsonObj.put("path", " ");
        JsonObj.put("video", " ");
        JsonObj.put("path", " ");
        JsonObj.put("audio", " ");
        JsonObj.put("path", " ");
        JsonObj.put("other", " ");
        JsonObj.put("path", " ");

    }

    /* TODO - implement data member variables
     * Non-Default Constructor
     */
    public JsonOut(JsonObject _JSONObject) {
        JsonObj.put("basePath", "");
        JsonObj.put("urls", " ");

        JsonObj.put("pages", "");
        JsonObj.put("path", " ");
        JsonObj.put("imageCount", "");
        JsonObj.put("jsCount"," ");
        JsonObj.put("cssCount"," ");
        JsonObj.put("imagePaths"," ");
        JsonObj.put("scriptPaths", " ");
        JsonObj.put("cssPaths", " ");
        JsonObj.put("linkCount", " ");

        JsonObj.put("images", "");
        JsonObj.put("path", " ");

        JsonObj.put("files", "");        
        JsonObj.put("archive", "");
        JsonObj.put("path", " ");
        JsonObj.put("video", "");
        JsonObj.put("path", " ");
        JsonObj.put("audio", "");
        JsonObj.put("path", " ");
        JsonObj.put("other", "");
        JsonObj.put("path", " ");
    }


    /** TODO
     * Retrieve JSON Data
     * 
     * @return current JSON data
     */
    public JsonObject getJSONObject() {
        
        
        return JsonObj;
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
    public void writeJSON() throws IOException {
        
        String fileName = nameFile();

        FileOutputStream out = new FileOutputStream(
            new File(fileName +".json"));
   

        JsonWriter writer = new JsonWriter(out);

        writer.write(JsonObj);
        writer.close();
    }






}
