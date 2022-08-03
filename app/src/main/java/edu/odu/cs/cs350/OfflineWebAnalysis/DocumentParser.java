package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.io.File;
// import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Vector;
import java.io.IOException;
import org.jsoup.Jsoup;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

// TODOs
// Refactor code
// Maybe create smaller "extract" methods out of the current setters
// Fix Classifications (external, internal, intra-site, intra-page)
// Update documentation of to be in line with javadoc standards
// Unit tests

/**
 * Description...
 * 
 * 
 * @author James Wright
 * 
 */
public class DocumentParser {
    private Path localPath;
    private Vector<Anchor> anchors = new Vector<Anchor>(0);
    private Vector<Image> images = new Vector<Image>(0);
    private Vector<Script> scripts = new Vector<Script>(0);
    private Vector<Stylesheet> sytlesheets = new Vector<Stylesheet>(0);

    /*
     * Non-default constructor
     */
    public DocumentParser(Path _localPath) throws IOException, URISyntaxException {
        setLocalPath(_localPath);
        File file = _localPath.toFile();
        Document doc = Jsoup.parse(file);
        setAnchors(doc);
        setImages(doc);
        setScripts(doc);
        setStylesheets(doc);
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
    // * Retrieve image collection as type Vector<Image>
    // *
    // * @return current Anchor Vector
    // */
    public Vector<Anchor> getAnchors() {
        return this.anchors;
    }

    // /**
    // * Retrieve image collection as type Vector<Image>
    // *
    // * @return current Image Vector
    // */
    public Vector<Image> getImages() {
        return this.images;
    }

    // /**
    // * Retrieve script collection as type Vector<Script>
    // *
    // * @return current Script Vector
    // */
    public Vector<Script> getScripts() {
        return this.scripts;
    }

    // /**
    // * Retrieve script collection as type Vector<Script>
    // *
    // * @return current Script Vector
    // */
    public Vector<Stylesheet> getStylesheets() {
        return this.sytlesheets;
    }

    public void setAnchors(Document _doc) throws URISyntaxException {
        Elements links = _doc.select("a[href]");
        // makes sure anchor container is empty
        this.anchors.clear();
        for (Element src : links) {
            String urlString = ""; // initial raw src String
            URI uri;
            
            // TODO -- might be useful
            // Attributes att = src.attributes();
            // System.out.println(att.toString());

            urlString = src.toString();
            urlString = urlString.substring(urlString.indexOf("href=\"") + 6);
            urlString = urlString.substring(0, urlString.indexOf("\""));
            uri = new URI(urlString);

            // TODO -- Categorize as intra-page, intra-site, or external

            if (isExternal(urlString)) {
                // create external image object (with fileSize = 0)
                // Path pathing = Paths.get(urlString);
                this.anchors.addElement(new Anchor(uri, Classification.EXTERNAL));
            }

            if (isInternal(urlString)) {
                this.anchors.addElement(new Anchor(uri, Classification.INTERNAL));
            }
        }
    }

    /**
     * Updates image collection by extracting image data from provided HTML document
     * 
     * @param doc A JSOUP document object
     */
    public void setImages(Document _doc) {
        Elements media = _doc.select("[src]");
        // makes sure image container is empty
        this.images.clear();
        for (Element src : media) {
            if (isImageData(src))
            {
                String srcString = ""; // initial raw src String
                String baseURIString = ""; // base URI String
                String path = ""; // image file path
                String absoluteFilePath = "";
                long bytes = 0;

                // TODO -- might be useful
                // Attributes att = src.attributes();
                // System.out.println(att.toString());

                // Can probably be refactored -- TODO
                srcString = src.toString();
                baseURIString = src.baseUri();
                path = srcString.substring(srcString.indexOf("src=\"") + 5);
                path = path.substring(0, path.indexOf("\""));

                if (isExternal(path)) {
                    // create external image object (with fileSize = 0)
                    Path pathing = Paths.get(baseURIString);
                    URI uri = pathing.toUri();
                    this.images.addElement(new Image(uri, Classification.EXTERNAL, bytes));
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
                    this.images.addElement(new Image(uri, Classification.INTERNAL, bytes));
                }
            }
        }
    }

    /**
     * Updates script collection by extracting script data from provided HTML document
     * 
     * @param doc A JSOUP document object
     */
    public void setScripts(Document _doc) {
        Elements media = _doc.select("[src]");
        // makes sure image container is empty
        this.scripts.clear();
        for (Element src : media) {
            if (isScriptData(src)) {
                String srcString = ""; // initial raw src String
                String baseURIString = ""; // base URI String
                String path = ""; // image file path
                String absoluteFilePath = "";

                // TODO -- might be useful
                // Attributes att = src.attributes();
                // System.out.println(att.toString());

                // Can probably be refactored -- TODO
                srcString = src.toString();
                baseURIString = src.baseUri();
                path = srcString.substring(srcString.indexOf("src=\"") + 5);
                path = path.substring(0, path.indexOf("\""));

                if (isExternal(path)) {
                    // create external image object (with fileSize = 0)
                    Path pathing = Paths.get(baseURIString);
                    URI uri = pathing.toUri();
                    this.scripts.addElement(new Script(uri, Classification.EXTERNAL));
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
                    this.scripts.addElement(new Script(uri, Classification.INTERNAL));
                }
            }
        }
    }

    /**
     * Updates script collection by extracting script data from provided HTML
     * document
     * 
     * @param doc A JSOUP document object
     */
    public void setStylesheets(Document _doc) {
        Elements media = _doc.select("link[href]");
    
        // makes sure image container is empty
        this.scripts.clear();
        for (Element src : media) {

            String stringKey = src.toString();
            // System.out.println(stringKey.contains("rel=\"stylesheet\""));
            // System.out.println(src.normalName());
            // System.out.println(src.attributes().toString());
            // System.out.println(src.wholeText());

            if (isStylesheetData(stringKey)) {
                String srcString = ""; // initial raw src String
                String baseURIString = ""; // base URI String
                String path = ""; // image file path
                String absoluteFilePath = "";

                // TODO -- might be useful
                // Attributes att = src.attributes();
                // System.out.println(att.toString());

                System.out.println(src.baseUri());

                // Can probably be refactored -- TODO
                srcString = src.toString();
                baseURIString = src.baseUri();
                path = srcString.substring(srcString.indexOf("rel=\"") + 5);
                path = path.substring(0, path.indexOf("\""));

                if (isExternal(path)) {
                    // create external image object (with fileSize = 0)
                    Path pathing = Paths.get(baseURIString);
                    URI uri = pathing.toUri();
                    this.scripts.addElement(new Script(uri, Classification.EXTERNAL));
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
                    this.scripts.addElement(new Script(uri, Classification.INTERNAL));
                }
            }
        }
    }

    public HTMLDocument build() {
        return new HTMLDocument(this);
    }

    // -------------------------------------------------------------------------------
    // The below methods are private and only used inside the setImageVector method
    // -------------------------------------------------------------------------------

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
     * Checks if provided Element contains script data
     * 
     * @param src
     * @return True if specified Element contains image data
     */
    private boolean isScriptData(Element src) {
        return src.normalName().equals("script");
    }

    /**
     * Checks if provided Element contains stylesheet data
     * 
     * @param src
     * @return True if specified Element contains image data
     */
    private boolean isStylesheetData(String key) {
        return key.contains("rel=\"stylesheet\"");
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