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
import mustc.be.Task;
import mustc.be.Session;
import mustc.be.User;

/**
 *
 * @author Trigger and Alan
 */
public interface IBLL {
    
       
// ClientDBDAO methods    

    /**
     *
     * @param clientName
     * @param logoImgLocation
     * @param email
     * @param standardRate
     * @return
     */
    public Client addNewClientToDB(String clientName, String logoImgLocation, String email, float standardRate);

    /**
     *
     * @param clientID
     * @return
     */
    public Client getClient(int clientID);

    /**
     *
     * @return
     */
    public List<Client> getAllClients();

    /**
     *
     * @return
     */
    public List<Client> getAllClientsIDsAndNames();  //  For Report selector

    /**
     *
     * @param editedClient
     * @param clientName
     * @param standardRate
     * @param logoImgLocation
     * @param email
     * @return
     */
    public Client editClient (Client editedClient,String clientName,float standardRate,String logoImgLocation, String email);

    /**
     *
     * @param clientToDelete
     */
    public void removeClientFromDB(Client clientToDelete);
    

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
    public Project addNewProjectToDB(String projectName, int associatedClientID, int phoneNr, float projectRate, int allocatedHours);

    /**
     *
     * @param projectID
     * @return
     */
    public Project getProjectForUser(int projectID);

    /**
     *
     * @return
     */
    public List<Project> getAllProjectsForUser();

    /**
     *
     * @param projectID
     * @return
     */
    public Project getProjectForAdmin(int projectID);

    /**
     *
     * @return
     */
    public List<Project> getAllProjectsForAdmin();

    /**
     *
     * @return
     */
    public List<Project> getAllProjectsIDsAndNames();

    /**
     *
     * @return
     */
    public List<Project> getAllProjectsIDsAndNamesForReport();  //  For Report selector // NEW

    /**
     *
     * @param clientID
     * @return
     */
    public List<Project> getAllProjectIDsAndNamesOfAClient(int clientID);

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
    public Project editProject (Project editedProject, String projectName, /*int associatedClientID,*/ int phoneNr, float projectRate, int allocatedHours, boolean isClosed);

    /**
     *
     * @param projectToDelete
     */
    public void removeProjectFromDB(Project projectToDelete);

    
// TaskDBDAO methods        

    /**
     *
     * @param taskName
     * @param associatedProjectID
     * @param isBillable
     * @return
     */
    public Task addNewTaskToDB(String taskName, int associatedProjectID, boolean isBillable);

    /**
     *
     * @param taskID
     * @return
     */
    public Task getTaskForUser(int taskID);

    /**
     *
     * @return
     */
    public List<Task> getAllTasksForUser();
//    public List<Task> getAllUsersTasks(); // not working yet    

    /**
     *
     * @param taskID
     * @return
     */
    public Task getTaskForAdmin(int taskID);

    /**
     *
     * @return
     */
    public List<Task> getAllTasksForAdmin();

    /**
     *
     * @param projectID
     * @return
     */
    public List<Task> getAllTaskIDsAndNamesOfAProject(int projectID);    //  For Report selector

    /**
     *
     * @param editedTask
     * @param taskName
     * @param associatedProjectID
     * @param isBillable
     * @return
     */
    public Task editTask (Task editedTask, String taskName, int associatedProjectID, boolean isBillable);

    /**
     *
     * @param taskToDelete
     */
    public void removeTaskFromDB(Task taskToDelete);

    /**
     *
     * @param loggedInUser
     * @return
     */
    public List<Task> getUsersThreeRecentTasks(User loggedInUser);
    
    

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
    public Session addNewSessionToDB(int associatedUserID, int associatedTaskID,String associatedTaskName, String startTime, String finishTime);

    /**
     *
     * @param sessionID
     * @return
     */
    public Session getSession(int sessionID);

    /**
     *
     * @return
     */
    public List<Session> getAllSessions();  // Admin view

    /**
     *
     * @param loggedInUser
     * @return
     */
    public List<Session> getAllSessionsOfAUser(User loggedInUser);

    /**
     *
     * @param taskID
     * @return
     */
    public List<Session> getAllSessionsOfATask(int taskID);

    /**
     *
     * @param editedSession
     * @param associatedUserID
     * @param associatedTaskID
     * @param startTime
     * @param finishTime
     * @return
     */
    public Session editSession (Session editedSession, int associatedUserID, int associatedTaskID, String startTime, String finishTime);

    /**
     *
     * @param sessionToDelete
     */
    public void removeSessionFromDB(Session sessionToDelete);
  
    
// UserDBDAO methods  // not tweeked for GUI yet

    /**
     *
     * @param userName
     * @param email
     * @param password
     * @param salary
     * @param status
     * @return
     */
    public User addNewUserToDB(String userName, String email, String password, float salary, String status); 

    /**
     *
     * @param userID
     * @return
     */
    public User getUser(int userID);

    /**
     *
     * @return
     */
    public List<User> getAllUsers();

    /**
     *
     * @return
     */
    public List<User> getAllUsersIDsAndName();    //  For Report selector

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
    public User editUser (User userToEdit, String userName, String email, String password, Float salary, String status); 

    /**
     *
     * @param userToDelete
     */
    public void removeUserFromDB(User userToDelete);

    /**
     *
     * @param loggedInUserEmail
     * @param password
     * @return
     */
    public int checkUserLogin(String loggedInUserEmail, String password);
 
    
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
    public List<Report> generateReport(int clientID, int projectID, int taskID, int userID, LocalDate searchFrom, LocalDate searchTo);

    /**
     *
     * @param reportList
     */
    public void addReportListToCSVFile(List<Report> reportList);
    
 
// TimeUtilites (BLL)

    /**
     *
     * @param LDT
     * @return
     */
    public String localDateTimeToString(LocalDateTime LDT);

    /**
     *
     * @param dateString
     * @return
     */
    public LocalDateTime stringToLocalDateTime(String dateString);



 // serchUtil

    /**
     *
     * @param allClient
     * @param text
     * @return
     */
    public ObservableList<Client> searchClient(ObservableList<Client> allClient, String text);

    /**
     *
     * @param allTask
     * @param text
     * @return
     */
    public ObservableList<Task> searchTask (ObservableList<Task> allTask, String text);

    /**
     *
     * @param allTask
     * @param text
     * @return
     */
    public ObservableList<Task> searchTaskpj(ObservableList<Task> allTask, String text);

    /**
     *
     * @param allProject
     * @param text
     * @return
     */
    public ObservableList<Project> searchProject(ObservableList<Project> allProject, String text);

    /**
     *
     * @param allSession
     * @param text
     * @return
     */
    public ObservableList<Session> searchSession(ObservableList<Session> allSession, String text);

    /**
     *
     * @param allUser
     * @param text
     * @return
     */
    public ObservableList<User> searchUser(ObservableList<User> allUser, String text);
    
  //validators

    /**
     *
     * @param email
     * @return
     */
    public boolean isValidEmail(String email);

    /**
     *
     * @param phoneNumber
     * @return
     */
    public boolean isValidPhoneNumber(String phoneNumber);
    
}
