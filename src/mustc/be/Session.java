/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

import java.time.LocalDateTime;

/**
 *
 * @author Trigger and Alan
 */
public class Session implements Comparable<Session>{
    private int sessionID;
//      private String description;
    private int associatedUserID;  // the person whom the Session is assigned to.
    private String associatedUserName;  // the person whom the Session is assigned to.    
    private int associatedTaskID;  // the Task whom the Session is assigned to.
    private String associatedTaskName;  // the person whom the Session is assigned to.    
    private String startTime;
    private String finishTime;
//    private int duration;  //  difference between start time and finish time ...maybe?

    
// Full constructor        
    public Session(int sessionID, int associatedUserID, String associatedUserName, int associatedTaskID, String associatedTaskName, String startTime, String finishTime) {
        this.sessionID = sessionID;
//        this.description = description;
        this.associatedUserID = associatedUserID;
        this.associatedUserName = associatedUserName;
        this.associatedTaskID = associatedTaskID;
        this.associatedTaskName = associatedTaskName;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

// User constructor        
    public Session(int sessionID, int associatedTaskID, String associatedTaskName, String startTime, String finishTime) {
        this.sessionID = sessionID;
//        this.description = description;
        this.associatedTaskID = associatedTaskID;
        this.associatedUserName = associatedTaskName;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
 /*   
// Admin constructor        
    public Session(int sessionID, int associatedUserID, String associatedUserName, String startTime, String finishTime) {
        this.sessionID = sessionID;
//        this.description = description;
        this.associatedUserID = associatedUserID;
        this.associatedUserName = associatedUserName;
        this.associatedTaskID = associatedTaskID;
        this.associatedUserName = associatedUserName;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
 */   
    

    
// RecentTasks constructor            
    public Session(int associatedTaskID, String startTime) {
        this.associatedTaskID = associatedTaskID;
        this.startTime = startTime;
    }

   @Override
    public int compareTo(Session session) {   // EXPERIMENT for getting latest tasks using sessionDBDao
        return Integer.compare(this.getAssociatedUserID(), session.getAssociatedTaskID()); //was getAssociatedTaskID, worked but not right
        //       return this.getAssociatedUserID().compareTo(session.getAssociatedTaskID());
    }   
    
    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getAssociatedUserID() {
        return associatedUserID;
    }

    public void setAssociatedUserID(int associatedUserID) {
        this.associatedUserID = associatedUserID;
    }

    public String getAssociatedUserName() {
        return associatedUserName;
    }

    public void setAssociatedUserName(String associatedUserName) {
        this.associatedUserName = associatedUserName;
    }
    
    public int getAssociatedTaskID() {
        return associatedTaskID;
    }

    public void setAssociatedTaskID(int associatedTaskID) {
        this.associatedTaskID = associatedTaskID;
    }

    public String getAssociatedTaskName() {
        return associatedUserName;
    }

    public void setAssociatedTaskName(String associatedTaskName) {
        this.associatedTaskName = associatedTaskName;
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
    
    
    
}
