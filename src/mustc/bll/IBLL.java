/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.bll;

import java.time.LocalDateTime;
import java.util.List;
import mustc.be.Client;
import mustc.be.Project;
import mustc.be.Task;
import mustc.be.Session;
import mustc.be.User;

/**
 *
 * @author Trigger and Alan
 */
public interface IBLL {
    
       
// ClientDBDAO methods    
    public Client addNewClientToDB(String clientName, String logoImgLocation, String email, float standardRate);
    public Client getClient(int clientID);
    public List<Client> getAllClients();
    public Client editClient (Client editedClient,String clientName,float standardRate,String logoImgLocation, String email);
    public void removeClientFromDB(Client clientToDelete);
     

// ProjectDBDAO methods    
    public Project addNewProjectToDB(String projectName, int associatedClientID, int phoneNr, float projectRate, int allocatedHours);
    public Project getProjectForUser(int projectID);
    public List<Project> getAllProjectsForUser();
    public Project getProjectForAdmin(int projectID);
    public List<Project> getAllProjectsForAdmin();
    public List<Project> getAllProjectsIDsAndNames();
    public Project editProject (Project editedProject, String projectName, /*int associatedClientID,*/ int phoneNr, float projectRate, int allocatedHours, boolean isClosed);
    public void removeProjectFromDB(Project projectToDelete);

    
// TaskDBDAO methods        
    public Task addNewTaskToDB(String taskName, int associatedProjectID, boolean isBillable);
    public Task getTaskForUser(int taskID);
    public List<Task> getAllTasksForUser();
//    public List<Task> getAllUsersTasks(); // not working yet    
    public Task getTaskForAdmin(int taskID);
    public List<Task> getAllTasksForAdmin();
    public Task editTask (Task editedTask, String taskName, String description, int associatedProjectID);
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
  
    
// UserDBDAO methods  // not tweeked for GUI yet
    public User addNewUserToDB(String userName, String email, String password, float salary, String status); 
    public User getUser(int userID);
    public List<User> getAllUsers();
    public User editUser (User userToEdit, String userName, String email, String password, Float salary, String status); 
    public void removeUserFromDB(User userToDelete);
    
 // TimeUtilites (BLL)
    public String localDateTimeToString(LocalDateTime LDT);
    public LocalDateTime stringToLocalDateTime(String dateString);
}