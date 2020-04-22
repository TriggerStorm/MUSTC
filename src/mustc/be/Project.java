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
public class Project {
    private int id;
    private String name;
    private Client associatedClient;  // the person whom the task is assigned to.
    private float projectRate;
    private List<Task> taskList;
    private boolean closed;
    

    public Project(int id, String name, Client associatedClient, float projectRate, List<Task> taskList, boolean closed) {
        this.id = id;
        this.name = name;
        this.associatedClient = associatedClient;
        this.projectRate = projectRate;
        this.taskList = taskList;
        this.closed = closed;
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

    public Client getAssociatedClient() {
        return associatedClient;
    }

    public void setAssociatedClient(Client associatedClient) {
        this.associatedClient = associatedClient;
    }

    public float getProjectRate() {
        return projectRate;
    }

    public void setProjectRate(float projectRate) {
        this.projectRate = projectRate;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
    
    
}
