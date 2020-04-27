/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import mustc.be.Client;
import mustc.dal.ClientDBDAO;

/**
 *
 * @author Trigger
 */
public class UserViewController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    private ClientDBDAO cbdb;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException {
      //  ClientDBDAO cbdb = new ClientDBDAO();
       // Client c = new Client(8,"","",7,"");
       // System.out.println(cbdb.getSpecificClient(8).getEmail()+"emaill");
       // System.out.println(cbdb.addNewClientToDB("name", 0, "logo", "email").getName());
               
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
