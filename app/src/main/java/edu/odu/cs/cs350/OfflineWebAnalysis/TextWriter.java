package edu.odu.cs.cs350.OfflineWebAnalysis;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
//import org.jsoup.nodes.Document;

//import org.jsoup.nodes.Document;
//import org.jsoup.Jsoup;
import javax.swing.text.html.parser.DocumentParser;
import java.nio.file.Path;
import java.nio.file.Paths;


import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

//import org.jsoup.helper.Validate;
import java.io.IOException;
//import org.jsoup.Jsoup;


//functions that i need for this class
// sum up all the sizes
//get the sizes of the webpage (this functionality is already done so just call it from another class)
//i also need something to get the paths of the pages


public class TextWriter {

    public void createFile(){
        //Document doc = Jsoup.parse();
        //this line below v is taking a element from an 
        //array because each element represents a single page and every page has a path
        String stringPath = "";
        Path path = Paths.get(stringPath);
        File file = path.toFile();
        //Document doc = Jsoup.parse(file);

       // Document doc = Jsoup.parse(input);
        //this line below v is taking a element from an 
        //array because each element represents a single page and every page has a path
        
        //DocumentParser p = new DocumentParser(doc, path);
        TextWriter p = new TextWriter();
        
        //String path = p.getPath();



        //make a variable that stores the absolute path of a file 
        //String path = "";

        //FileWriter outFile = new FileWriter("things.txt");
        
        //Format this output to the 2 decimal place
        double sizeOfPage = 0.00000095367431640625 * p.getBytes(stringPath);
        //1 Bytes = 0.00000095367431640625 MiB
        //1 MiB = 1,048,576 Bytes
        
        //NOW I NEED TO CREATE THE ALGORITHM TO ORDER IT AND I NEED TO MAKE AN IF THEN STATEMENT THAT CONTINUES TO 
        //ADD THE DIFFERENT PAGES AND PATHS
        //outFile.write(sizeOfPage + "  "+ path);
        try {
            FileWriter myWriter = new FileWriter("things.txt");
            int tempSize = 10;
            for(int x=0; x < tempSize; x++)
            {
                 myWriter.write(sizeOfPage + "  "+ path);
             }
            //myWriter.write(sizeOfPage + "  "+ path);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }

        //hardcoding forloop size
        // size of page needs to interate
        /* 
        int tempSize = 10;
        for(int x=0; x < tempSize; x++)
            {
                 myWriter.print(sizeOfPage + "  "+ path);
             }
        */
        
        //outFile.close();


    }

    private long getBytes(String absoluteFilePath) {
        File file = new File(absoluteFilePath);
        if (file.exists()) {
            // size of a file (in bytes)
            return file.length();
        }
        return 0;
    }


     
}