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

    //defult constructor, wont be needed in most cases
    public ReportWriter(){

    }
//naming outPut files
// type of file will be json, txt or xlsx
public static String nameFile(){
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
    return ""+currentYear + currentMonth + 
    currentDay + "-" + currentHour + currentMinute + currentSecond + "-" + "summary.";

// need to decide if file will be made here or in subclass'
}

}