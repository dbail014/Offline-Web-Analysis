package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.io.File;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Vector;
import java.io.IOException;
import org.jsoup.Jsoup;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The DocumentParser class is the main driver for parsing through the components of given HTML resources, as well as the builder for the HTMLDocument class. DocumentParser makes extensive use of the JSOUP library.
 * 
 * @author James Wright
 * 
 */
public class DocumentParser {
    private Path basePath;
    private Vector<URL> urls = new Vector<URL>(0);
    private Path localPath;
    private Vector<Anchor> anchors = new Vector<Anchor>(0);
    private Vector<Image> images = new Vector<Image>(0);
    private Vector<Script> scripts = new Vector<Script>(0);
    private Vector<Stylesheet> stylesheets = new Vector<Stylesheet>(0);
    private Vector<OtherResource> otherResources = new Vector<OtherResource>(0);

    /*
     * Non-default constructor -- there is no default constructor in the builder pattern
     */
    public DocumentParser(Path _basePath, Path _localPath, Vector<URL> _urls) throws IOException, URISyntaxException {
        setBasePath(_basePath);
        setLocalPath(_localPath);
        setUrls(_urls);
        File file = this.localPath.toFile();
        Document doc = Jsoup.parse(file);
        setAnchors(doc);
        setImages(doc);
        setScripts(doc);
        setStylesheets(doc);
        setOtherResources(doc);
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
    * Getter for the local path
    *
    * @return localPath
    */
    public Path getLocalPath() {
        return this.localPath;
    }

    /**
     * Setter for the local path
     * 
     * @param _localPath file local path
     */
    public void setLocalPath(Path _localPath) {
        this.localPath = _localPath;
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
     */
    public void setUrls(Vector<URL> _urls) {
        this.urls = _urls;
    }

    /**
    * Retrieve anchor collection as type Vector<Anchor>
    *
    * @return Vector<Anchor>
    */
    public Vector<Anchor> getAnchors() {
        return this.anchors;
    }

    /**
    * Retrieve image collection as type Vector<Image>
    *
    * @return Vector<Image>
    */
    public Vector<Image> getImages() {
        return this.images;
    }

    /**
    * Retrieve script collection as type Vector<Script>
    *
    * @return Vector<Script>
    */
    public Vector<Script> getScripts() {
        return this.scripts;
    }

    /**
    * Retrieve stylesheet collection as type Vector<Stylesheet>
    *
    * @return Vector<Stylesheet>
    */
    public Vector<Stylesheet> getStylesheets() {
        return this.stylesheets;
    }

    /**
     * Retrieve collection of resources not already in a category as type Vector<OtherResource>
     *
     * @return Vector<OtherResource>
     */
    public Vector<OtherResource> getOtherResources() {
        return this.otherResources;
    }

    /**
     * Setter for Vector<Anchor> anchors. This method parses through a given Document parameter (_doc) using the JSOUP library to extract all elements that match a pattern corresponding to a link/anchor resource. Once the raw data is extracted, each element is converted into a string. These strings are parsed and categorized by URI and classification then added to anchors as individual Anchor objects.
     * 
     * @param _doc
     * @throws URISyntaxException
     */
    public void setAnchors(Document _doc) throws URISyntaxException, MalformedURLException {
        Elements links = _doc.select("a[href]");
        this.anchors.clear();
        for (Element src : links) {
            String urlString = "";
            URI uri;
            urlString = src.toString();
            urlString = urlString.substring(urlString.indexOf("href=\"") + 6);
            urlString = urlString.substring(0, urlString.indexOf("\""));
            uri = new URI(urlString);

            if (isExternal(this.basePath.toString(), this.urls, urlString)) {
                this.anchors.addElement(new Anchor(uri, Classification.EXTERNAL));
            }

            if (isInternal(this.basePath.toString(), this.urls, urlString)) {
                if (isIntraPage(this.localPath.toString(), this.urls, urlString)) {
                    this.anchors.addElement(new Anchor(uri, Classification.INTRAPAGE));
                }
                else {
                    this.anchors.addElement(new Anchor(uri, Classification.INTERNAL));
                }
            }

            // TODO -- INTRAPAGE classification...
        }
    }

    /**
     * Setter for Vector<Image> images. This method parses through a given Document parameter (_doc) using the JSOUP library to extract all element that match a pattern corresponding to an image resource. Once the raw data is extracted, each element is converted into a string. These strings are parsed and categorized by URI, classification, and file size then added to images as individual Image objects.
     * 
     * @param _doc
     * @throws URISyntaxException
     */
    public void setImages(Document _doc) throws URISyntaxException {
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
                URI uri;
                Path pathing = this.localPath;

                srcString = src.toString();
                baseURIString = src.baseUri();
                path = srcString.substring(srcString.indexOf("src=\"") + 5);
                path = path.substring(0, path.indexOf("\""));

                if (path.startsWith("https") || path.startsWith("http")) {
                    uri = new URI(path);
                }
                else {
                    baseURIString = baseURIString.replaceAll("\\\\", "/");
                    absoluteFilePath = baseURIString.substring(0, (baseURIString.lastIndexOf("/") + 1));
                    absoluteFilePath += path.substring(path.indexOf("/") + 1);
                    pathing = Paths.get(absoluteFilePath);
                    uri = pathing.toUri();
                    path = pathing.toString();
                }

                if (isExternal(this.basePath.toString(), this.urls, path)) {
                    this.images.addElement(new Image(uri, Classification.EXTERNAL, bytes));
                }

                if (isInternal(this.basePath.toString(), this.urls, path)) {
                    bytes = getBytes(absoluteFilePath);
                    // uri = pathing.toUri();
                    this.images.addElement(new Image(uri, Classification.INTERNAL, bytes));
                }
            }
        }
    }

    /**
     * Setter for Vector<Script> scripts. This method parses through a given Document parameter (_doc) using the JSOUP library to extract all elements that match a pattern corresponding to a JavaScript resource. Once the raw data is extracted, each element is converted into a string. These strings are parsed and categorized by URI and classification then added to scripts as individual Script objects.
     * 
     * @param _doc
     * @throws URISyntaxException
     */
    public void setScripts(Document _doc) throws URISyntaxException {
        Elements media = _doc.select("[src]");
        // makes sure image container is empty
        this.scripts.clear();
        for (Element src : media) {
            if (isScriptData(src)) {
                String srcString = ""; // initial raw src String
                String baseURIString = ""; // base URI String
                String path = ""; // image file path
                String absoluteFilePath = "";
                URI uri;
                Path pathing = this.localPath;

                srcString = src.toString();
                baseURIString = src.baseUri();
                path = srcString.substring(srcString.indexOf("src=\"") + 5);
                path = path.substring(0, path.indexOf("\""));

                if (path.startsWith("https") || path.startsWith("http")) {
                    uri = new URI(path);
                } else {
                    baseURIString = baseURIString.replaceAll("\\\\", "/");
                    absoluteFilePath = baseURIString.substring(0, (baseURIString.lastIndexOf("/") + 1));
                    absoluteFilePath += path.substring(path.indexOf("/") + 1);
                    pathing = Paths.get(absoluteFilePath);
                    uri = pathing.toUri();
                    path = pathing.toString();
                }

                if (isExternal(this.basePath.toString(), this.urls, path)) {
                    this.scripts.addElement(new Script(uri, Classification.EXTERNAL));
                }

                if (isInternal(this.basePath.toString(), this.urls, path)) {
                    // uri = pathing.toUri();
                    this.scripts.addElement(new Script(uri, Classification.INTERNAL));
                }
            }
        }
    }

    /**
     * Setter for Vector<Stylesheet> stylesheets. This method parses through a given Document parameter (_doc) using the JSOUP library to extract all elements that match a pattern corresponding to a CSS resource. Once the raw data is extracted, each element is converted into a string. These strings are parsed and categorized by URI and classification then added to stylesheets as individual Stylesheet objects.
     * 
     * @param _doc
     * @throws URISyntaxException
     */
    public void setStylesheets(Document _doc) throws URISyntaxException {
        Elements media = _doc.select("link[href]");
    
        // makes sure image container is empty
        this.stylesheets.clear();
        for (Element src : media) {

            String stringKey = src.toString();

            if (isStylesheetData(stringKey)) {
                String srcString = ""; // initial raw src String
                String baseURIString = ""; // base URI String
                String path = ""; // image file path
                String absoluteFilePath = "";
                URI uri;
                Path pathing = this.localPath;

                srcString = src.toString();
                baseURIString = src.baseUri();
                path = srcString.substring(srcString.indexOf("href=\"") + 6);
                path = path.substring(0, path.indexOf("\""));

                if (path.startsWith("https") || path.startsWith("http")) {
                    uri = new URI(path);
                } else {
                    baseURIString = baseURIString.replaceAll("\\\\", "/");
                    absoluteFilePath = baseURIString.substring(0, (baseURIString.lastIndexOf("/") + 1));
                    absoluteFilePath += path.substring(path.indexOf("/") + 1);
                    pathing = Paths.get(absoluteFilePath);
                    uri = pathing.toUri();
                    path = pathing.toString();
                }

                if (isExternal(this.basePath.toString(), this.urls, path)) {
                    this.stylesheets.addElement(new Stylesheet(uri, Classification.EXTERNAL));
                }

                if (isInternal(this.basePath.toString(), this.urls, path)) {
                    this.stylesheets.addElement(new Stylesheet(uri, Classification.INTERNAL));
                }
            }
        }
    }

    /**
     * Setter for Vector<OtherResource> otherResources. This method parses through a given Document parameter (_doc) using the JSOUP library to extract all elements that do not match a previously established resource type (ie: audio, video, other). Once the raw data is extracted, each element is converted into a string. These strings are parsed and categorized by URI, classification, type, and file size then added to otherResources as individual OtherResource objects.
     * 
     * @param _doc
     * @throws URISyntaxException
     */
    public void setOtherResources(Document _doc) throws URISyntaxException {
        Elements media = _doc.select("[src]");
        // makes sure image container is empty
        this.otherResources.clear();
        for (Element src : media) {
            if (!isImageData(src) && !isScriptData(src) && !isStylesheetData(src.toString())) {
                String srcString = ""; // initial raw src String
                String baseURIString = ""; // base URI String
                String path = ""; // image file path
                String absoluteFilePath = "";
                URI uri;
                Path pathing = this.localPath;

                srcString = src.toString();
                baseURIString = src.baseUri();
                path = srcString.substring(srcString.indexOf("src=\"") + 5);
                path = path.substring(0, path.indexOf("\""));

                baseURIString = baseURIString.replaceAll("\\\\", "/");
                absoluteFilePath = baseURIString.substring(0, (baseURIString.lastIndexOf("/") + 1));
                absoluteFilePath += path.substring(path.indexOf("/") + 1);
                pathing = Paths.get(absoluteFilePath);
                uri = pathing.toUri();
                path = pathing.toString();
                long fileSize = getBytes(path);

                if (srcString.contains("audio")) {
                    this.otherResources.addElement(new OtherResource(uri, Classification.INTERNAL, TargetType.AUDIO, fileSize));
                }
                else if (srcString.contains("video")) {
                    this.otherResources.addElement(new OtherResource(uri, Classification.INTERNAL, TargetType.VIDEO,
                            fileSize));
                }
                else
                    this.otherResources.addElement(new OtherResource(uri, Classification.INTERNAL, TargetType.OTHER,
                            fileSize));
            }
        }
    }

    /**
     * Build function for an HTMLDocument.
     * 
     */
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
     * Checks if provided String links to an internal image
     * 
     * @param path
     * @return True if specified path links internal image
     */
    private boolean isInternal(String basePath, Vector<URL> urlList, String baseURIString) {
        for (URL url : urlList) {
            if (baseURIString.startsWith(url.toString())) {
                return true;
            }
        }
        return baseURIString.startsWith(basePath);
    }

    /**
     * Checks if provided String links to an intrapage image
     * TODO -- Still broken...
     * 
     * @param path
     * @return True if specified path links internal image
     */
    private boolean isIntraPage(String localPath, Vector<URL> urlList, String baseURIString) throws MalformedURLException {
        for (URL url : urlList) {
            URL baseURItoURL = new URL(baseURIString);
            if (baseURItoURL == url) {
                return true;
            }
        }
        return localPath == baseURIString;
    }

    /**
     * Checks if provided String links to an external image
     * 
     * @param path
     * @return True if specified path links an external image
     */
    // TODO -- Fix based off professor input
    private boolean isExternal(String basePath, Vector<URL> urlList, String baseURIString) {
        return !isInternal(basePath, urlList, baseURIString);
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