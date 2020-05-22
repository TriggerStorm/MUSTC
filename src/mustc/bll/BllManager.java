/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import mustc.be.Client;
import mustc.be.Project;
import mustc.be.Report;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;
import mustc.dal.DalManager;
import mustc.bll.TimeUtilities;
/**
 *
 * @author Trigger and Alan
 */
public class BllManager implements IBLL {
    private DalManager dalManager;
    private TimeUtilities timeUtilities;
    
    public BllManager() {
        dalManager = new DalManager();
        timeUtilities = new TimeUtilities();
    }
    
    
    
    
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
    public List<Client> getAllClientsIDsAndNames() {
        return dalManager.getAllClientsIDsAndNames();
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
    public List<Project> getAllProjectsIDsAndNames() {
        return dalManager.getAllProjectsIDsAndNames();
    }
    
    
    @Override
    public List<Project> getAllProjectIDsAndNamesOfAClient(int clientID) {
        return dalManager.getAllProjectIDsAndNamesOfAClient(clientID);
    }

   
    @Override
    public Project editProject(Project editedProject, String projectName, /*int associatedClientID,*/ int phoneNr, float projectRate, int allocatedHours, boolean isClosed) {
        return dalManager.editProject(editedProject, projectName, /*associatedClientID,*/ phoneNr, projectRate, allocatedHours, isClosed);
    }

    
    @Override
    public void removeProjectFromDB(Project projectToDelete) {
        dalManager.removeProjectFromDB(projectToDelete);
    }
    
    
    
    
// TaskDBDAO methods   
    
    @Override
    public Task addNewTaskToDB(String taskName, int associatedProjectID, boolean isBillable) {
        return dalManager.addNewTaskToDB(taskName, associatedProjectID, isBillable);
    }

    
    @Override
    public Task getTaskForUser(int taskID) {
        return dalManager.getTaskForUser(taskID);
    }


    @Override
    public List<Task> getAllTasksForUser() {
        return dalManager.getAllTasksForUser();
    }

    
 /*   @Override
    public List<Task> getAllUsersTasks() {
         return dalManager.getAllUsersTasks();
    }
*/
    
    @Override
    public Task getTaskForAdmin(int taskID) {
         return dalManager.getTaskForAdmin(taskID);
    }

    
    @Override
    public List<Task> getAllTasksForAdmin() {
         return dalManager.getAllTasksForAdmin();
    }
       

    @Override
    public List<Task> getAllTaskIDsAndNamesOfAProject(int projectID) {
        return dalManager.getAllTaskIDsAndNamesOfAProject(projectID);
    }

    
    @Override
    public Task editTask(Task editedTask, String taskName, int associatedProjectID, boolean isBillable) {
        return dalManager.editTask(editedTask, taskName, associatedProjectID, isBillable);
    }

    
    @Override
    public void removeTaskFromDB(Task taskToDelete) {
        dalManager.removeTaskFromDB(taskToDelete);
    }

   
    @Override
    public List<Task> getUsersThreeRecentTasks(User loggedInUser) {
        return dalManager.getUsersThreeRecentTasks(loggedInUser);
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
    public List<Session> getAllSessions() {
        return dalManager.getAllSessions();
    }

    @Override
    public List<Session> getAllSessionsOfAUser(User loggedInUser) {
        return dalManager.getAllSessionsOfAUser(loggedInUser);
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
    public User addNewUserToDB(String userName, String email, String password, float salary, String status) {
        return dalManager.addNewUserToDB(userName, email, password, salary, status);
    }

    @Override
    public User getUser(int userID) {
        return dalManager.getUser(userID);
    }


    @Override
    public List<User> getAllUsers() {
        return dalManager.getAllUsers();
    }
    
    @Override
    public List<User> getAllUsersIDsAndName() {
        return dalManager.getAllUsersIDsAndName();
    }
    
    @Override
    public User editUser(User userToEdit, String userName, String email, String password, Float salary, String status) {
        return dalManager.editUser(userToEdit, userName, email, password, salary, status);
    }

    @Override
    public void removeUserFromDB(User userToDelete) {
        dalManager.removeUserFromDB(userToDelete);
    }
    
    public int checkUserLogin(String loggedInUserEmail, String password){
        return dalManager.checkUserLogin(loggedInUserEmail, password);
    }

  
    
// ReportDBDAO methods    
   @Override
    public List<Report> generateReport(int clientID, int projectID, int taskID, int userID, LocalDate searchFrom, LocalDate searchTo) {
        return dalManager.generateReport(clientID, projectID, taskID, userID, searchFrom, searchTo);
    }

   
    
// TimeUtilites (BLL)
    @Override
    public String localDateTimeToString(LocalDateTime LDT) {
        return timeUtilities.localDateTimeToString(LDT);
    }

    @Override
    public LocalDateTime stringToLocalDateTime(String dateString) {
        return timeUtilities.stringToLocalDateTime(dateString);
    }

  
    
}
