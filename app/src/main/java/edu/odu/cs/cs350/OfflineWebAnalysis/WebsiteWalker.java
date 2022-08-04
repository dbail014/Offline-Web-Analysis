package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.io.File;
import java.nio.file.Path;
import java.util.Vector;
// import java.nio.file.Paths;

// import org.jsoup.helper.Validate;

// TODOs
// Refactor code
// Update documentation of to be in line with javadoc standards

/**
 * Description...
 * 
 * 
 * @author James Wright
 * 
 */
public class WebsiteWalker {
    
    // public static void main(String[] args) {
    //     Validate.isTrue(args.length == 1, "usage: supply url to fetch");
    //     String stringPath = args[0];
    //     Path path = Paths.get(stringPath);
    //     WebsiteWalker site = new WebsiteWalker();
    //     site.setPaths(path);
    //     Vector<Path> paths = site.getPaths();
    //     for (Path p : paths) {
    //         System.out.println(p.toString());
    //     }
    // }

    private Vector<Path> paths;

    public WebsiteWalker () {
        this.paths = new Vector<Path>(0);
    }

    
    /** 
     * @return Vector<Path>
     */
    public Vector<Path> getPaths() {
        return this.paths;
    }

    
    /** 
     * @param basePath
     */
    // needs to control for sizes 1 - 1000
    public void setPaths(Path basePath) {
        File baseFile = basePath.toFile();
        File[] files = baseFile.listFiles();
        for (File file : files) {
            Path currPath = file.toPath();
            if (file.isDirectory()) {
                setPaths(currPath);
            }
            else {
                if (currPath.toString().endsWith("html") || currPath.toString().endsWith("htm"))
                    this.paths.addElement(currPath);
            }
        }
    }
}
