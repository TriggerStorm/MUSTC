/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Trigger,and Alan
 */
public class MUSTC extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
          Parent root = FXMLLoader.load(getClass().getResource("gui/view/LogIn.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("gui/view/AdminView.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("gui/view/Test.fxml"));

        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        /*stage.setMaxHeight(488);
        stage.setMaxWidth(260);
        stage.setMinHeight(488);
        stage.setMinWidth(260);*/
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
