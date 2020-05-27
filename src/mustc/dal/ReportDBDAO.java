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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import mustc.be.Project;
import mustc.be.Report;
import mustc.be.Session;
import mustc.be.Task;
import mustc.bll.TimeUtilities;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
    private UserDBDAO userDBDao;
    private static final String reportCSV = "reportCSV/reportCSV.csv";

    
    public ReportDBDAO() {
        dbc = new DBConnection();
        timeUtilities = new TimeUtilities();
        clientDBDao = new ClientDBDAO();
        projectDBDao = new ProjectDBDAO();
        taskDBDao = new TaskDBDAO();
        sessionDBDao = new SessionDBDAO();
        userDBDao = new UserDBDAO();
    }
    
    
    public List<Report> generateReport(int clientID, int projectID, int taskID, int userID, LocalDate searchFrom, LocalDate searchTo) throws SQLException {
    //  Returns a list of Reports based of the users search parameters. Imcludes a two line header, and footer of totals
        List<Report> reportList = getReportHeader(clientID, projectID, taskID, userID, searchFrom, searchTo);
        List<Session> allReportSessions = compileSessionsForReport(clientID, projectID, taskID, userID, searchFrom, searchTo);
        int totalBillableMinutes = 0;
        int totalUnbillableMinutes = 0;
        int totalRevenue = 0;
        
        for (int i = 0; i < allReportSessions.size(); i++) {
            Session session = allReportSessions.get(i);
                int associatedTaskID = session.getAssociatedTaskID();
                String loggedInUser = session.getAssociatedUserName();
                String startTime = session.getStartTime();
                String finishTime = session.getFinishTime();
                int minutes = sessionDBDao.calculateDurationOfASession(startTime, finishTime);
                String minutesSTR = Integer.toString(minutes);
                String billable;
                Task task = taskDBDao.getTaskForReport(associatedTaskID);
                String taskName = task.getTaskName();
                int associatedProjectID = task.getAssociatedProjectID();
                boolean isBillable = task.getIsBillable();       
                Project project = projectDBDao.getProjectForReport(associatedProjectID);     
                String projectName = project.getProjectName();
                String clientName = project.getClientName();
                 
                float projectRate = project.getProjectRate();
                int allocatedHours = project.getAllocatedHours();
                int revenue;
                if (isBillable) {
                     billable = "$$";
                     totalBillableMinutes += minutes;
                    revenue = (int) (minutes * (projectRate / 60));  // minutes * 'minuteRate'
                } else {
                    billable = "--";                       
                    totalUnbillableMinutes += minutes;
                    revenue = 0;
                }
                totalRevenue += revenue;
                String revenueSTR = Integer.toString(revenue);
                Report report = new Report(clientName, projectName, taskName, loggedInUser, startTime, finishTime, minutesSTR, billable, revenueSTR);
                reportList.add(report);
            } 
        String billableSTR = Integer.toString(totalBillableMinutes);
        String unBillableSTR = Integer.toString(totalUnbillableMinutes);
        String totalRevenueSTR = Integer.toString(totalRevenue);
        reportList.add(new Report("TOTALS -> ", " BILLABLE (mins) = ", billableSTR, ",UNBILLABLE (mins) = ", unBillableSTR, "", " REVENUE EARNED = ", totalRevenueSTR, ""));
        return reportList;
    }
    
    
    private List<Report> getReportHeader(int clientID, int projectID, int taskID, int userID, LocalDate searchFrom, LocalDate searchTo) throws SQLException {
        List<Report> reportList = new ArrayList<>();
        String client;
        if (clientID >= 0) {
            client = clientDBDao.getClientName(clientID);
        } else client = "all Clients";
           
        String project;
        if (projectID >= 0) {
            project = projectDBDao.getProjectName(projectID);
        } else project = "all Projects";
        
        String task;
        if (taskID >= 0) {
            task = taskDBDao.getTaskName(taskID);
        } else task = "all Tasks";
            
        String user;
        if (userID >= 0) {
            user = userDBDao.getUserName(userID);
        } else user = "all Users";
        String date = LocalDate.now().toString(); 
        String time = LocalTime.now().toString().substring(0, 8);
        
        reportList.add(new Report("REPORT ", "GENERATED", " on: ", date, " at " + time, " with  ", "SEARCH", " PARAMETERS", " OF: "));
        String from = "from " + searchFrom.toString();
        String to = "to " + searchTo.toString();

        reportList.add(new Report(client, project, task, user, from, to, "Duration", " Billable", "REVENUE"));
        return reportList;
    } 
    
    
    
    public List<Session> compileSessionsForReport(int clientID, int projectID, int taskID, int userID, LocalDate searchFrom, LocalDate searchTo) throws SQLException {
      
        List<Session> allValidSessions = null; // new ArrayList<>(); //??
        
        if (taskID == -2) {  // if all Tasks of a Project has been selected for filtering
            allValidSessions = getAllSessionsOfAProject(projectID);
        } else {
            if ((taskID == -1)/*(projectID == -1) //All Clients Projects*/  && (clientID >= 0)) {  // If Client is selected for filtering, but no Project   // NOT SELECTABLE??
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
           
      
    public void addReportListToCSVFile(List<Report> reportList) throws IOException {
    //Add reportList to File
        File file = new File(reportCSV);
        try (FileWriter fw = new FileWriter (file, false)) {  
            for (int i = 0; i < reportList.size(); i++) {
            Report report = reportList.get(i);
            String reportSTR = (report.getClientName() + "," + report.getProjectName() + "," + report.getTaskName() + "," + report.getLoggedInUser() + "," + report.getStartTime() 
        + "," + report.getFinishTime() + "," + report.getMinutes() + "," + report.getBillable()+ "," + report.getRevenue()) + "\n";
            fw.write(reportSTR);
    
            }
            fw.close();
        }
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
