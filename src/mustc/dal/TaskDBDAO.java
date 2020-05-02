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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mustc.be.Project;
import mustc.be.Session;
import mustc.be.Task;

/**
 *
 * @author Trigger and Alan
 */
public class TaskDBDAO {
    private DBConnection dbc;
    private SessionDBDAO sessionDBDao;    
    public TaskDBDAO() {
            dbc = new DBConnection();
            sessionDBDao = new SessionDBDAO();
    }        

    public Task addNewTaskToDB(String taskName, int associatedProjectID) throws SQLException { 
    //  Adds a new Task to the DB, and returns the updated Project to the GUI
    ProjectDBDAO projectDBDao = new ProjectDBDAO();
    String projectName = projectDBDao.getProjectName(associatedProjectID);  // ADD TO DB
    float projectRate = projectDBDao.getProjectRate(associatedProjectID);  // TEMO
        double myTaskHours = 0;
        double totalTaskHours = 345.50;    // ADD TO DB
        String developers = "";    // ADD TO DB
        List<Session> emptySessionList = new ArrayList<>();
        emptySessionList = null;
 //       int[] taskDuration = new int[2];
 //       taskDuration[0] = 0;  // set taskDuration hours to 0
 //       taskDuration[1] = 0;  // set taskDuration minutes to 0
        Task newTask = new Task(0, taskName, associatedProjectID, projectName, projectRate, myTaskHours, totalTaskHours, developers, emptySessionList);
        try (Connection con = dbc.getConnection()) {
            String sql = "INSERT INTO Tasks(name, associatedProject) VALUES (?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, taskName);
            pstmt.setInt(2, associatedProjectID);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Task failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newTask.setTaskId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating Task failed, no ID obtained.");
                } 
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newTask;
    }
     
    
    public Task getTaskForUser(int taskID) throws SQLException {
    //  Returns a Task from the DB where ID = taskID
        Task taskInProject = null;
//        int [] taskDuration = new int[2];
        String sql = "SELECT * FROM Tasks WHERE id = '" + taskID + "'"; 
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
           ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                String taskName =  rs.getString("name");
                int associatedProjectID = rs.getInt("associatedProject");
//                taskDuration[0] = rs.getInt("durationHours");
//                taskDuration[1] = rs.getInt("durationMinutes");
//  { To BE REPLACED BY DB
                ProjectDBDAO projectDBDao = new ProjectDBDAO();
                String projectName = projectDBDao.getProjectName(associatedProjectID);
 /* } */            double myTaskHours = 45.25;  // MOCK DATA 
                String developers = "";  // TO BE DONE
                taskInProject = new Task(taskID, taskName, associatedProjectID, projectName, myTaskHours, developers/*, taskDuration*/);       
            }    
        }
        return taskInProject ;
    }
    
     
    public List<Task> getAllUsersTasks() throws SQLException {
        List<Task> allUsersTasks = new ArrayList<>();
        int loggedInUserID = 3;   // MOCK DATA
        try(Connection con = dbc.getConnection()){
               String sql = "SELECT id, name, associatedProject FROM Tasks INNER JOIN Tasks.Users.id ="; // work in progress
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                int taskID =  rs.getInt("id");
                String taskName = rs.getString("name");
                int associatedProjectID =rs.getInt("associatedProject");
                ProjectDBDAO projectDBDao= new ProjectDBDAO();  // TEMP
                String projectName = projectDBDao.getProjectName(associatedProjectID) ;  // TEMP 
                double myTaskHours = 707.75;  // MOCK DATA
                String developers = "Mike, Karen";  // MOCK DATA
                Task taskForUser = new Task(taskID, taskName, associatedProjectID, projectName, myTaskHours, developers);
                allUsersTasks.add(taskForUser);
            }    
        }
        return allUsersTasks; 
    }
     
     
    public Task getTaskForAdmin(int taskID) throws SQLException {
    //  Returns a Task from the DB where ID = taskID
        Task taskInProject = null;
//        int [] taskDuration = new int[2];
        String sql = "SELECT * FROM Tasks"; 
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                String taskName =  rs.getString("name");
                int associatedProjectID = rs.getInt("associatedProject");
                ProjectDBDAO projectDBDao= new ProjectDBDAO();  // TEMP
                String projectName = projectDBDao.getProjectName(associatedProjectID) ;  // TEMP
                float projectRate = projectDBDao.getProjectRate(associatedProjectID);  // TEMP               List<Session> allSessionsOfATask = sessionDBDao.getAllSessionsOfATask(taskID);
                String developers = "Gus, John, BumbleWeed";  // MOCK DATA
                taskInProject = new Task(taskID, taskName, associatedProjectID, projectName, projectRate, developers);       
            }    
        }
        return taskInProject ;
    }
      
     public List<Task> getAllTasksForAdmin() throws SQLException {
        List<Task> allTasksForAdmin = new ArrayList<>();
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT id, name, associatedProject FROM Tasks";
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                int taskID =  rs.getInt("id");
                String taskName = rs.getString("name");
                int associatedProjectID =rs.getInt("associatedProject");
                ProjectDBDAO projectDBDao= new ProjectDBDAO();  // TEMP
                String projectName = projectDBDao.getProjectName(associatedProjectID) ;  // TEMP
                float projectRate = projectDBDao.getProjectRate(associatedProjectID);  // TEMP
                double totalTaskHours = 667.75;  // MOCK DATA
                String developers = "Bob, Sue";
                Task taskForAdmin = new Task(taskID, taskName, associatedProjectID, projectName, projectRate, totalTaskHours, developers);
                allTasksForAdmin.add(taskForAdmin);
            }    
        }
       return allTasksForAdmin; 
    }
     
     
/*    public List<Task> getAllTaskIDsAndNamesOfAProject(int projectID) throws SQLException {
        List<Task> allTaskIDsAndNamesOfAProject = new ArrayList<>();
        int [] taskDuration = new int[2];
        try(Connection con = dbc.getConnection()) {
            String sql = "SELECT id, name FROM Tasks WHERE associatedProject = '" + projectID + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) //While you have something in the results
            {
                int taskID = rs.getInt("id");
                String taskName =  rs.getString("name");
 //               taskDuration[0] = rs.getInt("durationHours");
 //               taskDuration[1] = rs.getInt("durationMinutes");
                Task taskInProject = new Task(taskID, taskName, null, projectID, null/*, taskDuration);*/
/*                allTaskIDsAndNamesOfAProject.add(taskInProject); 
            }    
        }
        return allTaskIDsAndNamesOfAProject ;
    }
 */   
    
    public Task editTask (Task editedTask, String taskName , int associatedProjectID) { 
    //  Edits a Task in the Task table of the database given the Projects new details.  
        String sql = "UPDATE Tasks SET name = ?, associatedProject = ? WHERE id = '" + editedTask.getTaskID() + "'";
        try ( Connection con = dbc.getConnection()) {
            //Create a prepared statement.
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setString(1, taskName);
            pstmt.setInt(2, associatedProjectID);
            pstmt.executeUpdate();  //Execute SQL query.
            editedTask.setTaskName(taskName);
            editedTask.setAssociatedProjectID(associatedProjectID);
            return editedTask;
        } catch (SQLServerException ex) {
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TaskDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
      
        
    public void removeTaskFromDB(Task taskToDelete) {
    //  Removes a user from the User table of the database given a User data object
        try (Connection con = dbc.getConnection()) {
        //  Delete all Sessions of taskToDelete
            String sql = "DELETE FROM Sessions WHERE associatedTask = ?";//+ taskToDelete.getTaskID() + "'";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,taskToDelete.getTaskID());
            pstmt.execute();
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }      
        try (Connection con = dbc.getConnection()) {
        //  Delete taskToDelete
            String sql = "DELETE FROM Tasks WHERE id = ?" ;//+ taskToDelete.getTaskID() + "'";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,taskToDelete.getTaskID());
            pstmt.execute();
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    }
      
    
}
