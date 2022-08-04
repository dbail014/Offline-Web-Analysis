package edu.odu.cs.cs350.OfflineWebAnalysis;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

/**
 * @author James Wright
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestHTMLDocument {
    Path basePath;
    Path testAnchor1;
    Path testImage1;
    Path testScript1;
    Path testStylesheet1;
    Path testOtherResource1;
    Path testAll1;

    Vector<URL> urlVect = new Vector<URL>(0);

    HTMLDocument allParser;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException {
        String directoryPath = System.getProperty("user.dir");
        directoryPath += "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/";
        basePath = Paths.get(directoryPath);
        testAll1 = Paths.get(directoryPath + "testAll1.html");
        
        urlVect.addElement(new URL("https://www.cs.odu.edu"));
        allParser = new DocumentParser(basePath, testAll1, urlVect).build();
    }

    @Test
    public void testHTMLDocumentCreation() throws IOException, URISyntaxException {
        String checkBase = System.getProperty("user.dir");
        checkBase += "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/";
        String checkLocal = checkBase + "testAll1.html";
        Path checkBasePath = Paths.get(checkBase);
        Path checkLocalPath = Paths.get(checkLocal);
        HTMLDocument anotherAllParser = new DocumentParser(basePath, testAll1, urlVect).build();
        assertThat(anotherAllParser.getBasePath(), equalTo(checkBasePath));
        assertThat(anotherAllParser.getLocalPath(), equalTo(checkLocalPath));
        assertThat(anotherAllParser.getUrls().isEmpty(), is(false));
    }

    @Test
    public void testHTMLDocumentFunction() {
        assertThat(allParser.getAnchors().isEmpty(), is(false));
        assertThat(allParser.getImages().isEmpty(), is(false));
        assertThat(allParser.getScripts().isEmpty(), is(false));
        assertThat(allParser.getStylesheets().isEmpty(), is(false));
        assertThat(allParser.getOtherResources().isEmpty(), is(false));
    }
}
