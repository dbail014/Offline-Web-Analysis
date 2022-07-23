package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.io.File;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Vector;
import org.jsoup.helper.Validate;
import java.io.IOException;
import org.jsoup.Jsoup;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

// TODO -- Refactor / Abstract some of the messier methods

public class DocumentParser {

    // Driver function for testing
    public static void main(String[] args) throws IOException, URISyntaxException {
        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        // String path = args[0];
        String pat = System.getProperty("user.dir");
        String fileInProject = "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/test2.html";
        pat += fileInProject;
        pat = pat.replaceAll("\\\\", "/");
        
        Path pathing = Paths.get(pat);
        File input = pathing.toFile();
        // System.out.println(pathing.toString());
        URI uri = pathing.toUri();
        // System.out.println(uri.toString());

        Document doc = Jsoup.parse(input);
        DocumentParser p = new DocumentParser(doc, pathing);
        Vector<Image> lImages = new Vector<Image>(p.getImageVector());
        System.out.println(lImages.toString());
    }

    private Path localPath;
    private Vector<Image> images = new Vector<Image>(0);

    /*
     * Default Constructor can not be created
     */
    private DocumentParser() { }
    
    /*
     * Non-default constructor
     */
    public DocumentParser(Document doc, Path _localPath) {
        setLocalPath(_localPath);
        // setPath(_localPath);
        setImageVector(doc);
    }

    // /**
    // * Getter for localPath
    // *
    // * @return local path
    // */
    public Path getPath() {
        return this.localPath;
    }

    /**
     * Setter for localPath
     * 
     * @param _localPath
     */
    public void setLocalPath(Path _localPath) {
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
            if (imageProcessor(src) != null)
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
        if (isImageData(src)) {
            String srcString = ""; // initial raw src String
            String baseURIString = ""; // base URI String
            String path = ""; // image file path
            String absoluteFilePath = "";
            long bytes = 0;

            // Can probably be refactored -- TODO
            srcString = src.toString();
            baseURIString = src.baseUri();
            path = srcString.substring(srcString.indexOf("src=\"") + 5);
            path = path.substring(0, path.indexOf("\""));

            if (isExternal(path)) {
                // create external image object (with fileSize = 0)
                Path pathing = Paths.get(baseURIString);
                URI uri = pathing.toUri();
                return new Image(uri, Classification.EXTERNAL, bytes);
            }

            // Get the URI from string.
            // Will refactor/abstract in next sprint.
            // Took way too long to figure this out and time
            // is limited.
            baseURIString = baseURIString.replaceAll("\\\\", "/");
            absoluteFilePath = baseURIString.substring(0, (baseURIString.lastIndexOf("/") + 1));
            absoluteFilePath += path.substring(path.indexOf("/") + 1);
            Path pathing = Paths.get(absoluteFilePath);
            URI uri = pathing.toUri();
            
            if (isInternal(path)) {
                bytes = getBytes(absoluteFilePath);
                return new Image(uri, Classification.INTERNAL, bytes);
            }
            // defined above with placeholder value
            // add image object to collection<image> images from HTMLDocuments.java
        }
        return null;
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

    // -------------------------------------------------------------------------------
    // End of private methods contained inside setImageVector method
    // -------------------------------------------------------------------------------
}
