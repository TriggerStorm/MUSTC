/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

/**
 *
 * @author Trigger, Filip, Cecillia and Alan
 */
public class Session {
    private int sessionID;
    private int associatedUserID;  // the person whom the Session is assigned to.
    private int /*(Task?)*/ associatedTaskID;  // the Task whom the Session is assigned to.
    private int startTime;
    private int finishTime;
//    private int sessionTime;  //  difference between start time and finish time ...maybe?

    
    public Session(int sessionID, User associatedUser, int associatedTask, int startTime, int finishTime) {
        this.sessionID = sessionID;
        this.associatedUserID = associatedUserID;
        this.associatedTaskID = associatedTaskID;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    
    public int getId() {
        return sessionID;
    }

    public void setId(int id) {
        this.sessionID = sessionID;
    }

    public int getAssociatedUserID() {
        return associatedUserID;
    }

    public void setAssociatedUserID(int associatedUserID) {
        this.associatedUserID = associatedUserID;
    }

    public int getAssociatedTask() {
        return associatedTaskID;
    }

    public void setAssociatedTask(int associatedTaskID) {
        this.associatedTaskID = associatedTaskID;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }
    
    
    
}
