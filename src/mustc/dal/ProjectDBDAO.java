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
 * @author admin
 */
public class ProjectDBDAO {
    private DBConnection dbc;
    private TaskDBDAO taskDBDao;
    
    
    public ProjectDBDAO() {
        dbc = new DBConnection();
        taskDBDao = new TaskDBDAO();
    }
    
    
    public Project addNewProjectToDB(String name, int associatedClientID, float projectRate, int hoursAllocated, boolean isClosed) throws SQLException { 
    //  Adds a new project to the DB, and returns the updated projectList to the GUI
        String sql = "INSERT INTO PROJECTS(name, clientName, projectRate, hoursAllocated, closed) VALUES (?,?,?,?,?)";
        List<Task> emptyTaskList = new ArrayList<>();
        emptyTaskList = null;
        Project newProject = new Project(0, name, associatedClientID, projectRate, hoursAllocated, emptyTaskList, isClosed);
        try (Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setInt(2, associatedClientID);
            pstmt.setFloat(3, projectRate);
            pstmt.setInt(4, hoursAllocated);
            int closed = 0;
            if(isClosed == true)
                closed = 1;
            pstmt.setInt(5, closed);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating attendance failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newProject.setId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating attendance failed, no ID obtained.");
                } 
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newProject;
    }
     
     
    public Project getProject(int projectID) throws SQLException {
    //  Returns a spacific Project data object given the Project id
        Project project = null;
        List<Task> taskList = new ArrayList<>(); //  Tasks here only contain id, name and projectID
        taskList = taskDBDao.getAProjectsTaskIDsAndNames(projectID);
        String SQLStmt = "SELECT name, associatedClient, projectRate, hoursAllocated, closed FROM Projects WHERE id ='" + projectID + "'";
        try(Connection con = dbc.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(SQLStmt);
            while(rs.next()) //While you have something in the results
            {
                String projectName = rs.getString("name");
                int associatedClientID = rs.getInt("associatedClient");
                Float projectRate = rs.getFloat("projectRate");
                int hoursAllocated = rs.getInt("hoursAllocated");
                int closed = rs.getInt("closed");
                boolean isClosed = false;
                if(closed == 1)
                isClosed = true;
                project = new Project(projectID, projectName, associatedClientID, projectRate, hoursAllocated, taskList, isClosed); 
            }    
        }
        return project;
    }   
    
    
    public Project editProject (Project editedProject, String name, int associatedClientID, float projectRate, int hoursAllocated, boolean isClosed) { 
    //  Edits a Project in the Projects table of the database given the Projects new details.  
        String sql = "UPDATE Projects SET name = clientName, projectRate = ?, hoursAllocated = ?, closed = ? WHERE email = ?";
        try ( Connection con = dbc.getConnection()) {
            //Create a prepared statement.
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setString(1, name);
            pstmt.setInt(2, associatedClientID);
            pstmt.setFloat(3, projectRate);
            pstmt.setInt(4, hoursAllocated);
            int closed = 0;
            if(isClosed == true)
                closed = 1;
            pstmt.setInt(5, closed);
            pstmt.executeUpdate();  //Execute SQL query.
            editedProject.setName(name);
            editedProject.setAssociatedClient(associatedClientID);  
            editedProject.setProjectRate(projectRate);
            editedProject.setHoursAllocated(hoursAllocated);
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
