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
    private int id;
    private User associatedUser;  // the person whom the task is assigned to.
    private Task associatedTask;  // the person whom the task is assigned to.
    private int startTime;
    private int finishTime;
//    private int sessionTime;  //  difference between start time and finish time ...maybe?

    
    public Session(int id, User associatedUser, Task associatedTask, int startTime, int finishTime) {
        this.id = id;
        this.associatedUser = associatedUser;
        this.associatedTask = associatedTask;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAssociatedUser() {
        return associatedUser;
    }

    public void setAssociatedUser(User associatedUser) {
        this.associatedUser = associatedUser;
    }

    public Task getAssociatedTask() {
        return associatedTask;
    }

    public void setAssociatedTask(Task associatedTask) {
        this.associatedTask = associatedTask;
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
