/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

import java.util.List;

/**
 *
 * @author Trigger, Filip, Cecillia and Alan
 */
public class Task {
    private int taskID;
    private String taskName;
    private String description;
    private int /*(Project?)*/ associatedProjectID;  // the person whom the task is assigned to.
//    private User associatedUser;  // the person to whom the task is assigned to.
//or  private List<User> associatedUsers;  // the people to whom the task is assigned to.
    private List<Session> sessions;  //time??
//    private int taskTime;  //  total time used on a task  ... maybe

    
    public Task(int taskID, String name, String description, int associatedProject, List<Session> sessions) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.description = description;
        this.associatedProjectID = associatedProjectID;
        this.sessions = sessions;
    }

    
    public int getId() {
        return taskID;
    }

    public void setId(int id) {
        this.taskID = taskID;
    }

    public String getName() {
        return taskName;
    }

    public void setName(String name) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAssociatedProjectID() {
        return associatedProjectID;
    }

    public void setAssociatedProject(int associatedProjectID) {
        this.associatedProjectID = associatedProjectID;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
    
    
    
}
