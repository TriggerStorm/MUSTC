/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

     public Session addNewSessionToDB(int associatedUserID, int associatedTaskID, String startTime, String finishTime) { 
    //  Adds a new session to the Session table of the database given the sessions details. Generates an id key    
        String sql = "INSERT INTO Sessions(associatedUser, associatedTask, startTime, finishTime) VALUES (?,?,?,?)";
        Session newSession = new Session(0, associatedUserID, associatedTaskID, startTime, finishTime);
        try (Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, associatedUserID);
            pstmt.setInt(2, associatedTaskID);
            pstmt.setString(3, startTime);
            pstmt.setString(4, finishTime);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Seesion failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newSession.setSessionId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating Session failed, no ID obtained.");
                } 
                return newSession;
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(SessionDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
     
    public List<Session> getSession(int taskID) throws SQLException {
        List<Session> taskSessions = new ArrayList<>();  //null;?
        try(Connection con = dbc.getConnection()) {
            String sql = "SELECT id AND name FROM Sessions WHERE AssociatedTask = '" + taskID + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) // While you have something in the results
            {
                int sessionID = rs.getInt("id");
                int associatedUserID = rs.getInt("AssociatedUser");
                int associatedTaskID = rs.getInt("AssociatedTask");
                java.sql.Date sqlStartTime = rs.getDate("StartTime");
    //            LocalDateTime startTime = sqlStartTime.toLocalDate();  Need to work out time
    //            LocalDateTime startTime = sqlStartTime.toLocalDate();
                java.sql.Date startTime = rs.getDate("StartTime");
                java.sql.Date finishTime = rs.getDate("FinishTime");
//  Following 2 lines can't be implementeduntil time is sorted out
       //         Session taskSession = new Session(sessionID, associatedUserID, associatedTaskID, startTime, finishTime);
        //        taskSessions.add(taskSession); 
            }    
        }
        return taskSessions ;
    
    
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    
    }
    
    
}
