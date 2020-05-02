/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.dal;

import java.sql.SQLException;
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
public interface DalFaçade {
    
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
    public Project editProject (Project editedProject, String projectName, /*int associatedClientID,*/ int phoneNr, float projectRate, int allocatedHours, boolean isClosed);

    
// TaskDBDAO methods        
    public Task addNewTaskToDB(String taskName, String description, int associatedProjectID);
    public Task getTaskForUser(int taskID);
    public List<Task> getAllUsersTasks(); // not working yet    
    public Task getTaskForAdmin(int taskID);
    public List<Task> getAllTasksForAdmin();
    public Task editTask (Task editedTask, String taskName, String description, int associatedProjectID);
    public void removeTaskFromDB(Task taskToDelete);
  

// SessionDBDAO methods            
    public Session addNewSessionToDB(int associatedUserID, int associatedTaskID, String startTime, String finishTime);
    public Session getSession(int sessionID);
    public List<Session> getAllSessionsOfATask(int taskID);
    public Session editSession (Session editedSession, int associatedUserID, int associatedTaskID, String startTime, String finishTime);
    public void removeSessionFromDB(Session sessionToDelete);
  
    
// UserDBDAO methods
    public User addNewUserToDB(String userName, String email, String password, float salary, boolean isAdmin); 
    public User getUser(int userID);
    public User editUser (User userToEdit, String userName, String email, String password, Float salary, boolean isAdmin); 
    public void removeUserFromDB(User userToDelete);
    
 
}
