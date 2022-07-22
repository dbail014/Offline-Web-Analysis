package edu.odu.cs.cs350.OfflineWebAnalysis;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.net.URI;
import java.net.URISyntaxException;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestImage {
    Image testImage;
    Image emptyImage;
    URI test1Uri;
    URI test2URI;
    URI test3URI;

    @BeforeEach
    public void setUp() throws URISyntaxException { // DONE
        emptyImage = new Image();
        testImage = new Image(); 

        test1Uri = new URI("./user/someDir/file.html");
        test2URI = new URI("./user/OtherDir/file.html");
        test3URI = new URI("www.google.com");
        
        testImage.setClassification(Classification.INTERNAL);
        testImage.setFileSize(1000);
        testImage.setURIPath(test1Uri);
    }

    @Test
    public void testDefaultConstructor() { // DONE
        Image defaultImage = new Image();
        Resource generic = (Resource) defaultImage;

        // Checks -- No check for URI because there is no empty value for URI
        assertThat(defaultImage.getClassification(), equalTo(Classification.EXTERNAL));
        assertThat(defaultImage.getFileSize(), equalTo(0L));
        assertThat(generic.getClassification(), equalTo(Classification.EXTERNAL));
    }

    @Test
    public void testNonDefaultConstructor() {
        Image nonDefaultImage = new Image(test2URI, Classification.INTERNAL, 100);

        // Checks
        assertThat(nonDefaultImage.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(nonDefaultImage.getFileSize(), equalTo(100L));
        assertThat(nonDefaultImage.getURIPath(), equalTo(test2URI));
    }
    
    @Test
    public void testCopyConstructor() {
        Image copy = new Image(testImage);

        // Checks
        assertThat(copy.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(copy.getFileSize(), equalTo(1000L));
        assertThat(copy.getURIPath(), equalTo(test1Uri));
        
    }

    @Test
    public void testClone() {
        Image copy = (Image) testImage.clone();

        // Checks
        assertThat(copy.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(copy.getFileSize(), equalTo(1000L));
        assertThat(copy.getURIPath(), equalTo(test1Uri));
    }

    @Test
    public void testEquals() {
        Image generic = new Image();

        assertThat(testImage, not(equalTo(generic)));
        
        Image imitation = (Image) testImage.clone();

        imitation.setClassification(Classification.INTERNAL);
        assertThat(testImage, is(equalTo(imitation)));

        imitation.setFileSize(1000L);
        assertThat(testImage, is(equalTo(imitation)));

        imitation.setURIPath(test1Uri);
        assertThat(testImage, is(equalTo(imitation)));
        
        imitation = (Image) testImage.clone();
        imitation.setClassification(Classification.INTRAPAGE);
        assertThat(testImage, is(not(equalTo(imitation))));

        imitation = (Image) testImage.clone();
        imitation.setFileSize(10L);
        assertThat(testImage, is(not(equalTo(imitation))));

        imitation = (Image) testImage.clone();
        imitation.setURIPath(test3URI);
        assertThat(testImage, is(not(equalTo(imitation))));
    }

    @Test
    public void testHashCode() {
        Image generic = new Image();

        assertThat(testImage.hashCode(), not(equalTo(generic.hashCode())));
    }
}