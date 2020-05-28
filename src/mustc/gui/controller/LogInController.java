/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mustc.be.LoggedInUser;
import mustc.gui.model.LogInModel;


/**
 * FXML Controller class
 *
 * @author Trigger
 */
public class LogInController implements Initializable {

    
    @FXML
    private JFXTextField tf_email;
    @FXML
    private JFXTextField tf_pass;
    @FXML
    private JFXButton bn_login;
    
    private LogInModel loginmodel;
    private LoggedInUser loginuser;
    @FXML
    private Button bv_Test;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginuser = LoggedInUser.getInstance();
    }    

    
    
   

    private void adminlogin(String mail, String password) throws IOException {
       /*Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mustc/gui/view/AdminView.fxml"));
        root1 = (Parent) fxmlLoader.load();
        
        fxmlLoader.<AdminViewController>getController();

        Stage addStage = new Stage();
        Scene addScene = new Scene(root1);

        
        addStage.setScene(addScene);
        addStage.show();*/
          Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mustc/gui/view/AdminView.fxml"));
        root1 = (Parent) fxmlLoader.load();
        fxmlLoader.<AdminViewController>getController();
        Stage addStage = new Stage();
        Scene addScene = new Scene(root1);
        addStage.setScene(addScene);
        addStage.show();
        addStage.show();
        addStage.setMaxHeight(488);
        addStage.setMaxWidth(260);
        addStage.setMinHeight(488);
        addStage.setMinWidth(260);
        
        Stage stage = (Stage) bn_login.getScene().getWindow();
        stage.close();
    }

    private void userlogin(String mail, String password) throws IOException {
        Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mustc/gui/view/UserView.fxml"));
        root1 = (Parent) fxmlLoader.load();
        fxmlLoader.<UserViewController>getController();
        Stage addStage = new Stage();
        Scene addScene = new Scene(root1);
        addStage.setScene(addScene);
        addStage.show();
        addStage.setMaxHeight(488);
        addStage.setMaxWidth(260);
        addStage.setMinHeight(488);
        addStage.setMinWidth(260);
        
        Stage stage = (Stage) bn_login.getScene().getWindow();
        stage.close(); 
    }

    @FXML
    private void handel_login(ActionEvent event) throws IOException {
         loginmodel = new LogInModel();
       String loginmail = tf_email.getText().trim();
       String passw = tf_pass.getText().trim();
       int loginstate = loginmodel.checkUserLogin(loginmail, passw);//returns an int, as it also checks if it is a teacher or a student.
        switch (loginstate) {
            case 1:  adminlogin(loginmail, passw); //teacher login needs creation and then place make something like teacherLogin method in stead.
                    break;
            case 2:  userlogin(loginmail, passw); //student login 
                    break;
            default: System.out.println("Sorry wrong authentication"); //Might want to make a popup here in stead....
    }
    }

    @FXML
    private void test(ActionEvent event) throws IOException {
         Parent root1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/mustc/gui/view/Test.fxml"));
        root1 = (Parent) fxmlLoader.load();
        fxmlLoader.<AdminViewController>getController();
        Stage addStage = new Stage();
        Scene addScene = new Scene(root1);
        addStage.setScene(addScene);
        addStage.show();
        Stage stage = (Stage) bn_login.getScene().getWindow();
        stage.close();
    }
    
    
}
