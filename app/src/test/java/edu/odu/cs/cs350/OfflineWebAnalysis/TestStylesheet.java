package edu.odu.cs.cs350.OfflineWebAnalysis;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Description...
 * 
 * 
 * @author James Wright
 * 
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestStylesheet {
    Stylesheet testStylesheet;
    Stylesheet emptyStylesheet;
    URI test1Uri;
    URI test2URI;
    URI test3URI;

    @BeforeEach
    public void setUp() throws URISyntaxException { // DONE
        emptyStylesheet = new Stylesheet();
        testStylesheet = new Stylesheet(); 

        test1Uri = new URI("./user/someDir/file.html");
        test2URI = new URI("./user/OtherDir/file.html");
        test3URI = new URI("www.google.com");
        
        testStylesheet.setClassification(Classification.INTERNAL);
        testStylesheet.setURIPath(test1Uri);
    }

    @Test
    public void testDefaultConstructor() { // DONE
        Stylesheet defaultStylesheet = new Stylesheet();
        Resource generic = (Resource) defaultStylesheet;

        // Checks -- No check for URI because there is no empty value for URI
        assertThat(defaultStylesheet.getClassification(), equalTo(Classification.EXTERNAL));
        assertThat(generic.getClassification(), equalTo(Classification.EXTERNAL));
    }

    @Test
    public void testNonDefaultConstructor() {
        Stylesheet nonDefaultStylesheet = new Stylesheet(test2URI, Classification.INTERNAL);

        // Checks
        assertThat(nonDefaultStylesheet.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(nonDefaultStylesheet.getURIPath(), equalTo(test2URI));
    }
    
    @Test
    public void testCopyConstructor() {
        Stylesheet copy = new Stylesheet(testStylesheet);

        // Checks
        assertThat(copy.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(copy.getURIPath(), equalTo(test1Uri));
        
    }

    @Test
    public void testClone() {
        Stylesheet copy = (Stylesheet) testStylesheet.clone();

        // Checks
        assertThat(copy.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(copy.getURIPath(), equalTo(test1Uri));
    }

    @Test
    public void testEquals() {
        Stylesheet generic = new Stylesheet();

        assertThat(testStylesheet, not(equalTo(generic)));

        Stylesheet imitation = (Stylesheet) testStylesheet.clone();

        imitation.setClassification(Classification.INTERNAL);
        assertThat(testStylesheet, is(equalTo(imitation)));
        imitation.setURIPath(test1Uri);
        assertThat(testStylesheet, is(equalTo(imitation)));

        imitation = (Stylesheet) testStylesheet.clone();
        imitation.setClassification(Classification.INTRAPAGE);
        assertThat(testStylesheet, is(not(equalTo(imitation))));

        imitation = (Stylesheet) testStylesheet.clone();
        imitation.setURIPath(test3URI);
        assertThat(testStylesheet, is(not(equalTo(imitation))));
    }

    @Test
    public void testHashCode() {
        Stylesheet generic = new Stylesheet();

        assertThat(testStylesheet.hashCode(), not(equalTo(generic.hashCode())));
    }

    @Test
    public void testToString() {
        // TODO -- write test
    }

}