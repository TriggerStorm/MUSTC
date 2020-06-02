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

    /**
     *
     */
    public AdminModel() {
        bllManager = new BllManager();
        liu = LoggedInUser.getInstance();
        getUsersThreeRecentTaskss(bllManager.getUser(liu.getId()));
 
    }
    
    /**
     *
     * @return
     */
    public static AdminModel getInstance(){
        return AMSingelton;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Report> oReport(){
    return Report;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Task> oListTask(){
    return taskList;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Client> oListClient(){
    return clientList;
    }

    /**
     *
     * @return
     */
    public ObservableList<Project> oListProject(){
    return pjList;
    }

    /**
     *
     * @return
     */
    public ObservableList<Session> oListSession(){
    return sessionList;
    }

    /**
     *
     * @return
     */
    public ObservableList<User> oListUser(){
    return userList;
    }

    /**
     *
     * @return
     */
    public ObservableList<User> oListUserNameAndId(){
    return user;
    }

    /**
     *
     * @return
     */
    public ObservableList<Task> projectTask(){
    return projectTask;
    }

    /**
     *
     * @return
     */
    public ObservableList<Project> oListProjectNameAndId(){
    return pj;
    }

    /**
     *
     * @return
     */
    public ObservableList<Client> oListClientIdAndName(){
    return client;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<String> getAdmin(){
          List<String> admins = new ArrayList<>();
        String s1 = new String("Admin");
        String s2 = new String ("dev");
        admins.add(s1);
        admins.add(s2);
        
        admin = FXCollections.observableArrayList(admins);
        
        return admin;
    }

    /**
     *
     * @return
     */
    public ObservableList<Client> getAllClient() {
        
        List<Client> allClients = bllManager.getAllClients();
        clientList = FXCollections.observableArrayList(allClients);
        return clientList;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Task> getAllTask() {
         List<Task> allTask = bllManager.getAllTasksForAdmin();
         taskList = FXCollections.observableArrayList(allTask);
         return taskList;
    }

    /**
     *
     * @return
     */
    public ObservableList<Session>getAllSessions(){
         
          List<Session> allSession = bllManager.getAllSessions();
         sessionList = FXCollections.observableArrayList(allSession);
         return sessionList;
     }

    /**
     *
     * @return
     */
    public ObservableList<User> getAllUser() {
        
         List<User> allUser = bllManager.getAllUsers();
         userList = FXCollections.observableArrayList(allUser);
         return userList;
    }
    
    //Client

    /**
     *
     * @return
     */
     public ObservableList<Client> getAllClientNameAndId() {
        
        List<Client> Clients = bllManager.getAllClientsIDsAndNames();
        client = FXCollections.observableArrayList(Clients);
        return client;
    }
    
    /**
     *
     * @param clientName
     * @param logoImgLocation
     * @param email
     * @param standardRate
     * @return
     */
    public Client addNewClientToDB(String clientName, String logoImgLocation, String email, float standardRate) {
        Client cl = bllManager.addNewClientToDB(clientName, logoImgLocation, email, standardRate);
        clientList.add(cl);
        client.add(cl);
        return null;
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
    public Client editClient(Client editedClient, String clientName, float standardRate, String logoImgLocation, String email) {
        bllManager.editClient(editedClient, clientName, standardRate, logoImgLocation, email);
        return null;
    }
    
    /**
     *
     * @param clientToDelete
     */
    public void removeClientFromDB(Client clientToDelete) {
        bllManager.removeClientFromDB(clientToDelete);
    }
    
    //pj

    /**
     *
     * @return
     */
    
    public ObservableList<Project> getAllProject() {
        
       List<Project> allProjcets = bllManager.getAllProjectsForAdmin();
       pjList = FXCollections.observableArrayList(allProjcets);
       return pjList;
    }

    /**
     *
     * @return
     */
    public ObservableList<Project> getAllProjectsIDsAndNames() {
        List<Project> allProjcets =bllManager.getAllProjectsIDsAndNames();
        pj = FXCollections.observableArrayList(allProjcets);
        return pj;
    }

    /**
     *
     * @param projectName
     * @param associatedClientID
     * @param phoneNr
     * @param projectRate
     * @param allocatedHours
     * @return
     */
    public Project addNewProjectToDB(String projectName, int associatedClientID, int phoneNr, float projectRate, int allocatedHours) {
        Project addpj = bllManager.addNewProjectToDB(projectName, associatedClientID, phoneNr, projectRate, allocatedHours);
        pjList.add(addpj);
        pj.add(addpj);
        pjreport.add(addpj);
        return null;
    }

    /**
     *
     * @param editedProject
     * @param projectName
     * @param phoneNr
     * @param projectRate
     * @param allocatedHours
     * @param isClosed
     * @return
     */
    public Project editProject(Project editedProject, String projectName,int phoneNr,float projectRate, int allocatedHours, boolean isClosed) {
        bllManager.editProject(editedProject, projectName,phoneNr, projectRate, allocatedHours, isClosed);
        return null;
    }

    /**
     *
     * @param projectToDelete
     */
    public void removeProjectFromDB(Project projectToDelete) {
        bllManager.removeProjectFromDB(projectToDelete);
    }
   
   
    //task
    
    /**
     *
     * @param taskName
     * @param associatedProjectID
     * @param isBillable
     * @return
     */
    public Task addNewTaskToDB(String taskName, int associatedProjectID, boolean isBillable){ // do this for add to db.
        Task addT = bllManager.addNewTaskToDB(taskName, associatedProjectID, isBillable);
        taskList.add(addT);
        
        return null;
   }

    /**
     *
     * @param editedTask
     * @param taskName
     * @param associatedProjectID
     * @param isBillable
     * @return
     */
    public Task editTask(Task editedTask, String taskName , int associatedProjectID, boolean isBillable) {
        bllManager.editTask(editedTask, taskName, associatedProjectID, isBillable);
        
        return null;
    }
    
    /**
     *
     * @param taskToDelete
     */
    public void removeTaskFromDB(Task taskToDelete) {
        bllManager.removeTaskFromDB(taskToDelete);
    }
    
    
    // user

    /**
     *
     * @param userID
     * @return
     */
    public User getUser(int userID) {
        return bllManager.getUser(userID);
    }
    
    /**
     *
     * @return
     */
    public ObservableList<User> getAllUserNameAndId() {
        
         List<User> User = bllManager.getAllUsersIDsAndName();
         user = FXCollections.observableArrayList(User);
         return user;
    }

    /**
     *
     * @param userName
     * @param email
     * @param password
     * @param salary
     * @param status
     */
    public void addNewUserToDB(String userName, String email, String password, float salary, String status) {
        User u = bllManager.addNewUserToDB(userName, email, password, salary, status);
        userList.add(u);
        user.add(u);
    }
    
    /**
     *
     * @param userToEdit
     * @param userName
     * @param email
     * @param password
     * @param salary
     * @param status
     * @return
     */
    public User editUser(User userToEdit, String userName, String email, String password, Float salary, String status) {
        bllManager.editUser(userToEdit, userName, email, password, salary, status);
        return null;
    }
    
    /**
     *
     * @param userToDelete
     */
    public void removeUserFromDB(User userToDelete) {
        bllManager.removeUserFromDB(userToDelete);
    }
    
    // Session

    /**
     *
     * @param editedSession
     * @param associatedUserID
     * @param associatedTaskID
     * @param startTime
     * @param finishTime
     * @return
     */
    public Session editSession(Session editedSession, int associatedUserID, int associatedTaskID, String startTime, String finishTime) {
        bllManager.editSession(editedSession, associatedUserID, associatedTaskID, startTime, finishTime);
        return null;
    }
    
    /**
     *
     * @param sessionToDelete
     */
    public void removeSessionFromDB(Session sessionToDelete) {
        bllManager.removeSessionFromDB(sessionToDelete);
    }
    
    //3 task

    /**
     *
     * @param loggedInUser
     * @return
     */
    public ObservableList<Task> getUsersThreeRecentTaskss(User loggedInUser){
        
        
        List<Task> task = bllManager.getUsersThreeRecentTasks(loggedInUser);
        RecentTask = FXCollections.observableArrayList(task);
        
        return RecentTask;
        
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Task> get1(){
        Task t1 = RecentTask.get(0);
        g1 = FXCollections.observableArrayList(t1);
        
        return g1;
    }

    /**
     *
     * @return
     */
    public ObservableList<Task> get2(){
        Task t2 = RecentTask.get(1);
        g2 = FXCollections.observableArrayList(t2);
        
        return g2;
    }
    
    /**
     *
     * @return
     */
    public ObservableList<Task> get3(){
        Task t3 = RecentTask.get(2);
        g3 = FXCollections.observableArrayList(t3);
        
        return g3;
    }
    
    /**
     *
     * @param projectID
     * @return
     */
    public Project getProjectForUser(int projectID){
       return bllManager.getProjectForUser(projectID);
    }

    /**
     *
     * @param taskID
     * @return
     */
    public Task getTaskForUser(int taskID) {
        return bllManager.getTaskForUser(taskID);
    }

    /**
     *
     * @param associatedUserID
     * @param associatedTaskID
     * @param associatedTaskName
     * @param startTime
     * @param finishTime
     * @return
     */
    public Session addNewSessionToDB(int associatedUserID, int associatedTaskID,String associatedTaskName, String startTime, String finishTime) {
      Session s = bllManager.addNewSessionToDB(associatedUserID, associatedTaskID,associatedTaskName, startTime, finishTime);
       sessionList.add(s);
       return null;
    }

    /**
     *
     * @param LDT
     * @return
     */
    public String localDateTimeToString(LocalDateTime LDT) {
        return bllManager.localDateTimeToString(LDT);
    }
  // sertch

    /**
     *
     * @param allClient
     * @param text
     * @return
     */
    public ObservableList<Client> searchClient(ObservableList<Client> allClient, String text){
        return bllManager.searchClient(allClient, text);
    }
    
    /**
     *
     * @param allTask
     * @param filterTask
     * @return
     */
    public ObservableList<Task> searchTask(ObservableList<Task> allTask, String filterTask){
        return bllManager.searchTask(allTask, filterTask);
    }
    
    /**
     *
     * @param allTask
     * @param text
     * @return
     */
    public ObservableList<Task> searchTaskpj(ObservableList<Task> allTask, String text){
        return bllManager.searchTask(allTask, text);
    }

    /**
     *
     * @param allProject
     * @param text
     * @return
     */
    public ObservableList<Project> searchProject(ObservableList<Project> allProject, String text){
        return bllManager.searchProject(allProject, text);
    }
    
    /**
     *
     * @param allSession
     * @param text
     * @return
     */
    public ObservableList<Session> searchSession(ObservableList<Session> allSession, String text){
        return bllManager.searchSession(allSession, text);
    }
     
    /**
     *
     * @param allUser
     * @param text
     * @return
     */
    public ObservableList<User> searchUser(ObservableList<User> allUser, String text){
        return bllManager.searchUser(allUser, text);
    }
    // Report --------------------------------------------------------------------------------------------------------------------------------------------
    
    /**
     *
     * @return
     */
    public List<Report> Csv(){
         return Reportlist; // if 0 dont return
     }
     
    /**
     *
     * @param clientID
     * @param projectID
     * @param taskID
     * @param userID
     * @param searchFrom
     * @param searchTo
     * @return
     */
    public ObservableList<Report> generateReport(int clientID, int projectID, int taskID, int userID, LocalDate searchFrom, LocalDate searchTo) {
                Reportlist = bllManager.generateReport(clientID, projectID, taskID, userID, searchFrom, searchTo);
                System.out.println(""+Reportlist.size());
                Report = FXCollections.observableArrayList(Reportlist);
                
                return Report;
    }

    /**
     *
     * @param clientID
     * @return
     */
    public ObservableList<Project> getAllProjectIDsAndNamesOfAClient(int clientID) {
         
        List<Project> clintProjcets = bllManager.getAllProjectIDsAndNamesOfAClient(clientID);
       clientpj = FXCollections.observableArrayList(clintProjcets);
       return clientpj;
    }

    /**
     *
     * @param projectID
     * @return
     */
    public ObservableList<Task> getAllTaskIDsAndNamesOfAProject(int projectID) {
         
         List<Task> pjTask = bllManager.getAllTaskIDsAndNamesOfAProject(projectID);
       projectTask = FXCollections.observableArrayList(pjTask);
       return projectTask;
    }

    /**
     *
     * @return
     */
    public ObservableList<Project> getAllProjectsIDsAndNamesForReport() {
        
       List<Project> pj = bllManager.getAllProjectsIDsAndNamesForReport();
       pjreport = FXCollections.observableArrayList(pj);
       return pjreport;
        
        
    }

    /**
     *
     * @param reportList
     */
    public void addReportListToCSVFile(List<Report> reportList) {
        bllManager.addReportListToCSVFile(reportList);
    }
    // inputValidators
      
    /**
     *
     * @param email
     * @return
     */
    public boolean isValidEmail(String email){
         return bllManager.isValidEmail(email);
     }

    /**
     *
     * @param phoneNumber
     * @return
     */
    public boolean isValidPhoneNumber(String phoneNumber){
        return bllManager.isValidPhoneNumber(phoneNumber);
    }
    
}
