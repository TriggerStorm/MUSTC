/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.gui.model;

import javafx.collections.ObservableList;
import mustc.be.Client;
import mustc.be.Project;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;

/**
 *
 * @author Trigger
 */
public class AdminModel {

    public ObservableList<Client> getAllClient;

    public ObservableList<Project> getAllProject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ObservableList<Client> getAllClient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ObservableList<Task> getAllTask() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     public ObservableList<Session>getAllSessions(){
         throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }

    public ObservableList<User> getAllUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
