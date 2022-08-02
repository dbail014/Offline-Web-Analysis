package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.io.IOException;
import java.net.URISyntaxException;
// import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

import org.jsoup.helper.Validate;

// TODOs
// Refactor code
// Possibly combine WebsiteWalker class into single Website class
// Update documentation of to be in line with javadoc standards
// Unit tests
// URL implementation question for professor

/**
 * Description...
 * 
 * 
 * @author James Wright
 * 
 */
public class Website {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Validate.isTrue(args.length == 1, "usage: supply url to fetch");
        String stringPath = args[0];
        Path path = Paths.get(stringPath);
        Website site = new Website();
        site.setBasePath(path);
        site.setAllPages();
        Vector<HTMLDocument> allDocs = site.getAllPages();
        int count = 1;
        for (HTMLDocument htmlDoc : allDocs) {
            System.out.println(count + ": " + htmlDoc.getLocalPath().toString());
            Vector<Anchor> anchor = new Vector<Anchor>(htmlDoc.getAnchors());
            Vector<Image> image = new Vector<Image>(htmlDoc.getImages());
            Vector<Script> script = new Vector<Script>(htmlDoc.getScripts());
            System.out.println("Anchor Vector " + count + ":");
            System.out.println(anchor.toString());
            System.out.println("Image Vector " + count + ":");
            System.out.println(image.toString());
            System.out.println("Script Vector " + count + ":");
            System.out.println(script.toString());
            count++;
        }
    }

    private Path basePath;
    private Vector<HTMLDocument> allPages;

    // TODO!!!
    // private Vector<URL> urls; 

    public Website() {
        allPages = new Vector<HTMLDocument>(0);
    }

    public Path getBasePath() {
        return this.basePath;
    }

    public void setBasePath(Path _basePath) {
        this.basePath = _basePath;
    }

    public Vector<HTMLDocument> getAllPages() {
        return this.allPages;
    }

    public void setAllPages() throws IOException, URISyntaxException {
        WebsiteWalker site = new WebsiteWalker();
        site.setPaths(this.basePath);
        Vector<Path> paths = site.getPaths();
        for (Path p : paths) {
            HTMLDocument currPage = new DocumentParser(p).build();
            this.allPages.addElement(currPage);
        }
    }
}
