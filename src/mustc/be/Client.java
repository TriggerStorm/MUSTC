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
public class Client {
    private int clientID;
    private String clientName;
    private String imgLocation;
    private float standardRate;
    private List<Project> projectList;

    
    public Client(int id, String name, String imgLocation, float standardRate, List<Project> projectList) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.imgLocation = imgLocation;
        this.standardRate = standardRate;
        this.projectList = projectList;
    }

    
    public int getId() {
        return clientID;
    }

    public void setId(int clientID) {
        this.clientID = clientID;
    }

    public String getName() {
        return clientName;
    }

    public void setName(String clientName) {
        this.clientName = clientName;
    }

    public String getImgLocation() {
        return imgLocation;
    }

    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }

    public float getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(float standardRate) {
        this.standardRate = standardRate;
    }

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }
    
    
    
}
