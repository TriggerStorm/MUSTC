/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.gui.model;

import java.time.LocalDateTime;
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
    private Task task;
    private ObservableList<Client> clientList;
    private ObservableList<User> userList;
    private ObservableList<Task> task3;
    private ObservableList<Task> task1;
    private ObservableList<Task> g1;
    private ObservableList<Task> g2;
    private ObservableList<Task> g3;
    private ObservableList<Project> pj;
    private ObservableList<String> admin;
    
    public AdminModel() {
        bllManager = new BllManager();
        
       
 
    }
    
    public ObservableList<String> getAdmin(){
          List<String> admins = new ArrayList<>();
        String s1 = new String("Admin");
        String s2 = new String ("dev");
        admins.add(s1);
        admins.add(s2);
        
        admin = FXCollections.observableArrayList(admins);
        
        return admin;
    }

    
    
    public ObservableList<Client> getAllClient() {
        
        List<Client> allClients = bllManager.getAllClients();
        clientList = FXCollections.observableArrayList(allClients);
        return clientList;
    }
    

    

    public ObservableList<Task> getAllTask() {
         List<Task> allTask = bllManager.getAllTasksForAdmin();
         taskList = FXCollections.observableArrayList(allTask);
         return taskList;
    }
     public ObservableList<Session>getAllSessions(){
         
          List<Session> allSession = bllManager.getAllSessions();
         sessionList = FXCollections.observableArrayList(allSession);
         return sessionList;
     }

    public ObservableList<User> getAllUser() {
        
         List<User> allUser = bllManager.getAllUsers();
         userList = FXCollections.observableArrayList(allUser);
         return userList;
    }
    
    //Client
    
    public Client addNewClientToDB(String clientName, String logoImgLocation, String email, float standardRate) {
        return bllManager.addNewClientToDB(clientName, logoImgLocation, email, standardRate);
    }
    
    public Client editClient(Client editedClient, String clientName, float standardRate, String logoImgLocation, String email) {
        return bllManager.editClient(editedClient, clientName, standardRate, logoImgLocation, email);
    }
    
    public void removeClientFromDB(Client clientToDelete) {
        bllManager.removeClientFromDB(clientToDelete);
    }
    
    //pj
    
    public ObservableList<Project> getAllProject() {
        
       List<Project> allProjcets = bllManager.getAllProjectsForAdmin();
       pjList = FXCollections.observableArrayList(allProjcets);
       return pjList;
    }
    public ObservableList<Project> getAllProjectsIDsAndNames() {
        List<Project> allProjcets =bllManager.getAllProjectsIDsAndNames();
        pj = FXCollections.observableArrayList(allProjcets);
        return pj;
    }
    public Project addNewProjectToDB(String projectName, int associatedClientID, int phoneNr, float projectRate, int allocatedHours) {
        return bllManager.addNewProjectToDB(projectName, associatedClientID, phoneNr, projectRate, allocatedHours);
    }

    public Project editProject(Project editedProject, String projectName, int associatedClientID, float projectRate, int allocatedHours, boolean isClosed) {
        return bllManager.editProject(editedProject, projectName, associatedClientID, projectRate, allocatedHours, isClosed);
    }

    public void removeProjectFromDB(Project projectToDelete) {
        bllManager.removeProjectFromDB(projectToDelete);
    }
    
    //task

    public Task addNewTaskToDB(String taskName, int associatedProjectID, boolean isBillable){
       
       return bllManager.addNewTaskToDB(taskName, associatedProjectID, isBillable);
       
   }

    public Task editTask(Task editedTask, String taskName, String description, int associatedProjectID) {
        return bllManager.editTask(editedTask, taskName, description, associatedProjectID);
    }
    
    public void removeTaskFromDB(Task taskToDelete) {
        bllManager.removeTaskFromDB(taskToDelete);
    }
    
    
    // user
    public void addNewUserToDB(String userName, String email, String password, float salary, String status) {
        bllManager.addNewUserToDB(userName, email, password, salary, status);
    }
    
    public User editUser(User userToEdit, String userName, String email, String password, Float salary, String status) {
        return bllManager.editUser(userToEdit, userName, email, password, salary, status);
    }
    
    public void removeUserFromDB(User userToDelete) {
        bllManager.removeUserFromDB(userToDelete);
    }
    
    // Session
    public Session editSession(Session editedSession, int associatedUserID, int associatedTaskID, String startTime, String finishTime) {
        return bllManager.getSession(associatedTaskID);
    }
    
    public void removeSessionFromDB(Session sessionToDelete) {
        bllManager.removeSessionFromDB(sessionToDelete);
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
    
    /*public ObservableList<Task> getAllTaskFromProjectId(){
        List<Task> allTask =  new ArrayList<>();
        Task t1 = new Task("t1" + 0,0);
        Task t2 = new Task("t2" + 5,5);
        Task t3 = new Task("t3" + 1,5);
        allTask.add(t1);
        allTask.add(t2);
        allTask.add(t3);
        
        task3 = FXCollections.observableArrayList(allTask);
        
        return task3;
    }*/
    
    public Task getTaskForUser(int taskID) {
        return bllManager.getTaskForUser(taskID);
    }
    public Session addNewSessionToDB(int associatedUserID, int associatedTaskID, String startTime, String finishTime) {
       return bllManager.addNewSessionToDB(associatedUserID, associatedTaskID, startTime, finishTime);
    }
    public String localDateTimeToString(LocalDateTime LDT) {
        return bllManager.localDateTimeToString(LDT);
    }
}
