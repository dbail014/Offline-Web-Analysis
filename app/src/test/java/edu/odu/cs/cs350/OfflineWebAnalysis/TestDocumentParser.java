package edu.odu.cs.cs350.OfflineWebAnalysis;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

@TestMethodOrder(MethodOrderer.MethodName.class) 
class TestDocumentParser {
    String HTMLString1;
    String HTMLString2;
    String HTMLString3;

    Path testPath1;
    Path testPath2;
    Path testPath3;
    
    Document doc2;

    DocumentParser parser2;
    DocumentParser parser3;

    @BeforeEach
    public void setUp() throws IOException {
        HTMLString1 = "<img src=\"./img_girl.jpg\"alt=\"Girl in a jacket\">";
        HTMLString2 = "<img src=\"./pic_trulli.jpg\" alt=\"Italian Trulli\">";
        HTMLString3 = "<img src=\"./more/picture_generic.jpg\" alt=\"Flowers in Chania\">";

        testPath1 = Paths.get("./some/pic_tsdafrulli.jpg");
        testPath2 = Paths.get("./pic_trulli.jpg");
        testPath3 = Paths.get("./more/picture_generic.jpg");

        doc2 = Jsoup.parseBodyFragment(HTMLString2);
        Document doc3 = Jsoup.parseBodyFragment(HTMLString3);

        parser2 = new DocumentParser(doc2, testPath2);
        parser3 = new DocumentParser(doc3, testPath3);
    }

    @Test
    public void testNonDefaultConstructor() {
        Document nonDefaultDoc = Jsoup.parseBodyFragment(HTMLString2);
        DocumentParser nonDefaultParse = new DocumentParser(nonDefaultDoc, testPath2);
        assertThat(nonDefaultParse.getPath(), equalTo(testPath2));
        assertThat(nonDefaultParse.getImageVector(), equalTo(parser2.getImageVector()));
        assertThat(nonDefaultParse, not(equalTo(parser3)));
    }

    @Test // This was overkill. I should probably remove some of these eventually.
    public void testSetLocalPath() {
        assertThat(parser2, not(equalTo(parser3)));
        assertThat(parser2.getPath(), not(equalTo(parser3.getPath())));
        Path somePath = parser3.getPath();
        assertThat(parser3.getPath(), equalTo(somePath));
        assertThat(parser3.getPath(), equalTo(testPath3));
        assertThat(parser2.getPath(), not(equalTo(somePath)));
        assertThat(parser2.getPath(), not(equalTo(testPath3)));
        
        parser2.setLocalPath(testPath3);
        assertThat(parser2.getPath(), equalTo(testPath3));
        assertThat(parser2.getPath(), equalTo(somePath));
        assertThat(parser2, not(equalTo(parser3)));
        assertThat(parser2.getPath(), equalTo(parser3.getPath()));
    }

    @Test
    public void testSetImageVector() {
        assertThat(parser2.getImageVector(), not(equalTo(parser3.getImageVector())));
        parser3.setImageVector(doc2);
        assertThat(parser2.getImageVector(), equalTo(parser3.getImageVector()));
    }
}