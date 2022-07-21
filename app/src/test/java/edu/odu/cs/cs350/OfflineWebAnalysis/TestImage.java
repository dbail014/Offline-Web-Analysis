package edu.odu.cs.cs350.OfflineWebAnalysis;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestImage {
    Image testImage;

    @BeforeEach
    public void setUp() { // DONE
        testImage = new Image();
        
        testImage.setClassification(Classification.INTERNAL);
        testImage.setFileSize(1000);
        testImage.setURIPath("C:\\Users\\person\\Desktop\\Agile_Methods.html");
    }

    @Test
    public void testDefaultConstructor() { // DONE
        Image defaultImage = new Image();
        Resource generic = (Resource) defaultImage;

        // Checks
        assertThat(defaultImage.getClassification(), equalTo(Classification.EXTERNAL));
        assertThat(defaultImage.getFileSize(), equalTo(0L));
        assertThat(defaultImage.getURIPath(), equalTo(""));
        assertThat(generic.getClassification(), equalTo(Classification.EXTERNAL));
        assertThat(generic.getURIPath(), equalTo(""));
    }

    @Test
    public void testNonDefaultConstructor() {
        Image nonDefaultImage = new Image("C:\\Users\\person\\Desktop\\Agile_Methods.html", Classification.INTERNAL, 100);

        // Checks
        assertThat(nonDefaultImage.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(nonDefaultImage.getFileSize(), equalTo(100L));
        assertThat(nonDefaultImage.getURIPath(), equalTo("C:\\Users\\person\\Desktop\\Agile_Methods.html"));
    }
    
    @Test
    public void testCopyConstructor() {
        Image copy = new Image(testImage);

        // Checks
        assertThat(copy.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(copy.getFileSize(), equalTo(1000L));
        assertThat(copy.getURIPath(), equalTo("C:\\Users\\person\\Desktop\\Agile_Methods.html"));
        
    }

    @Test
    public void testClone() {
        Image copy = (Image) testImage.clone();

        // Checks
        assertThat(copy.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(copy.getFileSize(), equalTo(1000L));
        assertThat(copy.getURIPath(), equalTo("C:\\Users\\person\\Desktop\\Agile_Methods.html"));
    }

    @Test
    public void testToString() {
        String expected = "{IMG:\n  URI: " + "C:\\Users\\person\\Desktop\\Agile_Methods.html" + ",\n  Classification: " + "internal" + ",\n  Size: " + "1000" + "}\n";

        assertThat(testImage.toString(), equalTo(expected));
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

        imitation.setURIPath("C:\\Users\\person\\Desktop\\Agile_Methods.html");
        assertThat(testImage, is(equalTo(imitation)));
        
        imitation = (Image) testImage.clone();
        imitation.setClassification(Classification.INTRAPAGE);
        assertThat(testImage, is(not(equalTo(imitation))));

        imitation = (Image) testImage.clone();
        imitation.setFileSize(10L);
        assertThat(testImage, is(not(equalTo(imitation))));

        imitation = (Image) testImage.clone();
        imitation.setURIPath("www.google.com");
        assertThat(testImage, is(not(equalTo(imitation))));
    }

    @Test
    public void testHashCode() {
        Image generic = new Image();

        assertThat(testImage.hashCode(), not(equalTo(generic.hashCode())));
    }
}