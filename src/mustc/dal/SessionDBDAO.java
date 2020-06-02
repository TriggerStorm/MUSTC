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
import mustc.bll.TimeUtilities;


/**
 *
 * @author Trigger and Alan
 */
public class SessionDBDAO {
    private DBConnection dbc;
    private UserDBDAO userDBDao;
    private TimeUtilities timeUtilities;
    
 //   private TaskDBDAO taskDBDao;

    /**
     *
     */
    public SessionDBDAO() {
        dbc = new DBConnection();
 //       taskDBDao = new TaskDBDAO();
        userDBDao = new UserDBDAO();
        timeUtilities = new TimeUtilities();
    
    }

    /**
     *
     * @param associatedUserID
     * @param associatedTaskID
     * @param associatedTaskName
     * @param startTime
     * @param finishTime
     * @return
     * @throws SQLException
     */
    public Session addNewSessionToDB(int associatedUserID, int associatedTaskID,String associatedTaskName, String startTime, String finishTime) throws SQLException { 
    //  Adds a new Session to the Sessions table of the database given the sessions details. Generates an id key    
        String associatedUserName = getSessionsUserName(associatedUserID);   
        Session newSession = new Session(0, associatedUserID,  associatedUserName, associatedTaskID, associatedTaskName, startTime, finishTime);
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
     
    /**
     *
     * @param sessionID
     * @return
     * @throws SQLException
     */
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
    //            java.sql.Date startTime = rs.getDate("StartTime");
    //            java.sql.Date finishTime = rs.getDate("FinishTime");
                String associatedUserName = getSessionsUserName(associatedUserID); 
                String associatedTaskName = getSessionsTaskName( associatedTaskID);
                session = new Session(sessionID, associatedUserID, associatedUserName, associatedTaskID, associatedTaskName, startTime, finishTime);
                setSessionsLDTs(session);
            }    
        }
        return session ;
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Session> getAllSessions() throws SQLException { // Admin view
    // Returns a list of Sessions where the associatedUser = loggedInUser
        List<Session> allSessions = new ArrayList<>();
        String sql = "SELECT * FROM Sessions"; 
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) // While you have something in the results
            {
                int sessionID = rs.getInt("id");
                int associatedUserID = rs.getInt("AssociatedUser");
                String associatedUserName = getSessionsUserName(associatedUserID);
                int associatedTaskID =  rs.getInt("associatedTask");
                String associatedTaskName = getSessionsTaskName(associatedTaskID);
                String startTime = rs.getString("startTime");                
                String finishTime = rs.getString("FinishTime");
                Session session = new Session(sessionID, associatedUserID, associatedUserName, associatedTaskID, associatedTaskName, startTime, finishTime);

                setSessionsLDTs(session);
                calculateDurationOfASession(startTime, finishTime);
                setSessionsDuration(session);
                allSessions.add(session); 
            }    
        }
  
        return allSessions ;
    }
 
    /**
     *
     * @param loggedInUser
     * @return
     * @throws SQLException
     */
    public List<Session> getAllSessionsOfAUser(User loggedInUser) throws SQLException { // User view
    // Returns a list of Sessions where the associatedUser = loggedInUser
        List<Session> allLoggedInUserSessions = new ArrayList<>();
        int loggedInUserID = loggedInUser.getUserID();
        String sql = "SELECT * FROM Sessions WHERE associatedUser = '" + loggedInUserID + "'"; 
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) // While you have something in the results
            {
                int sessionID = rs.getInt("id");
                int associatedTaskID =  rs.getInt("associatedTask");
                String associatedTaskName = getSessionsTaskName(associatedTaskID);
                String startTime = rs.getString("startTime");
                 String finishTime = rs.getString("FinishTime");
                Session loggedInUserSession = new Session(sessionID, associatedTaskID, associatedTaskName, startTime, finishTime);
                allLoggedInUserSessions.add(loggedInUserSession); 
            }    
        }
        return allLoggedInUserSessions ;
    }
     
    /**
     *
     * @param taskID
     * @return
     * @throws SQLException
     */
    public List<Session> getAllSessionsOfATask(int taskID) throws SQLException {  // Work in progress
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
                int associatedUserID =  rs.getInt("associatedUser");
 //               int associatedTaskID =  rs.getInt("associatedTask");
                String startTime = rs.getString("startTime");
                String finishTime = rs.getString("finishTime");
                
                String associatedUserName = getSessionsUserName(associatedUserID);
                
                Session sessionInTask = new Session(sessionID, associatedUserID, associatedUserName, 0, "", startTime, finishTime);
                sessionInTask.setAssociatedUserName(associatedUserName);
                System.out.println("Task: " + taskID + "    Session: " + sessionID + "   AssociatedUserID = " + associatedUserID + "   AssociatedUserName = " + sessionInTask.getAssociatedUserName());            
               allSessionsOfATask.add(sessionInTask); 
            }    
        }
        return allSessionsOfATask ;
    }
   
    /**
     *
     * @param projectID
     * @return
     * @throws SQLException
     */
    public List<Session> getAllSessionsOfAProject(int projectID) throws SQLException {
            List<Session> allSessionsOfAProject = new ArrayList<>();
    //        List<Task> allTaskIDsAndNamesOfAProject = TaskDBDAOgetAllTaskIDsAndNamesOfAProject(int projectID);

        List<Session> allSessions = new ArrayList<>();
 
          String sql = "SELECT * FROM Sessions INNER JOIN Tasks ON Sessions.AssociatedTask = tasks.id"
                  + "INNER JOIN Projects ON Tasks.AssociatedProject = Projects.id WHERE Projects.id '" + projectID + "'"; //Tasks.id)";// "; 
  
        try(Connection con = dbc.getConnection()) 
        {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
   
            while(rs.next()) // While you have something in the results
            {
                int sessionID = rs.getInt("id");
                int associatedUserID = rs.getInt("AssociatedUser");
                String associatedUserName = getSessionsUserName(associatedUserID);
                int associatedTaskID =  rs.getInt("associatedTask");
                String associatedTaskName = getSessionsTaskName(associatedTaskID);
                String startTime = rs.getString("startTime");                
                String finishTime = rs.getString("FinishTime");
                Session session = new Session(sessionID, associatedUserID, associatedUserName, associatedTaskID, associatedTaskName, startTime, finishTime);
                allSessionsOfAProject.add(session);
            }  
        }   
        return allSessionsOfAProject;
    }
        
    /**
     *
     * @param editedSession
     * @param associatedUserID
     * @param associatedTaskID
     * @param startTime
     * @param finishTime
     * @return
     */
    public Session editSession (Session editedSession, int associatedUserID, int associatedTaskID, String startTime, String finishTime) { 
    //  Edits a Session in the Session table of the database given the Sessions new details.  
        int sessionID = editedSession.getSessionID();
        String sql = "UPDATE Sessions SET associatedUser = ?, associatedTask = ?, startTime = ?, finishTime = ? WHERE id = '" + sessionID + "'";
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

    /**
     *
     * @param sessionToDelete
     */
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
     
    /**
     *
     * @param associatedUserID
     * @return
     * @throws SQLException
     */
    public String getSessionsUserName(int associatedUserID) throws SQLException {
        UserDBDAO userDBDao = new UserDBDAO();
        String associatedUserName = userDBDao.getUserName(associatedUserID);
        return associatedUserName;
    }
    
    /**
     *
     * @param associatedTaskID
     * @return
     * @throws SQLException
     */
    public String getSessionsTaskName(int associatedTaskID) throws SQLException {
        TaskDBDAO taskDBDao = new TaskDBDAO();
        String associatedTaskName = taskDBDao.getTaskName(associatedTaskID);
        return associatedTaskName;
    }
    
     
    private void setSessionsLDTs(Session session) {  // Maybe not need
        LocalDateTime startLDT = timeUtilities.stringToLocalDateTime(session.getStartTime());
        session.setStartLDT(startLDT);  //  Sets startLDT (LocalDateTime) of Session to LDT version of startTime
        LocalDateTime finishLDT = timeUtilities.stringToLocalDateTime(session.getFinishTime());
        session.setFinishLDT(finishLDT);  //  Sets finishLDT (LocalDateTime) of Session to LDT version of finishTime     
    }
    
    
    private void setSessionsDuration(Session session) {  // Maybe not need
 System.out.println("setSessionsDuration");
    String startTime = session.getStartTime();
    String finishTime = session.getFinishTime();
    int duration = calculateDurationOfASession(startTime, finishTime);
      session.setDuration(duration);
 //System.out.println("Session id = " + session.getSessionID() + "   Session Duration = " + session.getDuration());
    }
    
    /**
     *
     * @param startTime
     * @param finishTime
     * @return
     */
    public int calculateDurationOfASession(String startTime, String finishTime) {
    //  Calculates the number od minutesi n a Session.    
        LocalDateTime startLDT = timeUtilities.stringToLocalDateTime(startTime);
        LocalDateTime finishLDT = timeUtilities.stringToLocalDateTime(finishTime);
        int duration = (int) ChronoUnit.MINUTES.between(startLDT, finishLDT);  //  https://stackoverflow.com/questions/25747499/java-8-difference-between-two-localdatetime-in-multiple-units#26954864
        //  Round duration up to th nearest 15 mins
        int quarters = (int)duration / 15;
        int mins = duration % 15;
        if (mins > 0)
            quarters ++;
        duration = (quarters * 15);
        return duration;
    }
 
    /**
     *
     * @param taskID
     * @return
     * @throws SQLException
     */
    public Task returnTotalTaskMinutesAndDevelopers(int taskID) throws SQLException {
    // Returns the total minutes of a given Task and it's Developers
        String developers = "";
        int taskDuration = 0;
        List<Session> allSessionsInATask = getAllSessionsOfATask(taskID);
        for (int i = 0; i < allSessionsInATask.size(); i++) {
            Session session = allSessionsInATask.get(i);
            String startTime = session.getStartTime();
            String finishTime = session.getFinishTime();
            int sessionDuration = calculateDurationOfASession(startTime, finishTime);
            int updatedDuration = taskDuration + sessionDuration;
            taskDuration = updatedDuration;
            int AssociatedUserID = session.getAssociatedUserID();
            String associatedUserName = getSessionsUserName(AssociatedUserID);
            developers = compileDevelopersList(associatedUserName, developers);
        }
        Task task = new Task(taskDuration, developers);
        return task;
    }    
 
    
    private String compileDevelopersList(String associatedUserName, String developers) {
    //  Returns the String value of all of the developers that worked on a Task
        if (!(developers.contains(associatedUserName))) {                                       //  https://www.javatpoint.com/java-string-contains
            developers +=  associatedUserName + ", " ;
        }
        return developers;
    }

    /**
     *
     * @param userID
     * @param taskID
     * @return
     * @throws SQLException
     */
    public int calculateUsersTaskMinutes(int userID, int taskID) throws SQLException{
    // Returns the total minutes a given User has spent dong a Task  
        int usersTaskMinutes = 0;
        
 String sql = "SELECT startTime, finishTime FROM Sessions WHERE associatedUser = '" + userID + "'AND associatedTask = '" + taskID + "'";
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) // While you have something in the results
            {
                String startTime = rs.getString("startTime");
                String finishTime = rs.getString("finishTime");
                int sessionDuration = calculateDurationOfASession(startTime, finishTime);
                usersTaskMinutes += sessionDuration; 
            }    
        }
System.out.println("TaskID = " + taskID + "   USERs TASK MINUTES = " + usersTaskMinutes);
        return usersTaskMinutes;
    }
  
    /**
     *
     * @param loggedInUser
     * @return
     * @throws SQLException
     */
    public List<Session> getAllLoggedInUsersSessionsStartTimseAndTaskIDs(User loggedInUser) throws SQLException {  // UNUSED??
    
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
                LocalDateTime startLDT = timeUtilities.stringToLocalDateTime(startTime);
                Session loggedInUserSession = new Session(associatedTaskID, startLDT);
                allLoggedInUserSessions.add(loggedInUserSession); 
            }    
        }
        System.out.println(" Sorting");
         Collections.sort(allLoggedInUserSessions, Collections.reverseOrder());  // https://beginnersbook.com/2013/12/sort-arraylist-in-descending-order-in-java/
          for (int i = 0; i < allLoggedInUserSessions.size(); i++) {
            Session session = allLoggedInUserSessions.get(i);
              System.out.println("");
               System.out.println(session.getStartTime());
              
        }
  
        return allLoggedInUserSessions ;
    }

}
