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
    private int id;
    private String name;
    private String imgLocation;
    private float standardRate;
    private List<Project> projectList;

    
    public Client(int id, String name, String imgLocation, float standardRate, List<Project> projectList) {
        this.id = id;
        this.name = name;
        this.imgLocation = imgLocation;
        this.standardRate = standardRate;
        this.projectList = projectList;
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
