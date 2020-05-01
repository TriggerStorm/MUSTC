/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import mustc.be.Client;
import mustc.be.Project;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;
import mustc.dal.ClientDBDAO;
import mustc.dal.ProjectDBDAO;
import mustc.dal.SessionDBDAO;
import mustc.dal.TaskDBDAO;
import mustc.dal.UserDBDAO;


/**
 *
 * @author Trigger
 */
public class UserViewController extends JFrame implements Initializable {
    
    private Label label;
    @FXML

    private TextField tf_newtask;
    @FXML
    private ComboBox<?> cb_project;
    @FXML
    private Button bn_add;
    @FXML
    private TextField search;
    @FXML
    private ToggleButton tb_toggle;
    @FXML
    private Button bn_start_stop;
    @FXML
    private Label lb_tasktime;
    @FXML
    private Label lb_timetoday;
    @FXML
    private Label lb_loginuser;
    @FXML
    private JFXButton bn_settings;
    @FXML
    private Button bn_expandview;
    @FXML
    private FlowPane fp_last3task;
    @FXML
    private Tab tab_pj;
    @FXML
    private TableView<?> Tbv_pj;
    @FXML
    private TableColumn<?, ?> Col_pj_name;
    @FXML
    private TableColumn<?, ?> Col_pj_clint;
    @FXML
    private TableColumn<?, ?> Col_pj_contact;
    @FXML
    private TableColumn<?, ?> Col_pj_nroftask;
    @FXML
    private TableColumn<?, ?> Col_pj_myhours;
    @FXML
    private TextField pj_search;
    @FXML
    private Tab tab_task;
    @FXML
    private TableView<?> tbv_task;
    @FXML
    private TableColumn<?, ?> Col_task_taskname;
    @FXML
    private TableColumn<?, ?> Col_task_project;
    @FXML
    private TableColumn<?, ?> Col_task_devs;
    @FXML
    private TableColumn<?, ?> Col_task_myhours;
    @FXML
    private JFXTextField task_name;
    @FXML
    private JFXComboBox<?> cb_task_project;
    @FXML
    private JFXButton bn_task_add;
    @FXML
    private JFXButton bn_task_eddit;
    @FXML
    private JFXButton bn_task_delete;
    @FXML
    private JFXDatePicker dp_task_from;
    @FXML
    private JFXDatePicker dp_task_to;
    @FXML
    private TextField task_search;
    @FXML
    private Tab tab_stat;
    @FXML
    private BarChart<?, ?> stat_graf;
    @FXML
    private JFXComboBox<?> cb_stat_project;
    @FXML
    private JFXComboBox<?> cb_stat_task;
    @FXML
    private JFXDatePicker dp_stat_from;
    @FXML
    private JFXDatePicker dp_stat_to;
    @FXML
    private Label lb_stat_taskhours;
    @FXML
    private Label lb_stat_totalhours;
    @FXML
    private TextField stat_search;
    @FXML
    private Tab tab_sesion;
    @FXML
    private TableColumn<?, ?> col_sesion_taskname;
    @FXML
    private TableColumn<?, ?> col_sesion_date;
    @FXML
    private TableColumn<?, ?> col_sesion_start;
    @FXML
    private TableColumn<?, ?> col_sesion_stop;
    @FXML
    private TableColumn<?, ?> col_sesion_myhours;
    @FXML
    private TextField sesion_search;
    @FXML
    
    private ScrollPane Sp_last3;
    int MaxWidth;
    boolean min;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //to do
    }    

    public UserViewController() {
        MaxWidth = 260;
        min = true;
    }
    
    public void sizeExpantion(){
        
        
        if(MaxWidth == 260){
        
        Stage stage = (Stage) bn_expandview.getScene().getWindow();
        stage.setMaxHeight(488);
        stage.setMaxWidth(1044);
        stage.setMinHeight(488);
        stage.setMinWidth(1044);
        MaxWidth = 1044;
        Sp_last3.setVisible(true);
            min = true;
        
        }
        else{
            if(min == false){
                Stage stage = (Stage) bn_expandview.getScene().getWindow();
                stage.setMaxHeight(488);
                stage.setMaxWidth(1044);
                stage.setMinHeight(488);
                stage.setMinWidth(1044);
                MaxWidth = 1044;
                Sp_last3.setVisible(true);
                min = true;
            }
            else{
                Stage stage = (Stage) bn_expandview.getScene().getWindow();
                stage.setMaxHeight(488);
                stage.setMaxWidth(260);
                stage.setMinHeight(488);
                stage.setMinWidth(260);
                MaxWidth = 260;
                Sp_last3.setVisible(true);
                min = true;
            }
        }
       
    }
    public void toggelSize(){
        
        if(min == false){    
                Sp_last3.setVisible(true);
                min = true;
           
                System.out.println("true");
                Stage stage = (Stage) Sp_last3.getScene().getWindow();
                stage.setMaxHeight(488);
                stage.setMaxWidth(260);
                stage.setMinHeight(488);
                stage.setMinWidth(260);
                MaxWidth = 260;
            }
        else{
                Sp_last3.setVisible(false);
                 min = false;
            
                System.out.println("false");
                Stage stage = (Stage) Sp_last3.getScene().getWindow();
                stage.setMaxHeight(248);
                stage.setMaxWidth(255);
                stage.setMinHeight(248);
                stage.setMinWidth(255);
        }
    }
 
    @FXML
    private void handle_view(ActionEvent event) {
        sizeExpantion();
    }

    @FXML
    private void toggel_size(ActionEvent event) {
        toggelSize();
    }

    @FXML
    private void handel_startsotp(ActionEvent event) {
        
    }
    
    @FXML
    private void test (ActionEvent event) throws SQLException {
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
 /*       Project testp = projectDBDao.getProjectForAdmin(7);
        Project test = projectDBDao.editProject(testp, "bob's job", 55555555, aHours, 1200, true);
 */
 
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
     Task test = taskDBDao.getTaskForUser(2);
 //       Task test = taskDBDao.editTask(testt, "ed", "it", 2);
 //       taskDBDao.removeTaskFromDB(test);
 
        System.out.println("");
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
 
/*       System.out.println("");
       System.out.println("ID = " + test.getUserID());
        System.out.println(test.getUserName());
        System.out.println(test.getEmail());
        System.out.println(test.getPassword());
        System.out.println(test.getSalary());
        System.out.println(test.getIsAdmin());
        System.out.println("");
*/
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
         System.out.println("test finish");
*/
    }
}
    