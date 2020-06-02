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
    private int associatedUserID;                           // the person whom the Session is assigned to.
    private String associatedUserName;                       // the person whom the Session is assigned to.    
    private int associatedTaskID;                           // the Task whom the Session is assigned to.
    private String associatedTaskName;                       // the person whom the Session is assigned to.    
    private String startTime;
    private String finishTime;
    private LocalDateTime startLDT;
    private LocalDateTime finishLDT;
    private int duration;                                   //  difference between start time and finish time ...maybe?

    
// Full constructor        

    /**
     *
     * @param sessionID
     * @param associatedUserID
     * @param associatedUserName
     * @param associatedTaskID
     * @param associatedTaskName
     * @param startTime
     * @param finishTime
     * @param startLDT
     * @param finishLDT
     * @param duration
     */
    public Session(int sessionID, int associatedUserID, String associatedUserName, int associatedTaskID, String associatedTaskName, String startTime, String finishTime, LocalDateTime startLDT, LocalDateTime finishLDT, int duration) {
        this.sessionID = sessionID;
        this.associatedUserID = associatedUserID;
        this.associatedUserName = associatedUserName;
        this.associatedTaskID = associatedTaskID;
        this.associatedTaskName = associatedTaskName;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.startLDT = startLDT;
        this.finishLDT = finishLDT;
        this.duration = duration;
    }

// Admin constructor        

    /**
     *
     * @param sessionID
     * @param associatedUserID
     * @param associatedUserName
     * @param associatedTaskID
     * @param associatedTaskName
     * @param startTime
     * @param finishTime
     */
    public Session(int sessionID, int associatedUserID, String associatedUserName, int associatedTaskID, String associatedTaskName, String startTime, String finishTime) {
        this.sessionID = sessionID;
        this.associatedUserID = associatedUserID;
        this.associatedUserName = associatedUserName;
        this.associatedTaskID = associatedTaskID;
        this.associatedTaskName = associatedTaskName;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

// User constructor        

    /**
     *
     * @param sessionID
     * @param associatedTaskID
     * @param associatedTaskName
     * @param startTime
     * @param finishTime
     */
    public Session(int sessionID, int associatedTaskID, 
            String associatedTaskName, String startTime, 
            String finishTime) {
        this.sessionID = sessionID;
        this.associatedTaskID = associatedTaskID;
        this.associatedTaskName = associatedTaskName;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
    
    
 
    
    // getDuration constructor        

    /**
     *
     * @param sessionID
     * @param associatedTaskID
     * @param associatedTaskName
     * @param startTime
     * @param finishTime
     * @param duration
     */
    public Session(int sessionID, int associatedTaskID, 
            String associatedTaskName, String startTime, 
            String finishTime, int duration) {
        this.sessionID = sessionID;
        this.associatedTaskID = associatedTaskID;
        this.associatedTaskName = associatedTaskName;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
    
    
// Ordering by DateTime constructor        

    /**
     *
     * @param sessionID
     * @param associatedTaskID
     * @param associatedTaskName
     * @param startLDT
     * @param finishLDT
     */
    public Session(int sessionID, int associatedTaskID, String associatedTaskName, LocalDateTime startLDT, LocalDateTime finishLDT) {
        this.sessionID = sessionID;
//        this.description = description;
        this.associatedTaskID = associatedTaskID;
        this.associatedTaskName = associatedTaskName; // Do I need this?
        this.startLDT = startLDT;
        this.finishLDT = finishLDT; // Do I need this?
    }

    
// RecentTasks constructor            

    /**
     *
     * @param associatedTaskID
     * @param startLDT
     */
    public Session(int associatedTaskID, LocalDateTime startLDT) {
        this.associatedTaskID = associatedTaskID;
        this.startLDT = startLDT;
   }

    /**
     *
     * @param session
     * @return
     */
    @Override
    public int compareTo(Session session) {   
    // Comparator used for getting recent tasks using sessionDBDao
       return this.startLDT.compareTo(session.startLDT);  //  https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date#5927408
  }   
    
    /**
     *
     * @return
     */
    public int getSessionID() {
        return sessionID;
    }

    /**
     *
     * @param sessionID
     */
    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    /**
     *
     * @return
     */
    public int getAssociatedUserID() {
        return associatedUserID;
    }

    /**
     *
     * @param associatedUserID
     */
    public void setAssociatedUserID(int associatedUserID) {
        this.associatedUserID = associatedUserID;
    }

    /**
     *
     * @return
     */
    public String getAssociatedUserName() {
        return associatedUserName;
    }

    /**
     *
     * @param associatedUserName
     */
    public void setAssociatedUserName(String associatedUserName) {
        this.associatedUserName = associatedUserName;
    }
    
    /**
     *
     * @return
     */
    public int getAssociatedTaskID() {
        return associatedTaskID;
    }

    /**
     *
     * @param associatedTaskID
     */
    public void setAssociatedTaskID(int associatedTaskID) {
        this.associatedTaskID = associatedTaskID;
    }

    /**
     *
     * @return
     */
    public String getAssociatedTaskName() {
        return associatedTaskName;
    }

    /**
     *
     * @param associatedTaskName
     */
    public void setAssociatedTaskName(String associatedTaskName) {
        this.associatedTaskName = associatedTaskName;
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
    public LocalDateTime getStartLDT() {
        return startLDT;
    }

    /**
     *
     * @param startLDT
     */
    public void setStartLDT(LocalDateTime startLDT) {
        this.startLDT = startLDT;
    }

    /**
     *
     * @return
     */
    public LocalDateTime getFinishLDT() {
        return finishLDT;
    }

    /**
     *
     * @param finishLDT
     */
    public void setFinishLDT(LocalDateTime finishLDT) {
        this.finishLDT = finishLDT;
    }
     
    /**
     *
     * @return
     */
    public int getDuration() {
        return duration;
    }

    /**
     *
     * @param duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    
}
