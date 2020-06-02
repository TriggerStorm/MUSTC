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
 * @author Trigger and Alan
 */
public class Task {
    private int taskID;
    private String taskName;
    private int associatedProjectID;  // the project that the task is assigned to.
    private String projectName;
    private float projectRate;
    private int usersTaskMinutes;
    private int totalTaskMinutes;       //  total time used on a task in minutes
    String developers; 
    private List<Session> sessions; 
    private boolean isBillable;


// Full constructor    

    /**
     *
     * @param taskID
     * @param taskName
     * @param associatedProjectID
     * @param projectName
     * @param projectRate
     * @param usersTaskMinutes
     * @param totalTaskMinutes
     * @param developers
     * @param sessions
     * @param isBillable
     */
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

    /**
     *
     * @param taskID
     * @param taskName
     * @param associatedProjectID
     * @param projectName
     * @param projectRate
     * @param totalTaskMinutes
     * @param developers
     * @param isBillable
     */
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

    /**
     *
     * @param taskID
     * @param taskName
     * @param associatedProjectID
     * @param projectName
     * @param usersTaskMinutes
     * @param developers
     */
    public Task(int taskID, String taskName, int associatedProjectID, String projectName, int usersTaskMinutes, String developers) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.associatedProjectID = associatedProjectID;
        this.projectName = projectName;
        this.usersTaskMinutes = usersTaskMinutes;
        this.developers = developers; 
    }

    
// Report constructor    

    /**
     *
     * @param taskName
     * @param associatedProjectID
     * @param isBillable
     */
    public Task(String taskName, int associatedProjectID, boolean isBillable) {
        this.taskName = taskName;
        this.associatedProjectID = associatedProjectID;
        this.isBillable = isBillable;
    }
    
    
// Report selection constructor (NB associatedProjectID only used as constructor with (int, String) used below. 

    /**
     *
     * @param taskID
     * @param taskName
     * @param associatedProjectID
     */
    public Task(int taskID, String taskName, int associatedProjectID) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.associatedProjectID = associatedProjectID;
    }
    

//  Constructor to pass a Tasks Name and  it total minutes    

    /**
     *
     * @param taskName
     * @param totalTaskMinutes
     */
    public Task(String taskName, int totalTaskMinutes ){
        this.taskName = taskName;
        this.totalTaskMinutes = totalTaskMinutes;
    }

    
//  Constructor used to return totalTaskMinutes and developers from getAllSessions method

    /**
     *
     * @param totalTaskMinutes
     * @param developers
     */
    public Task(int totalTaskMinutes, String developers ){  
        this.totalTaskMinutes = totalTaskMinutes;
        this.developers = developers; 
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return  taskName;// + taskDuration ;
    }
    
   /* @Override
    public String toString() {
        return "Task{" + "taskID=" + taskID + ", taskName=" + taskName + ", associatedProjectID=" + associatedProjectID + ", projectName=" + projectName + ", projectRate=" + projectRate + ", usersTaskMinutes=" + usersTaskMinutes + ", totalTaskMinutes=" + totalTaskMinutes + ", developers=" + developers + ", sessions=" + sessions + '}';
    }*/
    
    /**
     *
     * @return
     */
    public int getTaskID() {

        return taskID;
    }

    /**
     *
     * @param taskID
     */
    public void setTaskId(int taskID) {
        this.taskID = taskID;
    }

    /**
     *
     * @return
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     *
     * @param taskName
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     *
     * @return
     */
    public int getAssociatedProjectID() {
        return associatedProjectID;
    }

    /**
     *
     * @param associatedProjectID
     */
    public void setAssociatedProjectID(int associatedProjectID) {
        this.associatedProjectID = associatedProjectID;
    }

    /**
     *
     * @return
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     *
     * @param projectName
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    /**
     *
     * @return
     */
    public float getProjectRate() {
        return projectRate;
    }

    /**
     *
     * @param projectRate
     */
    public void setProjectRate(float projectRate) {
        this.projectRate = projectRate;
    }
     
    /**
     *
     * @return
     */
    public int getUsersTaskMinutes() {
        return usersTaskMinutes;
    }

    /**
     *
     * @param usersTaskMinutes
     */
    public void setUsersTaskMinutes(int usersTaskMinutes) {
        this.usersTaskMinutes = usersTaskMinutes;
    }
     
    /**
     *
     * @return
     */
    public int getTotalTaskMinutes() {
        return totalTaskMinutes;
    }

    /**
     *
     * @param totalTaskMinutes
     */
    public void setTotalTaskMinutes(int totalTaskMinutes) {
        this.totalTaskMinutes = totalTaskMinutes;
    }
    
    /**
     *
     * @return
     */
    public String getDevelopers() {
        return developers;
    }

    /**
     *
     * @param developers
     */
    public void setDevelopers(String developers) {
        this.developers = developers;
    }
    
    /**
     *
     * @return
     */
    public List<Session> getSessions() {
        return sessions;
    }

    /**
     *
     * @param sessions
     */
    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
   
    /**
     *
     * @return
     */
    public boolean getIsBillable() {
        return isBillable;
    }

    /**
     *
     * @param isBillable
     */
    public void setIsBillable(boolean isBillable) {
        this.isBillable = isBillable;
    }
    
}
