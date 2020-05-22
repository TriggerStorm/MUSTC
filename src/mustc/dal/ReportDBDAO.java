/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import mustc.be.Project;
import mustc.be.Report;
import mustc.be.Session;
import mustc.be.Task;
import mustc.bll.TimeUtilities;

/**
 *
 * @author Trigger and Alan
 */
public class ReportDBDAO {
    private DBConnection dbc;
    private TimeUtilities timeUtilities;
    private ClientDBDAO clientDBDao;
    private ProjectDBDAO projectDBDao;
    private TaskDBDAO taskDBDao;
    private SessionDBDAO sessionDBDao;

    
    public ReportDBDAO() {
        dbc = new DBConnection();
        timeUtilities = new TimeUtilities();
        clientDBDao = new ClientDBDAO();
        projectDBDao = new ProjectDBDAO();
        taskDBDao = new TaskDBDAO();
        sessionDBDao = new SessionDBDAO();
    }
    
    
    public List<Report> generateReport(int clientID, int projectID, int taskID, int userID, LocalDate searchFrom, LocalDate searchTo) throws SQLException {
        List<Report> reportList = new ArrayList<>();
        List<Session> allReportSessions = compileSessionsForReport(clientID, projectID, taskID, userID, searchFrom, searchTo);
            for (int i = 0; i < allReportSessions.size(); i++) {
                Session session = allReportSessions.get(i);
                   String clientName = clientDBDao.getClientName(clientID);
                   String taskName = session.getAssociatedTaskName();
                   Project project = projectDBDao.getProjectForAdmin(projectID);
                            
System.out.println("clientName: " + clientName);
                Report report = new Report(clientName, "mockPJ", taskName, "mockLIU", "mockSTART", "mockSTOP", 0, 0);  //String clientName, String projectName, String taskName, String startTime, String finishTime, String developers, int totalBillableMinutes, int totalPrice)();
        
            }                
                return reportList;
    }
    
     
    public List<Session> compileSessionsForReport(int clientID, int projectID, int taskID, int userID, LocalDate searchFrom, LocalDate searchTo) throws SQLException {
      
        List<Session> allValidSessions = null;
        
        if (taskID == -2) {  // if all Tasks of a Project has been selected for filtering
            allValidSessions = getAllSessionsOfAProject(projectID);
        } else {
            if ((taskID == -1) && (clientID >= 0)) {  // If Client is selected for filtering, but no Project   // NOT SELECTABLE??
                allValidSessions = getAllSessionsOfAClient(clientID);
            } else {
                if  (taskID > -1) {  // if a Task has be selected for filtering
                    allValidSessions =  sessionDBDao.getAllSessionsOfATask(taskID);
                } else {
                    allValidSessions = sessionDBDao.getAllSessions();
                }
            }
        }
        
        if (!(userID == -1)) {  // if a user has be selected for filtering
            allValidSessions =  filterSessionListForUser(allValidSessions, userID);
        }
                    
        allValidSessions = filterSessionListForDatePeriod(allValidSessions, searchFrom, searchTo);
        
        return allValidSessions;
    }  
           
    

        
    public List<Session> getAllSessionsOfAProject(int projectID) throws SQLException {
        List<Session> allSessionsOfAProject = new ArrayList<>();
        List<Session> allSessions = new ArrayList<>();
        String sql = "SELECT * FROM Sessions INNER JOIN Tasks ON Sessions.AssociatedTask = tasks.id "// WHERE tasks.id "= '" + taskID + "'" 
          +"INNER JOIN Projects ON Tasks.AssociatedProject = Projects.id WHERE Projects.id = '" + projectID + "'"; //Tasks.id)";// "; 
        try(Connection con = dbc.getConnection()) 
        {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) // While you have something in the results
            {
                int sessionID = rs.getInt("id");
                int associatedUserID = rs.getInt("AssociatedUser");
                String associatedUserName = sessionDBDao.getSessionsUserName(associatedUserID);
                int associatedTaskID =  rs.getInt("associatedTask");
                String associatedTaskName = sessionDBDao.getSessionsTaskName(associatedTaskID);
                String startTime = rs.getString("startTime");                
                String finishTime = rs.getString("FinishTime");
                Session session = new Session(sessionID, associatedUserID, associatedUserName, associatedTaskID, associatedTaskName, startTime, finishTime);
                allSessionsOfAProject.add(session);
            }  
        }   
        return allSessionsOfAProject;
    }
            
    
    public List<Session> getAllSessionsOfAClient(int clientID) throws SQLException {
        List<Session> allSessionsOfAProject = new ArrayList<>();
        List<Session> allSessions = new ArrayList<>();
        String sql = "SELECT * FROM Sessions INNER JOIN Tasks ON Sessions.AssociatedTask = tasks.id "
          + "INNER JOIN Projects ON Tasks.AssociatedProject = Projects.id "
          + "INNER JOIN Clients ON Projects.AssociatedClient = Clients.id WHERE Clients.id = '" + clientID + "'";
    try(Connection con = dbc.getConnection()) 
        {
            PreparedStatement pstmt = con.prepareStatement(sql);   
            pstmt.execute();    
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) // While you have something in the results
            {
                int sessionID = rs.getInt("id");
                int associatedUserID = rs.getInt("AssociatedUser");
                String associatedUserName = sessionDBDao.getSessionsUserName(associatedUserID);
                int associatedTaskID =  rs.getInt("associatedTask");
                String associatedTaskName = sessionDBDao.getSessionsTaskName(associatedTaskID);
                String startTime = rs.getString("startTime");                
                String finishTime = rs.getString("FinishTime");
                Session session = new Session(sessionID, associatedUserID, associatedUserName, associatedTaskID, associatedTaskName, startTime, finishTime);
                allSessionsOfAProject.add(session);
            }  
        }   
        return allSessionsOfAProject;
    }
         
    
    private List<Session> filterSessionListForUser(List<Session> allValidSessions, int userID) {
        List<Session> developerFilteredSessionList = new ArrayList<>();
       // developerFilteredSessionList = null;
System.out.println("UserID = " + userID);            
       
       for (int i = 0; i < allValidSessions.size(); i++) {
            Session currentSession = allValidSessions.get(i);
System.out.println("currentSession.UserID = " + currentSession.getAssociatedUserID());            
            if (currentSession.getAssociatedUserID() == userID) {  // if session was created by userID (developer
                developerFilteredSessionList.add(currentSession);
            }
        }
        return developerFilteredSessionList;
    }
     
      
    private List<Session> filterSessionListForDatePeriod(List<Session> allValidSessions, LocalDate searchFrom, LocalDate searchTo) {
        List<Session> searchFromFilteredSessionList = new ArrayList<>();
     //   searchFromFilteredSessionList = null;
        for (int i = 0; i < allValidSessions.size(); i++) {
            Session currentSession = allValidSessions.get(i);
         //   String startTimeSTR = currentSession.getStartTime();
            LocalDate startDate = timeUtilities.stringToLocalDate(currentSession.getStartTime());
            LocalDate finishDate = timeUtilities.stringToLocalDate(currentSession.getFinishTime());
                if ((startDate.isAfter(searchFrom.minusDays(1))) && (finishDate.isBefore(searchTo.plusDays(1)))) {  // minusDays(1) and plusDays(1) are to include the date selected by DatePicker
                searchFromFilteredSessionList.add(currentSession);
            }
        }
        return searchFromFilteredSessionList;
    }
    
    
         
}
