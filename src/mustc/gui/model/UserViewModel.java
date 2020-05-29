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
public class UserViewModel {
    private BllManager bllManager;
    private static final AdminModel AMSingelton = new AdminModel();
    private LoggedInUser liu;
    private ObservableList<Project> pjList;
    private ObservableList<Task> taskList;
    private ObservableList<Session> sessionList; 
    private ObservableList<Task> RecentTask;
    private ObservableList<Project> pj;
    private List<Report> Reportlist;
    private ObservableList<Report> Report;
    private ObservableList<Project> clientpj;
    private ObservableList<Task> projectTask;
    private ObservableList<Project> pjreport;
     
    private ObservableList<Task> g1;
    private ObservableList<Task> g2;
    private ObservableList<Task> g3;

    public UserViewModel() {
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
    
    public ObservableList<Project> oListProject(){
    return pjList;
    }
    public ObservableList<Session> oListSession(){
    return sessionList;
    }
 
    public ObservableList<Task> projectTask(){
    return projectTask;
    }
    
    
    // Projects
    public ObservableList<Project> getAllProject(){
        
       List<Project> allProjcets = bllManager.getAllProjectsForUser();
       pjList = FXCollections.observableArrayList(allProjcets);
       return pjList;
        
    }
    
   
    
     public ObservableList<Project> getAllProjectsIDsAndNames() {
        List<Project> allProjcets =bllManager.getAllProjectsIDsAndNames();
        pj = FXCollections.observableArrayList(allProjcets);
        return pj;
    }
    
    // Task
    public ObservableList<Task> getAllTask(){
        
         List<Task> allTask = bllManager.getAllTasksForUser();
         taskList = FXCollections.observableArrayList(allTask);
         return taskList;
    }
    
    public ObservableList<Task> getUsersThreeRecentTaskss(User loggedInUser){
        
        
        List<Task> task = bllManager.getUsersThreeRecentTasks(loggedInUser);
        RecentTask = FXCollections.observableArrayList(task);
        
        return RecentTask;
        
    }
   

    //Task

    
    public Task addNewTaskToDB(String taskName, int associatedProjectID, boolean isBillable){
        Task addT = bllManager.addNewTaskToDB(taskName, associatedProjectID, isBillable);
        taskList.add(addT);
        return null;
   }
    
    public Task editTask(Task editedTask, String taskName, int associatedProjectID, boolean isBillable) {
         bllManager.editTask(editedTask, taskName, associatedProjectID, isBillable);
         return null;
    }
    
    public void removeTaskFromDB(Task taskToDelete) {
        bllManager.removeTaskFromDB(taskToDelete);
    }
    
    
    // Session
    public ObservableList<Session> getAllSession(){
          User t = bllManager.getUser(liu.getId());
          List<Session> allSession = bllManager.getAllSessionsOfAUser(t);
          sessionList = FXCollections.observableArrayList(allSession);
          return sessionList;
    }
    
    // 3 recent task 
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
    
    // Session
    public Session editSession(Session editedSession, int associatedUserID, int associatedTaskID, String startTime, String finishTime) {
        return bllManager.getSession(associatedTaskID);
    }
    
    public void removeSessionFromDB(Session sessionToDelete) {
        bllManager.removeSessionFromDB(sessionToDelete);
    }
    
    public Session addNewSessionToDB(int associatedUserID, int associatedTaskID, String associatedTaskName ,String startTime, String finishTime) {
       Session s = bllManager.addNewSessionToDB(associatedUserID, associatedTaskID, associatedTaskName, startTime, finishTime);
       sessionList.add(s);
       return null;
    }
    
    
    
    public String localDateTimeToString(LocalDateTime LDT) {
        return bllManager.localDateTimeToString(LDT);
    }
    // sertch
    
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
    
    
    public User getUser(int userID) {
        return bllManager.getUser(userID);
    }
    // Report
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
    
}
