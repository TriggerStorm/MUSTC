/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import jdk.jfr.Timestamp;
import mustc.be.Project;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;
import mustc.bll.BllManager;
import mustc.dal.ClientDBDAO;
import mustc.dal.DalManager;
import mustc.dal.ProjectDBDAO;
import mustc.dal.SessionDBDAO;
import mustc.dal.TaskDBDAO;
import mustc.dal.UserDBDAO;
//import sq
/**
 * FXML Controller class
 *
 * @author Trigger
 */
public class TestController implements Initializable {
    private BllManager bllManager;
    
    
    @FXML
    private Button bn_test;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handel_button(ActionEvent event) throws SQLException{
       bllManager = new BllManager();
       ClientDBDAO clientDBDao = new ClientDBDAO();
       ProjectDBDAO projectDBDao = new ProjectDBDAO();
       TaskDBDAO taskDBDao = new TaskDBDAO();
       SessionDBDAO sessionDBDao = new SessionDBDAO();
       UserDBDAO userDBDao = new UserDBDAO(); 

     
     System.out.println("");
        System.out.println("test start");
     float aHours = 350;
  
  //   int duration = sessionDBDao.calculateTotalMinutesOfATask(3);  //  TEST
   //      System.out.println("test duration" + duration);
  
    
 
 
 
 //int[] results = taskDBDao.getTotalMinutesOfAProject(2);
//System.out.println("PROJECT2: Unbillable" + results[0] + "   Billable: " + results[1]);               





  //     Project test = projectDBDao.getProjectForAdmin(2);
 
//       Project test = projectDBDao.editProject(testp, "bob's job", 55555555, aHours, 1200, true);
 //        bllManager.removeProjectFromDB(testp);
//        List<Project> allProjects = projectDBDao.getAllProjectsIDsAndNames();
 
 
List<Project> allProjects = projectDBDao.getAllProjectsForAdmin();
        for (int i = 0; i < allProjects.size(); i++) {
            Project test = allProjects.get(i);
            
 System.out.println("");
        System.out.println("ID = " + test.getProjectID());
        System.out.println(test.getProjectName());
        System.out.println(test.getClientName());
        System.out.println(test.getPhoneNr());
        System.out.println(test.getProjectRate());
        System.out.println(test.getTotalBillableMinutes());
        System.out.println(test.getTotalUnbillableMinutes());

        System.out.println(test.getTotalPrice());
        System.out.println(test.getNoOfTasks());
        System.out.println("");
        }
 /*
    Project test = projectDBDao.addNewProjectToDB("testy", 9, 66661234, aHours, 999);
        Project test = projectDBDao.getProjectForUser(2);
 */
 /*
 List<Project> allProjects = projectDBDao.getAllProjectsForUser();
        for (int i = 0; i < allProjects.size(); i++) {
            Project test = allProjects.get(i);
 */ 
 //Project test = projectDBDao.getProjectForUser(2);
 /*         
 System.out.println("");
        System.out.println("ID = " + test.getProjectID());
        System.out.println(test.getProjectName());
        System.out.println(test.getClientName());
        System.out.println(test.getPhoneNr());
        System.out.println(test.getUsersProjectMinutes());
 //       System.out.println(test.getUsersProjectMinutes());
 //       System.out.println(test.getNoOfTasks());
        System.out.println("");
        }
 */       
 
 
 
 
 
 
 // TASK
 
 /*     List<Task> allTasks = taskDBDao.getAllTasksForUser();
        for (int i = 0; i < allTasks.size(); i++) {
            Task test = allTasks.get(i);
 */   
 /*   User testUser = userDBDao.getUser(14);
      List<Task> recentTask = taskDBDao.getUsersThreeRecentTasks(testUser);
 */     
 //    Task test = taskDBDao.addNewTaskToDB("testname", "stuff", 4);
  //   Task test = taskDBDao.getTaskForUser(8);
 //       Task test = taskDBDao.editTask(testt, "newTaskTest", 5, true);
 //       taskDBDao.removeTaskFromDB(test);
 /*
        System.out.println("");
        System.out.println("ID = " + test.getTaskID());
        System.out.println(test.getTaskName());
        System.out.println(test.getAssociatedProjectID());
        System.out.println(test.getProjectName());
        System.out.println(test.getUsersTaskMinutes());
        System.out.println(test.getDevelopers());
        }
  */      
        /*if (test.getSessions() == null) {
        System.out.println("null");
        } else {
        System.out.println(test.getSessions().size()); 
        }  
        System.out.println("");
 */
 
 
  //      } 
            
 /*    List<Task> allTasks = taskDBDao.getAllTasksForAdmin();
        for (int i = 0; i < allTasks.size(); i++) {
            Task test = allTasks.get(i);  
           
 System.out.println("");
        System.out.println("ID = " + test.getTaskID());
        System.out.println(test.getTaskName());
        System.out.println(test.getProjectName());
        System.out.println(test.getProjectRate());
        System.out.println(test.getTotalTaskMinutes());
        System.out.println(test.getDevelopers());
        System.out.println("");
        }
 */     
     
 
 
 
 
 
 //SESSION
   
 //     int usersTaskMinutes = sessionDBDao.calculateUsersTaskMinutes(1, 6);
//System.out.println(" USERs TASK MINUTES 2 = " + usersTaskMinutes);
      

//     Session test = sessionDBDao.addNewSessionToDB(15, 10, "2020-04-20 13:30:00", "2020-04-20 13:40:00");
  //      Session test = sessionDBDao.getSession(19);
 //       Session test = sessionDBDao.editSession(tests, 16, 10, "2020-04-20 16:36:00.0", "2020-04-20 17:58:00.0");
 //       sessionDBDao.removeSessionFromDB(test);
 //        User loggedInUser = userDBDao.getUser(1);
 //        List<Session> allUserSessions = bllManager.getAllSessionsOfAUser(loggedInUser);
//         List<Session> allUserSessions = sessionDBDao.getAllSessions();
 /*        for (int i = 0; i < allUserSessions.size(); i++) {
            Session test = allUserSessions.get(i);
            
      System.out.println("");
        System.out.println("ID = " + test.getSessionID());
 //       System.out.println(test.getAssociatedUserID());
 //        System.out.println(test.getAssociatedUserName());
        System.out.println(test.getAssociatedTaskID());
         System.out.println(test.getAssociatedTaskName());
       System.out.println(test.getStartTime());
        System.out.println(test.getFinishTime());
        System.out.println("");
         } 
 */      
       
 //      User test = userDBDao.addNewUserToDB("Test", "test@test.com", "test", 666, true);
 //      User test = userDBDao.getUser(13);
 //    User test = userDBDao.editUser( testu,  "edited",  "editeda",  "editedb",  1035f,  false);
 //    userDBDao.removeUserFromDB(test);
 /*       List<User> allUsers = bllManager.getAllUsers();
        for (int i = 0; i < allUsers.size(); i++) {
            User test = allUsers.get(i); 
        
       System.out.println("");
       System.out.println("ID = " + test.getUserID());
        System.out.println(test.getUserName());
        System.out.println(test.getEmail());
 //       System.out.println(test.getPassword());
        System.out.println(test.getSalary());
        System.out.println(test.getStatus());
        System.out.println("");
        }
 *)       
        
        
        float standardRate = 799;
 //       Client ctest = clientDBDao.addNewClientToDB("smurf co", "imageplace", "a@dfs.cc", standardRate);
 //      Client ctest = clientDBDao.getClient(9);
 //      clientDBDao.deleteClient( clientToDelete);
  //     Client ctest = clientDBDao.addNewClientToDB("test", 666, "imageloc", "tset@test.com");
 /*  List<Client> allClients = clientDBDao.getAllClients();
        for (int i = 0; i < allClients.size(); i++) {
            Client test = allClients.get(i);
            
  System.out.println("");
       System.out.println(test.getClientId());
        System.out.println(test.getClientName());
        System.out.println(test.getImgLocation());
       System.out.println(test.getEmail());
        System.out.println(test.getStandardRate());
        System.out.println(test.getTotalHours());        
        System.out.println(test.getNoOfProjects());
        System.out.println("");
        }
*/
 
 // CONVERTERS
 
 

  //   LocalDateTime LDTnow = LocalDateTime.now();
  //   String LDTnowSTR = convertLDTtoSqlSTR(LDTnow);

  //   System.out.println("LDTnowSTR = " + LDTnowSTR);

  //  Session test = sessionDBDao.addNewSessionToDB(2, 5, "2020-04-26 13:50:42", "2020-04-26 14:50:42");
  //      System.out.println(test);
    
    
    
 /*              System.out.println(LDTtest);
         System.out.println(LDTtestSTR);
                     System.out.println(sqlSTR);
        System.out.println(timestamp);
*/
 /*   LocalDateTime LDT = LocalDateTime.now();
        System.out.println("LDT =  " + LDT);

  
    String DTN = dateTimeToSqlString(LDT);
    System.out.println("DTN =  " + DTN);
    
    LocalDateTime LDTtest = stringToLocalDate(DTN);
    System.out.println("LDTtest =  " + LDTtest);
*/
  //  Session test = sessionDBDao.addNewSessionToDB(2, 5, DTN, DTN);

    
 //     orderTest();

  
  
  
  
  
          System.out.println("test finish");
    }
    
    
    
 /*   
    public void orderTest() {
        ArrayList<Session> allSessions = new ArrayList<>();
        String dts1 =  "2020-05-02 12:01:02";    
        LocalDateTime ldt1 = stringToLocalDateTime(dts1) ;       
        Session task1 = new Session(1, 3, "A", ldt1, ldt1);
        allSessions.add(task1);  

        String dts2 =  "2020-05-02 05:01:02";    
         LocalDateTime ldt2 = stringToLocalDateTime(dts2) ;       
         Session task2 = new Session(2, 1, "B", ldt2, ldt2);
        allSessions.add(task2);  //      LocalDateTime ldt1 = stringToLocalDate(dts1) ;       
        
        String dts3 =  "2020-05-08 05:01:02";    
        LocalDateTime ldt3 = stringToLocalDateTime(dts3) ;       
        Session task3 = new Session(3, 2, "C", ldt3, ldt3);
        allSessions.add(task3);  //      LocalDateTime ldt1 = stringToLocalDate(dts1) ;       
        
        String dts4 =  "2020-05-01 05:01:02";    
         LocalDateTime ldt4 = stringToLocalDateTime(dts4) ;       
         Session task4 = new Session(4, 4, "D", ldt4, ldt4);
         allSessions.add(task4);  //      LocalDateTime ldt1 = stringToLocalDate(dts1) ;       
                      
        String dts5 =  "2020-05-06 05:01:02";    
       LocalDateTime ldt5 = stringToLocalDateTime(dts5) ;       
        Session task5 = new Session(5, 2, "E", ldt5, ldt5);
         allSessions.add(task5);  //      LocalDateTime ldt1 = stringToLocalDate(dts1) ;       
           
                     
        System.out.println("");
        System.out.println("TEST ! UNSORTED"); 

        for (int i = 0; i < allSessions.size(); i++) {
            Session test = allSessions.get(i);
            System.out.println(" Session " + test.getSessionID() + " Start = " + test.getStartLDT());
        }

         Collections.sort(allSessions, Collections.reverseOrder());  // https://beginnersbook.com/2013/12/sort-arraylist-in-descending-order-in-java/
  //      Collections.sort(allSessions);
        
            System.out.println("");
            System.out.println("TEST ! SORTED"); 
            for (int i = 0; i < allSessions.size(); i++) {
            Session test = allSessions.get(i);
 
            System.out.println(" Session " + test.getSessionID() + " Start = " + test.getStartLDT());
        }
        
        
        }
  
  */  
    
    
    
    
    
    
 // CONVERTERS
    
    public String dateTimeToSqlString(LocalDateTime LDT) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateNowString = LDT.format(formatter);
        return dateNowString;
    } 
    
    public LocalDateTime stringToLocalDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd['T'HH:mm:ss[Z]]");
        String[] parts = dateString.split(" ");
     String sqlSTR = parts[0] + "T" + parts[1].substring(0,8);  // https://stackoverflow.com/questions/17685977/cut-java-string-at-a-number-of-character
        LocalDateTime LDT = LocalDateTime.parse(sqlSTR, formatter);
        return LDT;
    }
    
  




//  LocalDateTime test = LocalDateTime.now().
    
  /*    public String convertLDTtoSqlSTR(LocalDateTime LDT) {    
        String LDTSTR = LDT.toString();
  //      String[] parts = LDTSTR.split("T");
  //      String sqlSTR = parts[0] + " " + parts[1];
  
  
 /* DateTimeFormatter formatter =
    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

System.out.println(LocalDateTime.now().format(formatter));




     LocalDateTime dateTime = LocalDateTime.parse(LDTSTR, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
System.out.println(" dateTime = " + dateTime);

//System.out.println(parts[0] + " " + parts[1]);
 /*       String[] parts2 = sqlSTR.split(".");
System.out.println(parts2[0]);

 */ /*       String sqlSTR2 = "DD";//parts[0] + " " + parts2[0];
        return sqlSTR2;
    
      }
    
  */  
    
    
 /*   public String makeCurrentTimestamp() {    
        Calendar calendar = Calendar.getInstance();
        java.util.Date now = calendar.getTime();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
        String timestampSTR = currentTimestamp.toString();
         System.out.println(currentTimestamp);
        return timestampSTR; 
    }
        
   */ 
  /*  public Timestamp convertLDTtoTimeStamp() {    
        Timestamp timestamp = null;
        LocalDateTime LDTnow = LocalDateTime.now();
        String LDTnowSTR = LDTnow.toString();
        String[] parts = LDTnowSTR.split("T");
        String sqlSTR = parts[0] + " " + parts[1];
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date parsedDate = dateFormat.parse(sqlSTR);
            timestamp = (Timestamp) new java.sql.Timestamp(parsedDate.getTime());
       } catch(Exception e) { 
       }     
       return timestamp;
    }

*/

}
