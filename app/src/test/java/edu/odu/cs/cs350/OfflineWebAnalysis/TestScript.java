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
public class TestScript {
    Script testScript;
    Script emptyScript;
    URI test1Uri;
    URI test2URI;
    URI test3URI;
    URI test4URI;

    @BeforeEach
    public void setUp() throws URISyntaxException { // DONE
        emptyScript = new Script();
        testScript = new Script(); 

        test1Uri = new URI("./user/someDir/file.html");
        test2URI = new URI("./user/OtherDir/file.html");
        test3URI = new URI("https://www.google.com");
        test4URI = new URI("https://www.example.com");
        
        testScript.setClassification(Classification.INTERNAL);
        testScript.setURIPath(test1Uri);
    }

    @Test
    public void testDefaultConstructor() { // DONE
        Script defaultScript = new Script();
        Resource generic = (Resource) defaultScript;

        // Checks -- No check for URI because there is no empty value for URI
        assertThat(defaultScript.getClassification(), equalTo(Classification.EXTERNAL));
        assertThat(generic.getClassification(), equalTo(Classification.EXTERNAL));
    }

    @Test
    public void testNonDefaultConstructor() {
        Script nonDefaultScript = new Script(test2URI, Classification.INTERNAL);

        // Checks
        assertThat(nonDefaultScript.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(nonDefaultScript.getURIPath(), equalTo(test2URI));
    }
    
    @Test
    public void testCopyConstructor() {
        Script copy = new Script(testScript);

        // Checks
        assertThat(copy.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(copy.getURIPath(), equalTo(test1Uri));
        
    }

    @Test
    public void testClone() {
        Script copy = (Script) testScript.clone();

        // Checks
        assertThat(copy.getClassification(), equalTo(Classification.INTERNAL));
        assertThat(copy.getURIPath(), equalTo(test1Uri));
    }

    @Test
    public void testEquals() {
        Script generic = new Script();

        assertThat(testScript, not(equalTo(generic)));
        
        Script imitation = (Script) testScript.clone();

        imitation.setClassification(Classification.INTERNAL);
        assertThat(testScript, is(equalTo(imitation)));
        imitation.setURIPath(test1Uri);
        assertThat(testScript, is(equalTo(imitation)));

        imitation = (Script) testScript.clone();
        imitation.setClassification(Classification.INTRAPAGE);
        assertThat(testScript, is(not(equalTo(imitation))));


        imitation = (Script) testScript.clone();
        imitation.setURIPath(test3URI);
        assertThat(testScript, is(not(equalTo(imitation))));
    }

    @Test
    public void testHashCode() {
        Script generic = new Script();

        assertThat(testScript.hashCode(), not(equalTo(generic.hashCode())));
    }

    @Test
    public void testToString() {
        Anchor notSame = new Anchor();

        testScript.setClassification(Classification.INTERNAL);
        testScript.setURIPath(test3URI);

        notSame.setClassification(Classification.EXTERNAL);
        notSame.setURIPath(test4URI);

        assertThat(notSame.toString().isEmpty(), is(false));
        assertThat(testScript.toString().isEmpty(), is(false));

        assertThat(notSame.toString(), not(equalTo(testScript.toString())));
    }

}