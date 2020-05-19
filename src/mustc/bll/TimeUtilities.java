/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.bll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Trigger and Alan
 */
public class TimeUtilities {
    
 
    public String localDateTimeToString(LocalDateTime LDT) {
    // Converts a formatted LocalDateTime to string of format "yyyy-MM-dd HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateNowString = LDT.format(formatter);
        return dateNowString;
    } 
    
      
    public LocalDateTime stringToLocalDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd['T'HH:mm:ss[Z]]");
        String[] parts = dateString.split(" ");
        String time = parts[1];
        String[] parts2 = time.split(".");
        String sqlSTR = parts[0] + "T" + parts[1].substring(0,8);                                    // https://stackoverflow.com/questions/17685977/cut-java-string-at-a-number-of-character
        LocalDateTime LDT = LocalDateTime.parse(sqlSTR, formatter);
        return LDT;
    }
    
    
    
}
