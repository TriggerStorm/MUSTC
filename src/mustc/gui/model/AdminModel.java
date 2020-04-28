/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.gui.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mustc.be.Client;
import mustc.be.Project;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;
import mustc.bll.BllManager;

/**
 *
 * @author Trigger
 */
public class AdminModel {
    private BllManager bllManager;
    private ObservableList<Project> pjList;
    private ObservableList<Task> taskList;
    private ObservableList<Session> sessionList; 
    private ObservableList<String> sString; 
    private Task task;
    private ObservableList<Client> clientList;
    private ObservableList<User> userList;

    public AdminModel() {
        bllManager = new BllManager();
        task = new Task("teast2");
    }
    
    

    public ObservableList<Project> getAllProject() {
        return null;
        // List<Project> allProjcets = bllManager.getAllProjects();
       //pjList = FXCollections.observableArrayList(allProjcets);
       //return pjList;
    }

    public ObservableList<Client> getAllClient() {
        return null;
        // List<Client> allClients = bllManager.getAllClient();
       //clientList = FXCollections.observableArrayList(allClients);
       //return clientList;
    }

    public ObservableList<Task> getAllTask() {
        List<Task> allTask =  new ArrayList<>();
        allTask.add(task);
        
         // List<Task> allTask = bllManager.getAllTask();
         taskList = FXCollections.observableArrayList(allTask);
         return taskList;
    }
     public ObservableList<Session>getAllSessions(){
         return null;
         // List<Session> allSession = bllManager.getAllSession();
         //sessionList = FXCollections.observableArrayList(allSession);
         //return sessionList;
     }

    public ObservableList<User> getAllUser() {
        return null;
        // List<User> allUser = bllManager.getAllUsers();
       //userList = FXCollections.observableArrayList(allUser);
       //return userList;
    }
    
    public ObservableList<String> someStrings(){
        List<String> Strings = Arrays.asList("hey","you","test","done");
        sString = FXCollections.observableArrayList(Strings);
        return sString;
    }
    //pj
    public void addNewProjectToDB(String trim, Object selectedItem, String trim0, String trim1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void editProject(Project editedProject, String trim, Object selectedItem, String trim0, String trim1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeProjectFromDB(ObservableList<Project> selectedItems) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //task

    public void addNewTaskToDB(String trim, Object selectedItem, String trim0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void editTask(String trim, Object selectedItem, String trim0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void removeTaskFromDB(){}
    
    
}
