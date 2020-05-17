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
    private LocalDateTime startLDT;  // TEMP
    private LocalDateTime finishLDT;  // TEMP
    private int duration;  //  difference between start time and finish time ...maybe?

    
// Full constructor        
    public Session(int sessionID, int associatedUserID, String associatedUserName, int associatedTaskID, String associatedTaskName, String startTime, String finishTime, LocalDateTime startLDT, LocalDateTime finishLDT, int duration) {
        this.sessionID = sessionID;
//        this.description = description;
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
    public Session(int sessionID, int associatedTaskID, String associatedTaskName, String startTime, String finishTime) {
        this.sessionID = sessionID;
        this.associatedTaskID = associatedTaskID;
        this.associatedTaskName = associatedTaskName;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
    
    
 /*// getAllSessionsOfATask constructor        
    public Session(int sessionID, int associatedUserID, String associatedUserName, String startTime, String finishTime) {
        this.sessionID = sessionID;
        this.associatedUserID = associatedUserID;
        this.associatedUserName = associatedUserName;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }  
  */  
    
// getDuration constructor        
    public Session(int sessionID, int associatedTaskID, String associatedTaskName, String startTime, String finishTime, int duration) {
        this.sessionID = sessionID;
//        this.description = description;
        this.associatedTaskID = associatedTaskID;
        this.associatedTaskName = associatedTaskName;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }
    
    
// Ordering by DateTime constructor        
    public Session(int sessionID, int associatedTaskID, String associatedTaskName, LocalDateTime startLDT, LocalDateTime finishLDT) {
        this.sessionID = sessionID;
//        this.description = description;
        this.associatedTaskID = associatedTaskID;
        this.associatedTaskName = associatedTaskName; // Do I need this?
        this.startLDT = startLDT;
        this.finishLDT = finishLDT; // Do I need this?
    }

    
// RecentTasks constructor            
    public Session(int associatedTaskID, LocalDateTime startLDT) {
        this.associatedTaskID = associatedTaskID;
        this.startLDT = startLDT;
   }

    
   @Override
    public int compareTo(Session session) {   
    // Comparator used for getting recent tasks using sessionDBDao
        System.out.println("this LDT = "+ this.startLDT);
        System.out.println("session LDT = "+ session.startLDT);
       return this.startLDT.compareTo(session.startLDT);  //  https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date#5927408
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
        return associatedTaskName;
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
    
     public LocalDateTime getStartLDT() {
        return startLDT;
    }

    public void setStartLDT(LocalDateTime startLDT) {
        this.startLDT = startLDT;
    }

    public LocalDateTime getFinishLDT() {
        return finishLDT;
    }

    public void setFinishLDT(LocalDateTime finishLDT) {
        this.finishLDT = finishLDT;
    }
     
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    
}
