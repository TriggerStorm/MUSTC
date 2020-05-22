/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.bll;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    import java.io.Serializable;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Trigger and Alan
      
 */

public class InputValidators  implements Serializable {
   
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    
    
     

    public boolean isValidEmail(String email) {
    //  Returns true if email string is is in valid email format. Works pretty well. Not 100% (eg: abc@xyz..com)
    
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }


    public boolean isValidPhoneNumber(String phoneNumber) {
    //  Returns true if phoneNumber is 8 chars long and contains only numbers
        phoneNumber = phoneNumber.trim();
        if (!(phoneNumber.length() == 8)) {
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            char ch = phoneNumber.charAt(i);
              if (!Character.isDigit(ch)){
            return false;
            }
        }
        return true;    }
     
 
}
    
