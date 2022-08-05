package edu.odu.cs.cs350.OfflineWebAnalysis;


import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


// setting up test ExcelWriter
public class TestExcelWriter {
    Website testSite;
    Path testBasePath;
    String[] urlStrings = { "http://www.cs.odu.edu" };
    Path basePath;
    URL testURL;

    @BeforeEach
    public void setUp() throws IOException, URISyntaxException {
        String directoryPath = System.getProperty("user.dir");
        directoryPath += "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/";
        basePath = Paths.get(directoryPath);

        testSite = new Website();
        testSite.setBasePath(basePath);
        testSite.setUrls(urlStrings);
        testSite.setAllPages();
        testURL = new URL("http://www.cs.odu.edu");
    }

 ExcelWriter testEx;
    @Test
    public void testFileCreation(){
        assertThat("file ", equals(ReportWriter.nameFile() +".xlsx"))


    }
}
