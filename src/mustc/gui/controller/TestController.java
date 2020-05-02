/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import mustc.be.Project;
import mustc.be.Task;
import mustc.be.User;
import mustc.bll.BllManager;
import mustc.dal.ClientDBDAO;
import mustc.dal.DalManager;
import mustc.dal.ProjectDBDAO;
import mustc.dal.SessionDBDAO;
import mustc.dal.TaskDBDAO;
import mustc.dal.UserDBDAO;

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
  
     
 
     
 //       Project test = projectDBDao.addNewProjectToDB("testy", 9, 66661234, aHours, 999);
 //       Project test = projectDBDao.getProjectForUser(5);
        Project testp = projectDBDao.getProjectForAdmin(7);
 //       Project test = projectDBDao.editProject(testp, "bob's job", 55555555, aHours, 1200, true);
         bllManager.removeProjectFromDB(testp);
 
 
 /*List<Project> allProjects = projectDBDao.getAllProjectsForAdmin();
        for (int i = 0; i < allProjects.size(); i++) {
            Project test = allProjects.get(i);
            
 System.out.println("");
        System.out.println("ID = " + test.getProjectID());
        System.out.println(test.getProjectName());
        System.out.println(test.getClientName());
        System.out.println(test.getPhoneNr());
        System.out.println(test.getProjectRate());
        System.out.println(test.getTotalHours());
        System.out.println(test.getTotalPrice());
        System.out.println(test.getNoOfTasks());
        System.out.println("");
        }
  */      
 /*
 List<Project> allProjects = projectDBDao.getAllProjectsForUser();
        for (int i = 0; i < allProjects.size(); i++) {
            Project test = allProjects.get(i);
            
 System.out.println("");
        System.out.println("ID = " + test.getProjectID());
        System.out.println(test.getProjectName());
        System.out.println(test.getClientName());
        System.out.println(test.getPhoneNr());
        System.out.println(test.getMyProjectHours());
        System.out.println(test.getNoOfTasks());
        System.out.println("");
        }
  */      
 
 //    Task test = taskDBDao.addNewTaskToDB("testname", "stuff", 4);
 //    Task test = taskDBDao.getTaskForUser(2);
 //       Task test = taskDBDao.editTask(testt, "ed", "it", 2);
 //       taskDBDao.removeTaskFromDB(test);
 
 /*       System.out.println("");
        System.out.println("ID = " + test.getTaskID());
        System.out.println(test.getTaskName());
        System.out.println(test.getAssociatedProjectID());
        System.out.println(test.getProjectName());
        System.out.println(test.getMyTaskHours());
        System.out.println(test.getDevelopers());
/*if (test.getSessions() == null) {
        System.out.println("null");
        } else {
        System.out.println(test.getSessions().size()); 
        }  */
        System.out.println("");
 
/*        List<Task> allTasks = taskDBDao.getAllTasksForUser();
        for (int i = 0; i < allTasks.size(); i++) {
            Task test = allTasks.get(i);
   
            System.out.println("");
        System.out.println("ID = " + test.getTaskID());
        System.out.println(test.getTaskName());
        System.out.println(test.getAssociatedProjectID());
        System.out.println(test.getProjectName());
        System.out.println(test.getMyTaskHours());
        System.out.println(test.getDevelopers());
        System.out.println("");
        } 
   */         
 /*    List<Task> allTasks = taskDBDao.getAllTasksForAdmin();
        for (int i = 0; i < allTasks.size(); i++) {
            Task test = allTasks.get(i);  
           
 System.out.println("");
        System.out.println("ID = " + test.getTaskID());
        System.out.println(test.getTaskName());
        System.out.println(test.getProjectName());
        System.out.println(test.getProjectRate());
        System.out.println(test.getTaskDuration());
        System.out.println(test.getDevelopers());
        System.out.println("");
        }
 */     
     
 
   //     Session test = sessionDBDao.addNewSessionToDB(15, 10, "2020-04-20 13:30:00", "2020-04-20 13:40:00");
  //      Session test = sessionDBDao.getSession(19);
 //       Session test = sessionDBDao.editSession(tests, 16, 10, "2020-04-20 16:36:00.0", "2020-04-20 17:58:00.0");
 //       sessionDBDao.removeSessionFromDB(test);
 
 /*     System.out.println("");
        System.out.println("ID = " + test.getSessionID());
        System.out.println(test.getAssociatedUserID());
        System.out.println(test.getAssociatedTaskID());
        System.out.println(test.getStartTime());
        System.out.println(test.getFinishTime());
        System.out.println("");
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
          System.out.println("test finish");
   }
    
}
