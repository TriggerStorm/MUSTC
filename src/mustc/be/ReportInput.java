/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

import java.time.LocalDate;

/**
 *
 * @author Trigger and Alan
 */
public class ReportInput {
    private int clientID;
    private int projectID;
    private int taskID;
    private int userID;
    private LocalDate searchFrom;
    private LocalDate searchTo;
    
//  Full constructor
    public ReportInput(int clientID, int projectID, int taskID, int userID, LocalDate searchFrom, LocalDate searchTo) {
        this.clientID = clientID;
        this.projectID = projectID;
        this.taskID = taskID;
        this.userID = userID;
        this.searchFrom = searchFrom;
        this.searchTo = searchTo;
    }

    
    
    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDate getSearchFrom() {
        return searchFrom;
    }

    public void setSearchFrom(LocalDate searchFrom) {
        this.searchFrom = searchFrom;
    }

    public LocalDate getSearchTo() {
        return searchTo;
    }

    public void setSearchTo(LocalDate searchTo) {
        this.searchTo = searchTo;
    }
    
    
    
    
}
