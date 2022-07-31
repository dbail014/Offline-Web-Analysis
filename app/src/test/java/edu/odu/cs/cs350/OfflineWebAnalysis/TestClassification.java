package edu.odu.cs.cs350.OfflineWebAnalysis;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Description...
 * 
 * 
 * @author James Wright
 * 
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
public class TestClassification {
    @Test
    public void testToString() {
        assertThat(Classification.EXTERNAL.toString(), equalTo("external"));
        assertThat(Classification.INTERNAL.toString(), equalTo("internal"));
        assertThat(Classification.INTRAPAGE.toString(), equalTo("intrapage"));
    }
}
