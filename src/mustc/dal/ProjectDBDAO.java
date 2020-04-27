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

/**
 *
 * @author Trigger, Filip, Cecillia and Alan
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
    //  Adds a new Project to the DB, and returns the updated Project to the GUI
        String clientName = clientDBDao.getSpecificClient(associatedClientID).getName();
        String myHours = "0";
        String totalHours = "0";
        String totalPrice = "0";
        
        List<Task> emptyTaskList = new ArrayList<>();
        emptyTaskList = null;
        boolean isClosed = false;  //  New Project is open by default
        int closed = 0;
        Project newProject = new Project(0, projectName, associatedClientID, clientName, phoneNr, projectRate, allocatedHours, myHours, totalHours, totalPrice, emptyTaskList, isClosed);
       
        String sql = "INSERT INTO Projects(projectName, associatedClientID, phoneNr, projectRate, hoursAllocated, closed) VALUES (?,?,?,?,?,?)";
                       
        try (Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, projectName);
            pstmt.setInt(2, associatedClientID);
            pstmt.setInt(3, phoneNr);
            pstmt.setFloat(4, projectRate);
            pstmt.setInt(5, allocatedHours);                  
 //           int closed = 0;  //   //  New Project is open by default
            pstmt.setInt(6, closed);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Project failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newProject.setProjectId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating Project failed, no ID obtained.");
                } 
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        int projectForAdminID = newProject.getProjectId();
        Project projectForAdmin = new Project(projectForAdminID, projectName, associatedClientID, clientName, phoneNr, projectRate, allocatedHours, totalHours, totalPrice, emptyTaskList, isClosed);
        return projectForAdmin;
    }
    
    
    public Project getProjectForUser(int projectID) throws SQLException {
    //  Returns a spacific Project data object given the Project id
        Project projectForUser = null;
        String projectName = "Error";
        int associatedClientID = -1;
        String clientName;
        int phoneNr = 00000000;
        List<Task> taskList = new ArrayList<>(); //  Tasks here only contain id, name and projectID
        String sql = "SELECT name, associatedClient, projectRate FROM Projects WHERE id ='" + projectID + "'";
        try(Connection con = dbc.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) //While you have something in the results
            {
                projectName = rs.getString("name");
                associatedClientID = rs.getInt("associatedClient");
                phoneNr = rs.getInt("phoneNr");
            }    
        }
        clientName = clientDBDao.getSpecificClient(associatedClientID).getName();
        int loggedInUserId;  // TO GET FETCHED LATER
        loggedInUserId = -1;  // TO BE CALCULATED
        float myHoursAsFloat = getMyHoursForAProject(loggedInUserId, projectID);
        String myHours = Float.toString(myHoursAsFloat);
        taskList = taskDBDao.getAllTaskIDsAndNamesOfAProject(projectID);
        projectForUser = new Project(projectID, projectName, associatedClientID, clientName, phoneNr, myHours, taskList); 
        return projectForUser;
    }   
    
     
    public Project getProjectForAdmin(int projectID) throws SQLException {
    //  Returns a spacific Project data object given the Project id
        Project project = null;
        String projectName = "Error";
        int associatedClientID = -1;
        String clientName;
        int phoneNr = 00000000;
        Float projectRate = 0f;
        int allocatedHours = 0;
        String totalHours = "0";
        String totalPrice = "0";
        List<Task> taskList = new ArrayList<>(); //  Tasks here only contain id, name and projectID
        boolean isClosed = false;

        String sql = "SELECT name, associatedClient, projectRate FROM Projects WHERE id ='" + projectID + "'";
        try(Connection con = dbc.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) //While you have something in the results
            {
                projectName = rs.getString("name");
                associatedClientID = rs.getInt("associatedClient");
                projectRate = rs.getFloat("projectRate");
                phoneNr = rs.getInt("phoneNr");
                int closed = rs.getInt("closed");
                isClosed = false;
                if(closed == 1)
                isClosed = true;
            }    
        }
        clientName = clientDBDao.getSpecificClient(associatedClientID).getName();
        int loggedInUserId;  // TO GET FETCHED LATER
        loggedInUserId = -1;  // TO BE CALCULATED
        taskList = taskDBDao.getAllTaskIDsAndNamesOfAProject(projectID);
        Project projectForAdmin = new Project(projectID, projectName, associatedClientID, clientName, phoneNr, projectRate, allocatedHours, totalHours, totalPrice, taskList, isClosed);
        return projectForAdmin;
    }   
    
     
    private float getMyHoursForAProject(int user, int projectID) {
    float myHours = -1;  // METHOD NOT WRITTEN   
    return myHours;   
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

    public Project editProject (Project editedProject, String projectName, int associatedClientID, float projectRate, int allocatedHours, boolean isClosed) { 
    //  Edits a Project in the Projects table of the database given the Projects new details.  
        String sql = "UPDATE Projects SET name = ?, projectRate = ?, allocatedHours = ?, closed = ? WHERE email = ?";
        try ( Connection con = dbc.getConnection()) {
            //Create a prepared statement.
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setString(1, projectName);
            pstmt.setInt(2, associatedClientID);
            pstmt.setFloat(3, projectRate);
            pstmt.setInt(4, allocatedHours);
            int closed = 0;
            if(isClosed == true)
                closed = 1;
            pstmt.setInt(5, closed);
            pstmt.executeUpdate();  //Execute SQL query.
            editedProject.setProjectName(projectName);
            editedProject.setAssociatedClient(associatedClientID);  
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
      
      
}
