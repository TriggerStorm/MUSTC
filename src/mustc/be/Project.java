/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

import java.util.List;

/**
 *
 * @author Trigger, Filip, Cecillia and Alan
 */
public class Project {
    private int projectID;
    private String projectName;
    private int associatedClientID;  // the person whom the task is assigned to.
    private float projectRate;
    private int hoursAllocated;
    private List<Task> taskList;
    private boolean isClosed;
    

    public Project(int projectID, String projectName, int associatedClientID, float projectRate, int hoursAllocated, List<Task> taskList, boolean isClosed) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.associatedClientID = associatedClientID;
        this.projectRate = projectRate;
        this.taskList = taskList;
        this.isClosed = isClosed;
    }

    
    public int getId() {
        return projectID;
    }

    public void setId(int projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return projectName;
    }

    public void setName(String projectName) {
        this.projectName = projectName;
    }

    public int associatedClientID() {
        return associatedClientID;
    }

    public void setAssociatedClient(int associatedClientID) {
        this.associatedClientID = associatedClientID;
    }

    public float getProjectRate() {
        return projectRate;
    }

    public void setProjectRate(float projectRate) {
        this.projectRate = projectRate;
    }

     public int getHoursAllocated() {
        return hoursAllocated;
    }

    public void setHoursAllocated(int hoursAllocated) {
        this.hoursAllocated = hoursAllocated;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }
    
    
}
