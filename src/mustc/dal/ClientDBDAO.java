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
import mustc.be.Client;

/**
 *
 * @author Trigger and Alan
 */
public class ClientDBDAO {
    private DBConnection dbc;
    
    /**
     *
     */
    public ClientDBDAO() {
        dbc = new DBConnection();
    }
    
    /**
     *
     * @param clientName
     * @param logoImgLocation
     * @param email
     * @param standardRate
     * @return
     * @throws SQLException
     */
    public Client addNewClientToDB(String clientName, String logoImgLocation, String email, float standardRate) throws SQLException { 
    //  Adds a new Client to the DB, and returns the updated Client to the GUI
        Client newClient = new Client(0,clientName,logoImgLocation, email, standardRate, 0, 0);
        try (Connection con = dbc.getConnection()) {
            String sql = "INSERT INTO Clients(Name, logoImgLocation, standardRate, email) VALUES (?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, clientName);
            pstmt.setString(2, logoImgLocation);
            pstmt.setFloat(3, standardRate);
            pstmt.setString(4, email);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Client failed, no rows affected.");
            }
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newClient.setClientId((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating Client failed, no ID obtained.");
                } 
            }
        } catch (SQLServerException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newClient;
    }
    
    /**
     *
     * @param clientID
     * @return
     * @throws SQLException
     */
    public Client getClient(int clientID) throws SQLException {
    //  Returns specific Client
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT * FROM Clients WHERE id = '" + clientID + "'"; 
             PreparedStatement pstmt = con.prepareStatement(sql);   
 //            pstmt.setInt(1,clientID);
             pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()) //While you have something in the results
            {
                clientID =  rs.getInt("id");
                String clientName = rs.getString("name");
                String logoImgLocation = rs.getString("logoImgLocation");
                float standardRate = rs.getFloat("standardRate");
                String email = rs.getString("email");
                int totalHours = 232;  // UNNECESSARY??
                int noOfProjects = getNoOfProjectsOfAClient(clientID);
                return new Client(clientID,clientName,logoImgLocation,email, standardRate, totalHours, noOfProjects); 
            }    
        }
        return null;
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Client> getAllClients() throws SQLException {
    //  Returns all Clients  
        List<Client> allClients = new ArrayList<>();
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT * FROM Clients";
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                int clientID =  rs.getInt("id");
                String clientName = rs.getString("name");
                String logoImgLocation = rs.getString("logoImgLocation");  // not used here yet
                float standardRate = rs.getFloat("standardRate");
                String email = rs.getString("email");
                int totalHours = 222;  // mock data
                int noOfProjects = getNoOfProjectsOfAClient(clientID);
                allClients.add(new Client(clientID, clientName, email, standardRate, totalHours, noOfProjects));
            }    
        }
        return allClients;
    }
    
    /**
     *
     * @return
     * @throws SQLException
     */
    public List<Client> getAllClientsIDsAndNames() throws SQLException {
    //  Returns all Clients Id's and Names for Report Selection 
        List<Client> allClients = new ArrayList<>();
        allClients.add(new Client(-1, "All Clients"));
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT id, name FROM Clients";
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                int clientID =  rs.getInt("id");
                String clientName = rs.getString("name");
                allClients.add(new Client(clientID, clientName));
            }    
        }
        return allClients;
    }
    
    /**
     *
     * @param clientID
     * @return
     * @throws SQLException
     */
    public String getClientName(int clientID) throws SQLException {
    //  Returns a Client Name for a Report 
        String clientName = "";
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT name FROM Clients WHERE id ='" + clientID + "'"; 
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) //While you have something in the results
            {
                clientName = rs.getString("name");
            }    
        }
        return clientName;
    }
      
    
    private int getNoOfProjectsOfAClient(int clientID) throws SQLException {
        int noOfProjectsOfAClient = 0;
        try(Connection con = dbc.getConnection()){
            String sql = "SELECT id FROM Projects WHERE associatedClient ='" + clientID + "'"; 
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) //While you have something in the results
            {
                noOfProjectsOfAClient ++; 
            }    
        }
      
        return noOfProjectsOfAClient;
    }
     
    /**
     *
     * @param editedClient
     * @param clientName
     * @param standardRate
     * @param logoImgLocation
     * @param email
     * @return
     */
    public Client editClient (Client editedClient,String clientName,float standardRate,String logoImgLocation, String email) { 
    //  Edits a client  
        String sql = "UPDATE Clients SET name = ?, standardRate = ?, logoImgLocation = ?, email = ? WHERE id = ?";
        try ( Connection con = dbc.getConnection()) {
            //Create a prepared statement.
            PreparedStatement pstmt = con.prepareStatement(sql);
            //Set parameter values.
            pstmt.setString(1, clientName);
            pstmt.setFloat(2, standardRate);
            pstmt.setString(3, logoImgLocation);
            pstmt.setString(4, email);
            pstmt.setInt(5, editedClient.getClientId());
            pstmt.executeUpdate();  //Execute SQL query.
            editedClient.setClientName(clientName);
            editedClient.setImgLocation(logoImgLocation);
            editedClient.setStandardRate(standardRate);
            editedClient.setEmail(email);
            return editedClient;
        } catch (SQLServerException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null; 
    }
   
    /**
     *
     * @param clientToDelete
     * @throws SQLException
     */
    public void removeClientFromDB(Client clientToDelete) throws SQLException {
    //  Delete specific Client
        try(Connection con = dbc.getConnection()){
            String sql = "DELETE FROM Clients WHERE id = ?";
             PreparedStatement pstmt = con.prepareStatement(sql);   
             pstmt.setInt(1,clientToDelete.getClientId());
             pstmt.execute();
        }
       
    }
    
}

