package edu.odu.cs.cs350.OfflineWebAnalysis;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

/**
 * @author James Wright
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class SystemTest {
    Website testSite;
    Path testBasePath;
    String[] urlStrings = { "https://www.cs.odu.edu" };
    Path basePath;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException {
        String directoryPath = System.getProperty("user.dir");
        directoryPath += "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/";
        basePath = Paths.get(directoryPath);

        testSite = new Website();
        testSite.setBasePath(basePath);
        testSite.setUrls(urlStrings);
        testSite.setAllPages();
    }
    
    @Test
    public void TestWebsiteURI() throws URISyntaxException {
        URI testURI = new URI("https://www.cs.odu.edu/~tkennedy/cs350/sum22/Directory/outline/");
        assertThat(testSite.getAllPages().get(0).getAnchors().get(0).getURIPath(), equalTo(testURI));
    }

    @Test
    public void TestWebsiteToAnchor() throws URISyntaxException {
        Anchor testAnchor = new Anchor();
        URI test1Uri = new URI("https://www.cs.odu.edu/~tkennedy/cs350/sum22/Directory/outline/");
        testAnchor.setClassification(Classification.INTERNAL);
        testAnchor.setURIPath(test1Uri);

        assertThat(testSite.getAllPages().get(0).getAnchors().get(0), equalTo(testAnchor));
    }

    @Test
    public void TestWebsiteToAnchorVector() throws URISyntaxException {
        Vector<Anchor> anchorCollection = new Vector<Anchor>(0);
        
        Anchor testAnchor = new Anchor();
        URI test1Uri = new URI("https://www.cs.odu.edu/~tkennedy/cs350/sum22/Directory/outline/");
        testAnchor.setClassification(Classification.INTERNAL);
        testAnchor.setURIPath(test1Uri);
        anchorCollection.addElement(testAnchor);

        Anchor testAnchor2 = new Anchor();
        URI test2Uri = new URI("https://www.w3schools.com");
        testAnchor.setClassification(Classification.EXTERNAL);
        testAnchor2.setURIPath(test2Uri);
        anchorCollection.addElement(testAnchor2);

        assertThat(testSite.getAllPages().get(0).getAnchors(), equalTo(anchorCollection));
    }
}