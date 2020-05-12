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
    private int usersTaskMinutes;
    private int totalTaskMinutes;  //  total time used on a task in minutes
    String developers; 
    private List<Session> sessions;  //time??
    private boolean isBillable;


// Full constructor    
    public Task(int taskID, String taskName, int associatedProjectID, String projectName, float projectRate, int usersTaskMinutes, int totalTaskMinutes, String developers, List<Session> sessions, boolean isBillable) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.associatedProjectID = associatedProjectID;
        this.projectName = projectName;
        this.projectRate = projectRate;        
        this.usersTaskMinutes = usersTaskMinutes;
        this.totalTaskMinutes = totalTaskMinutes;
        this.developers = developers; 
        this.sessions = sessions;
        this.isBillable = isBillable;
    }


// Admin constructor for GUI
    public Task(int taskID, String taskName , int associatedProjectID, String projectName, float projectRate, int totalTaskMinutes, String developers/* List<Session> sessions*/, boolean isBillable) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.associatedProjectID = associatedProjectID;
        this.projectName = projectName;
        this.projectRate = projectRate;                
        this.totalTaskMinutes = totalTaskMinutes;
        this.developers = developers; 
 //       this.sessions = sessions;
        this.isBillable = isBillable;
    }
    
    
// User constructor for GUI
    public Task(int taskID, String taskName, int associatedProjectID, String projectName, int usersTaskMinutes, String developers) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.associatedProjectID = associatedProjectID;
        this.projectName = projectName;
        this.usersTaskMinutes = usersTaskMinutes;
        this.developers = developers; 
//        this.totalTaskMinutes = totalTaskMinutes;
    }

    
    public Task(String taskName, int totalTaskMinutes ){
        this.taskName = taskName;
        this.totalTaskMinutes = totalTaskMinutes;
        
    }
    
    @Override
    public String toString() {
        return  taskName;// + taskDuration ;
    }
    
   /* @Override
    public String toString() {
        return "Task{" + "taskID=" + taskID + ", taskName=" + taskName + ", associatedProjectID=" + associatedProjectID + ", projectName=" + projectName + ", projectRate=" + projectRate + ", usersTaskMinutes=" + usersTaskMinutes + ", totalTaskMinutes=" + totalTaskMinutes + ", developers=" + developers + ", sessions=" + sessions + '}';
    }*/
    

    

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
     
    public int setUsersTaskMinutes() {
        return usersTaskMinutes;
    }

    public void setUsersTaskMinutes(int usersTaskMinutes) {
        this.usersTaskMinutes = usersTaskMinutes;
    }
     
    public int getTotalTaskMinutes() {
        return totalTaskMinutes;
    }

    public void setTotalTaskMinutes(int totalTaskMinutes) {
        this.totalTaskMinutes = totalTaskMinutes;
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
   
    public boolean getIsBillable() {
        return isBillable;
    }

    public void setIsBillable(boolean isBillable) {
        this.isBillable = isBillable;
    }
    
}
