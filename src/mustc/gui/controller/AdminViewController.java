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
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.JFXTreeView;

import java.awt.event.MouseEvent;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.tree.TreeNode;

import mustc.be.Client;
import mustc.be.LoggedInUser;
import mustc.be.Project;
import mustc.be.Report;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;
import mustc.bll.BllManager;
import mustc.bll.TimeUtilities;
import mustc.dal.DalManager;
import mustc.dal.ProjectDBDAO;
import mustc.gui.model.AdminModel;
import mustc.be.Task;

/**
 * FXML Controller class
 *
 * @author Trigger
 */
public class AdminViewController implements Initializable, Runnable {

    @FXML
    private TextField tf_newtask;
    @FXML
    private ComboBox<Project> cb_project;
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
    private TableView<Project> Tbv_pj;
    @FXML
    private TableColumn<Project, String> Col_pj_name;
    @FXML
    private TableColumn<Project, String> Col_pj_clint;
    @FXML
    private TableColumn<Project, String> Col_pj_contact;
    @FXML
    private TableColumn<Project, String> Col_pj_nroftask;
    @FXML
    private TableColumn<Project, String> Col_pj_Billable;
    @FXML
    private TableColumn<Project, String> Col_pj_UnBillable;
    @FXML
    private TableColumn<Project, String> Col_pj_totalprice;
    @FXML
    private TableColumn<Project, String> Col_pj_projectrate;
    @FXML
    private Tab tab_task;
    @FXML
    private TableView<Task> tbv_task;
    @FXML
    private TableColumn<Task, String> Col_task_taskname;
    @FXML
    private TableColumn<Task, String> Col_task_project;
    @FXML
    private TableColumn<Task, String> Col_task_devs;
    @FXML
    private TableColumn<Task, String> Col_task_$perhour;
    @FXML
    private TableColumn<Task, String> Col_task_totalhours;
    @FXML
    private JFXTextField task_name;
    @FXML
    private JFXComboBox<Project> cb_task_project;
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
    private JFXComboBox<Project> cb_stat_project;
    @FXML
    private JFXComboBox<Task> cb_stat_task;
    @FXML
    private JFXDatePicker dp_stat_from;
    @FXML
    private JFXDatePicker dp_stat_to;
    @FXML
    private Tab tab_sesion;
    @FXML
    private TableView<Session> tbv_session;
    @FXML
    private TableColumn<Session, String> col_sesion_taskname;
    @FXML
    private TableColumn<Session, String> col_sesion_Developers;
    @FXML
    private TableColumn<Session, String> col_sesion_start;
    @FXML
    private TableColumn<Session, String> col_sesion_stop;
    @FXML
    private Tab tab_clint;
    @FXML
    private TableView<Client> Tbv_Clint;
    @FXML
    private TableColumn<Client, String> Col_clint_name;
    @FXML
    private TableColumn<Client, String> Col_clint_email;
    @FXML
    private TableColumn<Client, String> Col_clint_nrofpj;
    @FXML
    private TableColumn<Client, String> Col_clint_$perhour;
    @FXML
    private JFXTextField tf_clint_name;
    @FXML
    private JFXButton bn_clint_add;
    @FXML
    private JFXButton bn_clint_eddit;
    @FXML
    private JFXButton bn_clint_delete;
    @FXML
    private JFXTextField tf_clint_email;
    @FXML
    private JFXTextField tf_clint_$perhour;
    @FXML
    private JFXTextField tf_pj_name;
    @FXML
    private JFXTextField tf_pj_nr;
    @FXML
    private JFXTextField tf_pj_$perhour;
    @FXML
    private JFXComboBox<Client> cb_pj_clint;
    @FXML
    private JFXButton bn_pj_add;
    @FXML
    private JFXButton bn_pj_eddit;
    @FXML
    private JFXButton bn_pj_delete;
    @FXML
    private JFXDatePicker dp_pj_from;
    @FXML
    private JFXDatePicker dp_pj_to;
    @FXML
    private JFXComboBox<Client> cb_stat_clint;
    @FXML
    private JFXComboBox<User> cb_stat_dev;
    @FXML
    private Tab tab_user;
    @FXML
    private TableView<User> tbv_user;
    @FXML
    private TableColumn<User, String> col_user_name;
    @FXML
    private TableColumn<User, String> col_user_$perhour;
    @FXML
    private TableColumn<User, String> col_user_email;
    @FXML
    private TableColumn<User, String> col_user_status;
    @FXML
    private JFXTextField tf_user_name;
    @FXML
    private JFXTextField tf_user_$perhour;
    @FXML
    private JFXComboBox<String> cb_user_admin;
    @FXML
    private JFXButton bn_user_add;
    @FXML
    private JFXButton bn_user_eddit;
    @FXML
    private JFXButton bn_user_delete;
    private ScrollPane Sp_last3;
    
    
    @FXML
    private JFXButton bn_task1;
    private JFXComboBox<Task> cb_task1;
    @FXML
    private ImageView img_task1;
    @FXML
    private JFXButton bn_task2;
    private JFXComboBox<Task> cb_task2;
    @FXML
    private ImageView img_task2;
    @FXML
    private JFXButton bn_task3;
    private JFXComboBox<Task> cb_task3;
    @FXML
    private ImageView img_task3;
    @FXML
    private Button bn_filepath;
    @FXML
    private JFXTextField tf_user_email;
    @FXML
    private JFXTextField tf_user_password;
    @FXML
    private Text lb_task;
    @FXML
    private Text tb_project;
    @FXML
    private JFXTextField tf_session_start;
    @FXML
    private JFXTextField tf_session_stop;
    @FXML
    private JFXButton bn_session_edit;
    @FXML
    private JFXButton bn_session_delete;
    @FXML
    private JFXTreeView<String> tv_project_task;
    @FXML
    private TableColumn<Task, String> Col_task_Billable;
    @FXML
    private ToggleButton tb_task_billable;
    @FXML
    private ToggleButton tb_smallview_billable;
    @FXML
    private Label lb_session_name;
    @FXML
    private Label lb_session_dev;
    @FXML
    private JFXButton bn_searchClear;
    @FXML
    private JFXTextField tap_search;
    @FXML
    private JFXButton bn_report;
    @FXML
    private Tab tab_Report;
    @FXML
    private TableColumn<Report, String> Report_client;
    @FXML
    private TableColumn<Report, String> Report_project;
    @FXML
    private TableColumn<Report, String> Report_task;
    @FXML
    private TableColumn<Report, String> Report_user;
    @FXML
    private TableColumn<Report, String> Report_startTime;
    @FXML
    private TableColumn<Report, String> Report_finishTime;
    @FXML
    private TableColumn<Report, String> Report_billableMin;
    @FXML
    private TableColumn<Report, String> Report_totalPrice;
    @FXML
    private TableView<Report> Tbv_Report;
    
    private LoggedInUser liu;
    private AdminModel adminModel;
    private TimeUtilities Tu;
    private Task T;
    int MaxWidth;
    boolean min;
    boolean isStarted;
    String startTime;
    Task selectTask;
    String currentTab = "client";
    
    static int msec = 0;
    static int sec = 0;
    static int mins = 0;
    static int hours = 0;
    
    boolean timeState = true;
    
    boolean bb = true;
    boolean bbm = true;
    boolean onTop = false;
    
    Task taskToedit;
    Project projectToedit;
    Client clientToedit;
    Session SessionToedit;
    User userToedit;
    TreeItem TreeviewItem;
    @FXML
    private JFXButton bn_export;
    @FXML
    private Label lb_t1task;
    @FXML
    private Label lb_t1project;
    @FXML
    private Label lb_t2task;
    @FXML
    private Label lb_t2project;
    @FXML
    private Label lb_t3task;
    @FXML
    private Label lb_t3project;
    
   
   
    
    public AdminViewController() {
        MaxWidth = 260;
        min = true;
        isStarted = false;
        liu = LoggedInUser.getInstance();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        Tu = new TimeUtilities();
        adminModel = AdminModel.getInstance();
        
        currentTab = "client";
        setClint();
        setProject();
        setTask();
        setUser();
        setSession();
        cb_stat_clint.setItems(adminModel.getAllClientNameAndId());
        cb_stat_project.setItems(adminModel.getAllProjectsIDsAndNamesForReport());
        cb_stat_dev.setItems(adminModel.getAllUserNameAndId());
        //setTreeView();
        //adminModel.getUsersThreeRecentTasks(adminModel.getUser(1));
        //cb_task1.setSelectionModel(Task);
        // cb_task1.setItems(adminModel.getAllTask());
       // tv_project_task.setVisible(false);
        //recent task 1
        lb_t1task.setText(adminModel.get1().get(0).getTaskName());
        lb_t1project.setText(adminModel.get1().get(0).getProjectName());
        //resent task 2
        lb_t2task.setText(adminModel.get2().get(0).getTaskName());
        lb_t2project.setText(adminModel.get2().get(0).getProjectName());
        //recent task 3
        lb_t3task.setText(adminModel.get3().get(0).getTaskName());
        lb_t3project.setText(adminModel.get3().get(0).getProjectName());
        
        cb_project.setItems(adminModel.getAllProjectsIDsAndNames());
        
        //image work in progress
        /*Image image1 = new Image(userModel.taskImg1());
        Image image2 = new Image(userModel.taskImg2());
        Image image3 = new Image(userModel.taskImg3());
        img_task1.setImage(image1);
        img_task2.setImage(image2);
        img_task3.setImage(image3);*/
        
        
        
        cb_pj_clint.setItems(adminModel.getAllClient());
        cb_task_project.setItems(adminModel.getAllProjectsIDsAndNames());
        cb_user_admin.setItems(adminModel.getAdmin());
        
        lb_loginuser.setText(liu.getName());
        tv_project_task.setVisible(false);
        
       
         
    }    

    public void sizeExpantion(){
        
        
        if(MaxWidth == 260){
        
        Stage stage = (Stage) bn_expandview.getScene().getWindow();
        stage.setMaxHeight(488);
        stage.setMaxWidth(1044);
        stage.setMinHeight(488);
        stage.setMinWidth(1044);
        MaxWidth = 1044;
        fp_last3task.setVisible(true);
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
                fp_last3task.setVisible(true);
                min = true;
            }
            else{
                Stage stage = (Stage) bn_expandview.getScene().getWindow();
                stage.setMaxHeight(488);
                stage.setMaxWidth(260);
                stage.setMinHeight(488);
                stage.setMinWidth(260);
                MaxWidth = 260;
                fp_last3task.setVisible(true);
                min = true;
            }
        }
       
    }
    public void ToggelSize(){
        
        if(min == false){    
            fp_last3task.setVisible(true);
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
            fp_last3task.setVisible(false);
            min = false;
            
            System.out.println("false");
            Stage stage = (Stage) Sp_last3.getScene().getWindow();
            stage.setMaxHeight(248);
            stage.setMaxWidth(255);
            stage.setMinHeight(248);
            stage.setMinWidth(255);
        }
    }
    
    
   
    
    public void setTreeView(){
        
       
       String clientInSearch = search.getText();
       
       
        TreeItem<String> root = new TreeItem<>(clientInSearch);
        root.expandedProperty();
        int x =  adminModel.oListProject().size();
        int y =  adminModel.oListTask().size();
        String cid = clientInSearch.toLowerCase();
        for (int i = 0; i < x ; i++) {
                
                String pname = adminModel.oListProject().get(i).getClientName().toLowerCase();
                if(cid.equals(pname)){
                
                TreeItem<String> item = new TreeItem<String>();
                item.setValue(new String(adminModel.oListProject().get(i).toString()));
                root.getChildren().add(item);
                
                int id = adminModel.oListProject().get(i).getProjectID();
                 
                for (int c = 0; c < y ; c++) {
                    int tid = adminModel.oListTask().get(c).getAssociatedProjectID();
                if(id == tid ){
                TreeItem<String> it = new TreeItem<String>();
                it.setValue(new String(adminModel.oListTask().get(c).toString()));
                item.getChildren().add(it);
                };
            }
                }
            }
     
        tv_project_task.setRoot(root);
    }
    
    @FXML
    private void handel_pick_treeview(javafx.scene.input.MouseEvent event) {
        String T = tv_project_task.getSelectionModel().getSelectedItem().getValue();
       
       int y =  adminModel.oListTask().size();
       
        String tpj = tv_project_task.getSelectionModel().getSelectedItem().getParent().getValue();
       
       for (int c = 0; c < y ; c++) {
                    String tn = adminModel.oListTask().get(c).getTaskName();
                    //String tn = "TreeItem [ value: "+adminModel.oListTask().get(c).getTaskName()+" ]";
                    String tp = adminModel.oListTask().get(c).getProjectName();
                    
          if(       T.equals(tn)){
              if(tpj.equals(tp)){
            selectTask = adminModel.oListTask().get(c);
            tb_project.setText(selectTask.getProjectName());
            lb_task.setText(selectTask.getTaskName());
          }
          }
    }
    }
    
    
    public void setClint(){
    Col_clint_name.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
        Col_clint_email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        Col_clint_nrofpj.setCellValueFactory(new PropertyValueFactory<Client, String>("noOfProjects"));
        Col_clint_$perhour.setCellValueFactory(new PropertyValueFactory<Client, String>("standardRate"));
        //Col_clint_totalhours.setCellValueFactory(new PropertyValueFactory<Client, String>("totalHours"));
       // Col_clint_totalprice.setCellValueFactory(new PropertyValueFactory<Client, String>("totalPrice"));
        Tbv_Clint.setItems(adminModel.getAllClient());
    }
    
    public void setProject(){
    Col_pj_name.setCellValueFactory(new PropertyValueFactory<Project, String>("projectName"));
        Col_pj_clint.setCellValueFactory(new PropertyValueFactory<Project, String>("clientName"));
        Col_pj_contact.setCellValueFactory(new PropertyValueFactory<Project, String>("phoneNr"));
        Col_pj_nroftask.setCellValueFactory(new PropertyValueFactory<Project, String>("noOfTasks"));
        Col_pj_Billable.setCellValueFactory(new PropertyValueFactory<Project, String>("totalBillableMinutes"));
        Col_pj_UnBillable.setCellValueFactory(new PropertyValueFactory<Project, String>("totalUnbillableMinutes"));
        Col_pj_totalprice.setCellValueFactory(new PropertyValueFactory<Project, String>("totalPrice"));
        Col_pj_projectrate.setCellValueFactory(new PropertyValueFactory<Project, String>("projectRate")); // ad this
                Tbv_pj.setItems(adminModel.getAllProject());
      
    }
    
    public void setTask(){
    Col_task_taskname.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        Col_task_project.setCellValueFactory(new PropertyValueFactory<Task, String>("projectName"));
        Col_task_devs.setCellValueFactory(new PropertyValueFactory<Task, String>("developers"));
        Col_task_$perhour.setCellValueFactory(new PropertyValueFactory<Task, String>("projectRate"));
        Col_task_totalhours.setCellValueFactory(new PropertyValueFactory<Task, String>("totalTaskMinutes"));
        Col_task_Billable.setCellValueFactory(new PropertyValueFactory<Task, String>("isBillable"));
                 tbv_task.setItems(adminModel.getAllTask());
    }
    
    public void setUser(){
    col_user_name.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        col_user_$perhour.setCellValueFactory(new PropertyValueFactory<User, String>("salary"));
        col_user_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        col_user_status.setCellValueFactory(new PropertyValueFactory<User, String>("status"));
        
            tbv_user.setItems(adminModel.getAllUser());
    }
    
    public void setSession(){
     col_sesion_taskname.setCellValueFactory(new PropertyValueFactory<Session, String>("associatedTaskName"));
        col_sesion_Developers.setCellValueFactory(new PropertyValueFactory<Session, String>("associatedUserName"));
        col_sesion_start.setCellValueFactory(new PropertyValueFactory<Session, String>("startTime"));
        col_sesion_stop.setCellValueFactory(new PropertyValueFactory<Session, String>("finishTime"));
        
            tbv_session.setItems(adminModel.getAllSessions());
    }
    
    public void setReport(){
        
        Report_client.setCellValueFactory(new PropertyValueFactory<Report, String>("clientName"));
        Report_project.setCellValueFactory(new PropertyValueFactory<Report, String>("projectName"));
        Report_task.setCellValueFactory(new PropertyValueFactory<Report, String>("taskName"));
        Report_user.setCellValueFactory(new PropertyValueFactory<Report, String>("loggedInUser"));
        Report_startTime.setCellValueFactory(new PropertyValueFactory<Report, String>("startTime"));
        Report_finishTime.setCellValueFactory(new PropertyValueFactory<Report, String>("finishTime"));
        Report_billableMin.setCellValueFactory(new PropertyValueFactory<Report, String>("totalBillableMinutes"));
        Report_totalPrice.setCellValueFactory(new PropertyValueFactory<Report, String>("totalPrice"));
        
        Tbv_Report.setItems(adminModel.oReport());
        System.out.println(""+adminModel.oReport().size());
    }
    
    
    
    public void addTask(){  //  COMMENTED OUT FOR NOW
       adminModel.addNewTaskToDB(tf_newtask.getText(),cb_project.getSelectionModel().getSelectedItem().getProjectID(), bbm);
       /*selectTask.setTaskName(tf_newtask.getText());
       selectTask.setProjectName(cb_project.getSelectionModel().toString());*/
       tb_project.setText(cb_project.getSelectionModel().getSelectedItem().toString());
       lb_task.setText(tf_newtask.getText().toString());
    }
    
    @FXML
    private void toggel_size(ActionEvent event) {
        ToggelSize();
    }
    @FXML
    private void handel_view(ActionEvent event) {
        sizeExpantion();
    }

    

    @FXML
    private void handle_tap_clint(Event event) {
        
        tap_search.clear();
        if(currentTab != "client" ){
        Tbv_Clint.setItems(adminModel.oListClient());}
        currentTab ="client";
    }

    @FXML
    private void handle_tap_project(Event event) {
       currentTab ="project";    
       tap_search.clear();
       Tbv_pj.setItems(adminModel.oListProject());
    }

    @FXML
    private void handle_tap_task(Event event) {
       currentTab ="task";
       tap_search.clear();
       tbv_task.setItems(adminModel.oListTask());
    }

    private void handle_tap_stats(Event event) {
        currentTab ="stats";
        tap_search.clear();
    }

    @FXML
    private void handle_tap_sessions(Event event) {
        currentTab ="sessions";
        tap_search.clear();
        tbv_session.setItems(adminModel.oListSession());
    }

    @FXML
    private void handle_tap_user(Event event) {
        currentTab ="user";
        tap_search.clear();
        tbv_user.setItems(adminModel.oListUser());
    }

    

    @FXML
    private void handel_task1(ActionEvent event) throws SQLException {
       
            selectTask = adminModel.get1().get(0);
            lb_task.setText(adminModel.get1().get(0).getTaskName());
            tb_project.setText(adminModel.get1().get(0).getProjectName());
    }

    @FXML
    private void handle_task2(ActionEvent event) {
        selectTask = adminModel.get2().get(0);
        lb_task.setText(adminModel.get2().get(0).getTaskName());
            tb_project.setText(adminModel.get2().get(0).getProjectName());
    }

    @FXML
    private void handle_task3(ActionEvent event) {
        selectTask = adminModel.get3().get(0);
        lb_task.setText(adminModel.get3().get(0).getTaskName());
            tb_project.setText(adminModel.get3().get(0).getProjectName());
    }

    @FXML
    private void handel_client_add(ActionEvent event) {
               String Rate = tf_clint_$perhour.getText().trim();
               float fRate = Float.parseFloat(Rate);
        adminModel.addNewClientToDB(
                tf_clint_name.getText().trim(),
                "string path",
                tf_clint_email.getText().trim(),
                fRate);
    }

    @FXML
    private void handel_client_eddit(ActionEvent event) {
        String Rate = tf_clint_$perhour.getText().trim();
               float fRate = Float.parseFloat(Rate);
        adminModel.editClient(clientToedit,
                tf_clint_name.getText().trim(),
                fRate,
                "string path",
                tf_clint_email.getText().trim()
                );
    }

    @FXML
    private void handel_client_delete(ActionEvent event) {
        adminModel.removeClientFromDB(clientToedit);
                
    }

    @FXML
    private void handel_project_add(ActionEvent event) {
        int allocatedHours = 10;
        double Nr = Double.parseDouble(tf_pj_nr.getText());
        int iNr = (int)Nr;
        String Rate = tf_pj_$perhour.getText().trim();
        float fRate = Float.parseFloat(Rate);
        adminModel.addNewProjectToDB(
        tf_pj_name.getText().trim(),
        cb_pj_clint.getSelectionModel().getSelectedItem().getClientId(),
        iNr,
        fRate,
        allocatedHours);
        tf_pj_name.clear();
        tf_pj_nr.clear();
        tf_pj_$perhour.clear();
    }

    @FXML
    private void handel_project_eddit(ActionEvent event) {
        int allocatedHours = 10;
        String Rate = tf_pj_$perhour.getText().trim();
               float fRate = Float.parseFloat(Rate);
        adminModel.editProject(
                projectToedit,
                tf_pj_name.getText().trim(),
                cb_pj_clint.getSelectionModel().getSelectedItem().getClientId(),  
                fRate,
                allocatedHours,
                projectToedit.isClosed()
                );
        
    }

    @FXML
    private void handel_project_delete(ActionEvent event) {
        adminModel.removeProjectFromDB(projectToedit);
        /*ObservableList<TablePosition> remove = Tbv_pj.getSelectionModel().getSelectedCells();
        Tbv_pj.getSelectionModel().getSelectedCells().remove(remove);*/
    }

    @FXML
    private void handel_task_add(ActionEvent event) {
        adminModel.addNewTaskToDB(
                task_name.getText().trim(),              
                cb_task_project.getSelectionModel().getSelectedItem().getProjectID(),
                bb);
                
                
    }

    @FXML
    private void handel_task_eddit(ActionEvent event) {
        adminModel.editTask(taskToedit,
                task_name.getText().trim(),
                taskToedit.getAssociatedProjectID(),
                bb);    // MOCK DATA  
    }

    @FXML
    private void handel_task_delete(ActionEvent event) {
        adminModel.removeTaskFromDB(taskToedit);
    }

    @FXML
    private void handel_user_add(ActionEvent event) {
        String Rate = tf_user_$perhour.getText().trim();
               float fSal = Float.parseFloat(Rate);
            adminModel.addNewUserToDB(
                tf_user_name.getText().trim(),
                tf_user_email.getText().trim(),
                tf_user_password.getText().trim(),
                fSal,// add convter to flote
                cb_user_admin.getSelectionModel().getSelectedItem().toString());
    }

    @FXML
    private void handel_user_eddit(ActionEvent event) {
        String Rate = tf_user_$perhour.getText().trim();
               float fSal = Float.parseFloat(Rate);    
        adminModel.editUser(userToedit,
                tf_user_name.getText().trim(),
                tf_user_email.getText().trim(),
                tf_user_password.getText().trim(),
                fSal,
                cb_user_admin.getSelectionModel().getSelectedItem().toString()
                );
    }

    @FXML
    private void handel_user_delete(ActionEvent event) {
       adminModel.removeUserFromDB(userToedit);
    }

    @FXML
    private void handle_filepath(ActionEvent event) {
    }

    @FXML
    private void handel_session_edit(ActionEvent event) {
        adminModel.editSession(SessionToedit,
                SessionToedit.getAssociatedUserID(),
                SessionToedit.getAssociatedTaskID(),
                tf_session_start.getText(),
                tf_session_stop.getText());
    }

    @FXML
    private void handel_session_delete(ActionEvent event) {
        adminModel.removeSessionFromDB(SessionToedit);
    }

    @FXML
    private void handle_start_stop(ActionEvent event) {
        
       sessionStartNStop();
        
    }
    
    public void sessionStartNStop(){
     if(isStarted == false)
            {
            isStarted = true;
             Platform.runLater(()->{
                 bn_start_stop.setText("Stop");
             });
            LocalDateTime LDTnow = LocalDateTime.now();
            
            startTime = adminModel.localDateTimeToString(LDTnow);
            timeState = true;
            clock();
            }
                else{
             isStarted = false;
             Platform.runLater(()->{   
             bn_start_stop.setText("Start");
             });
             int lu = 1;
            
             LocalDateTime LDTnow = LocalDateTime.now();
             String StopTime = adminModel.localDateTimeToString(LDTnow);
             adminModel.addNewSessionToDB(lu, selectTask.getTaskID(), startTime, StopTime);
             timeState = false;
             Platform.runLater(()->{
             lb_tasktime.setText("00:00:00");
             });
        }
    }
    
    @FXML
    private void handel_addTaskminview(ActionEvent event) {
         Thread t = new Thread()
        {
            public void run()
            {
        addTask();
        adminModel.getAllTask();
        
        tbv_task.setItems(adminModel.oListTask());
        
        sessionStartNStop();
        
       String T = tf_newtask.getText();
       
       int y =  adminModel.oListTask().size();
       
        String tpj = cb_project.getSelectionModel().getSelectedItem().getProjectName();
       
       for (int c = 0; c < y ; c++) {
                    String tn = adminModel.oListTask().get(c).getTaskName();
                    //String tn = "TreeItem [ value: "+adminModel.oListTask().get(c).getTaskName()+" ]";
                    String tp = adminModel.oListTask().get(c).getProjectName();
                    
          if(       T.equals(tn)){
              if(tpj.equals(tp)){
            selectTask = adminModel.oListTask().get(c);
              }}}
                 }
        
        };
                 t.start();
        
    }

    @FXML
    private void handel_pick_project(javafx.scene.input.MouseEvent event) {
        projectToedit = Tbv_pj.getSelectionModel().getSelectedItem();
        tf_pj_name.setText(projectToedit.getProjectName());
        cb_pj_clint.setPromptText(projectToedit.getClientName());
        Integer i = projectToedit.getPhoneNr();
        String String = i.toString();
        tf_pj_nr.setText(String);
        
        float n = projectToedit.getProjectRate();
        String Rate = String.valueOf(n);
        tf_pj_$perhour.setText(Rate);
    
    }

    @FXML
    private void handel_pick_client(javafx.scene.input.MouseEvent event) {
        clientToedit = Tbv_Clint.getSelectionModel().getSelectedItem();
        tf_clint_name.setText(clientToedit.getClientName());
        tf_clint_email.setText(clientToedit.getEmail());
        float n = clientToedit.getStandardRate();
        String Rate = String.valueOf(n);
        tf_clint_$perhour.setText(Rate);
        
    }

    @FXML
    private void handel_pick_task(javafx.scene.input.MouseEvent event) {
        taskToedit = tbv_task.getSelectionModel().getSelectedItem();
        task_name.setText(taskToedit.getTaskName());
        cb_task_project.setPromptText(taskToedit.getProjectName());
        selectTask = tbv_task.getSelectionModel().getSelectedItem();
        lb_task.setText(tbv_task.getSelectionModel().getSelectedItem().getTaskName());
        tb_project.setText(tbv_task.getSelectionModel().getSelectedItem().getProjectName());
    }

    @FXML
    private void handel_pick_session(javafx.scene.input.MouseEvent event) {
        SessionToedit = tbv_session.getSelectionModel().getSelectedItem();
        lb_session_name.setText(SessionToedit.getAssociatedTaskName());
        tf_session_start.setText(SessionToedit.getStartTime());
        tf_session_stop.setText(SessionToedit.getFinishTime());
        lb_session_dev.setText(SessionToedit.getAssociatedUserName());
       
            
   
    }

    @FXML
    private void handel_pick_user(javafx.scene.input.MouseEvent event) {
        userToedit = tbv_user.getSelectionModel().getSelectedItem();
        tf_user_name.setText(userToedit.getUserName());
        tf_user_email.setText(userToedit.getEmail());
        tf_user_password.setText(userToedit.getPassword());
        float n = userToedit.getSalary();
        String Rate = String.valueOf(n);
        tf_user_$perhour.setText(Rate);
        cb_user_admin.setPromptText(userToedit.getStatus());
        
        
    }

    @FXML
    private void handel_billable(ActionEvent event) {
        
        if(bb == true){
            bb = false;  
            
            
        }
        else{
        bb = true;
        }
        
    }

    @FXML
    private void handle_small_billable(ActionEvent event) {
        
        if(bbm == true){
            bbm = false;     
        }
        else{
        bbm = true;
        }
        
    }

    @FXML
    private void handel_onTop(ActionEvent event) {
        
        if(onTop == false){
        Stage stage = (Stage) bn_settings.getScene().getWindow();
        stage.setAlwaysOnTop(true);
        onTop = true;}
        else{
        Stage stage = (Stage) bn_settings.getScene().getWindow();
        stage.setAlwaysOnTop(false);
        onTop = false;        
        }
        
    }

    @FXML
    private void handel_startDP(ActionEvent event) {
        
    }

    @FXML
    private void handel_stopDP(ActionEvent event) {
    }
    
    public void clock(){
        
        
        Thread t = new Thread()
        {
            public void run()
            {
                    for(;;)
                    {
                        if(timeState==true)
                        {
                            try
                            {
                                
                                sleep(1);
                                
                                if(msec>500)
                                {
                                msec=0;
                                sec++;
                                }
                                if(sec>59)
                                {
                                msec=0;
                                sec=0;
                                mins++;
                                }
                                if(mins>59)
                                {
                                msec=0;
                                sec=0;
                                mins=0;
                                hours++;
                                }
                                
                                msec++;
                                DecimalFormat df = new DecimalFormat("00");
                                
                                String fsec = df.format(sec);
                                String fmin = df.format(mins);
                                String fhour = df.format(hours);
                                Platform.runLater(()->{
               
                                lb_tasktime.setText(""+ fhour+":" +fmin+":" +fsec);
                                });
                            }
                            catch(Exception e)
                            {
                            
                            }
                            
                        }
                        
                    
                        else
                        {
                         break;       
                        }
                    }
            }
            
                
            
        };
        
        t.start();
        
        
        
    }
  
    @FXML
    private void handel_searchClear(ActionEvent event) {
       search.clear();
       tv_project_task.setVisible(false);
 
    }
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void Search(KeyEvent event) {
      if(currentTab == "task"){ 
       ObservableList<Task> taskInSearch;
       taskInSearch = adminModel.searchTask(adminModel.oListTask(), tap_search.getText());
       tbv_task.setItems(taskInSearch);}
       else if(currentTab =="client"){
       ObservableList<Client> clientInSearch;
       clientInSearch = adminModel.searchClient(adminModel.oListClient(), tap_search.getText());
       Tbv_Clint.setItems(clientInSearch);
       }
       else if(currentTab =="project"){
           ObservableList<Project> projectInSearch;
            projectInSearch = adminModel.searchProject(adminModel.oListProject(), tap_search.getText());
            Tbv_pj.setItems(projectInSearch);
       }
       else if (currentTab =="sessions"){
       ObservableList<Session> sessionInSearch;
            sessionInSearch = adminModel.searchSession(adminModel.oListSession(), tap_search.getText());
            tbv_session.setItems(sessionInSearch);
       }
       else if (currentTab =="stats"){
       
       }
       else if (currentTab == "user"){
         ObservableList<User> userInSearch;
            userInSearch = adminModel.searchUser(adminModel.oListUser(), tap_search.getText());
            tbv_user.setItems(userInSearch);
       }
  
       
    }
    
    public void Refresh (){
        ObservableList<Task> observableList = adminModel.oListTask();
       tbv_task.setItems(observableList);
       
    }

    @FXML
    private void handel_selectClient(ActionEvent event) {
        ObservableList<Project> cp = adminModel.getAllProjectIDsAndNamesOfAClient(cb_stat_clint.getSelectionModel().getSelectedItem().getClientId());
        cb_stat_project.setItems(cp);
    }

    @FXML
    private void handel_report(ActionEvent event) {
        // button
        LocalDate from;
        LocalDate to;
        if(dp_stat_from.getValue() == null){
            from = LocalDate.now().minusYears(100);
        }
            else{
               from= dp_stat_from.getValue();    
            }
        
        if(dp_stat_to.getValue() == null){
                   to = LocalDate.now().plusDays(1);
                }
                else{
                to = dp_stat_to.getValue();
                };
        
        adminModel.generateReport(
                cb_stat_clint.getSelectionModel().getSelectedItem().getClientId(),
                cb_stat_project.getSelectionModel().getSelectedItem().getProjectID(),
                cb_stat_task.getSelectionModel().getSelectedItem().getTaskID(),
                cb_stat_dev.getSelectionModel().getSelectedItem().getUserID(),
                from,
                to
                );
        setReport();
        
    }

    @FXML
    private void handle_tap_report(Event event) {
        
        
    }

    @FXML
    private void Handel_project(ActionEvent event) {
        
        ObservableList<Task> cp = adminModel.getAllTaskIDsAndNamesOfAProject(cb_stat_project.getSelectionModel().getSelectedItem().getProjectID());
        cb_stat_task.setItems(cp);
                
    }

    @FXML
    private void handel_search(KeyEvent event) {
        setTreeView();
        int l = search.getLength();
        if(l <= 0){
         tv_project_task.setVisible(false);
        }
        else{
        tv_project_task.setVisible(true);
        }
        
        
    }

    @FXML
    private void handle_export(ActionEvent event) {
    }

    
}
