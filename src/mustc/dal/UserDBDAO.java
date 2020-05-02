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
import java.util.logging.Level;
import java.util.logging.Logger;
import mustc.be.User;

/**
 *
 * @author Trigger and Alan
 */
public class UserDBDAO {
    private DBConnection dbc;

    
    public UserDBDAO() {
        dbc = new DBConnection();
    } 
    
    
    public User addNewUserToDB(String userName, String email, String password, float salary, boolean isAdmin) { 
    //  Adds a new user to the User table of the database given the users details. Generated an id key    
        User newUser = new User(10, userName, email, password, salary, isAdmin);
        String sql = "INSERT INTO Users(name, email, password, salary, admin) VALUES (?,?,?,?,?)";
        try (Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, userName);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setFloat(4, salary);
            int admin = 0;
            if(isAdmin == true)
                admin = 1;
            pstmt.setInt(5, admin);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating User failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newUser.setUserID((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating User failed, no ID obtained.");
                } 
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(UserDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newUser;
    }
     
    
    public User getUser(int userID) throws SQLException {
    //  Returns a User data object given a User id
        User user = null;
        String sql = "SELECT * FROM Users WHERE id = '" + userID + "'";  //  userName, email, password, salary, isAdmin 
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                String userName = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Float salary = rs.getFloat("salary");
                int admin = rs.getInt("admin");
                boolean isAdmin = false;
                if(admin == 1)
                    isAdmin = true;
               user = new User(userID, userName, email, password, salary, isAdmin); 
            }    
        }
        return user;
    }   
 
    public User getAllUser(int userID) throws SQLException {
    //  Returns a User data object given a User id
        User user = null;
        String sql = "SELECT * FROM Users WHERE id = '" + userID + "'";  //  userName, email, password, salary, isAdmin 
        try(Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                String userName = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Float salary = rs.getFloat("salary");
                int admin = rs.getInt("admin");
                boolean isAdmin = false;
                if(admin == 1)
                    isAdmin = true;
               user = new User(userID, userName, email, password, salary, isAdmin); 
            }    
        }
        return user;
    }   
 
    
    public User editUser (User editedUser, String userName, String email, String password, Float salary, boolean isAdmin) { 
    //  Edits a user in the User table of the database given the users new details.  
        String sql = "UPDATE Users SET name = ?, email = ?, password = ?, salary = ? , admin = ? WHERE id = '" + editedUser.getUserID() + "'";
        try (  //Get a connection to the database.
            Connection con = dbc.getConnection()) {  
            //Create a prepared statement.
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setString(1, userName);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setFloat(4, salary);
            int admin = 0;
            if(isAdmin == true)
                admin = 1;
            pstmt.setInt(5, admin);
            pstmt.executeUpdate();  // Execute SQL query.
            editedUser.setUserName(userName);
            editedUser.setEmail(email);
            editedUser.setPassword(password);   
            editedUser.setSalary(salary);
            editedUser.setIsAdmin(isAdmin);
            return editedUser;
        } catch (SQLServerException ex) {
            Logger.getLogger(UserDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

         
    public void removeUserFromDB(User userToDelete) {
    //  Removes a User from the Users table of the database given a User data object
        String sql = "DELETE FROM Users WHERE id = ?";//'" + userToDelete.getUserId() + "'";
        try (Connection con = dbc.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,userToDelete.getUserID());
            pstmt.execute();
        } catch (SQLException ex) {
            System.out.println("Exception " + ex);
        }
    }
    
    
}
