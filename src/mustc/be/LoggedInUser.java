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
public class LoggedInUser {
    private static LoggedInUser instance = null;
    private int userKey;
    private String userName;
    private String password;
    private int userRank;  // 0 = developer, 1 = admin, 2 = project owner... maybe . Could be a String
    private Task currentTask;
}
