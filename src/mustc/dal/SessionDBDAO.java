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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;

/**
 *
 * @author Trigger and Alan
 */
public class SessionDBDAO {
    private DBConnection dbc;
    private UserDBDAO userDBDao;
 //   private TaskDBDAO taskDBDao;

    
    
    public SessionDBDAO() {
        dbc = new DBConnection();
 //       taskDBDao = new TaskDBDAO();
        userDBDao = new UserDBDAO();
    }

    
    
     public Session addNewSessionToDB(int associatedUserID, int associatedTaskID, String startTime, String finishTime) { 
    //  Adds a new Session to the Sessions table of the database given the sessions details. Generates an id key    
        Session newSession = new Session(0, associatedUserID, associatedTaskID, startTime, finishTime);
        String sql = "INSERT INTO Sessions(associatedUser, associatedTask, startTime, finishTime) VALUES (?,?,?,?)";
        try (Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, associatedUserID);
            pstmt.setInt(2, associatedTaskID);
            pstmt.setString(3, startTime);
            pstmt.setString(4, finishTime);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Session failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newSession.setSessionID((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating Session failed, no ID obtained.");
                } 
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(SessionDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newSession;
    }
     
     
    public Session getSession(int sessionID) throws SQLException {
    //  Returns a Session data object given a Session id
        Session session = null; 
        String sql = "SELECT * FROM Sessions WHERE id = '" + sessionID + "'";
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) // While you have something in the results
            {
                int associatedUserID = rs.getInt("AssociatedUser");
                int associatedTaskID = rs.getInt("AssociatedTask");
                String startTime = rs.getString("StartTime");
                String finishTime = rs.getString("FinishTime");
    //            LocalDateTime startTime = sqlStartTime.toLocalDate();  Need to work out time
    //            LocalDateTime startTime = sqlStartTime.toLocalDate();
    //            java.sql.Date startTime = rs.getDate("StartTime");
    //            java.sql.Date finishTime = rs.getDate("FinishTime");
                session = new Session(sessionID, associatedUserID, associatedTaskID, startTime, finishTime);
            }    
        }
        return session ;
    }
    
          
    public List<Session> getAllSessionsOfATask(int taskID) throws SQLException {
        List<Session> allSessionsOfATask = new ArrayList<>();
//        allSessionsOfATask = null;
        String sql = "SELECT * FROM Sessions WHERE associatedTask = '" + taskID + "'"; 
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) // While you have something in the results
            {
                int sessionID = rs.getInt("id");
                int AssociatedUserID =  rs.getInt("associatedUser");
                int associatedTaskID =  rs.getInt("associatedTask");
                String startTime = "0"; //rs.getString("startTime");
                String finishTime = "1"; //rs.getString("finishTime");            
 /*System.out.println("");
 System.out.println("ID = " + sessionID);               
 System.out.println("AssociatedUserID = " + AssociatedUserID);               
 System.out.println("associatedTaskID = " + associatedTaskID);               
 System.out.println("startTime = " + startTime);               
 System.out.println("finishTime = " + finishTime);               
 */               Session sessionInTask = new Session(sessionID, AssociatedUserID, associatedTaskID, startTime, finishTime);
                allSessionsOfATask.add(sessionInTask); 
            }    
        }
        return allSessionsOfATask ;
    }
    
    public List<Session> getAllSessionsStartTimeAndTaskID(User loggedInUser) throws SQLException {
    // UNTESTED
  //      PriorityQueue<Task> allUserTasksPQ = new PriorityQueue<>(comparator);
  
        List<Session> allLoggedInUserSessions = new ArrayList<>();
        int loggedInUserID = loggedInUser.getUserID();
        String sql = "SELECT associatedTask, StartTime FROM Sessions WHERE associatedUser = '" + loggedInUserID + "'"; 
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) // While you have something in the results
            {
                int associatedTaskID =  rs.getInt("associatedTask");
                String startTime = rs.getString("startTime");
               Session loggedInUserSession = new Session(associatedTaskID, startTime);
                allLoggedInUserSessions.add(loggedInUserSession); 
            }    
        }
        Collections.sort(allLoggedInUserSessions);//new Comparator<Session>()) {
          for (int i = 0; i < allLoggedInUserSessions.size(); i++) {
            Session session = allLoggedInUserSessions.get(i);
              System.out.println("");
               System.out.println(session.getStartTime());
              
        }
  
        return allLoggedInUserSessions ;
    }
    
     
     
/*    public List<Integer> getAllSessionIDsOfATask(int taskID) throws SQLException {  
// method only needed for getAllUserIDsAndNamesOfATask. May not need
        List<Integer> allSessionIDsOfATask = new ArrayList<>();
        try(Connection con = dbc.getConnection()) {
            String sql = "SELECT id FROM Sessions WHERE associatedTask = '" + taskID + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) // While you have something in the results
            {
                int sessionID = rs.getInt("id");
                allSessionIDsOfATask.add(sessionID); 
            }    
        }
        return allSessionIDsOfATask ;
    } 
 */        
       
    private void addStartTimeToSession(Session currentSession) { 
    //  Adds a strartTime to a given Session   
        LocalDateTime LDTnow = LocalDateTime.now();
        String startTime = LDTnow.toString();
        currentSession.setStartTime(startTime);
    }

     
    private void addFinishTimeToSession(Session currentSession) throws SQLException { 
    //  Adds a strartTime to a given Session   
        LocalDateTime LDTnow = LocalDateTime.now();
        String finishTime = LDTnow.toString();
        currentSession.setFinishTime(finishTime);
        String startTime = currentSession.getStartTime();
        int associatedTaskID = currentSession.getAssociatedTaskID();
 //       Task currentTask = taskDBDao.getTask(associatedTaskID);  // HAD TO COMMENT OUT 
 /*       int[] sessionDuration = calculateDurationOfASession(startTime, finishTime);
        int sessionkHours = sessionDuration[0];
        int sessionMinutes = sessionDuration[1];
        
 //       int[] taskDuration = currentTask.getTaskDuration();
        int taskHours = taskDuration[0];
        int taskMinutes = taskDuration[1];
        taskMinutes += sessionMinutes;
        if (taskMinutes >= 60) {
            taskMinutes =- 60;
            taskHours ++;
        }
        taskHours += sessionkHours;
        taskDuration[0] = taskHours;
        taskDuration[1] = taskMinutes;   
        currentTask.setTaskDuration(taskDuration);
 */ //      int associatedUserID = loggedInUser.getId();
  //      addNewSessionToDB(associatedUserID, associatedTaskID, startTime, finishTime) { 

        

    }
    
    
/*    private int[] calculateDurationOfASession(String startTime, String finishTime) {
        int[] duration = new int[2];
        LocalDateTime startLDT = LocalDateTime.parse(startTime);
        LocalDateTime finishLDT = LocalDateTime.parse(finishTime);
        LocalDateTime tempLDT = LocalDateTime.from(startLDT);

        int hours = (int) tempLDT.until( finishLDT, ChronoUnit.HOURS );
        tempLDT = tempLDT.plusHours(hours);

        int minutes = (int) tempLDT.until( finishLDT, ChronoUnit.MINUTES );
        tempLDT = tempLDT.plusMinutes( minutes );  // probably don't need this line
        duration[0] = hours;
        duration[1] = minutes;
        return duration;
    }
*/            
            
            
            
/*    public List<User> getAllUserIDsAndNamesOfATask(int taskID) throws SQLException {
        List<Integer> allSessionsIDsOfATask = new ArrayList<>();
        allSessionsIDsOfATask = getAllSessionIDsOfATask(taskID); 
        if (allSessionsIDsOfATask.size() > 0) {
            for (int i = 0; i < allSessionsIDsOfATask.size(); i++) {            
                try(Connection con = dbc.getConnection()) {
                    String sql = "SELECT id, name FROM Users WHERE associatedUser = '" + taskID + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    while(rs.next()) // While you have something in the results
                    {
                        int sessionID = rs.getInt("id");
                        int AssociatedUserID = rs.getInt("associatedUser");
                        int associatedTaskID = rs.getInt("associatedTask");
                        String startTime = rs.getString("startTime");
                        String finishTime = rs.getString("finishTime");            
                        Session sessionInTask = new Session(sessionID, AssociatedUserID, associatedTaskID, startTime, finishTime);
                        allUserIDsOfATask.add(sessionInTask); 
                    }    
        }
        return allUserIDsOfATask ;
    }
 */         
     
    
    
    public Session editSession (Session editedSession, int associatedUserID, int associatedTaskID, String startTime, String finishTime) { 
    //  Edits a Session in the Session table of the database given the Sessions new details.  
        String sql = "UPDATE Sessions SET associatedUser = ?, associatedTask = ?, startTime = ?, finishTime = ? WHERE id = '" + editedSession.getSessionID()+ "'";
        try (  //Get a connection to the database.
            Connection con = dbc.getConnection()) {  
            //Create a prepared statement.
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setInt(1, associatedUserID);
            pstmt.setInt(2, associatedTaskID);
            pstmt.setString(3, startTime);
            pstmt.setString(4, finishTime);
            pstmt.executeUpdate();  // Execute SQL query.
            editedSession.setAssociatedUserID(associatedUserID);
            editedSession.setAssociatedTaskID(associatedTaskID);
            editedSession.setStartTime(startTime);   
            editedSession.setFinishTime(finishTime);
            return editedSession;
        } catch (SQLServerException ex) {
            Logger.getLogger(SessionDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SessionDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

       
    public void removeSessionFromDB(Session sessionToDelete) {
    //  Removes a session from the Session table of the database given a Session data object
        String sql = "DELETE FROM Sessions WHERE id = ?";
        try (Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,sessionToDelete.getSessionID());
            pstmt.execute();
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    }
      
    
}
