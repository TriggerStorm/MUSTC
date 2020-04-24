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
import java.util.ArrayList;
import java.util.List;
import mustc.be.Project;
import mustc.be.Session;
import mustc.be.Task;

/**
 *
 * @author admin
 */
public class TaskDBDAO {
    private DBConnection dbc;
    private SessionDBDAO sessionDBDao;
    
    
    public TaskDBDAO() {
            dbc = new DBConnection();
            sessionDBDao = new SessionDBDAO();
    }        

    
    public Task getTask(int taskID) throws SQLException {
        Task task = null;
        try(Connection con = dbc.getConnection()) {
            String sql = "SELECT * FROM Tasks WHERE id = '" + taskID + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) //While you have something in the results
            {
                String taskName =  rs.getString("Name");
                String description =  rs.getString("Description");   
                int associatedProjectID = rs.getInt("associatedProject");
                List<Session> sessionIDsAndNameInTask = sessionDBDao.getSession(taskID);

                Task taskInProject = new Task(taskID, taskName, description, associatedProjectID, sessionIDsAndNameInTask);
            }    
        }
        return task ;
    }
    
    
    public List<Task> getAProjectsTaskIDsAndNames(int projectID) throws SQLException {
        List<Task> projectTasks = new ArrayList<>();  //null;?
        try(Connection con = dbc.getConnection()) {
            String sql = "SELECT id AND name FROM Tasks WHERE associatedProjectID = '" + projectID + "'";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) //While you have something in the results
            {
                int taskID = rs.getInt("id");
                String taskName =  rs.getString("Name");
                Task taskInProject = new Task(taskID, taskName, null, projectID, null);
                projectTasks.add(taskInProject); 
            }    
        }
        return projectTasks ;
    }
    
    
    
}
