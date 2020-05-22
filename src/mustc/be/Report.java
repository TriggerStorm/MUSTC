/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

/**
 *
 * @author Trigger and Alan
 */
public class Report {
    private String clientName;
    private String projectName;
    private String taskName;
    private String loggedInUser;  // the person whom the Session is assigned to.    
    private String startTime;   // LDT?
    private String finishTime;  // LDT?
    private int totalBillableMinutes;       // for Admin
    private int totalPrice;             // for Admin

    
// Full constructor            
    public Report(String clientName, String projectName, String taskName, String loggedInUser, String startTime, String finishTime, int totalBillableMinutes, int totalPrice) {
        this.clientName = clientName;
        this.projectName = projectName;
        this.taskName = taskName;
        this.loggedInUser = loggedInUser;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.totalBillableMinutes = totalBillableMinutes;
        this.totalPrice = totalPrice;
}

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
 
    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public int getTotalBillableMinutes() {
        return totalBillableMinutes;
    }

    public void setTotalBillableMinutes(int totalBillableMinutes) {
        this.totalBillableMinutes = totalBillableMinutes;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }










}
