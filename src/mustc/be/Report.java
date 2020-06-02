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
    private String startTime;     // LDT?
    private String finishTime;    // LDT?
    private String minutes;       // for Admin
    private String billable;
    private String revenue;             // for Admin


    
// Full constructor            

    /**
     *
     * @param clientName
     * @param projectName
     * @param taskName
     * @param loggedInUser
     * @param startTime
     * @param finishTime
     * @param minutes
     * @param billable
     * @param revenue
     */
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
    public String getLoggedInUser() {
        return loggedInUser;
    }

    /**
     *
     * @param loggedInUser
     */
    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    /**
     *
     * @return
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     *
     * @param startTime
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return
     */
    public String getFinishTime() {
        return finishTime;
    }

    /**
     *
     * @param finishTime
     */
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    /**
     *
     * @return
     */
    public String getMinutes() {
        return minutes;
    }

    /**
     *
     * @param minutes
     */
    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    /**
     *
     * @return
     */
    public String getBillable() {
        return billable;
    }

    /**
     *
     * @param billable
     */
    public void setBillable(String billable) {
        this.billable = billable;
    }

    /**
     *
     * @return
     */
    public String getRevenue() {
        return revenue;
    }

    /**
     *
     * @param revenue
     */
    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }










}
