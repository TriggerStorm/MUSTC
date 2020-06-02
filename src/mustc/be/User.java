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
public class User {
    
    private int userID;
    private String userName;
    private String email;
    private String password;
    private float salary;  // do we really need this?
    private String status;
 //   private List<Task> usersTasks;  //  may be faster to process if we have this.

    
 // Full constructor         

    /**
     *
     * @param userID
     * @param userName
     * @param email
     * @param password
     * @param salary
     * @param status
     */
    public User(int userID, String userName, String email, String password, float salary, String status) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.salary = salary;
        this.status = status;
    }


//User constructor for GUI (Admin only)

    /**
     *
     * @param userID
     * @param userName
     * @param email
     * @param salary
     * @param status
     */
    public User(int userID, String userName, String email , float salary, String status) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.salary = salary;       
        this.status = status;
    }

    
// Report selection constructor 

    /**
     *
     * @param userID
     * @param userName
     */
    public User(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return  userName;
    }

    /**
     *
     * @return
     */
    public int getUserID() {
        return userID;
    }

    /**
     *
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
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
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public float getSalary() {
        return salary;
    }

    /**
     *
     * @param salary
     */
    public void setSalary(float salary) {
        this.salary = salary;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
