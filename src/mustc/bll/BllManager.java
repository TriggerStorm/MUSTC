/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.bll;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javafx.collections.ObservableList;
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
    private SearchUtilities searchUtil;
    private InputValidators inputVdators;
    
    /**
     *
     */
    public BllManager() {
        dalManager = new DalManager();
        timeUtilities = new TimeUtilities();
        searchUtil = new SearchUtilities();
        inputVdators = new InputValidators();
    }
    
    
    
    
// ClientDBDAO methods    

    /**
     *
     * @param clientName
     * @param logoImgLocation
     * @param email
     * @param standardRate
     * @return
     */

    @Override
    public Client addNewClientToDB(String clientName, String logoImgLocation, String email, float standardRate) {
        return dalManager.addNewClientToDB(clientName, logoImgLocation, email, standardRate);
    }
    
    /**
     *
     * @param clientID
     * @return
     */
    @Override
    public Client getClient(int clientID) {
        return dalManager.getClient(clientID);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Client> getAllClients() {
        return dalManager.getAllClients();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Client> getAllClientsIDsAndNames() {
        return dalManager.getAllClientsIDsAndNames();
    }

    /**
     *
     * @param editedClient
     * @param clientName
     * @param standardRate
     * @param logoImgLocation
     * @param email
     * @return
     */
    @Override
    public Client editClient(Client editedClient, String clientName, float standardRate, String logoImgLocation, String email) {
        return dalManager.editClient(editedClient, clientName, standardRate, logoImgLocation, email);
    }

    /**
     *
     * @param clientToDelete
     */
    @Override
    public void removeClientFromDB(Client clientToDelete) {
        dalManager.removeClientFromDB(clientToDelete);
    }



    
 // ProjectDBDAO methods  

    /**
     *
     * @param projectName
     * @param associatedClientID
     * @param phoneNr
     * @param projectRate
     * @param allocatedHours
     * @return
     */
    
    @Override
    public Project addNewProjectToDB(String projectName, int associatedClientID, int phoneNr, float projectRate, int allocatedHours) {
        return dalManager.addNewProjectToDB(projectName, associatedClientID, phoneNr, projectRate, allocatedHours);
    }

    /**
     *
     * @param projectID
     * @return
     */
    @Override
    public Project getProjectForUser(int projectID) {
        return dalManager.getProjectForUser(projectID);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Project> getAllProjectsForUser() {
        return dalManager.getAllProjectsForUser();
    }

    /**
     *
     * @param projectID
     * @return
     */
    @Override
    public Project getProjectForAdmin(int projectID) {
        return dalManager.getProjectForAdmin(projectID);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Project> getAllProjectsForAdmin() {
        return dalManager.getAllProjectsForAdmin();
    }

    /**
     *
     * @return
     */
    @Override
    public List<Project> getAllProjectsIDsAndNames() {
        return dalManager.getAllProjectsIDsAndNames();
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<Project> getAllProjectsIDsAndNamesForReport() {
        return dalManager.getAllProjectsIDsAndNamesForReport();
    }
    
    /**
     *
     * @param clientID
     * @return
     */
    @Override
    public List<Project> getAllProjectIDsAndNamesOfAClient(int clientID) {
        return dalManager.getAllProjectIDsAndNamesOfAClient(clientID);
    }

    /**
     *
     * @param editedProject
     * @param projectName
     * @param phoneNr
     * @param projectRate
     * @param allocatedHours
     * @param isClosed
     * @return
     */
    @Override
    public Project editProject(Project editedProject, String projectName, /*int associatedClientID,*/ int phoneNr, float projectRate, int allocatedHours, boolean isClosed) {
        return dalManager.editProject(editedProject, projectName, /*associatedClientID,*/ phoneNr, projectRate, allocatedHours, isClosed);
    }

    /**
     *
     * @param projectToDelete
     */
    @Override
    public void removeProjectFromDB(Project projectToDelete) {
        dalManager.removeProjectFromDB(projectToDelete);
    }
    
    
    
    
// TaskDBDAO methods   

    /**
     *
     * @param taskName
     * @param associatedProjectID
     * @param isBillable
     * @return
     */
    
    @Override
    public Task addNewTaskToDB(String taskName, int associatedProjectID, boolean isBillable) {
        return dalManager.addNewTaskToDB(taskName, associatedProjectID, isBillable);
    }

    /**
     *
     * @param taskID
     * @return
     */
    @Override
    public Task getTaskForUser(int taskID) {
        return dalManager.getTaskForUser(taskID);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Task> getAllTasksForUser() {
        return dalManager.getAllTasksForUser();
    }

    /**
     *
     * @param taskID
     * @return
     */

    
    @Override
    public Task getTaskForAdmin(int taskID) {
         return dalManager.getTaskForAdmin(taskID);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Task> getAllTasksForAdmin() {
         return dalManager.getAllTasksForAdmin();
    }
       
    /**
     *
     * @param projectID
     * @return
     */
    @Override
    public List<Task> getAllTaskIDsAndNamesOfAProject(int projectID) {
        return dalManager.getAllTaskIDsAndNamesOfAProject(projectID);
    }

    /**
     *
     * @param editedTask
     * @param taskName
     * @param associatedProjectID
     * @param isBillable
     * @return
     */
    @Override
    public Task editTask(Task editedTask, String taskName, int associatedProjectID, boolean isBillable) {
        return dalManager.editTask(editedTask, taskName, associatedProjectID, isBillable);
    }

    /**
     *
     * @param taskToDelete
     */
    @Override
    public void removeTaskFromDB(Task taskToDelete) {
        dalManager.removeTaskFromDB(taskToDelete);
    }

    /**
     *
     * @param loggedInUser
     * @return
     */
    @Override
    public List<Task> getUsersThreeRecentTasks(User loggedInUser) {
        return dalManager.getUsersThreeRecentTasks(loggedInUser);
        }

      
    
    
// SessionDBDAO methods                    

    /**
     *
     * @param associatedUserID
     * @param associatedTaskID
     * @param associatedTaskName
     * @param startTime
     * @param finishTime
     * @return
     */
    @Override
    public Session addNewSessionToDB(int associatedUserID, int associatedTaskID,String associatedTaskName, String startTime, String finishTime) {
        return dalManager.addNewSessionToDB(associatedUserID, associatedTaskID, associatedTaskName, startTime, finishTime);
    }

    /**
     *
     * @param sessionID
     * @return
     */
    @Override
    public Session getSession(int sessionID) {
        return dalManager.getSession(sessionID);
    }

    /**
     *
     * @return
     */
    @Override
    public List<Session> getAllSessions() {
        return dalManager.getAllSessions();
    }

    /**
     *
     * @param loggedInUser
     * @return
     */
    @Override
    public List<Session> getAllSessionsOfAUser(User loggedInUser) {
        return dalManager.getAllSessionsOfAUser(loggedInUser);
    }

    /**
     *
     * @param taskID
     * @return
     */
    @Override
    public List<Session> getAllSessionsOfATask(int taskID) {
        return dalManager.getAllSessionsOfATask(taskID);
    }

    /**
     *
     * @param editedSession
     * @param associatedUserID
     * @param associatedTaskID
     * @param startTime
     * @param finishTime
     * @return
     */
    @Override
    public Session editSession(Session editedSession, int associatedUserID, int associatedTaskID, String startTime, String finishTime) {
        return dalManager.editSession(editedSession, associatedUserID, associatedTaskID, startTime, finishTime);
    }

    /**
     *
     * @param sessionToDelete
     */
    @Override
    public void removeSessionFromDB(Session sessionToDelete) {
        dalManager.removeSessionFromDB(sessionToDelete);
    }

    
    
// UserDBDAO methods        

    /**
     *
     * @param userName
     * @param email
     * @param password
     * @param salary
     * @param status
     * @return
     */
    @Override
    public User addNewUserToDB(String userName, String email, String password, float salary, String status) {
        return dalManager.addNewUserToDB(userName, email, password, salary, status);
    }

    /**
     *
     * @param userID
     * @return
     */
    @Override
    public User getUser(int userID) {
        return dalManager.getUser(userID);
    }

    /**
     *
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        return dalManager.getAllUsers();
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<User> getAllUsersIDsAndName() {
        return dalManager.getAllUsersIDsAndName();
    }
    
    /**
     *
     * @param userToEdit
     * @param userName
     * @param email
     * @param password
     * @param salary
     * @param status
     * @return
     */
    @Override
    public User editUser(User userToEdit, String userName, String email, String password, Float salary, String status) {
        return dalManager.editUser(userToEdit, userName, email, password, salary, status);
    }

    /**
     *
     * @param userToDelete
     */
    @Override
    public void removeUserFromDB(User userToDelete) {
        dalManager.removeUserFromDB(userToDelete);
    }
    
    /**
     *
     * @param loggedInUserEmail
     * @param password
     * @return
     */
    public int checkUserLogin(String loggedInUserEmail, String password){
        return dalManager.checkUserLogin(loggedInUserEmail, password);
    }

  
    
    
// ReportDBDAO methods    

    /**
     *
     * @param clientID
     * @param projectID
     * @param taskID
     * @param userID
     * @param searchFrom
     * @param searchTo
     * @return
     */
    @Override
    public List<Report> generateReport(int clientID, int projectID, int taskID, int userID, LocalDate searchFrom, LocalDate searchTo) {
        return dalManager.generateReport(clientID, projectID, taskID, userID, searchFrom, searchTo);
    }

    /**
     *
     * @param reportList
     */
    @Override
    public void addReportListToCSVFile(List<Report> reportList) {
        dalManager.addReportListToCSVFile(reportList);
    }
   
    
    
// TimeUtilites (BLL)

    /**
     *
     * @param LDT
     * @return
     */
    @Override
    public String localDateTimeToString(LocalDateTime LDT) {
        return timeUtilities.localDateTimeToString(LDT);
    }

    /**
     *
     * @param dateString
     * @return
     */
    @Override
    public LocalDateTime stringToLocalDateTime(String dateString) {
        return timeUtilities.stringToLocalDateTime(dateString);
    }

  
    
//search utilites (BLL)

    /**
     *
     * @param allTask
     * @param text
     * @return
     */
    @Override
    public ObservableList<Task> searchTask(ObservableList<Task> allTask, String text) {
        return searchUtil.searchTask(allTask, text);
    }
    
    /**
     *
     * @param allClient
     * @param text
     * @return
     */
    @Override
    public ObservableList<Client> searchClient(ObservableList<Client> allClient, String text){
        return searchUtil.searchClient(allClient, text);
    }

    /**
     *
     * @param allTask
     * @param text
     * @return
     */
    public ObservableList<Task> searchTaskpj(ObservableList<Task> allTask, String text){
        return searchUtil.searchTask(allTask, text);
    }

    /**
     *
     * @param allProject
     * @param text
     * @return
     */
    public ObservableList<Project> searchProject(ObservableList<Project> allProject, String text){
        return searchUtil.searchProject(allProject, text);
    }
    
    /**
     *
     * @param allSession
     * @param text
     * @return
     */
    public ObservableList<Session> searchSession(ObservableList<Session> allSession, String text){
        return searchUtil.searchSession(allSession, text);
    }
     
    /**
     *
     * @param allUser
     * @param text
     * @return
     */
    public ObservableList<User> searchUser(ObservableList<User> allUser, String text){
        return searchUtil.searchUser(allUser, text);
    }

  // inputValidators

    /**
     *
     * @param email
     * @return
     */
     public boolean isValidEmail(String email){
         return inputVdators.isValidEmail(email);
     }

    /**
     *
     * @param phoneNumber
     * @return
     */
    public boolean isValidPhoneNumber(String phoneNumber){
        return inputVdators.isValidPhoneNumber(phoneNumber);
    }
    
}
