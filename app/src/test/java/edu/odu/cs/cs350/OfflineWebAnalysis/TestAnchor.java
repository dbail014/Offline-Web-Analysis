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
public class TestAnchor {
    Anchor testAnchor;
    Anchor emptyAnchor;
    URI test1Uri;
    URI test2URI;
    URI test3URI;

    @BeforeEach
    public void setUp() throws URISyntaxException { // DONE
        emptyAnchor = new Anchor();
        testAnchor = new Anchor(); 

        test1Uri = new URI("./user/someDir/file.html");
        test2URI = new URI("./user/OtherDir/file.html");
        test3URI = new URI("www.google.com");
        
        testAnchor.setClassification(Classification.INTERNAL);
        testAnchor.setURIPath(test1Uri);
    }

    @Test
    public void testDefaultConstructor() { // DONE
        Anchor defaultAnchor = new Anchor();
        Resource generic = (Resource) defaultAnchor;

        // Checks -- No check for URI because there is no empty value for URI
        assertThat(defaultAnchor.getClassification(), equalTo(Classification.EXTERNAL));
        assertThat(generic.getClassification(), equalTo(Classification.EXTERNAL));
    }

    @Test
    public void testNonDefaultConstructor() {
        Anchor nonDefaultAnchor = new Anchor(test2URI, Classification.INTERNAL);

        // Checks
        assertThat(nonDefaultAnchor.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(nonDefaultAnchor.getURIPath(), equalTo(test2URI));
    }
    
    @Test
    public void testCopyConstructor() {
        Anchor copy = new Anchor(testAnchor);

        // Checks
        assertThat(copy.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(copy.getURIPath(), equalTo(test1Uri));
        
    }

    @Test
    public void testClone() {
        Anchor copy = (Anchor) testAnchor.clone();

        // Checks
        assertThat(copy.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(copy.getURIPath(), equalTo(test1Uri));
    }

    @Test
    public void testEquals() {
        Anchor generic = new Anchor();

        assertThat(testAnchor, not(equalTo(generic)));
        
        Anchor imitation = (Anchor) testAnchor.clone();

        imitation.setClassification(Classification.INTERNAL);
        assertThat(testAnchor, is(equalTo(imitation)));

        imitation.setURIPath(test1Uri);
        assertThat(testAnchor, is(equalTo(imitation)));
        
        imitation = (Anchor) testAnchor.clone();
        imitation.setClassification(Classification.INTRAPAGE);
        assertThat(testAnchor, is(not(equalTo(imitation))));

        imitation = (Anchor) testAnchor.clone();
        imitation.setURIPath(test3URI);
        assertThat(testAnchor, is(not(equalTo(imitation))));
    }

    @Test
    public void testHashCode() {
        Anchor generic = new Anchor();

        assertThat(testAnchor.hashCode(), not(equalTo(generic.hashCode())));
    }

    @Test
    public void testToString() {
        // TODO -- write test
    }

}