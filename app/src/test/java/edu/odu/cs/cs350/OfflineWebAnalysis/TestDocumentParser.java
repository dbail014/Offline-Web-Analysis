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

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

// TODO -- Needs full update

/**
 * Description...
 * 
 * 
 * @author James Wright
 * 
 */
@TestMethodOrder(MethodOrderer.MethodName.class) 
class TestDocumentParser {
    String HTMLString1;
    String HTMLString2;
    String HTMLString3;

    Path testPath1;
    Path testPath2;
    Path testPath3;
    
    Document doc2;

    HTMLDocument parser2;
    HTMLDocument parser3;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException {
        HTMLString1 = "<img src=\"./img_girl.jpg\"alt=\"Girl in a jacket\">";
        HTMLString2 = "<img src=\"./pic_trulli.jpg\" alt=\"Italian Trulli\">";
        HTMLString3 = "<img src=\"./more/picture_generic.jpg\" alt=\"Flowers in Chania\">";

        testPath1 = Paths.get("./some/pic_tsdafrulli.jpg");
        testPath2 = Paths.get("./pic_trulli.jpg");
        testPath3 = Paths.get("./more/picture_generic.jpg");

        doc2 = Jsoup.parseBodyFragment(HTMLString2);
        // Document doc3 = Jsoup.parseBodyFragment(HTMLString3);

        parser2 = new DocumentParser(testPath2).build();
        parser3 = new DocumentParser(testPath3).build();
    }

    @Test
    public void testNonDefaultConstructor() throws IOException, URISyntaxException {
        HTMLDocument nonDefaultParse = new DocumentParser(testPath2).build();
        assertThat(nonDefaultParse.getLocalPath(), equalTo(testPath2));
        assertThat(nonDefaultParse.getImages(), equalTo(parser2.getImages()));
        assertThat(nonDefaultParse, not(equalTo(parser3)));
    }

    @Test // This was overkill. I should probably remove some of these eventually.
    public void testSetLocalPath() {
        assertThat(parser2, not(equalTo(parser3)));
        assertThat(parser2.getLocalPath(), not(equalTo(parser3.getLocalPath())));
        Path somePath = parser3.getLocalPath();
        assertThat(parser3.getLocalPath(), equalTo(somePath));
        assertThat(parser3.getLocalPath(), equalTo(testPath3));
        assertThat(parser2.getLocalPath(), not(equalTo(somePath)));
        assertThat(parser2.getLocalPath(), not(equalTo(testPath3)));
    }

    @Test
    public void testSetImageVector() {
        assertThat(parser2.getImages(), not(equalTo(parser3.getImages())));
    }
}