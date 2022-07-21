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

    // This class is for functionality testing only.
    // Currently only runs on Windows system.
    // In powershell type:
    // ./gradlew run --args='_Your filePath_'(foward slashes are doubled)
        // I have been testing by downloading the html for the Agile Methods lecture notes.
        // I would use downloaded html lecture notes until/unless Professor comes out with some testing files for us.
    public static void main(String[] args) throws IOException {
        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String url = args[0];
        File input = new File(url);
        Document doc = Jsoup.parse(input);
        DocumentParser p = new DocumentParser(doc);
        Vector<Image> lImages = new Vector<Image>(p.getImageVector());
        System.out.println(lImages.toString());
    }

    protected Vector<Image> v = new Vector<Image>();

    public DocumentParser(Document doc) {
        Elements media = doc.select("[src]");
        this.setImageVector(media);
    }

    // Setter for image vector.
    // TODO -- Better documentation.
    public void setImageVector(Elements media) {
        for (Element src : media) {
            this.v.addElement(imageProcessor(src));
        }
    }

    public Vector<Image> getImageVector() {
        return this.v;
    }

    // TODO -- Getters and setters for vector(s)

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

            // Absolute File Path for Windows
            absoluteFilePath = createAbsoluteFilePathWindows(baseURIString, path);

            Image _image = new Image();
            // check for external with if statement here
            if (isExternal(path)) {
                // create external image object (with fileSize = 0)
                Image currImage = new Image(baseURIString, Classification.EXTERNAL, bytes);
                _image = (Image) currImage.clone();
            }
            if (isInternal(path)) {
                bytes = getBytes(absoluteFilePath);
                Image currImage = new Image(baseURIString, Classification.INTERNAL, bytes);
                _image = (Image) currImage.clone();
            }
            System.out.println(_image.toString());
            // defined above with placeholder value
            // add image object to collection<image> images from HTMLDocuments.java
            return _image;
        }
        Image im = new Image();
        return im;
    }
    
    private boolean isImageData(Element src) {
        return src.normalName().equals("img");
    }

    // if path is for external object (image)
    // Documentation -- TODO
    static boolean isExternal(String path) {
        return (path.contains("https://") || path.contains("http://"));
    }

    // if path is for internal object (image)
    // Documentation -- TODO
    private boolean isInternal(String path) {
        return (!path.contains("https://") && !path.contains("http://"));
    }

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

    //class needed ot create absolue file path for linux
}