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

    public UserViewModel() {
        bllManager = new BllManager();
        task = new Task("test");
        
    }
    
    
    // Projects
    public ObservableList<Project> getAllProject(){
        return null;
       // List<Project> allProjcets = bllManager.getAllProjects();
       //pjList = FXCollections.observableArrayList(allProjcets);
       //return pjList;
        
    }
    
    // Task
    public ObservableList<Task> getAllTask(){
        List<Task> allTask =  new ArrayList<>();
        allTask.add(task);
        
         // List<Task> allTask = bllManager.getAllTask();
         taskList = FXCollections.observableArrayList(allTask);
         return taskList;
    }
    public ObservableList<String> someStrings(){
        List<String> Strings = Arrays.asList("hey","you","test","done");
        sString = FXCollections.observableArrayList(Strings);
        return sString;
    }
    
    // Session
    public ObservableList<Session> getAllSession(){
        return null;
         // List<Session> allSession = bllManager.getAllSession();
         //sessionList = FXCollections.observableArrayList(allSession);
         //return sessionList;
    }
    
    
}
