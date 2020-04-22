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
    private int id;
    private String name;
    private String password;
    private int salary;  // do we really need this?
    private boolean admin;  // or 0 = developer, 1 = admin, 2 = project owner.
    private Task currentTask;

    
    public LoggedInUser(int id, String name, String password, int salary, boolean admin, Task currentTask) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.salary = salary;
        this.admin = admin;
        this.currentTask = currentTask;
    }

    
    public static LoggedInUser getInstance() {
        return instance;
    }

    public static void setInstance(LoggedInUser instance) {
        LoggedInUser.instance = instance;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(Task currentTask) {
        this.currentTask = currentTask;
    }
    
    
}
