package edu.odu.cs.cs350.OfflineWebAnalysis;
import java.io.File;
import java.io.FileOutputStream;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.lang.String;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Vector;
import java.util.Arrays;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


 /** ..
  *@author John Wasikye
  */
public class ExcelWriter extends ReportWriter {

public ExcelWriter(){

}
    public static void main(String[] args) throws Exception {

        //Create blank workbook
      XSSFWorkbook workbook = new XSSFWorkbook();
        
        //Create a blank sheet
      XSSFSheet spreadsheet = workbook.createSheet( " Employee Info ");
  
        //Create row object
      XSSFRow row;

        //declaring amount of rows, hardcoded to 4 until determined
        int tempSize = 4;
        String rowcount = Integer.toString(2);
        


        // fething the data

        Vector<Anchor> anchor = new Vector<Anchor>(htmlDoc.getAnchors());
        Vector<Image> image = new Vector<Image>(htmlDoc.getImages());
        Vector<Script> script = new Vector<Script>(htmlDoc.getScripts());

        String[] urlStrings = Arrays.copyOfRange(args, 1, args.length);

        String stringPath = args[0];
        Path path = Paths.get(stringPath);
        

        Website site = new Website();
        site.setBasePath(path);
        site.setUrls(urlStrings);
        site.setAllPages();
        Vector<HTMLDocument> docs = site.getAllPages();


        //getting the page count
        int pagecount = 0;
        for(HTMLDocument htmldocInd : docs)
        {
         pagecount++;
        }
   
        //  getting stylesheet count = cssCount
        int cssCount;
        int imagecount;
        int scriptCount;
        int linkIPCount;
        int linkINCount=0;
        int linkEXCount;

        
        //


        //collecting data that needs to be written
        Map < String, Object[] > empinfo = new TreeMap < String, Object[] >();
      empinfo.put( "1", new Object[] {
         "Page", "#Images", "#CSS","Scripts","#Links (Intra-Page)","#Links(Internal)","#Links (External)" });
      
         //adding the data, depening on amount
         // setting starting page number
         int page = 1;
         for(int x=0; x<pagecount ; x++)
         {

            //adding css counts
            cssCount = site.getAllPages().get(x).getStylesheets().size();
            //adding image counts
            imagecount = site.getAllPages().get(x).getImages().size();
            //adding script counts
            scriptCount = site.getAllPages().get(x).getScripts().size();
            // adding internal, external and intra page links
            
            for(int y=0 ; x<site.getAllPages().get(x).getAnchors().size(); x++)
            {
            if(site.getAllPages().get(x).getAnchors().get(y).getClassification().toString()== "internal")
               {
                  linkINCount++;
               }

               if(site.getAllPages().get(x).getAnchors().get(y).getClassification().toString()== "external")
               {
                  linkEXCount++;
               }
               if(site.getAllPages().get(x).getAnchors().get(y).getClassification().toString()== "intrapage")
               {
                  linkIPCount++;
               }
            }

  



            empinfo.put( rowcount, new Object[] {
                page, imagecount, cssCount,scriptCount,linkIPCount,linkINCount,linkEXCount });
                
                page++;
            
            linkINCount =0;
            linkEXCount =0;
            linkIPCount =0;
         }

         //Iterate over data and write to sheet
         Set < String > keyid = empinfo.keySet();
         int rowid = 0;
      
          for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object [] objectArr = empinfo.get(key);
            int cellid = 0;
         
         for (Object obj : objectArr){
           Cell cell = row.createCell(cellid++);
           cell.setCellValue((String)obj);
         }
      }

      String fileName = nameFile();
      //Write the workbook in file system
      FileOutputStream out = new FileOutputStream(
         new File(fileName +".xlsx"));
      
     workbook.write(out);
      out.close();
  
   }

    
}

