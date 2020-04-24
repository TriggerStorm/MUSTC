/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import mustc.be.Session;
import mustc.be.Task;

/**
 *
 * @author admin
 */
public class SessionDBDAO {
    private DBConnection dbc;

    
    public SessionDBDAO() {
        dbc = new DBConnection();
    }

    
    public List<Session> getSession(int taskID) throws SQLException {
        List<Session> taskSessions = new ArrayList<>();  //null;?
        try(Connection con = dbc.getConnection()) {
            String sql = "SELECT id AND name FROM Sessions WHERE AssociatedTask = '" + taskID + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) //While you have something in the results
            {
                int sessionID = rs.getInt("id");
                int associatedUserID = rs.getInt("AssociatedUser");
                int associatedTaskID = rs.getInt("AssociatedTask");
                java.sql.Date sqlStartTime = rs.getDate("StartTime");
    //            LocalDateTime startTime = sqlStartTime.toLocalDate();
    //            LocalDateTime startTime = sqlStartTime.toLocalDate();
                java.sql.Date startTime = rs.getDate("StartTime");
                java.sql.Date finishTime = rs.getDate("FinishTime");
                
       //         Session taskSession = new Session(sessionID, associatedUserID, associatedTaskID, startTime, finishTime);
        //        taskSessions.add(taskSession); 
            }    
        }
        return taskSessions ;
    
    
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    }
    
    
}
