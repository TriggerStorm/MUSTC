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
    private int projectID;               // for User & Admin
    private String projectName;          // for User & Admin
    private int associatedClientID;      // for User & Admin
    private String clientName;           // for User & Admin
    private int phoneNr;                 // for User & Admin
    private float projectRate;           // for Admin
    private int allocatedHours;          // for Admin
    private int usersProjectMinutes;     // for User
    private int totalBillableMinutes;    // for Admin
    private int totalUnbillableMinutes;  // for Admin
    private int totalPrice;              // for Admin
    private List<Task> taskList;         // for User & Admin
    private int noOfTasks;
    private boolean isClosed;            // for Admin
  
    
//  Full constructor      

    /**
     *
     * @param projectID
     * @param projectName
     * @param associatedClientID
     * @param clientName
     * @param phoneNr
     * @param projectRate
     * @param allocatedHours
     * @param usersProjectMinutes
     * @param totalBillableMinutes
     * @param totalUnbillableMinutes
     * @param totalPrice
     * @param taskList
     * @param noOfTasks
     * @param isClosed
     */
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

    /**
     *
     * @param projectID
     * @param projectName
     * @param associatedClientID
     * @param clientName
     * @param phoneNr
     * @param usersProjectMinutes
     * @param taskList
     */
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

    /**
     *
     * @param projectID
     * @param projectName
     * @param clientName
     * @param phoneNr
     * @param usersProjectMinutes
     * @param noOfTasks
     */
    public Project(int projectID, String projectName, String clientName, int phoneNr, int usersProjectMinutes, int noOfTasks) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.clientName = clientName;
        this.phoneNr = phoneNr;
        this.usersProjectMinutes = usersProjectMinutes;
        this.noOfTasks = noOfTasks;
    }

    
//  Admin constructor1      

    /**
     *
     * @param projectID
     * @param projectName
     * @param clientName
     * @param phoneNr
     * @param projectRate
     * @param totalBillableMinutes
     * @param totalUnbillableMinutes
     * @param totalPrice
     * @param noOfTasks
     */
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

    /**
     *
     * @param projectName
     * @param clientName
     * @param projectRate
     * @param allocatedHours
     */
 public Project(String projectName, String clientName, float projectRate, int allocatedHours) {
        this.projectName = projectName;
        this.clientName = clientName;
        this.projectRate = projectRate;
        this.allocatedHours = allocatedHours;
       }
    
    
//  getAllProjectsIDsAndNames constructor for ProjectDBDAO and Report selection

    /**
     *
     * @param projectID
     * @param projectName
     */
    public Project(int projectID, String projectName) {
        this.projectID = projectID;
        this.projectName = projectName;
    }
    
        
//  getProjectNameAndProjectRate constructor for TaskDBDAO

    /**
     *
     * @param projectName
     * @param projectRate
     */
    public Project(String projectName, float projectRate) {
        this.projectName = projectName;
        this.projectRate = projectRate;
    }    
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return  projectName;
    }

    /**
     *
     * @return
     */
    public int getProjectID() {
        return projectID;
    }

    /**
     *
     * @param projectID
     */
    public void setProjectID(int projectID) {
        this.projectID = projectID;
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
    public int associatedClientID() {
        return associatedClientID;
    }

    /**
     *
     * @param associatedClientID
     */
    public void setAssociatedClientID(int associatedClientID) {
        this.associatedClientID = associatedClientID;
    }

    /**
     *
     * @return
     */
    public String getClientName() {
        return clientName;
    }

    /**
     *
     * @param clientName
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    
    /**
     *
     * @return
     */
    public int getPhoneNr() {
        return phoneNr;
    }

    /**
     *
     * @param phoneNr
     */
    public void setPhoneNr(int phoneNr) {
        this.phoneNr = phoneNr;
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
    public int getAllocatedHours() {
        return allocatedHours;
    }

    /**
     *
     * @param allocatedHours
     */
    public void setAllocatedHours(int allocatedHours) {
        this.allocatedHours = allocatedHours;
    }

    /**
     *
     * @return
     */
    public int getUsersProjectMinutes() {
        return usersProjectMinutes;
    }

    /**
     *
     * @param usersProjectMinutes
     */
    public void setUsersProjectMinutes(int usersProjectMinutes) {
        this.usersProjectMinutes = usersProjectMinutes;
    }
    
    /**
     *
     * @return
     */
    public double getTotalBillableMinutes() {
        return totalBillableMinutes;
    }

    /**
     *
     * @param totalBillableMinutes
     */
    public void setTotalBillableMinutes(int totalBillableMinutes) {
        this.totalBillableMinutes = totalBillableMinutes;
    }
    
    /**
     *
     * @return
     */
    public double getTotalUnbillableMinutes() {
        return totalUnbillableMinutes;
    }

    /**
     *
     * @param totalUnbillableMinutes
     */
    public void setTotalUnbillableMinutes(int totalUnbillableMinutes) {
        this.totalUnbillableMinutes = totalUnbillableMinutes;
    }
    
    /**
     *
     * @return
     */
    public int getTotalPrice() {
        return totalPrice;
    }

    /**
     *
     * @param totalPrice
     */
    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    /**
     *
     * @return
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     *
     * @param taskList
     */
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     *
     * @return
     */
    public int getNoOfTasks() {
        return noOfTasks;
    }

    /**
     *
     * @param noOfTasks
     */
    public void setNoOfTasks(int noOfTasks) {
        this.noOfTasks = noOfTasks;
    }
    
    /**
     *
     * @return
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     *
     * @param isClosed
     */
    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }
    
    
}
