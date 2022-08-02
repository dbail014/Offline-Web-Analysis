package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.util.Vector;
import org.jsoup.helper.Validate;
import java.io.IOException;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

// TODOs
// Update documentation of to be in line with javadoc standards
// Unit tests

/**
 * Utilizes builder pattern to create 
 * 
 * 
 * @author James Wright
 * 
 */
public class HTMLDocument {

    // This method is for functionality testing only.
    public static void main(String[] args) throws IOException, URISyntaxException {
        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String stringPath = args[0];
        Path path = Paths.get(stringPath);
        HTMLDocument htmlDoc = new DocumentParser(path).build();
        Vector<Anchor> anchor = new Vector<Anchor>(htmlDoc.getAnchors());
        Vector<Image> image = new Vector<Image>(htmlDoc.getImages());
        Vector<Script> script = new Vector<Script>(htmlDoc.getScripts());
        System.out.println("Anchor Vector:");
        System.out.println(anchor.toString());
        System.out.println("Image Vector:");
        System.out.println(image.toString());
        System.out.println("Script Vector:");
        System.out.println(script.toString());
    }

    private final Path localPath;
    private final Vector<Anchor> anchors;
    private final Vector<Image> images;
    private final Vector<Script> scripts;
    private final Vector<Stylesheet> stylesheets;

    public HTMLDocument (DocumentParser parser) {
        this.localPath = parser.getPath();
        this.anchors = parser.getAnchors();
        this.images = parser.getImages();
        this.scripts = parser.getScripts();
        this.stylesheets = parser.getStylesheets();
    }

    public Path getLocalPath() {
        return localPath;
    }

    public Vector<Anchor> getAnchors() {
        return anchors;
    }

    public Vector<Image> getImages() {
        return images;
    }

    public Vector<Script> getScripts() {
        return scripts;
    }

    public Vector<Stylesheet> getStylesheets() {
        return stylesheets;
    }
}
