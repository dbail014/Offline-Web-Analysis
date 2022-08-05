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

/**
 * @author James Wright
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestWebsite {
    Website testSite;
    Path testBasePath;
    String[] urlStrings = { "http://www.cs.odu.edu" };
    Path basePath;
    URL testURL;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException {
        String directoryPath = System.getProperty("user.dir");
        directoryPath += "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/";
        basePath = Paths.get(directoryPath);

        testSite = new Website();
        testSite.setBasePath(basePath);
        testSite.setUrls(urlStrings);
        testSite.setAllPages();
        testURL = new URL("http://www.cs.odu.edu");
    }

    @Test
    public void testDefaultConstructor() throws IOException, URISyntaxException {
        Website defaultSite = new Website();
        defaultSite.setBasePath(basePath);
        defaultSite.setUrls(urlStrings);
        defaultSite.setAllPages();
    }
    
    @Test
    public void TestGetterAccuracies() {
        assertThat(testSite.getAllPages().size(), equalTo(6));
        assertThat(testSite.getBasePath(), equalTo(basePath));
        assertThat(testSite.getUrls().get(0), equalTo(testURL));
    }

}
