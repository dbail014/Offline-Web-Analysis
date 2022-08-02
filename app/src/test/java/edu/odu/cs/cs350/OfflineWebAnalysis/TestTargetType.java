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
public class TestTargetType {
    @Test
    public void testToString() {
        assertThat(TargetType.ARCHIVE.toString(), equalTo("archive"));
        assertThat(TargetType.AUDIO.toString(), equalTo("audio"));
        assertThat(TargetType.VIDEO.toString(), equalTo("video"));
    }
}
