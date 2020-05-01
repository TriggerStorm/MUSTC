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
public class Session {
    private int sessionID;
//      private String description;
    private int associatedUserID;  // the person whom the Session is assigned to.
    private int associatedTaskID;  // the Task whom the Session is assigned to.
    private String startTime;
    private String finishTime;
//    private int sessionTime;  //  difference between start time and finish time ...maybe?

    
    public Session(int sessionID, int associatedUserID, int associatedTaskID, String startTime, String finishTime) {
        this.sessionID = sessionID;
//          this.description = description;
        this.associatedUserID = associatedUserID;
        this.associatedTaskID = associatedTaskID;
        this.startTime = startTime;
        this.finishTime = finishTime;
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

    public int getAssociatedTaskID() {
        return associatedTaskID;
    }

    public void setAssociatedTaskID(int associatedTaskID) {
        this.associatedTaskID = associatedTaskID;
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
