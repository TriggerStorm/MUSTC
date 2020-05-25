/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

import java.util.List;

/**
 *
 * @author Trigger and Alan
 */
public class Project {
    private int projectID;              // for User & Admin
    private String projectName;         // for User & Admin
    private int associatedClientID;      // for User & Admin
    private String clientName;           // for User & Admin
    private int phoneNr;                // for User & Admin
    private float projectRate;           // for Admin
    private int allocatedHours;             // for Admin
    private int usersProjectMinutes;         // for User
    private int totalBillableMinutes;       // for Admin
    private int totalUnbillableMinutes;  // for Admin
    private int totalPrice;             // for Admin
    private List<Task> taskList;         // for User & Admin
    private int noOfTasks;
    private boolean isClosed;           // for Admin
  
    
//  Full constructor      
    public Project(int projectID, String projectName, int associatedClientID, String clientName, int phoneNr, float projectRate, int allocatedHours, int usersProjectMinutes, int totalBillableMinutes, int totalUnbillableMinutes, int totalPrice, List<Task> taskList, int noOfTasks, boolean isClosed) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.associatedClientID = associatedClientID;
        this.clientName = clientName;
        this.phoneNr = phoneNr;
        this.projectRate = projectRate;
        this.allocatedHours = allocatedHours;
        this.usersProjectMinutes = usersProjectMinutes;
        this.totalBillableMinutes = totalBillableMinutes;
        this.totalUnbillableMinutes = totalUnbillableMinutes;
        this.totalPrice = totalPrice;
        this.taskList = taskList;
        this.noOfTasks = noOfTasks;
        this.isClosed = isClosed;
       }
    
    
//  User constructor1  
    public Project(int projectID, String projectName, int associatedClientID,  String clientName, int phoneNr, int usersProjectMinutes, List<Task> taskList) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.associatedClientID = associatedClientID;
        this.clientName = clientName;
        this.phoneNr = phoneNr;
        this.usersProjectMinutes = usersProjectMinutes;
        this.taskList = taskList;
    }

    
    //  User constructor2  
    public Project(int projectID, String projectName, String clientName, int phoneNr, int usersProjectMinutes, int noOfTasks) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.clientName = clientName;
        this.phoneNr = phoneNr;
        this.usersProjectMinutes = usersProjectMinutes;
        this.noOfTasks = noOfTasks;
    }

    
//  Admin constructor1      
    public Project(int projectID, String projectName, String clientName, int phoneNr, float projectRate, int totalBillableMinutes, int totalUnbillableMinutes, int totalPrice, int noOfTasks) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.clientName = clientName;
        this.phoneNr = phoneNr;
        this.projectRate = projectRate;
        this.totalBillableMinutes = totalBillableMinutes;
        this.totalUnbillableMinutes = totalUnbillableMinutes;
        this.totalPrice = totalPrice;
        this.noOfTasks = noOfTasks;

    }
    

 //  Report constructor      
 public Project(String projectName, String clientName, float projectRate, int allocatedHours) {
        this.projectName = projectName;
        this.clientName = clientName;
        this.projectRate = projectRate;
        this.allocatedHours = allocatedHours;
       }
    
    
//  getAllProjectsIDsAndNames constructor for ProjectDBDAO and Report selection
    public Project(int projectID, String projectName) {
        this.projectID = projectID;
        this.projectName = projectName;
    }
    
        
//  getProjectNameAndProjectRate constructor for TaskDBDAO
    public Project(String projectName, float projectRate) {
        this.projectName = projectName;
        this.projectRate = projectRate;
    }    
    
    
    @Override
    public String toString() {
        return  projectName;
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

    public int associatedClientID() {
        return associatedClientID;
    }

    public void setAssociatedClientID(int associatedClientID) {
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

    public int getUsersProjectMinutes() {
        return usersProjectMinutes;
    }

    public void setUsersProjectMinutes(int usersProjectMinutes) {
        this.usersProjectMinutes = usersProjectMinutes;
    }
    
    public double getTotalBillableMinutes() {
        return totalBillableMinutes;
    }

    public void setTotalBillableMinutes(int totalBillableMinutes) {
        this.totalBillableMinutes = totalBillableMinutes;
    }
    
    public double getTotalUnbillableMinutes() {
        return totalUnbillableMinutes;
    }

    public void setTotalUnbillableMinutes(int totalUnbillableMinutes) {
        this.totalUnbillableMinutes = totalUnbillableMinutes;
    }
    
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int getNoOfTasks() {
        return noOfTasks;
    }

    public void setNoOfTasks(int noOfTasks) {
        this.noOfTasks = noOfTasks;
    }
    
    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }
    
    
}
