package edu.odu.cs.cs350.OfflineWebAnalysis;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Description...
 * 
 * 
 * @author James Wright
 * 
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestSystem
{
    HTMLDocument test1;
    HTMLDocument test2;
    HTMLDocument test3;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException { 
        String localPCPath = System.getProperty("user.dir");

        String test1String = "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/testImage1.html";
        String test2String = "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/testImage2.html";
        String test3String = "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/testImage3.html";

        test1String = localPCPath + test1String;
        test2String = localPCPath + test2String;
        test3String = localPCPath + test3String;

        test1String = test1String.replaceAll("\\\\", "/");
        test2String = test2String.replaceAll("\\\\", "/");
        test3String = test3String.replaceAll("\\\\", "/");
        
        Path test1Path = Paths.get(test1String);
        Path test2Path = Paths.get(test2String);
        Path test3Path = Paths.get(test3String);

        // File test1File = test1Path.toFile();
        // File test2File = test2Path.toFile();
        // File test3File = test3Path.toFile();

        // org.jsoup.nodes.Document test1Doc = Jsoup.parse(test1File);
        // org.jsoup.nodes.Document test2Doc = Jsoup.parse(test2File);
        // org.jsoup.nodes.Document test3Doc = Jsoup.parse(test3File);
        
        test1 = new DocumentParser(test1Path).build();
        test2 = new DocumentParser(test2Path).build();
        test3 = new DocumentParser(test3Path).build();
    }

    @Test
    public void testParsers() throws URISyntaxException {
        assertThat(test1.getImages().size(), equalTo(1));
        assertThat(test2.getImages().size(), equalTo(3));
        assertThat(test3.getImages().size(), equalTo(3));
        assertThat(test1, not(equalTo(test2)));
        assertThat(test1, not(equalTo(test3)));
        assertThat(test2, not(equalTo(test3)));
    }

    @Test // This will obviously only work if images are not deleted by github
    public void testFileSize() {
        long sizeSum = 0;
        for (Image i : test1.getImages()) {
            sizeSum += i.getFileSize();
        }
        assertThat(sizeSum, equalTo(535L));

        sizeSum = 0;
        for (Image i : test2.getImages()) {
            sizeSum += i.getFileSize();
        }
        assertThat(sizeSum, equalTo(1908L));

        sizeSum = 0;
        for (Image i : test3.getImages()) {
            sizeSum += i.getFileSize();
        }
        assertThat(sizeSum, equalTo(1478L));
    }

    @Test
    public void testOutput() {
        // TODO
    }
}
