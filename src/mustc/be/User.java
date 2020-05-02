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
public class User {
    
    private int userID;
    private String userName;
    private String email;
    private String password;
    private float salary;  // do we really need this?
    private String status;
 //   private List<Task> usersTasks;  //  may be faster to process if we have this.

    
 // Full constructor         
    public User(int userID, String userName, String email, String password, float salary, String status) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.salary = salary;
        this.status = status;
    }

//User constructor for GUI (Admin only)
    public User(int userID, String userName, String email , float salary, String status) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.salary = salary;       
        this.status = status;
    }

    
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

     public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
