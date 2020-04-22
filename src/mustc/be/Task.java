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
    private int id;
    private String name;
    private String description;
    private Project associatedProject;  // the person whom the task is assigned to.
//    private User associatedUser;  // the person to whom the task is assigned to.
//or  private List<User> associatedUsers;  // the people to whom the task is assigned to.
    private List<Session> sessions;  //time??
//    private int taskTime;  //  total time used on a task  ... maybe

    
    public Task(int id, String name, String description, Project associatedProject, List<Session> sessions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.associatedProject = associatedProject;
        this.sessions = sessions;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getAssociatedProject() {
        return associatedProject;
    }

    public void setAssociatedProject(Project associatedProject) {
        this.associatedProject = associatedProject;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
    
    
    
}
