/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mustc.be.Task;
import mustc.be.TreeItems;

/**
 *
 * @author Trigger
 */
public class TreeItemDBDAO {
     private DBConnection dbc;

    public TreeItemDBDAO() {
        dbc = new DBConnection();
    }
   
    public List<TreeItems> getTreeItems(String clientName) throws SQLException{
    List<TreeItems> ti = new ArrayList<>();
    String sql = " Select Tasks.Name,Tasks.Id,Projects.Name,projects.Id,Clients.Name "+
            " From Tasks "+  
            " INNER JOIN Projects On Tasks.AssociatedProject = Projects.Id "+
            " INNER Join Clients On Projects.AssociatedClient = Clients.Id "+
            " where Clients.Name = '" + clientName + "'";
            try(Connection con = dbc.getConnection()) 
        {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next())  
            {
                List<Task> tasklist = (List<Task>) rs.getArray("Tasks.Name"+ "Tasks.Id");
                String projectName = rs.getString("Projects.Name");
                int projectID = rs.getInt("Projects.Id");
                
                
                TreeItems treeItem = new TreeItems(clientName,projectID,projectName,tasklist);
                ti.add(treeItem);
                System.out.println(ti+"");
            }
        }
            return ti;
            
            
    }
    
}
