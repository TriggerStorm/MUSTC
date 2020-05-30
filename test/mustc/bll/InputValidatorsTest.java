/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.bll;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author admin
 */
public class InputValidatorsTest {
    
    public InputValidatorsTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isValidEmail method, of class InputValidators.
     */
    @Test
    public void testIsValidEmail() {
        System.out.println("isValidEmail");
        String email = "Brave New World";
        InputValidators instance = new InputValidators();
        boolean expResult = true;
        boolean result = instance.isValidEmail(email);
        assertEquals(expResult, result);
        }

    /**
     * Test of isValidPhoneNumber method, of class InputValidators.
     */
    @Test
    public void testIsValidPhoneNumber() {
        System.out.println("isValidPhoneNumber");
        String phoneNumber = "12t35490";
        InputValidators instance = new InputValidators();
        boolean expResult = true;
        boolean result = instance.isValidPhoneNumber(phoneNumber);
        assertEquals(expResult, result);
    }
    
}
