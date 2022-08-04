package edu.odu.cs.cs350.OfflineWebAnalysis;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

/**
 * @author James Wright
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestWebsiteWalker {
    Vector<Path> paths = new Vector<Path>(0);
    Path basePath;

    @BeforeEach
    public void setUp() {
        String directoryPath = System.getProperty("user.dir");
        directoryPath += "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/";
        basePath = Paths.get(directoryPath);
    }

    @Test
    public void testDefaultConstructor() {
        WebsiteWalker defaultSite = new WebsiteWalker();
        assertThat(defaultSite.getPaths(), equalTo(paths));
    }

    @Test
    public void testSetPaths() {
         WebsiteWalker defaultSite = new WebsiteWalker();
         defaultSite.setPaths(basePath);
         assertThat(defaultSite.getPaths().size(), equalTo(6));
    }
}
