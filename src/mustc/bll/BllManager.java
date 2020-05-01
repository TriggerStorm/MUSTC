/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.bll;

import java.util.List;
import mustc.be.Client;
import mustc.be.Project;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;
import mustc.dal.DalManager;

/**
 *
 * @author Trigger and Alan
 */
public class BllManager implements IBLL {
    private DalManager dalManager;
    
   
// ClientDBDAO methods    

    @Override
    public Client addNewClientToDB(String clientName, String logoImgLocation, String email, float standardRate) {
        return dalManager.addNewClientToDB(clientName, logoImgLocation, email, standardRate);
    }
    
        
    @Override
    public Client getClient(int clientID) {
        return dalManager.getClient(clientID);
    }

    
    @Override
    public List<Client> getAllClients() {
        return dalManager.getAllClients();
    }

    
    @Override
    public Client editClient(Client editedClient, String clientName, float standardRate, String logoImgLocation, String email) {
        return dalManager.editClient(editedClient, clientName, standardRate, logoImgLocation, email);
    }

    
    @Override
    public void removeClientFromDB(Client clientToDelete) {
        dalManager.removeClientFromDB(clientToDelete);
    }



    
 // ProjectDBDAO methods  
    
    @Override
    public Project addNewProjectToDB(String projectName, int associatedClientID, int phoneNr, float projectRate, int allocatedHours) {
        return dalManager.addNewProjectToDB(projectName, associatedClientID, phoneNr, projectRate, allocatedHours);
    }

    
    @Override
    public Project getProjectForUser(int projectID) {
        return dalManager.getProjectForUser(projectID);
    }

    
    @Override
    public List<Project> getAllProjectsForUser() {
        return dalManager.getAllProjectsForUser();
    }

    
    @Override
    public Project getProjectForAdmin(int projectID) {
        return dalManager.getProjectForAdmin(projectID);
    }

    
    @Override
    public List<Project> getAllProjectsForAdmin() {
        return dalManager.getAllProjectsForAdmin();
    }

    
    @Override
    public Project editProject(Project editedProject, String projectName, int associatedClientID, float projectRate, int allocatedHours, boolean isClosed) {
        return dalManager.editProject(editedProject, projectName, associatedClientID, projectRate, allocatedHours, isClosed);
    }

    
    
    
// TaskDBDAO methods   
    
    @Override
    public Task addNewTaskToDB(String taskName, String description, int associatedProjectID) {
        return dalManager.addNewTaskToDB(taskName, description, associatedProjectID);
    }

    
    @Override
    public Task getTaskForUser(int taskID) {
         return dalManager.getTaskForUser(taskID);
    }

    
    @Override
    public List<Task> getAllUsersTasks() {
         return dalManager.getAllUsersTasks();
    }

    
    @Override
    public Task getTaskForAdmin(int taskID) {
         return dalManager.getTaskForAdmin(taskID);
    }

    
    @Override
    public List<Task> getAllTasksForAdmin() {
         return dalManager.getAllTasksForAdmin();
    }
       

    @Override
    public Task editTask(Task editedTask, String taskName, String description, int associatedProjectID) {
        return dalManager.editTask(editedTask, taskName, description, associatedProjectID);
    }

    
    @Override
    public void removeTaskFromDB(Task taskToDelete) {
        dalManager.removeTaskFromDB(taskToDelete);
    }

    
    
    
// SessionDBDAO methods                    
    @Override
    public Session addNewSessionToDB(int associatedUserID, int associatedTaskID, String startTime, String finishTime) {
        return dalManager.addNewSessionToDB(associatedUserID, associatedTaskID, startTime, finishTime);
    }

    @Override
    public Session getSession(int sessionID) {
        return dalManager.getSession(sessionID);
    }

    @Override
    public List<Session> getAllSessionsOfATask(int taskID) {
        return dalManager.getAllSessionsOfATask(taskID);
    }

    @Override
    public Session editSession(Session editedSession, int associatedUserID, int associatedTaskID, String startTime, String finishTime) {
        return dalManager.getSession(associatedTaskID);
    }

    
    @Override
    public void removeSessionFromDB(Session sessionToDelete) {
        dalManager.removeSessionFromDB(sessionToDelete);
    }

    
    
// UserDBDAO methods        
    @Override
    public User addNewUserToDB(String userName, String email, String password, float salary, boolean isAdmin) {
        return dalManager.addNewUserToDB(userName, email, password, salary, isAdmin);
    }

    @Override
    public User getUser(int userID) {
        return dalManager.getUser(userID);
    }

    @Override
    public User editUser(User userToEdit, String userName, String email, String password, Float salary, boolean isAdmin) {
        return dalManager.editUser(userToEdit, userName, email, password, salary, isAdmin);
    }

    @Override
    public void removeUserFromDB(User userToDelete) {
        dalManager.removeUserFromDB(userToDelete);
    }

    
   
}
