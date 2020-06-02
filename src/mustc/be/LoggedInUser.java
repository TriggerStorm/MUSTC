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
    private int loggedInUserID;
    private String loggedInUserName;
    private String loggedInUserEmail;
    private String password;
    private int salary;  // do we really need this?
    private boolean admin;  // or 0 = developer, 1 = admin, 2 = project owner.
    private Task currentTask;
    private LoggedInUser(){
       Task currentTask = new Task("",1);
    }

    /**
     *
     * @param LoggedInUserID
     * @param LoggedInUserName
     * @param LoggedInUserEmail
     * @param password
     * @param salary
     * @param admin
     * @param currentTask
     */
    public LoggedInUser(int LoggedInUserID, String LoggedInUserName, String LoggedInUserEmail, String password, int salary, boolean admin, Task currentTask) {
        this.loggedInUserID = LoggedInUserID;
        this.loggedInUserName = LoggedInUserName;
        this.loggedInUserEmail = LoggedInUserEmail;
        this.password = password;
        this.salary = salary;
        this.admin = admin;
        this.currentTask = currentTask;
    }
    
    /**
     *
     * @return
     */
    public static LoggedInUser getInstance()
    {
        if(instance == null)
        {
            instance = new LoggedInUser();
        }
        
        return instance;
    }
    

    /**
     *
     * @return
     */


    public int getId() {
        return loggedInUserID;
    }

    /**
     *
     * @param loggedInUserID
     */
    public void setId(int loggedInUserID) {
        this.loggedInUserID = loggedInUserID;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return loggedInUserName;
    }
    
    /**
     *
     * @return
     */
    public String getEmail() {
        return loggedInUserEmail;
    }

    /**
     *
     * @param loggedInUserEmail
     */
    public void setEmail(String loggedInUserEmail) {
        this.loggedInUserEmail = loggedInUserEmail;
    }

    /**
     *
     * @param loggedInUserName
     */
    public void setName(String loggedInUserName) {
        this.loggedInUserName = loggedInUserName;
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
    public int getSalary() {
        return salary;
    }

    /**
     *
     * @param salary
     */
    public void setSalary(int salary) {
        this.salary = salary;
    }

    /**
     *
     * @return
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     *
     * @param admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     *
     * @return
     */
    public Task getCurrentTask() {
        return currentTask;
    }

    /**
     *
     * @param currentTask
     */
    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }
    
    
}
