package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import javax.swing.text.html.parser.DocumentParser;


//functions that i need for this class
// sum up all the sizes
//get the sizes of the webpage (this functionality is already done so just call it from another class)
//i also need something to get the paths of the pages


public class TextWriter extends ReportWriter{

    public void createFile(){
        Document doc = Jsoup.parse(input);
        //this line below v is taking a element from an 
        //array because each element represents a single page and every page has a path
        
        DocumentParser p = new DocumentParser(doc);
        String path = p.getPath();

        //make a variable that stores the absolute path of a file 
        //String path = "";

        PrintWriter outFile = new PrintWriter("things.txt");
        
        //Format this output to the 2 decimal place
        long sizeOfPage = 0.00000095367431640625 * p.getBytes(path);
        //1 Bytes = 0.00000095367431640625 MiB
        //1 MiB = 1,048,576 Bytes
        
        //NOW I NEED TO CREATE THE ALGORITHM TO ORDER IT AND I NEED TO MAKE AN IF THEN STATEMENT THAT CONTINUES TO 
        //ADD THE DIFFERENT PAGES AND PATHS
        outFile.print(sizeOfPage + "  "+ path);


        outFile.close();


    }

     
}