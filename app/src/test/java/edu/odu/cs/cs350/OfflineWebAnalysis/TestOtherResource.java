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
public class TestOtherResource {
    OtherResource testResource;
    OtherResource emptyResource;
    URI test1Uri;
    URI test2URI;
    URI test3URI;
    URI test4URI;

    @BeforeEach
    public void setUp() throws URISyntaxException { // DONE
        emptyResource = new OtherResource();
        testResource = new OtherResource();

        test1Uri = new URI("./user/someDir/file.html");
        test2URI = new URI("./user/OtherDir/file.html");
        test3URI = new URI("https://www.google.com");
        test4URI = new URI("https://www.example.com");

        testResource.setClassification(Classification.INTERNAL);
        testResource.setTargetType(TargetType.AUDIO);
        testResource.setURIPath(test1Uri);
    }

    @Test
    public void testDefaultConstructor() { // DONE
        OtherResource defaultResource = new OtherResource();
        Resource generic = (Resource) defaultResource;

        // Checks -- No check for URI because there is no empty value for URI
        assertThat(defaultResource.getClassification(), equalTo(Classification.EXTERNAL));
        assertThat(defaultResource.getTargetType(), equalTo(TargetType.ARCHIVE));
        assertThat(generic.getClassification(), equalTo(Classification.EXTERNAL));
    }

    @Test
    public void testNonDefaultConstructor() {
        OtherResource nonDefaultResource = new OtherResource(test2URI, Classification.INTERNAL, TargetType.VIDEO, 0L);

        // Checks
        assertThat(nonDefaultResource.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(nonDefaultResource.getTargetType(), equalTo(TargetType.VIDEO));
        assertThat(nonDefaultResource.getURIPath(), equalTo(test2URI));
    }

    @Test
    public void testCopyConstructor() {
        OtherResource copy = new OtherResource(testResource);

        // Checks
        assertThat(copy.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(copy.getTargetType(), equalTo(TargetType.AUDIO));
        assertThat(copy.getURIPath(), equalTo(test1Uri));

    }

    @Test
    public void testClone() {
        OtherResource copy = (OtherResource) testResource.clone();

        // Checks
        assertThat(copy.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(copy.getTargetType(), equalTo(TargetType.AUDIO));
        assertThat(copy.getURIPath(), equalTo(test1Uri));
    }

    @Test
    public void testEquals() {
        OtherResource generic = new OtherResource();

        assertThat(testResource, not(equalTo(generic)));

        generic = (OtherResource) testResource.clone();

        // assertThat(testResource, is(equalTo(generic)));

        // imitation.setTargetType(TargetType);
        // assertThat(testResource, is(equalTo(imitation)));

        // imitation.setURIPath(test1Uri);
        // assertThat(testResource, is(equalTo(imitation)));

        // imitation = (Image) testResource.clone();
        // imitation.setClassification(Classification.INTRAPAGE);
        // assertThat(testResource, is(not(equalTo(imitation))));

        // imitation = (Image) testResource.clone();
        // imitation.setFileSize(10L);
        // assertThat(testResource, is(not(equalTo(imitation))));

        // imitation = (Image) testResource.clone();
        // imitation.setURIPath(test3URI);
        // assertThat(testResource, is(not(equalTo(imitation))));
    }

    @Test
    public void testHashCode() {
        OtherResource generic = new OtherResource();

        assertThat(testResource.hashCode(), not(equalTo(generic.hashCode())));
    }

    @Test
    public void testToString() {
        OtherResource notSame = new OtherResource();

        testResource.setClassification(Classification.INTERNAL);
        testResource.setURIPath(test3URI);

        notSame.setClassification(Classification.EXTERNAL);
        notSame.setURIPath(test4URI);

        assertThat(notSame.toString().isEmpty(), is(false));
        assertThat(testResource.toString().isEmpty(), is(false));

        assertThat(notSame.toString(), not(equalTo(testResource.toString())));
    }
}