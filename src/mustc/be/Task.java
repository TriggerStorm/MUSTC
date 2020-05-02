/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Trigger, Filip, Cecillia and Alan
 */
public class Task {
    private int taskID;
    private String taskName;
    private int associatedProjectID;  // the project that the task is assigned to.
    private String projectName;
    private float projectRate;
    private double myTaskHours;
    private double totalTaskHours;  //  total time used on a task in minutes
    String developers; 
    private List<Session> sessions;  //time??



// Full constructor    
    public Task(int taskID, String taskName , int associatedProjectID, String projectName, float projectRate, double myTaskHours, double totalTaskHours, String developers, List<Session> sessions/*, int[] totalTaskHours*/) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.associatedProjectID = associatedProjectID;
        this.projectName = projectName;
        this.projectRate = projectRate;        
        this.myTaskHours = myTaskHours;
        this.totalTaskHours = totalTaskHours;
        this.developers = developers; 
        this.sessions = sessions;
    }
    public Task(String taskName ){
        this.taskName = taskName;
        
    }
    //admin
    public Task(String taskName, String project, String devs, String taskRate, String totalHours, String totalPrice){
    
    }
    //User
    public Task(String taskName, String project, String devs, String myHours){
    
    }

    

    @Override
    public String toString() {
        return  taskName;// + taskDuration ;
    }

    

// Admin constructor for GUI
    public Task(int taskID, String taskName , int associatedProjectID, String projectName, float projectRate, double totalTaskHours, String developers/* List<Session> sessions/*, int[] totalTaskHours*/) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.associatedProjectID = associatedProjectID;
        this.projectName = projectName;
        this.projectRate = projectRate;                
        this.totalTaskHours = totalTaskHours;
        this.developers = developers; 
 //       this.sessions = sessions;
    }
    
    
// User constructor for GUI
    public Task(int taskID, String taskName, int associatedProjectID, String projectName, double myTaskHours, String developers) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.associatedProjectID = associatedProjectID;
        this.projectName = projectName;
        this.myTaskHours = myTaskHours;
        this.developers = developers; 
//        this.totalTaskHours = totalTaskHours;
    }

    
    public int getTaskID() {

        return taskID;
    }

    public void setTaskId(int taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getAssociatedProjectID() {
        return associatedProjectID;
    }

    public void setAssociatedProjectID(int associatedProjectID) {
        this.associatedProjectID = associatedProjectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public float getProjectRate() {
        return projectRate;
    }

    public void setProjectRate(float projectRate) {
        this.projectRate = projectRate;
    }
     
    public double getMyTaskHours() {
        return myTaskHours;
    }

    public void setMyTaskHours(double myTaskHours) {
        this.myTaskHours = myTaskHours;
    }
     
    public double getTotalTaskHours() {
        return totalTaskHours;
    }

    public void setTotalTaskHours(double totalTaskHours) {
        this.totalTaskHours = totalTaskHours;
    }
    
    public String getDevelopers() {
        return developers;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }
    
    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
   
 
}
