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
import java.util.logging.Level;
import java.util.logging.Logger;
import mustc.be.User;

/**
 *
 * @author admin
 */
public class UserDBDAO {
    private DBConnection dbc;

    
    public UserDBDAO() {
        dbc = new DBConnection();
    } 
    
    
    public User addNewUserToDB(String userName, String email, String password, float salary, boolean isAdmin) { 
    //  Adds a new user to the User table of the database given the users details. Generated an id key    
        String sql = "INSERT INTO Users(name, email, password, phoneNr, salary, admin) VALUES (?,?,?,?,?)";
        User newUser = new User(0, userName, email, password, salary, isAdmin);
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
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newUser.setUserId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                } 
                return newUser;
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(UserDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
     
}