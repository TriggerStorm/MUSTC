/*
 * To change this license header, choose License Headers in ProjectDBDAO Properties.
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
import mustc.be.Client;
import mustc.be.Project;
import mustc.be.Task;
import mustc.be.Session;
/**
 *
 * @author Trigger and Alan
 */
public class ProjectDBDAO {
    private DBConnection dbc;
    private ClientDBDAO clientDBDao;
    private TaskDBDAO taskDBDao;
    
    public ProjectDBDAO() {
        dbc = new DBConnection();
        clientDBDao = new ClientDBDAO();
        taskDBDao = new TaskDBDAO();
    }
    
    
    public Project addNewProjectToDB(String projectName, int associatedClientID , int phoneNr, float projectRate, int allocatedHours) throws SQLException { 
    //  Adds a new Admin created Project to the DB, and returns the updated Admin Project to the GUI
        String clientName = clientDBDao.getClient(associatedClientID).getClientName();
System.out.println(" client Name = " + clientName);        
        int usersProjectMinutes = 0;
        int totalBillableMinutes = 0;
        int totalUnbillableMinutes = 0;
        int totalPrice = 0;
        List<Task> emptyTaskList = new ArrayList<>();
        emptyTaskList = null;
        int noOfTasks = 0;
        boolean isClosed = false;  //  New Project is open by default
        int closed = 0;  //  New Project is open by default
        Project newProject = new Project(0, projectName, associatedClientID, clientName, phoneNr, projectRate, allocatedHours, usersProjectMinutes, totalBillableMinutes, totalUnbillableMinutes, totalPrice, emptyTaskList, noOfTasks, isClosed);
        String sql = "INSERT INTO Projects(name, associatedClient, phoneNr, projectRate, allocatedHours, closed) VALUES (?,?,?,?,?,?)";
        try (Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, projectName);
            pstmt.setInt(2, associatedClientID);
            pstmt.setInt(3, phoneNr);
            pstmt.setFloat(4, projectRate);
            pstmt.setInt(5, allocatedHours);                  
            pstmt.setInt(6, closed);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Project failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newProject.setProjectID((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating Project failed, no ID obtained.");
                } 
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        int adminProjectID = newProject.getProjectID();
        Project adminProject = new Project(adminProjectID, projectName, associatedClientID, clientName, phoneNr, projectRate, allocatedHours, totalBillableMinutes, totalUnbillableMinutes, totalPrice, emptyTaskList, isClosed);
        return adminProject;
    }
    
    
    public Project getProjectForUser(int projectID) throws SQLException {
    //  Returns a Project for a User, given the Project id
        int loggedInUserId = 1;  // MOCK DATA   TO GET FETCHED LATER
        Project projectForUser = null;
        String projectName = "Error";
        int associatedClientID = -1;
        String clientName;
        int phoneNr = 00000000;
        List<Task> taskList = new ArrayList<>(); //  Tasks here only contain id, name and projectID
        String sql = "SELECT name, associatedClient, phoneNr FROM Projects WHERE id = '" + projectID + "'";
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                projectName = rs.getString("name");
                associatedClientID = rs.getInt("associatedClient");
                phoneNr = rs.getInt("phoneNr");
           }    
        }
        clientName = clientDBDao.getClient(associatedClientID).getClientName();
        List<Integer> taskIDlistOfProject = getTaskIDListForAProject(projectID);
        int usersProjectMinutes = calculateUsersProjectMinutes(taskIDlistOfProject);
        taskList = null;  // may use method below
        //taskDBDao.getAllTaskIDsAndNamesOfAProject(projectID);
        projectForUser = new Project(projectID, projectName, associatedClientID, clientName, phoneNr, usersProjectMinutes, taskList); 
        return projectForUser;
    }   
    
     
    public List<Project> getAllProjectsForUser() throws SQLException {
        int loggedInUserId = 1;  // MOCK DATA   TO GET FETCHED LATER
        List<Project> allProjectsForAUser = new ArrayList<>();
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT id, name, associatedClient, phoneNr FROM Projects";
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                int projectID =  rs.getInt("id");
                String projectName = rs.getString("name");
                int associatedClientID =rs.getInt("associatedClient");
                String clientName = clientDBDao.getClient(associatedClientID).getClientName() ;
                int phoneNr = rs.getInt("phoneNr");
                List<Integer> taskIDlistOfProject = getTaskIDListForAProject(projectID);
                int noOfTasks = taskIDlistOfProject.size();  //  NEW
                
                int usersProjectMinutes = calculateUsersProjectMinutes(taskIDlistOfProject);
                Project projectForUser = new Project(projectID, projectName, clientName, phoneNr, usersProjectMinutes, noOfTasks);
                projectForUser.setNoOfTasks(noOfTasks);
                allProjectsForAUser.add(projectForUser);
            }    
        }
        return allProjectsForAUser; 
    }
    
    
    public Project getProjectForAdmin(int projectID) throws SQLException {
    //  Returns a Project for an Admin, given the Project id
        Project projectForAdmin = null;
        String projectName = "Error";
        int associatedClientID = -1;
        String clientName;
        int phoneNr = 00000000;
        Float projectRate = 0f;
        int allocatedHours = 0;
        int totalBillableMinutes = 111;  // mock data
        int totalUnbillableMinutes = 222;  // mock data
        int totalPrice = 77777;  // mock data
        List<Task> taskList = new ArrayList<>(); //  Tasks here only contain id, name and projectID
        boolean isClosed = false;
        try(Connection con = dbc.getConnection()) {
        String sql = "SELECT name, associatedClient, phoneNr, projectRate, allocatedHours, closed FROM Projects WHERE id = '" + projectID + "'";
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                projectName = rs.getString("name");
                associatedClientID = rs.getInt("associatedClient");
                phoneNr = rs.getInt("phoneNr");
                projectRate = rs.getFloat("projectRate");
                allocatedHours = rs.getInt("allocatedHours");
                int closed = rs.getInt("closed");
                isClosed = false;
                if(closed == 1)
                isClosed = true;
            }    
        }
        clientName = clientDBDao.getClient(associatedClientID).getClientName();
        int loggedInUserId;  // TO GET FETCHED LATER
        loggedInUserId = -1;  // TO BE CALCULATED
        taskList = null;  //may use method below
        //taskList = taskDBDao.getAllTaskIDsAndNamesOfAProject(projectID);
        projectForAdmin = new Project(projectID, projectName, associatedClientID, clientName, phoneNr, projectRate, allocatedHours, totalBillableMinutes, totalUnbillableMinutes, totalPrice, taskList, isClosed);
        return projectForAdmin;
    }   
    
     
    public List<Project> getAllProjectsForAdmin() throws SQLException {
        List<Project> allProjectsForAdmin = new ArrayList<>();
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT id, name, associatedClient, phoneNr, projectRate FROM Projects";
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                int projectID =  rs.getInt("id");
                String projectName = rs.getString("name");
                int associatedClientID =rs.getInt("associatedClient");
                String clientName = clientDBDao.getClient(associatedClientID).getClientName() ;
                int phoneNr = rs.getInt("phoneNr");
                float projectRate = rs.getFloat("projectRate");
                int[] totalMinutesOfAProject = taskDBDao.getTotalMinutesOfAProject(projectID);
                int totalBillableMinutes = totalMinutesOfAProject[1];
                int totalUnbillableMinutes = totalMinutesOfAProject[0];
                int totalPrice = (int) (totalBillableMinutes * projectRate);
                int noOfTasks = getTaskIDListForAProject(projectID).size();  //  NEW
                Project projectForAdmin = new Project(projectID, projectName, clientName, phoneNr, projectRate, totalBillableMinutes, totalUnbillableMinutes, totalPrice, noOfTasks);
                projectForAdmin.setNoOfTasks(noOfTasks);
                projectForAdmin.setTotalPrice(totalPrice);
                allProjectsForAdmin.add(projectForAdmin);
            }    
        }
        return allProjectsForAdmin; 
    }
    
     public List<Project> getAllProjectsIDsAndNames() throws SQLException {
        List<Project> allProjectsIDsAndNames = new ArrayList<>();
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT id, name FROM Projects";
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                int projectID =  rs.getInt("id");
                String projectName = rs.getString("name");
                Project projectIDandName = new Project(projectID, projectName);
                allProjectsIDsAndNames.add(projectIDandName);
            }    
        }
        return allProjectsIDsAndNames; 
    }
    
    
    private List<Integer> getTaskIDListForAProject(int projectID) throws SQLException {
    // Returns an array of TaskIds in a Project
        List<Integer> taskIDlistOfProject = new ArrayList<>();
  //      taskIDlistOfProject = null;
 //       int projectTaskListCount = 0;
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT id FROM Tasks WHERE associatedProject = '" + projectID + "'";
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                int taskID = rs.getInt("id");
                taskIDlistOfProject.add(taskID);
//                projectTaskListCount ++;
            }    
        }
        return taskIDlistOfProject; 
    }
      
    
   
    
    public String getProjectName(int projectID) throws SQLException {
        return getProjectForUser(projectID).getProjectName();  // NEW BE NEEDED w- id + name
    } 
    
    
    public float getProjectRate(int projectID) throws SQLException {
        return getProjectForAdmin(projectID).getProjectRate();  // NEW BE NEEDED w- id + Rate
    } 
    
 
    public Project editProject (Project editedProject, String projectName, /*int associatedClientID,*/ int phoneNr, float projectRate, int allocatedHours, boolean isClosed) { 
    //  Returns an Admin edited Project in the Projects table of the database, given the Projects new details.  
        int projectID = editedProject.getProjectID();
        String sql = "UPDATE Projects SET name = ?, phoneNr = ?, projectRate = ?, allocatedHours = ?, closed = ? WHERE id = '" + projectID + "'";
        try ( Connection con = dbc.getConnection()) {
            //Create a prepared statement.
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setString(1, projectName);
 //           pstmt.setInt(2, associatedClientID);  // may edit client later
            pstmt.setInt(2, phoneNr);
            pstmt.setFloat(3, projectRate);
            pstmt.setInt(4, allocatedHours);
            int closed = 0;
            if(isClosed == true)
                closed = 1;
            pstmt.setInt(5, closed);
            pstmt.executeUpdate();  //Execute SQL query.
            editedProject.setProjectName(projectName);
//            editedProject.setAssociatedClientID(associatedClientID); 
            editedProject.setPhoneNr(phoneNr);
            editedProject.setProjectRate(projectRate);
            editedProject.setAllocatedHours(allocatedHours);
            editedProject.setClosed(isClosed);
            return editedProject;
        } catch (SQLServerException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;  //  editedProject?
    }
      
      
    public void removeProjectFromDB(Project projectToDelete) {
    //  Removes a session from the Session table of the database given a Session data object
        String sql = "DELETE FROM Projects WHERE id = ?";
        try (Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,projectToDelete.getProjectID());
            pstmt.execute();
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    }




//  CALCULATORS
    
    
    private int calculateUsersProjectMinutes(List<Integer> taskIDlistOfProject) throws SQLException {
        int loggedInUserId = 1;  //  MOCK DATA
        int usersProjectMinutes = 0;
        SessionDBDAO sessionDBDAO = new SessionDBDAO();
        for (int i = 0; i < taskIDlistOfProject.size(); i++) {
            int taskID = taskIDlistOfProject.get(i);
            int usersTaskMinutes = sessionDBDAO.calculateUsersTaskMinutes(loggedInUserId, taskID);
            usersProjectMinutes += usersTaskMinutes;
            System.out.println("TASK = " + taskID + "   usersProjectMinutes = " + usersProjectMinutes);
        }
        return usersProjectMinutes;
    }
       
    
 /*   public List<Project> getAllProjectIDsAndNamesOfAClient(int clientID) throws SQLException {
    //  Returns all Projects for all Client   
        List<Project> allProjectIDsAndNamesOfAClient = new ArrayList<>();
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT id AND name FROM Projects WHERE associatedClient = '" + clientID + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) //While you have something in the results
            {
                int projectID =  rs.getInt("id");
                String projectName = rs.getString("name");
                allProjectIDsAndNamesOfAClient.add(new Project(projectID, projectName, 0, 0, 0, 0, null, false)); 
            }    
        }
        return allProjectIDsAndNamesOfAClient;
    }
*/



}
