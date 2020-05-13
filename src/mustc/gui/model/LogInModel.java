/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.gui.model;

import mustc.bll.BllManager;

/**
 *
 * @author Trigger
 */
public class LogInModel {
    
    private BllManager bllManager;

    public LogInModel() {
        bllManager = new BllManager();
    }
    
    public int checkUserLogin(String loggedInUserEmail, String password){
        return bllManager.checkUserLogin(loggedInUserEmail, password);
    }
}
