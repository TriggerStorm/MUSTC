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
    private ObservableList<Task> task3;
    private ObservableList<Task> task1;
    private ObservableList<Task> g1;
    private ObservableList<Task> g2;
    private ObservableList<Task> g3;
    
    public AdminModel() {
        bllManager = new BllManager();
        
       
 
    }
    
    

    public ObservableList<Project> getAllProject() {
        
       List<Project> allProjcets = bllManager.getAllProjectsForAdmin();
       pjList = FXCollections.observableArrayList(allProjcets);
       return pjList;
    }

    public ObservableList<Client> getAllClient() {
        
        List<Client> allClients = bllManager.getAllClients();
        clientList = FXCollections.observableArrayList(allClients);
        return clientList;
    }

    public ObservableList<Task> getAllTask() {
       // List<Task> allTask =  new ArrayList<>();
      //  allTask.add(task);
        
          List<Task> allTask = bllManager.getAllTasksForAdmin();
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
        
         List<User> allUser = bllManager.getAllUsers();
         userList = FXCollections.observableArrayList(allUser);
         return userList;
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
    
    
    // user
    public void addNewUserToDB(String userName, String email, String password, float salary, String status) {
        bllManager.addNewUserToDB(userName, email, password, 0, status);
    }
    
    //3 task
    public void getUsersThreeRecentTaskss(User loggedInUser){
        
        User t = bllManager.getUser(1); // mok data
        bllManager.getUsersThreeRecentTasks(t);
        
    }
    public ObservableList<Task> get1() {
        User t = bllManager.getUser(1);
        Task g2 =  bllManager.getUsersThreeRecentTasks(t).get(0);
        g1 = FXCollections.observableArrayList(g2);
        
        return g1;
    }
    public ObservableList<Task> get2() {
    User t = bllManager.getUser(1);
        Task g20 =  bllManager.getUsersThreeRecentTasks(t).get(1);
        g2 = FXCollections.observableArrayList(g20);
        
        return g2;
        
    }
    public ObservableList<Task> get3() {
    User t = bllManager.getUser(1);
        Task g2 =  bllManager.getUsersThreeRecentTasks(t).get(2);
        g3 = FXCollections.observableArrayList(g2);
        
        return g3;
        
    }
    
    public Project getProjectForUser(int projectID){
       return bllManager.getProjectForUser(projectID);
    }
    
    public ObservableList<Task> getAllTaskFromProjectId(){
        List<Task> allTask =  new ArrayList<>();
        Task t1 = new Task("t1" + 0,0);
        Task t2 = new Task("t2" + 5,5);
        Task t3 = new Task("t3" + 1,5);
        allTask.add(t1);
        allTask.add(t2);
        allTask.add(t3);
        
        task3 = FXCollections.observableArrayList(allTask);
        
        return task3;
    }
    
    public Task getTaskForUser(int taskID) {
        return bllManager.getTaskForUser(taskID);
    }
}
