/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.bll;

import java.sql.SQLException;
import java.time.LocalDateTime;
import mustc.dal.SessionDBDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author admin
 */
public class TimeUtilitiesTest {
    SessionDBDAO sessionDBDao;
    
    public TimeUtilitiesTest() {
    sessionDBDao = new SessionDBDAO();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of localDateTimeToString method, of class TimeUtilities.
     */
    @Test
    public void testLocalDateTimeToString() throws SQLException {
        System.out.println("localDateTimeToString");
        LocalDateTime LDT = sessionDBDao.getSession(5).getStartLDT();
        TimeUtilities instance = new TimeUtilities();
        String expResult = "2020-04-16 13:19:00";
        String result = instance.localDateTimeToString(LDT);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToLocalDateTime method, of class TimeUtilities.
     */
    @Test
    public void testStringToLocalDateTime() throws SQLException {
        System.out.println("stringToLocalDateTime");
        String dateString = "2020-04-18 13:26:59";
        TimeUtilities instance = new TimeUtilities();
        LocalDateTime expResult = sessionDBDao.getSession(7).getStartLDT();
        LocalDateTime result = instance.stringToLocalDateTime(dateString);
        assertEquals(expResult, result);
    }
    
}
