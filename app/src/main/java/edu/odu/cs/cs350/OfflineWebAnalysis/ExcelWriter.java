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

import javax.swing.text.StyledEditorKit.ForegroundAction;

/*import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;*/

public class ExcelWriter extends ReportWriter {


    public static void main(String[] args) throws Exception {

        //Create blank workbook
   //     XSSFWorkbook workbook = new XSSFWorkbook();
        
        //Create a blank sheet
   //     XSSFSheet spreadsheet = workbook.createSheet( " Employee Info ");
  
        //Create row object
    //    XSSFRow row;

        //declaring amount of rows, hardcoded to 4 until determined
        int tempSize = 4;
        String rowcount = Integer.toString(2);
        


        // fething the data

        Vector<Anchor> anchor = new Vector<Anchor>(htmlDoc.getAnchors());
        Vector<Image> image = new Vector<Image>(htmlDoc.getImages());
        Vector<Script> script = new Vector<Script>(htmlDoc.getScripts());

        String stringPath = args[0];
        Path path = Paths.get(stringPath);
        HTMLDocument htmlDoc = new DocumentParser(path).build();

        Website site = new Website();
        Vector<HTMLDocument> pages = site.getAllPages();


        //getting the counts
        int pagecount = pages.size() ;
        String imagecount =Integer.toString(htmlDoc.getImages().size());
        int cssCount =1;
        String scriptCount = Integer.toString(htmlDoc.getScripts().size());
        int linkIPCount;
        int linkINCount;
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

            empinfo.put( rowcount, new Object[] {
                page, imagecount, "#CSS",scriptCount,"#Links (Intra-Page)","#Links(Internal)","#Links (External)" });
                
                page++;
         }

         //Iterate over data and write to sheet
         Set < String > keyid = empinfo.keySet();
         int rowid = 0;
      
          for (String key : keyid) {
     //       row = spreadsheet.createRow(rowid++);
            Object [] objectArr = empinfo.get(key);
            int cellid = 0;
         
         for (Object obj : objectArr){
      //      Cell cell = row.createCell(cellid++);
      //      cell.setCellValue((String)obj);
         }
      }

      String fileName = nameFile();
      //Write the workbook in file system
      FileOutputStream out = new FileOutputStream(
         new File(fileName +".xlsx"));
      
   //   workbook.write(out);
      out.close();
      System.out.println("Writesheet.xlsx written successfully");
   }

    
}

