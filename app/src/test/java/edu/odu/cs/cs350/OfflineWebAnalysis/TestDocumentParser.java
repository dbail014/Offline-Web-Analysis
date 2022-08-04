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
class TestDocumentParser {
    Path basePath;
    Path testAnchor1;
    Path testImage1;
    Path testScript1;
    Path testStylesheet1;
    Path testOtherResource1;
    Path testAll1;
    
    Vector<URL> urlVect = new Vector<URL>(0);

    DocumentParser anchorParser;
    DocumentParser imageParser;
    DocumentParser scriptParser;
    DocumentParser stylesheetParser;
    DocumentParser otherResourceParser;
    DocumentParser allParser;

    Path anchorTester;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException {
        String directoryPath = System.getProperty("user.dir");
        directoryPath += "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/";

        // setup paths
        basePath = Paths.get(directoryPath);
        testAnchor1 = Paths.get(directoryPath + "testAnchor1.html");
        testImage1 = Paths.get(directoryPath + "testImage1.html");
        testScript1 = Paths.get(directoryPath + "testScript1.html");
        testStylesheet1 = Paths.get(directoryPath + "testStylesheet1.html");
        testOtherResource1 = Paths.get(directoryPath + "testOtherResource1.html");
        testAll1 = Paths.get(directoryPath + "testAll1.html");

        urlVect.addElement(new URL("https://www.cs.odu.edu"));

        // DocumentParser(Path _basePath, Path _localPath, Vector<URL> _urls)
        anchorParser = new DocumentParser(basePath, testAnchor1, urlVect);
        imageParser = new DocumentParser(basePath, testImage1, urlVect);
        scriptParser = new DocumentParser(basePath, testScript1, urlVect);
        stylesheetParser = new DocumentParser(basePath, testStylesheet1, urlVect);
        otherResourceParser = new DocumentParser(basePath, testOtherResource1, urlVect);
        allParser = new DocumentParser(basePath, testAll1, urlVect);
    }

    @Test
    public void testNonDefaultConstructor() throws IOException, URISyntaxException {
        String checkBase = System.getProperty("user.dir");
        checkBase += "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/";
        String checkLocal = checkBase + "testAnchor1.html";
        Path checkBasePath = Paths.get(checkBase);
        Path checkLocalPath = Paths.get(checkLocal);

        DocumentParser anotherAnchorParser = new DocumentParser(basePath, testAnchor1, urlVect);
        assertThat(anotherAnchorParser.getBasePath(), equalTo(checkBasePath));
        assertThat(anotherAnchorParser.getLocalPath(), equalTo(checkLocalPath));
        assertThat(anotherAnchorParser.getUrls().isEmpty(), is(false));
    }

    @Test
    public void testAnchorParsing() {
        assertThat(anchorParser.getAnchors().isEmpty(), is(false));
        assertThat(anchorParser.getImages().isEmpty(), is(true));
        assertThat(anchorParser.getScripts().isEmpty(), is(true));
        assertThat(anchorParser.getStylesheets().isEmpty(), is(true));
        assertThat(anchorParser.getOtherResources().isEmpty(), is(true));

        assertThat(anchorParser.getAnchors().size(), equalTo(2));
        assertThat(anchorParser.getAnchors().get(0).getClassification(), equalTo(Classification.INTERNAL));
        assertThat(anchorParser.getAnchors().get(1).getClassification(), equalTo(Classification.EXTERNAL));
    }

    @Test
    public void testImageParsing() {
        assertThat(imageParser.getAnchors().isEmpty(), is(true));
        assertThat(imageParser.getImages().isEmpty(), is(false));
        assertThat(imageParser.getScripts().isEmpty(), is(true));
        assertThat(imageParser.getStylesheets().isEmpty(), is(true));
        assertThat(imageParser.getOtherResources().isEmpty(), is(true));

        assertThat(imageParser.getImages().size(), equalTo(2));
        assertThat(imageParser.getImages().get(0).getClassification(), equalTo(Classification.INTERNAL));
        assertThat(imageParser.getImages().get(1).getClassification(), equalTo(Classification.EXTERNAL));
    }

    @Test
    public void testScriptParsing() {
        assertThat(scriptParser.getAnchors().isEmpty(), is(true));
        assertThat(scriptParser.getImages().isEmpty(), is(true));
        assertThat(scriptParser.getScripts().isEmpty(), is(false));
        assertThat(scriptParser.getStylesheets().isEmpty(), is(true));
        assertThat(scriptParser.getOtherResources().isEmpty(), is(true));

        assertThat(scriptParser.getScripts().size(), equalTo(4));
        assertThat(scriptParser.getScripts().get(0).getClassification(), equalTo(Classification.INTERNAL));
        assertThat(scriptParser.getScripts().get(1).getClassification(), equalTo(Classification.INTERNAL));
        assertThat(scriptParser.getScripts().get(2).getClassification(), equalTo(Classification.EXTERNAL));
        assertThat(scriptParser.getScripts().get(3).getClassification(), equalTo(Classification.EXTERNAL));
    }

    @Test
    public void testStylesheetParsing() {
        assertThat(stylesheetParser.getAnchors().isEmpty(), is(true));
        assertThat(stylesheetParser.getImages().isEmpty(), is(true));
        assertThat(stylesheetParser.getScripts().isEmpty(), is(true));
        assertThat(stylesheetParser.getStylesheets().isEmpty(), is(false));
        assertThat(stylesheetParser.getOtherResources().isEmpty(), is(true));

        assertThat(stylesheetParser.getStylesheets().size(), equalTo(3));
        assertThat(stylesheetParser.getStylesheets().get(0).getClassification(), equalTo(Classification.INTERNAL));
        assertThat(stylesheetParser.getStylesheets().get(1).getClassification(), equalTo(Classification.EXTERNAL));
        assertThat(stylesheetParser.getStylesheets().get(2).getClassification(), equalTo(Classification.EXTERNAL));
    }

    @Test
    public void testOtherResourceParsing() {
        assertThat(otherResourceParser.getAnchors().isEmpty(), is(true));
        assertThat(otherResourceParser.getImages().isEmpty(), is(true));
        assertThat(otherResourceParser.getScripts().isEmpty(), is(true));
        assertThat(otherResourceParser.getStylesheets().isEmpty(), is(true));
        assertThat(otherResourceParser.getOtherResources().isEmpty(), is(false));

        assertThat(otherResourceParser.getOtherResources().size(), equalTo(2));
        assertThat(otherResourceParser.getOtherResources().get(0).getTargetType(), equalTo(TargetType.AUDIO));
        assertThat(otherResourceParser.getOtherResources().get(1).getTargetType(), equalTo(TargetType.VIDEO));
    }
}