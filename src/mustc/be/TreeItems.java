/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

import java.util.List;

/**
 *
 * @author Trigger
 */
public class TreeItems {
     private String clientName;
     private int projectID;
     private String projectName;
     private List<Task> tasklist;
     //private int taskID;
     //private String taskName;

    public TreeItems(String clientName, int projectID, String projectName, List<Task> tasklist) {
        this.clientName = clientName;
        this.projectID = projectID;
        this.projectName = projectName;
        this.tasklist = tasklist;
    }

    public TreeItems() {
        this.clientName = clientName;
        this.projectID = projectID;
        this.projectName = projectName;
        this.tasklist = tasklist;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Task> getTasklist() {
        return tasklist;
    }

    public void setTasklist(List<Task> tasklist) {
        this.tasklist = tasklist;
    }
    
}
