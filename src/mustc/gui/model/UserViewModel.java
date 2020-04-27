/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.gui.model;

import javafx.collections.ObservableList;
import mustc.be.Project;
import mustc.bll.BllManager;

/**
 *
 * @author Trigger
 */
public class UserViewModel {
    private BllManager bllManager;
    private ObservableList<Project> pjList;

    public UserViewModel() {
        bllManager = new BllManager();
        
    }
    
    public ObservableList<Project> getAllProject(){
        return null;
       // List<Project> allProjcets = bllManager.
        
    }
}
