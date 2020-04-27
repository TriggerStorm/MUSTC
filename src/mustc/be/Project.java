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
    private int projectID;  // for User & Admin
    private String projectName;  // for User & Admin
    private int associatedClientID;  // for User & Admin
    private String clientName;  // for User & Admin
    private int phoneNr;  // for User & Admin
    private float projectRate;  // for Admin
    private int allocatedHours;  // for Admin
    private String myHours;  // for User
    private String totalHours;  // for Admin
    private String totalPrice;  // for Admin
    private List<Task> taskList;  // for User & Admin
    private boolean isClosed;  // for Admin
  
    
//  Full constructor      
    public Project(int projectID, String projectName, int associatedClientID, String clientName, int phoneNr, float projectRate, int allocatedHours, String myHours, String totalHours, String totalPrice, List<Task> taskList, boolean isClosed) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.associatedClientID = associatedClientID;
        this.clientName = clientName;
        this.phoneNr = phoneNr;
        this.projectRate = projectRate;
        this.allocatedHours = allocatedHours;
        this.myHours = myHours;
        this.totalHours = totalHours;
        this.totalPrice = totalPrice;
        this.taskList = taskList;
        this.isClosed = isClosed;
       }
    
    
//  User constructor  
    public Project(int projectID, String projectName, int associatedClientID,  String clientName, int phoneNr, String myHours, List<Task> taskList) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.associatedClientID = associatedClientID;
        this.clientName = clientName;
        this.phoneNr = phoneNr;
        this.myHours = myHours;
        this.taskList = taskList;
    }

    
//  Admin constructor      
    public Project(int projectID, String projectName, int associatedClientID, String clientName, int phoneNr, float projectRate, int allocatedHours, String totalHours, String totalPrice, List<Task> taskList, boolean isClosed) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.associatedClientID = associatedClientID;
        this.clientName = clientName;
        this.phoneNr = phoneNr;
        this.projectRate = projectRate;
        this.allocatedHours = allocatedHours;
        this.totalHours = totalHours;
        this.totalPrice = totalPrice;
        this.taskList = taskList;
        this.isClosed = isClosed;
       }
    
    

        
    public int getProjectId() {
        return projectID;
    }

    public void setProjectId(int projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int associatedClientID() {
        return associatedClientID;
    }

    public void setAssociatedClient(int associatedClientID) {
        this.associatedClientID = associatedClientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    public int getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(int phoneNr) {
        this.phoneNr = phoneNr;
    }

    public float getProjectRate() {
        return projectRate;
    }

    public void setProjectRate(float projectRate) {
        this.projectRate = projectRate;
    }

     public int getAllocatedHours() {
        return allocatedHours;
    }

    public void setAllocatedHours(int allocatedHours) {
        this.allocatedHours = allocatedHours;
    }

    public String getMyHours() {
        return myHours;
    }

    public void getMyHours(String myHours) {
        this.myHours = myHours;
    }
    
    public String getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(String totalHours) {
        this.totalHours = totalHours;
    }
    
    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
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
