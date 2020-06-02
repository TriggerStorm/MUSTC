/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

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

    /**
     *
     * @param clientID
     * @param clientName
     * @param imgLocation
     * @param email
     * @param standardRate
     * @param totalHours
     * @param noOfProjects
     */
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

    /**
     *
     * @param clientID
     * @param clientName
     * @param email
     * @param standardRate
     * @param totalHours
     * @param noOfProjects
     */
    public Client(int clientID, String clientName, String email, float standardRate, int totalHours, int noOfProjects) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.email = email;
        this.standardRate = standardRate;
        this.totalHours = totalHours;
        this.noOfProjects = noOfProjects;
    }

// Report selection constructor  

    /**
     *
     * @param clientID
     * @param clientName
     */
    public Client(int clientID, String clientName) {
        this.clientID = clientID;
        this.clientName = clientName;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return clientName;
    }
    
    /**
     *
     * @return
     */
    public int getClientId() {
        return clientID;
    }

    /**
     *
     * @param clientID
     */
    public void setClientId(int clientID) {
        this.clientID = clientID;
    }

    /**
     *
     * @return
     */
    public String getClientName() {
        return clientName;
    }

    /**
     *
     * @param clientName
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     *
     * @return
     */
    public String getImgLocation() {
        return imgLocation;
    }

    /**
     *
     * @param imgLocation
     */
    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }
 
    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     *
     * @return
     */
    public float getStandardRate() {
        return standardRate;
    }

    /**
     *
     * @param standardRate
     */
    public void setStandardRate(float standardRate) {
        this.standardRate = standardRate;
    }

    /**
     *
     * @return
     */
    public int getTotalHours() {
        return totalHours;
    }

    /**
     *
     * @param totalHours
     */
    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    /**
     *
     * @return
     */
    public int getNoOfProjects() {
        return noOfProjects;
    }

    /**
     *
     * @param noOfProjects
     */
    public void setNoOfProjects(int noOfProjects) {
        this.noOfProjects = noOfProjects;
    }
}
