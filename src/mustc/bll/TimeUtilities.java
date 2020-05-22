/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Trigger and Alan
 */
public class TimeUtilities {
    
 static int msec = 0;
    static int sec = 0;
    static int mins = 0;
    static int hours = 0;
    
    boolean timeState = true;
    
    public String localDateTimeToString(LocalDateTime LDT) {
    // Converts a formatted LocalDateTime to string of format "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateNowString = LDT.format(formatter);
        return dateNowString;
    } 
    
      
    public LocalDateTime stringToLocalDateTime(String dateTimeSTR) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd['T'HH:mm:ss[Z]]");
        String[] parts = dateTimeSTR.split(" ");
        String time = parts[1];
        String[] parts2 = time.split(".");
        String sqlSTR = parts[0] + "T" + parts[1].substring(0,8);                                    // https://stackoverflow.com/questions/17685977/cut-java-string-at-a-number-of-character
        LocalDateTime LDT = LocalDateTime.parse(sqlSTR, formatter);
        return LDT;
    }
    
        
    public LocalDate stringToLocalDate(String dateTimeSTR) {
        LocalDateTime localDateTime = stringToLocalDateTime(dateTimeSTR);
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate;
    }


    
   /* public String runningClock(){
    timeState = true;
        
        Thread t = new Thread()
        {
            public void run()
            {
                    for(;;)
                    {
                        if(timeState==true)
                        {
                            try
                            {
                                sleep(1);
                                
                                if(msec>1000)
                                {
                                msec=0;
                                sec++;
                                }
                                if(sec>60)
                                {
                                msec=0;
                                sec=0;
                                mins++;
                                }
                                if(mins>60)
                                {
                                msec=0;
                                sec=0;
                                mins=0;
                                hours++;
                                }
                                
                                msec++;
                                
                                
                                
                            }
                            catch(Exception e)
                            {
                            
                            }
                            
                        }
                        
                    
                        else
                        {
                         break;       
                        }
                    }
            }
            
                
            
        };
        
        t.start();
     return (" " +hours + mins + sec);
    }*/

    
    
    
    
    
}
