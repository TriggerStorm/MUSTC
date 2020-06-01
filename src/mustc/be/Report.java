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
    private String startTime;    
    private String finishTime;  
    private String minutes;      
    private String billable;
    private String revenue;        


    
// Full constructor            
    public Report(String clientName, String projectName, String taskName, String loggedInUser, String startTime, String finishTime, String minutes, String billable, String revenue) {
        this.clientName = clientName;
        this.projectName = projectName;
        this.taskName = taskName;
        this.loggedInUser = loggedInUser;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.minutes = minutes;
        this.billable = billable;
        this.revenue = revenue;

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

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getBillable() {
        return billable;
    }

    public void setBillable(String billable) {
        this.billable = billable;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }










}
