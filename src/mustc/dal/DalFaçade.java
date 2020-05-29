/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.dal;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
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
public interface DalFaçade {
    
 // ClientDBDAO methods    
    public Client addNewClientToDB(String clientName, String logoImgLocation, String email, float standardRate);
    public Client getClient(int clientID);
    public List<Client> getAllClients();
    public List<Client> getAllClientsIDsAndNames();  //  For Report selector
    public Client editClient (Client editedClient,String clientName,float standardRate,String logoImgLocation, String email);
    public void removeClientFromDB(Client clientToDelete);
     

// ProjectDBDAO methods    
    public Project addNewProjectToDB(String projectName, int associatedClientID, int phoneNr, float projectRate, int allocatedHours);
    public Project getProjectForUser(int projectID);
    public List<Project> getAllProjectsForUser();
    public Project getProjectForAdmin(int projectID);
    public List<Project> getAllProjectsForAdmin();
    public List<Project> getAllProjectsIDsAndNames();  //  For GUI
    public List<Project> getAllProjectsIDsAndNamesForReport();  //  For Report selector // NEW
    public List<Project> getAllProjectIDsAndNamesOfAClient(int clientID);  //  For Report selector
    public Project editProject (Project editedProject, String projectName, /*int associatedClientID,*/ int phoneNr, float projectRate, int allocatedHours, boolean isClosed);
    public void removeProjectFromDB(Project projectToDelete);

    
// TaskDBDAO methods        
    public Task addNewTaskToDB(String taskName, int associatedProjectID, boolean isBillable);
    public Task getTaskForUser(int taskID);
    public List<Task> getAllTasksForUser();
//    public List<Task> getAllUsersTasks(); // not working yet    
    public Task getTaskForAdmin(int taskID);
    public List<Task> getAllTasksForAdmin();
    public List<Task> getAllTaskIDsAndNamesOfAProject(int projectID);    //  For Report selector
    public Task editTask (Task editedTask, String taskName , int associatedProjectID, boolean isBillable);
    public void removeTaskFromDB(Task taskToDelete);
    public List<Task> getUsersThreeRecentTasks(User loggedInUser);
  

// SessionDBDAO methods            
    public Session addNewSessionToDB(int associatedUserID, int associatedTaskID, String startTime, String finishTime);
    public Session getSession(int sessionID);
    public List<Session> getAllSessions();  // Admin view
    public List<Session> getAllSessionsOfAUser(User loggedInUser);
    public List<Session> getAllSessionsOfATask(int taskID);
    public Session editSession (Session editedSession, int associatedUserID, int associatedTaskID, String startTime, String finishTime);
    public void removeSessionFromDB(Session sessionToDelete);
  
    
// UserDBDAO methods
    public User addNewUserToDB(String userName, String email, String password, float salary, String status); 
    public User getUser(int userID);
    public List<User> getAllUsers();
    public List<User> getAllUsersIDsAndName();    //  For Report selector
    public User editUser (User userToEdit, String userName, String email, String password, Float salary, String status); 
    public void removeUserFromDB(User userToDelete);
    public int checkUserLogin(String loggedInUserEmail, String password);
    
    
// ReportDBDAO methods
    public List<Report> generateReport(int clientID, int projectID, int taskID, int userID, LocalDate searchFrom, LocalDate searchTo);
    public void addReportListToCSVFile(List<Report> reportList);

    
}