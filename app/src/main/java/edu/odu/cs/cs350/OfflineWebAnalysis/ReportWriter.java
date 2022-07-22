package edu.odu.cs.cs350.OfflineWebAnalysis;
import java.io.File; 
import java.util.Date;
import java.util.concurrent.Callable;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.io.IOException; 

// superclass for excelWriter, jsonWriter, textWriter
public class ReportWriter{

//naming outPut files
// type of file will be json, txt or xlsx
public void nameFile(String typeofFile){
    //getting current year, month, day, hour, minute,second
    LocalDate date = LocalDate.now();
    Calendar time = Calendar.getInstance();
    int currentDay = date.getDayOfMonth();
    int currentYear = date.getYear();
    int currentMonth = date.getMonthValue();
    int currentHour = time.get(Calendar.HOUR_OF_DAY);
    int currentMinute = time.get(Calendar.MINUTE);
    int currentSecond = time.get(Calendar.SECOND);
    



// creating the file
    File myFile = new File(""+currentYear + currentMonth + 
    currentDay + "-" + currentHour + currentMinute + currentSecond + "-" + "summary." + typeofFile);

// need to decide if file will be made here or in subclass'
}

}