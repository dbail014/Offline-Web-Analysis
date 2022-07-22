package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.io.File;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Vector;
import org.jsoup.helper.Validate;
import java.io.IOException;
import org.jsoup.Jsoup;

// TODO -- Add OOP convention methods.
// TODO -- Add full documentation.
// TODO -- Write unit tests; Ask about unit tests for abstracted functionality.

public class DocumentParser {

    // This method is for functionality testing only.
    // Currently only runs on Windows system.
    // In powershell type:
    // ./gradlew run --args='_Your filePath_'(foward slashes are doubled)
        // I have been testing by downloading the html for the Agile Methods lecture notes.
        // I would use downloaded html lecture notes until/unless Professor comes out with some testing files for us.
    public static void main(String[] args) throws IOException {
        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String path = args[0];
        File input = new File(path);
        Document doc = Jsoup.parse(input);
        DocumentParser p = new DocumentParser(doc, path);
        Vector<Image> lImages = new Vector<Image>(p.getImageVector());
        System.out.println(lImages.toString());
    }

    // Possibly make path type URL
    private String localPath;
    private Vector<Image> images = new Vector<Image>(0);

    /*
     * Default Constructor can not be created
     */
    private DocumentParser() { }
    
    /*
     * Non-default constructor
     */
    public DocumentParser(Document doc, String lPath) {
        setLocalPath(lPath);
        this.setImageVector(doc);
    }

    
    // /**
    //  * Getter for localPath
    //  * 
    //  * @return local path
    //  */
    public String getLocalPath() {
        return this.localPath;
    }

    /** 
     * Setter for localPath
     * 
     * @param _localPath
     */
    public void setLocalPath(String _localPath) {
        this.localPath = _localPath;
    }
    
    // /**
    //  * Retrieve image collection as type Vector<Image>
    //  * 
    //  * @return current Image Vector
    //  */
    public Vector<Image> getImageVector() {
        return this.images;
    }

    /** 
     * Updates image collection by extracting image data from provided HTML document
     * 
     * @param doc A JSOUP document object
     */
    public DocumentParser setImageVector(Document _doc) {
        Elements media = _doc.select("[src]");
        // makes sure vector is empty
        this.images.clear();
        for (Element src : media) {
            this.images.addElement(imageProcessor(src));
        }
        return this;
    }

    // -------------------------------------------------------------------------------
    // The below methods are private and only used inside the setImageVector method
    // -------------------------------------------------------------------------------
    
    /** 
     * Extract image data from provided JSOUP Element src
     * 
     * @param src JSOUP Element to be processed
     * @return _image The image data as an Image object
     */
    private Image imageProcessor(Element src) {
        Image _image = new Image();
        if (isImageData(src)) {
            String srcString = ""; // initial raw src String
            String baseURIString = ""; // base URI String
            URI uri; 
            String path = ""; // image file path
            String absoluteFilePath = "";
            long bytes = 0;

            // Can probably be refactored -- TODO
            srcString = src.toString();
            baseURIString = src.baseUri();
            path = srcString.substring(srcString.indexOf("src=\"") + 5);
            path = path.substring(0, path.indexOf("\""));

            // Absolute File Path for Windows
            absoluteFilePath = createAbsoluteFilePathWindows(baseURIString, path);

            // check for external with if statement here
            if (isExternal(path)) {
                // create external image object (with fileSize = 0)
                _image = new Image(baseURIString, Classification.EXTERNAL, bytes);
            }
            if (isInternal(path)) {
                bytes = getBytes(absoluteFilePath);
                _image = new Image(baseURIString, Classification.INTERNAL, bytes);
            }
            // defined above with placeholder value
            // add image object to collection<image> images from HTMLDocuments.java
            return _image;
        }
        return _image;
    }
    
    /** 
     * Checks if provided Element contains image data
     * 
     * @param src
     * @return True if specified Element contains image data
     */
    private boolean isImageData(Element src) {
        return src.normalName().equals("img");
    }
    
    /** 
     * Checks if provided String links to an external image
     * 
     * @param path
     * @return True if specified path links an external image
     */
    // TODO -- Fix based off professor input
    static boolean isExternal(String path) {
        return (path.contains("https://") || path.contains("http://"));
    }
    
    /**
     * Checks if provided String links to an internal image
     * 
     * @param path
     * @return True if specified path links internal image
     */
    // TODO -- Fix based off professor input
    private boolean isInternal(String path) {
        return (!path.contains("https://") && !path.contains("http://"));
    }
    
    /** 
     * Returns size of the file linked from file path
     * Size is returned as a long NOT an int... 0 != 0L
     * 
     * @param absoluteFilePath
     * @return file size
     */
    // return long for bytes
    // Documentation -- TODO
    private long getBytes(String absoluteFilePath) {
        File file = new File(absoluteFilePath);
        if (file.exists()) {
            // size of a file (in bytes)
            return file.length();
        }
        return 0;
    }
    
    /** 
     * @param baseURIString
     * @param path
     * @return String
     */
    // TODO -- Re-tool based off Professor input.
    // returns absoluteFilePath String
    // Documentation -- TODO
    private String createAbsoluteFilePathWindows(String baseURIString, String path) {
        String absoluteFilePath = "";
        absoluteFilePath = baseURIString.substring(0, (baseURIString.lastIndexOf("\\") + 1));
        absoluteFilePath += path.substring(path.indexOf("/") + 1);
        absoluteFilePath = absoluteFilePath.replaceAll("/", "\\\\");
        absoluteFilePath = absoluteFilePath.replaceAll("\\\\", "\\\\\\\\");
        return absoluteFilePath;
    }

    // -------------------------------------------------------------------------------
    // End of private methods contained inside setImageVector method
    // -------------------------------------------------------------------------------
}
