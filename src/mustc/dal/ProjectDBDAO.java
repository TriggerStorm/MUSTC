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

    
    public ProjectDBDAO() {
         dbc = new DBConnection();

    }
    
    
     public Project addNewProjectToDB(String name, Client associatedClient, float projectRate, boolean closed) throws SQLException { 
    //  Adds a new project to the DB, and returns the updated projectList to the GUI
        String sql = "INSERT INTO PROJECTS(name, associatedClient, projectRate, closed) VALUES (?,?,?,?)";
        String clientName = associatedClient.getName();
        List<Task> emptyTaskList = new ArrayList<>();
        emptyTaskList = null;
        Project newProject = new Project(0, name, associatedClient, projectRate, emptyTaskList, closed);
        try (Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, name);
            pstmt.setString(2, clientName);
            pstmt.setFloat(3, projectRate);
            pstmt.setBoolean(4, closed);
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
}
