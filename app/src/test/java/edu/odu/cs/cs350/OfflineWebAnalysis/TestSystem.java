// package edu.odu.cs.cs350.OfflineWebAnalysis;


// import org.junit.jupiter.api.TestMethodOrder;
// import org.junit.jupiter.api.MethodOrderer;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.BeforeEach;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.hamcrest.MatcherAssert.assertThat;
// import static org.hamcrest.Matchers.*;

// import org.hamcrest.Matcher;
// import org.hamcrest.core.IsNull;

// import org.jsoup.Jsoup;
// import org.jsoup.nodes.Document;

// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.Vector;


// @TestMethodOrder(MethodOrderer.MethodName.class)
// class TestDocumentParser3
// {
//     DocumentParser test1;
//     DocumentParser test2;
//     DocumentParser test3;

//     @BeforeEach
//     public void setUp() throws IOException { 
//         String localPCPath = System.getProperty("user.dir");

//         String test1String = "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/test1.html";
//         String test2String = "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/test2.html";
//         String test3String = "/src/test/java/edu/odu/cs/cs350/OfflineWebAnalysis/resources/test3.html";

//         test1String = localPCPath + test1String;
//         test2String = localPCPath + test2String;
//         test3String = localPCPath + test3String;

//         test1String = test1String.replaceAll("\\\\", "/");
//         test2String = test2String.replaceAll("\\\\", "/");
//         test3String = test3String.replaceAll("\\\\", "/");
        
//         Path test1Path = Paths.get(test1String);
//         Path test2Path = Paths.get(test2String);
//         Path test3Path = Paths.get(test3String);

//         File test1File = test1Path.toFile();
//         File test2File = test2Path.toFile();
//         File test3File = test3Path.toFile();

//         org.jsoup.nodes.Document test1Doc = Jsoup.parse(test1File);
//         org.jsoup.nodes.Document test2Doc = Jsoup.parse(test2File);
//         org.jsoup.nodes.Document test3Doc = Jsoup.parse(test3File);
        
//         test1 = new DocumentParser(test1Doc, test1Path);
//         test2 = new DocumentParser(test2Doc, test2Path);
//         test3 = new DocumentParser(test3Doc, test3Path);
//     }

//     @Test
//     public void testInitialState() {
//         // TODO

//     }

//     @Test
//     public void testSetLocalImage() {
//         // TODO
//     }

//     @Test
//     public void testSetImageVector() {
//         // TODO
//     }
// }
