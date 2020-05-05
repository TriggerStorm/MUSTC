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
import mustc.be.Project;
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
    private ObservableList<Project> pjList;
    private ObservableList<Task> taskList;
    private ObservableList<Session> sessionList; 
    private ObservableList<String> sString; 
    private Task task;
    private ObservableList<Project> pj;
    
    
    private ObservableList<Task> g1;
    private ObservableList<Task> g2;
    private ObservableList<Task> g3;

    public UserViewModel() {
        bllManager = new BllManager();
        
        
        
    }
    
    
    // Projects
    public ObservableList<Project> getAllProject(){
        
       List<Project> allProjcets = bllManager.getAllProjectsForUser();
       pjList = FXCollections.observableArrayList(allProjcets);
       return pjList;
        
    }
    
    public ObservableList<Project> getAllProjectList(){
      List<Project> allProjcets = bllManager.getAllProjectsForAdmin();
      pj = FXCollections.observableArrayList(allProjcets);
      
      return pj;
    }
    
    // Task
    public ObservableList<Task> getAllTask(){
        return null;
        /* List<Task> allTask = bllManager.getAllUsersTasks();
         taskList = FXCollections.observableArrayList(allTask);
         return taskList;*/
    }
   
    
   
    // Session
    public ObservableList<Session> getAllSession(){
        return null;
         // List<Session> allSession = bllManager.getAllSession();
         //sessionList = FXCollections.observableArrayList(allSession);
         //return sessionList;
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
    
    
}
