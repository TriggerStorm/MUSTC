/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.gui.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mustc.be.Client;
import mustc.be.LoggedInUser;
import mustc.be.Project;
import mustc.be.Report;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;
import mustc.bll.BllManager;

/**
 *
 * @author Trigger
 */
public class AdminModel {
    private static final AdminModel AMSingelton = new AdminModel();
    private BllManager bllManager;
    private ObservableList<Project> pjList;
    private ObservableList<Task> taskList;
    private ObservableList<Session> sessionList; 
    private ObservableList<Client> clientList;
    private ObservableList<User> userList;
    private ObservableList<Task> RecentTask;
    private ObservableList<Task> g1;
    private ObservableList<Task> g2;
    private ObservableList<Task> g3;
    private ObservableList<Project> pj;
    private ObservableList<String> admin;
    private ObservableList<Client> client;
    private ObservableList<User> user;
    private ObservableList<Project> clientpj;
    private ObservableList<Task> projectTask;
    private ObservableList<Report> Report;
    private ObservableList<Project> pjreport;
    private LoggedInUser liu;
    private List<Report> Reportlist;
    public AdminModel() {
        bllManager = new BllManager();
        liu = LoggedInUser.getInstance();
        getUsersThreeRecentTaskss(bllManager.getUser(liu.getId()));
 
    }
    
    public static AdminModel getInstance(){
        return AMSingelton;
    }
    
    public ObservableList<Report> oReport(){
    return Report;
    }
    
    public ObservableList<Task> oListTask(){
    return taskList;
    }
    
    public ObservableList<Client> oListClient(){
    return clientList;
    }
    public ObservableList<Project> oListProject(){
    return pjList;
    }
    public ObservableList<Session> oListSession(){
    return sessionList;
    }
    public ObservableList<User> oListUser(){
    return userList;
    }
    
    public ObservableList<Task> projectTask(){
    return projectTask;
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
     public ObservableList<Client> getAllClientNameAndId() {
        
        List<Client> Clients = bllManager.getAllClientsIDsAndNames();
        client = FXCollections.observableArrayList(Clients);
        return client;
    }
    
    public Client addNewClientToDB(String clientName, String logoImgLocation, String email, float standardRate) {
        Client cl = bllManager.addNewClientToDB(clientName, logoImgLocation, email, standardRate);
        clientList.add(cl);
        return null;
    }
    
    public Client editClient(Client editedClient, String clientName, float standardRate, String logoImgLocation, String email) {
        bllManager.editClient(editedClient, clientName, standardRate, logoImgLocation, email);
        return null;
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
        Project addpj = bllManager.addNewProjectToDB(projectName, associatedClientID, phoneNr, projectRate, allocatedHours);
        pjList.add(addpj);
        return null;
    }

    public Project editProject(Project editedProject, String projectName,int phoneNr,float projectRate, int allocatedHours, boolean isClosed) {
        bllManager.editProject(editedProject, projectName,phoneNr, projectRate, allocatedHours, isClosed);
        return null;
    }

    public void removeProjectFromDB(Project projectToDelete) {
        bllManager.removeProjectFromDB(projectToDelete);
    }
   
   
    //task
    
    
    
    public Task addNewTaskToDB(String taskName, int associatedProjectID, boolean isBillable){ // do this for add to db.
        Task addT = bllManager.addNewTaskToDB(taskName, associatedProjectID, isBillable);
        taskList.add(addT);
        return null;
   }

    public Task editTask(Task editedTask, String taskName , int associatedProjectID, boolean isBillable) {
        bllManager.editTask(editedTask, taskName, associatedProjectID, isBillable);
        
        return null;
    }
    
    public void removeTaskFromDB(Task taskToDelete) {
        bllManager.removeTaskFromDB(taskToDelete);
    }
    
    
    // user
    public User getUser(int userID) {
        return bllManager.getUser(userID);
    }
    
     public ObservableList<User> getAllUserNameAndId() {
        
         List<User> User = bllManager.getAllUsersIDsAndName();
         user = FXCollections.observableArrayList(User);
         return user;
    }
    public void addNewUserToDB(String userName, String email, String password, float salary, String status) {
        User u = bllManager.addNewUserToDB(userName, email, password, salary, status);
        userList.add(u);
    }
    
    public User editUser(User userToEdit, String userName, String email, String password, Float salary, String status) {
        bllManager.editUser(userToEdit, userName, email, password, salary, status);
        return null;
    }
    
    public void removeUserFromDB(User userToDelete) {
        bllManager.removeUserFromDB(userToDelete);
    }
    
    // Session
    public Session editSession(Session editedSession, int associatedUserID, int associatedTaskID, String startTime, String finishTime) {
        bllManager.editSession(editedSession, associatedUserID, associatedTaskID, startTime, finishTime);
        return null;
    }
    
    public void removeSessionFromDB(Session sessionToDelete) {
        bllManager.removeSessionFromDB(sessionToDelete);
    }
    
    //3 task
    public ObservableList<Task> getUsersThreeRecentTaskss(User loggedInUser){
        
        
        List<Task> task = bllManager.getUsersThreeRecentTasks(loggedInUser);
        RecentTask = FXCollections.observableArrayList(task);
        
        return RecentTask;
        
    }
    
    public ObservableList<Task> get1(){
        Task t1 = RecentTask.get(0);
        g1 = FXCollections.observableArrayList(t1);
        
        return g1;
    }
    public ObservableList<Task> get2(){
        Task t2 = RecentTask.get(1);
        g2 = FXCollections.observableArrayList(t2);
        
        return g2;
    }
    
    public ObservableList<Task> get3(){
        Task t3 = RecentTask.get(2);
        g3 = FXCollections.observableArrayList(t3);
        
        return g3;
    }
    
    
    
    public Project getProjectForUser(int projectID){
       return bllManager.getProjectForUser(projectID);
    }

    public Task getTaskForUser(int taskID) {
        return bllManager.getTaskForUser(taskID);
    }
    public Session addNewSessionToDB(int associatedUserID, int associatedTaskID,String associatedTaskName, String startTime, String finishTime) {
      Session s = bllManager.addNewSessionToDB(associatedUserID, associatedTaskID,associatedTaskName, startTime, finishTime);
       sessionList.add(s);
       return null;
    }
    public String localDateTimeToString(LocalDateTime LDT) {
        return bllManager.localDateTimeToString(LDT);
    }
  // sertch
    public ObservableList<Client> searchClient(ObservableList<Client> allClient, String text){
        return bllManager.searchClient(allClient, text);
    }
    
    public ObservableList<Task> searchTask(ObservableList<Task> allTask, String filterTask){
        return bllManager.searchTask(allTask, filterTask);
    }
    
    public ObservableList<Task> searchTaskpj(ObservableList<Task> allTask, String text){
        return bllManager.searchTask(allTask, text);
    }
    public ObservableList<Project> searchProject(ObservableList<Project> allProject, String text){
        return bllManager.searchProject(allProject, text);
    }
    
     public ObservableList<Session> searchSession(ObservableList<Session> allSession, String text){
        return bllManager.searchSession(allSession, text);
    }
     
     public ObservableList<User> searchUser(ObservableList<User> allUser, String text){
        return bllManager.searchUser(allUser, text);
    }
    // Report --------------------------------------------------------------------------------------------------------------------------------------------
    
     public List<Report> Csv(){
         return Reportlist; // if 0 dont return
     }
     
     public ObservableList<Report> generateReport(int clientID, int projectID, int taskID, int userID, LocalDate searchFrom, LocalDate searchTo) {
                Reportlist = bllManager.generateReport(clientID, projectID, taskID, userID, searchFrom, searchTo);
                System.out.println(""+Reportlist.size());
                Report = FXCollections.observableArrayList(Reportlist);
                
                return Report;
    }
     public ObservableList<Project> getAllProjectIDsAndNamesOfAClient(int clientID) {
         
        List<Project> clintProjcets = bllManager.getAllProjectIDsAndNamesOfAClient(clientID);
       clientpj = FXCollections.observableArrayList(clintProjcets);
       return clientpj;
    }
      public ObservableList<Task> getAllTaskIDsAndNamesOfAProject(int projectID) {
         
         List<Task> pjTask = bllManager.getAllTaskIDsAndNamesOfAProject(projectID);
       projectTask = FXCollections.observableArrayList(pjTask);
       return projectTask;
    }
      public ObservableList<Project> getAllProjectsIDsAndNamesForReport() {
        
       List<Project> pj = bllManager.getAllProjectsIDsAndNamesForReport();
       pjreport = FXCollections.observableArrayList(pj);
       return pjreport;
        
        
    }
      public void addReportListToCSVFile(List<Report> reportList) {
        bllManager.addReportListToCSVFile(reportList);
    }
}
