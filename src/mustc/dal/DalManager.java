/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.dal;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DalManager implements DalFaçade {
    private ClientDBDAO clientDBDao;
    private ProjectDBDAO projectDBDao;
    private TaskDBDAO taskDBDao;
    private SessionDBDAO sessionDBDao;
    private UserDBDAO userDBDao;
    private ReportDBDAO reportDBDao;
    
    /**
     *
     */
    public DalManager() {
        clientDBDao = new ClientDBDAO();
        projectDBDao = new ProjectDBDAO();
        taskDBDao = new TaskDBDAO();
        sessionDBDao = new SessionDBDAO();
        userDBDao = new UserDBDAO();
        reportDBDao = new ReportDBDAO();
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
        try {
            return clientDBDao.addNewClientToDB(clientName, logoImgLocation, email, standardRate);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *
     * @param clientID
     * @return
     */
    @Override
    public Client getClient(int clientID) {
        try {
            return clientDBDao.getClient(clientID);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<Client> getAllClients() {
        try {
            return clientDBDao.getAllClients();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Client> getAllClientsIDsAndNames() {
        try {
            return clientDBDao.getAllClientsIDsAndNames();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        return clientDBDao.editClient(editedClient, clientName, standardRate, logoImgLocation, email);
    }

    /**
     *
     * @param clientToDelete
     */
    @Override
    public void removeClientFromDB(Client clientToDelete) {
        try {    
            clientDBDao.removeClientFromDB(clientToDelete);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            return projectDBDao.addNewProjectToDB(projectName, associatedClientID, phoneNr, projectRate, allocatedHours);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *
     * @param projectID
     * @return
     */
    @Override
    public Project getProjectForUser(int projectID) {
        try {
            return projectDBDao.getProjectForUser(projectID);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<Project> getAllProjectsForUser() {
        try {
            return projectDBDao.getAllProjectsForUser();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param projectID
     * @return
     */
    @Override
    public Project getProjectForAdmin(int projectID) {
        try {
            return projectDBDao.getProjectForAdmin(projectID);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Project> getAllProjectsForAdmin() {
        try {
            return projectDBDao.getAllProjectsForAdmin();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Project> getAllProjectsIDsAndNames() {
        try {
            return projectDBDao.getAllProjectsIDsAndNames();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
  
    /**
     *
     * @return
     */
    @Override
    public List<Project> getAllProjectsIDsAndNamesForReport() {
        try {
            return projectDBDao.getAllProjectsIDsAndNamesForReport();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param clientID
     * @return
     */
    @Override
    public List<Project> getAllProjectIDsAndNamesOfAClient(int clientID) {
        try {
            return projectDBDao.getAllProjectIDsAndNamesOfAClient(clientID);
                    } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        return projectDBDao.editProject(editedProject, projectName, /*associatedClientID,*/ phoneNr, projectRate, allocatedHours, isClosed);
    }
    
    /**
     *
     * @param projectToDelete
     */
    @Override
    public void removeProjectFromDB(Project projectToDelete) {
        projectDBDao.removeProjectFromDB(projectToDelete);
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
        try {
            return taskDBDao.addNewTaskToDB(taskName, associatedProjectID, isBillable);
            
            //     return null;
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *
     * @param taskID
     * @return
     */
    @Override
    public Task getTaskForUser(int taskID) {
        try {
            return taskDBDao.getTaskForUser(taskID);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Task> getAllTasksForUser() {
        try {
            return taskDBDao.getAllTasksForUser();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param taskID
     * @return
     */
   
    
    @Override
    public Task getTaskForAdmin(int taskID) {
        try {
            return taskDBDao.getTaskForAdmin(taskID);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Task> getAllTasksForAdmin() {
        try {
            return taskDBDao.getAllTasksForAdmin();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param projectID
     * @return
     */
    @Override
    public List<Task> getAllTaskIDsAndNamesOfAProject(int projectID) {
        try {
            return taskDBDao.getAllTaskIDsAndNamesOfAProject(projectID);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
    public Task editTask(Task editedTask, String taskName , int associatedProjectID,  boolean isBillable) {
        return taskDBDao.editTask(editedTask, taskName, associatedProjectID, isBillable);
    }
    
    /**
     *
     * @param taskToDelete
     */
    @Override
    public void removeTaskFromDB(Task taskToDelete) {
        taskDBDao.removeTaskFromDB(taskToDelete);
    }

    /**
     *
     * @param loggedInUser
     * @return
     */
    @Override
    public List<Task> getUsersThreeRecentTasks(User loggedInUser) {
        try {
            return taskDBDao.getUsersThreeRecentTasks(loggedInUser);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        try {
            return sessionDBDao.addNewSessionToDB(associatedUserID, associatedTaskID,associatedTaskName, startTime, finishTime);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
   }
    
    /**
     *
     * @param sessionID
     * @return
     */
    @Override
    public Session getSession(int sessionID) {
        try {
            return sessionDBDao.getSession(sessionID);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Session> getAllSessions() {
        try {
            return sessionDBDao.getAllSessions();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
 
    /**
     *
     * @param loggedInUser
     * @return
     */
    @Override
    public List<Session> getAllSessionsOfAUser(User loggedInUser) {
        try {
            return sessionDBDao.getAllSessionsOfAUser(loggedInUser);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param taskID
     * @return
     */
    @Override
    public List<Session> getAllSessionsOfATask(int taskID) {
        try {
            return sessionDBDao.getAllSessionsOfATask(taskID);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        return sessionDBDao.editSession(editedSession, associatedUserID, associatedTaskID, startTime, finishTime);
    }

    /**
     *
     * @param sessionToDelete
     */
    @Override
    public void removeSessionFromDB(Session sessionToDelete) {
        sessionDBDao.removeSessionFromDB(sessionToDelete);
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
        return userDBDao.addNewUserToDB(userName, email, password, salary, status);
    }
    
    /**
     *
     * @param userID
     * @return
     */
    @Override
    public User getUser(int userID) {
        try {
            return userDBDao.getUser(userID);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        try {
            return userDBDao.getAllUsers();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<User> getAllUsersIDsAndName() {
        try {
            return userDBDao.getAllUsersIDsAndName();
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
        return userDBDao.editUser(userToEdit, userName, email, password, salary, status);
        
    }
    
    /**
     *
     * @param userToDelete
     */
    @Override
    public void removeUserFromDB(User userToDelete) {
        userDBDao.removeUserFromDB(userToDelete);
    }
    
    /**
     *
     * @param loggedInUserEmail
     * @param password
     * @return
     */
    public int checkUserLogin(String loggedInUserEmail, String password){
        try {
            return userDBDao.checkUserLogin(loggedInUserEmail, password);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
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
        try {
            return reportDBDao.generateReport(clientID, projectID, taskID, userID, searchFrom, searchTo);
        } catch (SQLException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param reportList
     */
    @Override
    public void addReportListToCSVFile(List<Report> reportList) {
        try {
            reportDBDao.addReportListToCSVFile(reportList);
        } catch (IOException ex) {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}