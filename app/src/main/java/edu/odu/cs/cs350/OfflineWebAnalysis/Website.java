package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;

import org.jsoup.helper.Validate;

// TODOs
// Refactor code
// Unit tests

/**
 * The Website class acts as a collection of all the HTMLDocuments in a given directory. This class contains the file path of a given directory, a vector of given URLs, and a vector of HTMLDocuments within a directory. Website calls WebsiteWalker gather collection of HTMLDocuments.
 * 
 * @author James Wright
 */
public class Website {

    
    /** 
     * @param args
     * @throws IOException
     * @throws URISyntaxException
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        Validate.isTrue(args.length >= 2, "usage: supply url to fetch");
        String stringPath = args[0];
        
        String[] urlStrings = Arrays.copyOfRange(args, 1, args.length);
        // System.out.println(urlStrings[1]);

        Path path = Paths.get(stringPath);
        Website site = new Website();
        site.setBasePath(path);
        site.setUrls(urlStrings);
        System.out.println(site.getUrls().toString());

        site.setAllPages();
        Vector<HTMLDocument> allDocs = site.getAllPages();
        int count = 1;
        for (HTMLDocument htmlDoc : allDocs) {
            System.out.println(count + ": " + htmlDoc.getLocalPath().toString());
            Vector<Anchor> anchor = new Vector<Anchor>(htmlDoc.getAnchors());
            // Vector<Image> image = new Vector<Image>(htmlDoc.getImages());
            // Vector<Script> script = new Vector<Script>(htmlDoc.getScripts());
            // Vector<Stylesheet> stylesheet = new Vector<Stylesheet>(htmlDoc.getStylesheets());
            System.out.println("Anchor Vector " + count + ":");
            System.out.println(anchor.toString());
            // System.out.println("Image Vector " + count + ":");
            // System.out.println(image.toString());
            // System.out.println("Script Vector " + count + ":");
            // System.out.println(script.toString());
            // System.out.println("Stylesheet Vector " + count + ":");
            // System.out.println(stylesheet.toString());
            count++;
        }
    }

    private Path basePath;
    private Vector<HTMLDocument> allPages;
    private Vector<URL> urls; 

    public Website() {
        allPages = new Vector<HTMLDocument>(0);
        urls = new Vector<URL>(0);
    }

    /**
     * Getter for the base path
     *
     * @return basePath
     */
    public Path getBasePath() {
        return this.basePath;
    }
    
    /**
     * Setter for the base path
     * 
     * @param _basePath file base path
     */
    public void setBasePath(Path _basePath) {
        this.basePath = _basePath;
    }
    
    /**
     * Getter for vector of HTMLDocuments on a site
     *
     * @return Vector<HTMLDocument>
     */
    public Vector<HTMLDocument> getAllPages() {
        return this.allPages;
    }
    
    /** 
     * Setter for Vector<HTMLDocument> allPages. This method utilizes WebsiteWalker class to step through all files within the given directory
     * @throws IOException
     * @throws URISyntaxException
     */
    public void setAllPages() throws IOException, URISyntaxException {
        WebsiteWalker site = new WebsiteWalker();
        site.setPaths(this.basePath);
        Vector<Path> paths = site.getPaths();
        for (Path p : paths) {
            HTMLDocument currPage = new DocumentParser(this.basePath, p, this.urls).build();
            this.allPages.addElement(currPage);
        }
    }
    
    /**
     * Getter for vector of site URLs
     *
     * @return Vector<URL>
     */
    public Vector<URL> getUrls() {
        return this.urls;
    }
    
    /**
     * Setter for vector of site URLs
     * 
     * @param _urls a vector of URLs
     * @throws MalformedURLException
     */
    public void setUrls(String[] urlStrings) throws MalformedURLException {
        for (String element : urlStrings) {
            URL url = new URL(element);
            urls.addElement(url);
        }
    }
}
