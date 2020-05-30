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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import mustc.be.Project;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;

/**
 *
 * @author Trigger and Alan
 */
public class TaskDBDAO {
    private DBConnection dbc;
    private SessionDBDAO sessionDBDao;    
    public TaskDBDAO() {
            dbc = new DBConnection();
            sessionDBDao = new SessionDBDAO();  // DANGEROUS??
    }        
    
   
            
    public Task addNewTaskToDB(String taskName, int associatedProjectID, boolean isBillable) throws SQLException { 
    //  Adds a new Task to the DB, and returns the updated Project to the GUI
        Project associatedProjectNameAndRate = getProjectNameAndProjectRate(associatedProjectID);
        String projectName = associatedProjectNameAndRate.getProjectName();
        float projectRate = associatedProjectNameAndRate.getProjectRate();
        int usersTaskMinutes = 0;
        int totalTaskMinutes = 0; 
        String developers = "";    // ADD TO DB?
        List<Session> emptySessionList = new ArrayList<>();
        emptySessionList = null;
        Task newTask = new Task(0, taskName, associatedProjectID, projectName, projectRate, usersTaskMinutes, totalTaskMinutes, developers, emptySessionList, isBillable);
        try (Connection con = dbc.getConnection()) {
            String sql = "INSERT INTO Tasks(name ,associatedProject, description) VALUES (?,?,?)";  // String description needs to be replaced in DB with int billable
            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, taskName);
            pstmt.setInt(2, associatedProjectID);
            int billable = convertBooleanToInt(isBillable);
            pstmt.setInt(3, billable);
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
        int LoggedInUserID = 1;  //  MOCK DATA
        String sql = "SELECT * FROM Tasks WHERE id = '" + taskID + "'"; 
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
           ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                String taskName =  rs.getString("name");
                int associatedProjectID = rs.getInt("associatedProject");
                Project associatedProjectNameAndRate = getProjectNameAndProjectRate(associatedProjectID);
                String projectName = associatedProjectNameAndRate.getProjectName();
 //               float projectRate = associatedProjectNameAndRate.getProjectRate();
                int usersTaskMinutes = sessionDBDao.calculateUsersTaskMinutes(LoggedInUserID, taskID);
                String developers = "";  // TO BE DONE
                taskInProject = new Task(taskID, taskName, associatedProjectID, projectName, usersTaskMinutes, developers);       
            }    
        }
        return taskInProject ;
    }
    
    public List<Task> getAllTasksForUser() throws SQLException {
    //  Returns a list of Tasks from the DB for a User
    List<Task> allTasksForAdmin = new ArrayList<>();
        int LoggedInUserID = 1;  //  MOCK DATA
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT id, name, associatedProject FROM Tasks";
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                int taskID = rs.getInt("id");
                String taskName = rs.getString("name");
                int associatedProjectID = rs.getInt("associatedProject");
                Project associatedProjectNameAndRate = getProjectNameAndProjectRate(associatedProjectID);
                String projectName = associatedProjectNameAndRate.getProjectName();
 //               float projectRate = associatedProjectNameAndRate.getProjectRate();
                int usersTaskMinutes = sessionDBDao.calculateUsersTaskMinutes(LoggedInUserID, taskID);
                String developers = "Bob, Sue";  // MOCK DATA
                Task taskForAdmin = new Task(taskID, taskName, associatedProjectID, projectName, usersTaskMinutes, developers);
                allTasksForAdmin.add(taskForAdmin);
            }    
        }
       return allTasksForAdmin; 
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
                Project associatedProjectNameAndRate = getProjectNameAndProjectRate(associatedProjectID);
                String projectName = associatedProjectNameAndRate.getProjectName();
                float projectRate = associatedProjectNameAndRate.getProjectRate();
                //List<Session> allSessionsOfATask = sessionDBDao.getAllSessionsOfATask(taskID);
                Task task =  sessionDBDao.returnTotalTaskMinutesAndDevelopers(taskID);  //  REPLACE WITH MODEL LATER
                int totalTaskMinutes = task.getTotalTaskMinutes();
                String developers = "Gus, John, BumbleWeed";  // MOCK DATA
                int billable = rs.getInt("description");  // String description needs to be replaced in DB with int billable 
                boolean isBillable = convertIntToBoolean(billable);
                taskInProject = new Task(taskID, taskName, associatedProjectID, projectName, projectRate, totalTaskMinutes, developers, isBillable);       
            }    
        }
        return taskInProject ;
    }
      
    
    public List<Task> getAllTasksForAdmin() throws SQLException {
    //  Returns a list of Tasks from the DB for an Admin
        List<Task> allTasksForAdmin = new ArrayList<>();
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT id, name, description, associatedProject FROM Tasks";
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                int taskID = rs.getInt("id");
                String taskName = rs.getString("name");
                int associatedProjectID = rs.getInt("associatedProject");
                Project associatedProjectNameAndRate = getProjectNameAndProjectRate(associatedProjectID);
                String projectName = associatedProjectNameAndRate.getProjectName();
                float projectRate = associatedProjectNameAndRate.getProjectRate();
                Task task =  sessionDBDao.returnTotalTaskMinutesAndDevelopers(taskID);  //  REPLACE WITH MODEL LATER
                int totalTaskMinutes = task.getTotalTaskMinutes();
                String developers = task.getDevelopers();  //"Bob, Sue";
                String billableSTR = rs.getString("Description");
                int billable = Integer.parseInt(billableSTR);    /*rs.getInt("description");*/  // String description needs to be replaced in DB with int billable 
                boolean isBillable = convertIntToBoolean(billable);
                Task taskForAdmin = new Task(taskID, taskName, associatedProjectID, projectName, projectRate, totalTaskMinutes, developers, isBillable);
                allTasksForAdmin.add(taskForAdmin);
            }    
        }
       return allTasksForAdmin; 
    }
     
    
    public List<Task> getAllTasksOfAProject(int projectID) throws SQLException {
        List<Task> allTasksOfAProject = new ArrayList<>();
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT * FROM Tasks WHERE associatedProject = '" + projectID + "'";
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                int taskID = rs.getInt("id");
                String taskName = rs.getString("name");
                int associatedProjectID = rs.getInt("associatedProject");
                Project associatedProjectNameAndRate = getProjectNameAndProjectRate(associatedProjectID);
                String projectName = associatedProjectNameAndRate.getProjectName();
                float projectRate = associatedProjectNameAndRate.getProjectRate();
                Task task = sessionDBDao.returnTotalTaskMinutesAndDevelopers(taskID);  //  REPLACE WITH MODEL LATER
                int totalTaskMinutes = task.getTotalTaskMinutes();
                //        totalBillableProjectMinutes += totalTaskHours;
                String developers = task.getDevelopers();  //"Bob, Sue";
                int billable = rs.getInt("description");  // String description needs to be replaced in DB with int billable   
                boolean isBillable = convertIntToBoolean(billable);
 System.out.println("taskID: " + taskID + "   totalTaskHours:  " + totalTaskMinutes + "   isBillable: " + isBillable);               
               Task taskOfAProject = new Task(taskID, taskName, associatedProjectID, projectName, projectRate, totalTaskMinutes, developers, isBillable);
                allTasksOfAProject.add(taskOfAProject);
            }    
        }
        return allTasksOfAProject;
    }
    
    
    public List<Task> getAllTaskIDsAndNamesOfAProject(int projectID) throws SQLException {
        List<Task> allTaskIDsAndNamesOfAProject = new ArrayList<>();
        String sql;
        if(projectID == -1) { 
            sql = "SELECT id, name FROM Tasks";
                    allTaskIDsAndNamesOfAProject.add(new Task(-1, "All Tasks", -1));// -1 used to determine the use of the Report constructor
        } else {
            sql = "SELECT id, name FROM Tasks WHERE associatedProject = '" + projectID + "'";
                    allTaskIDsAndNamesOfAProject.add(new Task(-2, "All Project Tasks", -1));// -1 used to determine the use of the Report constructor
        }
        try(Connection con = dbc.getConnection()){
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                int taskID = rs.getInt("id");
                String taskName = rs.getString("name");
               Task taskOfAProject = new Task(taskID, taskName, -1); // -1 used to determine the use of the Report constructor
                allTaskIDsAndNamesOfAProject.add(taskOfAProject);
            }    
        }
        return allTaskIDsAndNamesOfAProject;
    } 
    
    
    public Task getTaskForReport(int taskID) throws SQLException {
    //  Returns a Task from the DB where ID = taskID
        Task taskInProject = null;
        String sql = "SELECT name, associatedProject, description FROM Tasks  WHERE id = '" + taskID + "'"; 
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                String taskName =  rs.getString("name");
                int associatedProjectID = rs.getInt("associatedProject");
                int billable = rs.getInt("description");  // String description needs to be replaced in DB with int billable 
                boolean isBillable = convertIntToBoolean(billable);
                taskInProject = new Task(taskName, associatedProjectID, isBillable);       
            }    
        }
        return taskInProject ;
    }
      
      
    public int[] getTotalMinutesOfAProject(int projectID) throws SQLException {
        int[] totalProjectMinutes = new int[2];
        totalProjectMinutes[0] = 0;
        totalProjectMinutes[1] = 0;
        int totalBillableProjectMinutes = 0;
        int totalUnbillableProjectHours = 0;
        int totalTaskMinutes;
        List<Task> allTasksOfAProject = getAllTasksOfAProject(projectID);  //new ArrayList<>();
        for (int i = 0; i < allTasksOfAProject.size(); i++) {
            Task task = allTasksOfAProject.get(i);
            int taskID = task.getTaskID();
   //NEW 2 lines         
            Task taskMinutesAndDevs =  sessionDBDao.returnTotalTaskMinutesAndDevelopers(taskID);  //  REPLACE WITH MODEL LATER
            totalTaskMinutes = taskMinutesAndDevs.getTotalTaskMinutes();
            
System.out.println("Task: " + taskID + "   TotalTaskHours: " + totalTaskMinutes);    
            if (task.getIsBillable() == true) {
                totalBillableProjectMinutes += totalTaskMinutes;
            } else {
                totalUnbillableProjectHours += totalTaskMinutes;
            }
        }
System.out.println("totalBillableProjectMinutes: " + totalBillableProjectMinutes);    
System.out.println("totalUnbillableProjectHours: " + totalUnbillableProjectHours);    
        totalProjectMinutes[0] = totalUnbillableProjectHours;
        totalProjectMinutes[1] = totalBillableProjectMinutes;
        return totalProjectMinutes;
    }
    
  
    public List<Task> getUsersThreeRecentTasks(User loggedInUser) throws SQLException {
    //  Returns a list of maximum size 3 of the three most recent distinct Tasks of the loggedInUser
        List<Task> recentTasks = new ArrayList<>();
        int recentTask1ID = -1;  // Initialiser value - not a real taskID        
        int recentTask2ID = -1;  // Initialiser value - not a real taskID   
        int recentTask3ID = -1;  // Initialiser value - not a real taskID   
System.out.println("getUsersThreeRecentTasks = "); 
        
        List<Session> allLoggedInUserSessions = sessionDBDao.getAllLoggedInUsersSessionsStartTimseAndTaskIDs(loggedInUser);
        //  Get recentTask1
        if (allLoggedInUserSessions.size() > 0) {
            Session recentSession1 =  allLoggedInUserSessions.get(0);
            recentTask1ID = recentSession1.getAssociatedTaskID();
System.out.println("recentTask1ID = " + recentTask1ID); 
            Task recentTask1 = getTaskForUser(recentTask1ID);  // makes recentTask1 the the first Task from recentSession list 
            recentTasks.add(recentTask1);
        }
        int counter = 1;  // counter keeps track of the session being examines for duplicate TaskIDs
        //  Get recentTask2
        while (counter < allLoggedInUserSessions.size()) {
            Session recentSession2 =  allLoggedInUserSessions.get(counter);
            if (recentSession2.getAssociatedTaskID() != recentTask1ID) {
                recentTask2ID = recentSession2.getAssociatedTaskID();
System.out.println("recentTask2ID = " + recentTask2ID); 
                Task recentTask2 = getTaskForUser(recentTask2ID);  // makes recentTask2 the the second distinct Task from recentSession list                 
                recentTasks.add(recentTask2);
                break;
            } else counter++;
        }
        //  Get recentTask3          
        while (counter < allLoggedInUserSessions.size()) {
            Session recentSession3 =  allLoggedInUserSessions.get(counter);
            if ((recentSession3.getAssociatedTaskID() != recentTask1ID) && (recentSession3.getAssociatedTaskID() != recentTask2ID)) {
                recentTask3ID = recentSession3.getAssociatedTaskID();
System.out.println("recentTask3ID = " + recentTask3ID); 
                Task recentTask3 = getTaskForUser(recentTask3ID);  // makes recentTask2 the the second distinct Task from recentSession list                 
                recentTasks.add(recentTask3);
                break;
            } else counter++;
        }
        return recentTasks;
    }

    
     public String getTaskName(int taskID) throws SQLException {  //  USED??
        //  Returns a User data object given a User id
        String taskName = "mock";
        String sql = "SELECT name FROM Tasks WHERE id = '" + taskID + "'";  //  userName, email, password, salary, isAdmin 
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                taskName = rs.getString("name");
            }    
        }
        return taskName;
    }
    
        
    public Project getProjectNameAndProjectRate(int associatedProjectID) throws SQLException {  // AdminModel 113 and 
    //  Return a projectName and projectRate for a Task
        Project projectNameAndRate = null;
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT  name, projectRate FROM Projects WHERE id = '" + associatedProjectID + "'";
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                String projectName = rs.getString("name");
                float projectRate = rs.getFloat("projectRate");
                projectNameAndRate = new Project(projectName, projectRate);
            }    
        }
        return projectNameAndRate; 
    }
    
        
    public Task editTask (Task editedTask, String taskName, int associatedProjectID, boolean isBillable) { 
    //  Edits a Task in the Task table of the database given the Projects new details.  
        String sql = "UPDATE Tasks SET name = ?, associatedProject = ?, description = ? WHERE id = '" + editedTask.getTaskID() + "'";
        try ( Connection con = dbc.getConnection()) {
            //Create a prepared statement.
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setString(1, taskName);
            pstmt.setInt(2, associatedProjectID);
            int billable = convertBooleanToInt(isBillable);
            pstmt.setInt(3, billable);
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
      
     

     
     
//  CONVERTERS
     
    private int convertBooleanToInt(boolean isBillable) {
        int billable = 0;
        if (isBillable == true) {
            billable = 1;
        }
        return billable;
    }
    
            
    private boolean convertIntToBoolean(int billable) {
        boolean isBillable = false;
        if (billable == 1) {
            isBillable = true;
        }
        return isBillable;
    }
      
    
    
    
    
    
    
//  UNUSED CODE
     
     
     
/*    public List<Task> getAllUsersTasks() throws SQLException {
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
*/     
     
     
    /*   
    public List<Task> getAllTaskIDsAndNamesOfAProject(int projectID) throws SQLException {
        List<Task> allTaskIDsAndTaskNamesOfAProject = new ArrayList<>();
        try(Connection con = dbc.getConnection()) {
            String sql = "SELECT id, name FROM Tasks WHERE associatedProject = '" + projectID + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) //While you have something in the results
            {
                int taskID = rs.getInt("id");
                String taskName =  rs.getString("name");
  //              Task taskInProject = new Task(taskID, taskName, null, projectID, null/*, taskDuration);*/
//                allTaskIDsAndNamesOfAProject.add(taskInProject); 
 /*           }    
        }
        return allTaskIDsAndTaskNamesOfAProject ;
    }
  */  
    
   
    

}
