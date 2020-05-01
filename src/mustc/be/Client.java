/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

import java.util.List;

/**
 *
 * @author Trigger and Alan
 */
public class Client {
    private int clientID;
    private String clientName;
    private String imgLocation;
    private String email;
    private float standardRate;
    private int totalHours;
    private int noOfProjects;

// Full constructor    
    public Client(int clientID, String clientName, String imgLocation, String email, float standardRate, int totalHours, int noOfProjects) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.imgLocation = imgLocation;
        this.email = email;
        this.standardRate = standardRate;
        this.totalHours = totalHours;
        this.noOfProjects = noOfProjects;
    }

    // GUI constructor1  
    public Client(int clientID, String clientName, String email, float standardRate, int totalHours, int noOfProjects) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.email = email;
        this.standardRate = standardRate;
        this.totalHours = totalHours;
        this.noOfProjects = noOfProjects;
    }

    
    public int getClientId() {
        return clientID;
    }

    public void setClientId(int clientID) {
        this.clientID = clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getImgLocation() {
        return imgLocation;
    }

    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }
 
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public float getStandardRate() {
        return standardRate;
    }

    public void setStandardRate(float standardRate) {
        this.standardRate = standardRate;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public int getNoOfProjects() {
        return noOfProjects;
    }

    public void setNoOfProjects(int noOfProjects) {
        this.noOfProjects = noOfProjects;
    }
}
