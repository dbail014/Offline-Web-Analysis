package edu.odu.cs.cs350.OfflineWebAnalysis;

import java.io.File; 
import java.util.Date;
import java.util.concurrent.Callable;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.io.IOException; 

/**
 * @author John Wasikye
 */
public class TestReportWriter{

    @Test
    public void testNamefile(){

    //getting current year, month, day, hour, minute,second
    LocalDate date = LocalDate.now();
    Calendar time = Calendar.getInstance();
    int currentDay = date.getDayOfMonth();
    int currentYear = date.getYear();
    int currentMonth = date.getMonthValue();
    int currentHour = time.get(Calendar.HOUR_OF_DAY);
    int currentMinute = time.get(Calendar.MINUTE);
    int currentSecond = time.get(Calendar.SECOND);


    assertThat(ReportWriter.nameFile(), equalTo(""+currentYear + currentMonth + 
    currentDay + "-" + currentHour + currentMinute + currentSecond + "-" + "summary."));
    }
    
}

