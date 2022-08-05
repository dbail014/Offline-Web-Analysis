package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.util.Vector;

import java.net.URL;
import java.nio.file.Path;

// import java.util.Arrays;

// import org.jsoup.helper.Validate;
// import java.io.IOException;
// import java.net.URISyntaxException;
// import java.nio.file.Paths;

/**
 * The HTMLDocument class stores all the contents of a single parsed and analyzed webpage. HTMLDocument contains the base directory path, the local file path, and URLs passed to it. It also contains vectors of anchors, images, scripts, stylesheets, and other resources contained on the page. HTMLDocument is built by the DocumentParser class. 
 * 
 * 
 * @author James Wright
 * 
 */
public class HTMLDocument {

    
    // /** 
    //  * @param args
    //  * @throws IOException
    //  * @throws URISyntaxException
    //  */
    // // This method is for functionality testing only.
    // public static void main(String[] args) throws IOException, URISyntaxException {
    //     Validate.isTrue(args.length > 1, "usage: supply url to fetch");
    //     Vector<URL> _urls = new Vector<URL>(0);
    //     String stringPath = args[0];
    //     String[] urlStrings = Arrays.copyOfRange(args, 1, args.length);
    //     Path local_path = Paths.get(stringPath);
    //     for (String urlString : urlStrings) {
    //         _urls.addElement(new URL(urlString));
    //     }

    //     HTMLDocument htmlDoc = new DocumentParser(local_path.getParent(), local_path, _urls).build();
    //     Vector<Anchor> anchor = new Vector<Anchor>(htmlDoc.getAnchors());
    //     Vector<Image> image = new Vector<Image>(htmlDoc.getImages());
    //     Vector<Script> script = new Vector<Script>(htmlDoc.getScripts());
    //     Vector<Stylesheet> stylesheet = new Vector<Stylesheet>(htmlDoc.getStylesheets());
    //     Vector<OtherResource> otherResource = new Vector<OtherResource>(htmlDoc.getOtherResources());
    //     System.out.println("Anchor Vector:");
    //     System.out.println(anchor.toString());
    //     System.out.println("Image Vector:");
    //     System.out.println(image.toString());
    //     System.out.println("Script Vector:");
    //     System.out.println(script.toString());
    //     System.out.println("Stylesheet Vector:");
    //     System.out.println(stylesheet.toString());
    //     System.out.println("Other Vector:");
    //     System.out.println(otherResource.toString());
    // }

    private final Path basePath;
    private final Vector<URL> urls;
    private final Path localPath;
    private final Vector<Anchor> anchors;
    private final Vector<Image> images;
    private final Vector<Script> scripts;
    private final Vector<Stylesheet> stylesheets;
    private final Vector<OtherResource> otherResources;

    /*
     * Builder pattern style constructor
     */
    public HTMLDocument (DocumentParser parser) {
        this.basePath = parser.getBasePath();
        this.urls = parser.getUrls();
        this.localPath = parser.getLocalPath();
        this.anchors = parser.getAnchors();
        this.images = parser.getImages();
        this.scripts = parser.getScripts();
        this.stylesheets = parser.getStylesheets();
        this.otherResources = parser.getOtherResources();
    }

    
    /**
     * Getter for the base path
     *
     * @return basePath
     */
    public Path getBasePath() {
        return basePath;
    }

    
    /**
     * Getter for vector of site URLs
     *
     * @return Vector<URL>
     */
    public Vector<URL> getUrls() {
        return urls;
    }

    
    /**
     * Getter for the local path
     *
     * @return localPath
     */
    public Path getLocalPath() {
        return localPath;
    }

    
    /**
     * Retrieve anchor collection as type Vector<Anchor>
     *
     * @return Vector<Anchor>
     */
    public Vector<Anchor> getAnchors() {
        return anchors;
    }

    
    /**
     * Retrieve image collection as type Vector<Image>
     *
     * @return Vector<Image>
     */
    public Vector<Image> getImages() {
        return images;
    }

    
    /**
     * Retrieve script collection as type Vector<Script>
     *
     * @return Vector<Script>
     */
    public Vector<Script> getScripts() {
        return scripts;
    }

    
    /**
     * Retrieve stylesheet collection as type Vector<Stylesheet>
     *
     * @return Vector<Stylesheet>
     */
    public Vector<Stylesheet> getStylesheets() {
        return stylesheets;
    }

    
    /**
     * Retrieve collection of resources not already in a category as type
     * Vector<OtherResource>
     *
     * @return Vector<OtherResource>
     */
    public Vector<OtherResource> getOtherResources() {
        return otherResources;
    }
}
