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
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mustc.be.Client;
import mustc.be.Project;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;
import mustc.bll.BllManager;
import mustc.dal.DalManager;
import mustc.dal.ProjectDBDAO;
import mustc.gui.model.AdminModel;

/**
 * FXML Controller class
 *
 * @author Trigger
 */
public class AdminViewController implements Initializable {

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
    private TableColumn<Project, String> Col_pj_totalhours;
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
    private TableColumn<Task, String> Col_task_totalprice;
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
    private TableColumn<Client, String> Col_clint_totalhours;
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
    private JFXComboBox<?> cb_pj_clint;
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
    private Label lb_stat_priceperhour;
    @FXML
    private Label lb_stat_totalprice;
    @FXML
    private JFXComboBox<?> cb_stat_clint;
    @FXML
    private JFXComboBox<?> cb_stat_dev;
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
    private JFXComboBox<?> cb_user_admin;
    @FXML
    private JFXButton bn_user_add;
    @FXML
    private JFXButton bn_user_eddit;
    @FXML
    private JFXButton bn_user_delete;
    @FXML
    private ScrollPane Sp_last3;
    
    
    @FXML
    private JFXButton bn_task1;
    @FXML
    private JFXComboBox<Task> cb_task1;
    @FXML
    private ImageView img_task1;
    @FXML
    private JFXButton bn_task2;
    @FXML
    private JFXComboBox<Task> cb_task2;
    @FXML
    private ImageView img_task2;
    @FXML
    private JFXButton bn_task3;
    @FXML
    private JFXComboBox<Task> cb_task3;
    @FXML
    private ImageView img_task3;
    
    
    private AdminModel adminModel;
    int MaxWidth;
    boolean min;
    boolean isStarted;
    String startTime;
    Task selectTask;
    
    
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
    private JFXTextField tf_session_name;
    @FXML
    private JFXTextField tf_session_dev;
    @FXML
    private JFXTextField tf_session_start;
    @FXML
    private JFXTextField tf_session_stop;
    @FXML
    private JFXButton bn_session_edit;
    @FXML
    private JFXButton bn_session_delete;
    
    
    
   

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        adminModel = new AdminModel();
        setClint();
        setProject();
        setTask();
        setUser();
        setSession();
        
        //adminModel.getUsersThreeRecentTasks(adminModel.getUser(1));
        //cb_task1.setSelectionModel(Task);
        // cb_task1.setItems(adminModel.getAllTask());
       
        //recent task 1
        cb_task1.setItems(adminModel.get1());// dont work
        cb_task1.setPromptText(cb_task1.getItems().get(0).getTaskName());
        bn_task1.setText(cb_task1.getItems().get(0).getProjectName());
        //resent task 2
        cb_task2.setItems(adminModel.get2());
        cb_task2.setPromptText(cb_task2.getItems().get(0).getTaskName());
        bn_task2.setText(cb_task2.getItems().get(0).getProjectName());
        //recent task 3
        cb_task3.setItems(adminModel.get3());
        cb_task3.setPromptText(cb_task3.getItems().get(0).getTaskName());
        bn_task3.setText(cb_task3.getItems().get(0).getProjectName());
        
        cb_project.setItems(adminModel.getAllProjectsIDsAndNames());
        
        //image work in progress
        /*Image image1 = new Image(userModel.taskImg1());
        Image image2 = new Image(userModel.taskImg2());
        Image image3 = new Image(userModel.taskImg3());
        img_task1.setImage(image1);
        img_task2.setImage(image2);
        img_task3.setImage(image3);*/
    }    

    public AdminViewController() {
        MaxWidth = 260;
        min = true;
        isStarted = false;
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
    public void ToggelSize(){
        
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
 
    
    
    public void setClint(){
    Col_clint_name.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
        Col_clint_email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        Col_clint_nrofpj.setCellValueFactory(new PropertyValueFactory<Client, String>("noOfProjects"));
        Col_clint_$perhour.setCellValueFactory(new PropertyValueFactory<Client, String>("standardRate"));
        Col_clint_totalhours.setCellValueFactory(new PropertyValueFactory<Client, String>("totalHours"));
       // Col_clint_totalprice.setCellValueFactory(new PropertyValueFactory<Client, String>("totalPrice"));
        Tbv_Clint.setItems(adminModel.getAllClient());
    }
    
    public void setProject(){
    Col_pj_name.setCellValueFactory(new PropertyValueFactory<Project, String>("projectName"));
        Col_pj_clint.setCellValueFactory(new PropertyValueFactory<Project, String>("clientName"));
        Col_pj_contact.setCellValueFactory(new PropertyValueFactory<Project, String>("phoneNr"));
        Col_pj_nroftask.setCellValueFactory(new PropertyValueFactory<Project, String>("noOfTasks"));
        Col_pj_totalhours.setCellValueFactory(new PropertyValueFactory<Project, String>("totalHours"));
       // Col_pj_totalprice.setCellValueFactory(new PropertyValueFactory<Project, String>("totalPrice"));
        Col_pj_projectrate.setCellValueFactory(new PropertyValueFactory<Project, String>("projectRate")); // ad this
                Tbv_pj.setItems(adminModel.getAllProject());
    }
    
    public void setTask(){
    Col_task_taskname.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        Col_task_project.setCellValueFactory(new PropertyValueFactory<Task, String>("projectName"));
        Col_task_devs.setCellValueFactory(new PropertyValueFactory<Task, String>("developers"));
        Col_task_$perhour.setCellValueFactory(new PropertyValueFactory<Task, String>("projectRate"));
        Col_task_totalhours.setCellValueFactory(new PropertyValueFactory<Task, String>("totalTaskHours"));
       // Col_task_totalprice.setCellValueFactory(new PropertyValueFactory<Task, String>("totalPrice"));
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
    
    public void addTask(){  //  COMMENTED OUT FOR NOW
       adminModel.addNewTaskToDB(tf_newtask.getText(),cb_project.getSelectionModel().getSelectedItem().getProjectID(), true);
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
        /*Col_clint_name.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
        Col_clint_email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        Col_clint_nrofpj.setCellValueFactory(new PropertyValueFactory<Client, String>("noOfProjects"));
        Col_clint_$perhour.setCellValueFactory(new PropertyValueFactory<Client, String>("standardRate"));
        Col_clint_totalhours.setCellValueFactory(new PropertyValueFactory<Client, String>("totalHours"));
        // Col_clint_totalprice.setCellValueFactory(new PropertyValueFactory<Client, String>("totalPrice"));
        Tbv_Clint.setItems(adminModel.getAllClient());*/
    }

    @FXML
    private void handle_tap_project(Event event) {
        /*
        Col_pj_name.setCellValueFactory(new PropertyValueFactory<Project, String>("projectName"));
        Col_pj_clint.setCellValueFactory(new PropertyValueFactory<Project, String>("clientName"));
        Col_pj_contact.setCellValueFactory(new PropertyValueFactory<Project, String>("phoneNr"));
        Col_pj_nroftask.setCellValueFactory(new PropertyValueFactory<Project, String>("noOfTasks"));
        Col_pj_totalhours.setCellValueFactory(new PropertyValueFactory<Project, String>("totalHours"));
       // Col_pj_totalprice.setCellValueFactory(new PropertyValueFactory<Project, String>("totalPrice"));
        //.setCellValueFactory(new PropertyValueFactory<Project, String>("projectRate")); // ad this
                Tbv_pj.setItems(adminModel.getAllProject());*/
                
    }

    @FXML
    private void handle_tap_task(Event event) {
       /*
        Col_task_taskname.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        Col_task_project.setCellValueFactory(new PropertyValueFactory<Task, String>("projectName"));
        Col_task_devs.setCellValueFactory(new PropertyValueFactory<Task, String>("developers"));
        Col_task_$perhour.setCellValueFactory(new PropertyValueFactory<Task, String>("projectRate"));
        Col_task_totalhours.setCellValueFactory(new PropertyValueFactory<Task, String>("totalTaskHours"));
        // Col_task_totalprice.setCellValueFactory(new PropertyValueFactory<Task, String>("totalPrice"));
                 tbv_task.setItems(adminModel.getAllTask());*/
    }

    @FXML
    private void handle_tap_stats(Event event) {
    }

    @FXML
    private void handle_tap_sessions(Event event) {
        /*
        col_sesion_taskname.setCellValueFactory(new PropertyValueFactory<Session, String>("taskName"));
        col_sesion_date.setCellValueFactory(new PropertyValueFactory<Session, String>("date"));
        col_sesion_start.setCellValueFactory(new PropertyValueFactory<Session, String>("start"));
        col_sesion_stop.setCellValueFactory(new PropertyValueFactory<Session, String>("stop"));
        col_sesion_myhours.setCellValueFactory(new PropertyValueFactory<Session, String>("myHours"));
            tbv_session.setItems(adminModel.getAllSessions());*/
    }

    @FXML
    private void handle_tap_user(Event event) {
        /*
        col_user_name.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        col_user_$perhour.setCellValueFactory(new PropertyValueFactory<User, String>("salary"));
        col_user_email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        col_user_status.setCellValueFactory(new PropertyValueFactory<User, String>("status"));
        
            tbv_user.setItems(adminModel.getAllUser());*/
    }

    

    @FXML
    private void handel_task1(ActionEvent event) throws SQLException {
        //cb_task1.getSelectionModel().getSelectedItem();
       // System.out.println("t1" + bn_task1.getText());
       System.out.println("cb1"+ cb_task1.getSelectionModel().getSelectedItem().getAssociatedProjectID());
       // System.out.println("cb1"+ cb_task1.getSelectionModel().getSelectedItem().getTaskID());
        //if
            selectTask = cb_task1.getSelectionModel().getSelectedItem();
            lb_task.setText(cb_task1.getSelectionModel().getSelectedItem().getTaskName());
            tb_project.setText(cb_task1.getSelectionModel().getSelectedItem().getProjectName());
        //else
            //lb_task.setText(cb_task1.getItems().get(0).getTaskName());
            //tb_project.setText(cb_task1.getItems().get(0).getProjectName());
        
        
    }

    @FXML
    private void handle_task2(ActionEvent event) {
        selectTask = cb_task2.getSelectionModel().getSelectedItem();
        lb_task.setText(cb_task2.getSelectionModel().getSelectedItem().getTaskName());
            tb_project.setText(cb_task2.getSelectionModel().getSelectedItem().getProjectName());
    }

    @FXML
    private void handle_task3(ActionEvent event) {
        selectTask = cb_task3.getSelectionModel().getSelectedItem();
        lb_task.setText(cb_task3.getSelectionModel().getSelectedItem().getTaskName());
            tb_project.setText(cb_task3.getSelectionModel().getSelectedItem().getProjectName());
    }

    @FXML
    private void handel_client_add(ActionEvent event) {
        /*adminModel.addNewClientToDB(
                tf_clint_name.getText().trim(),
                tf_clint_email.getText().trim(),
                tf_clint_$perhour.getText().trim());*/
    }

    @FXML
    private void handel_client_eddit(ActionEvent event) {
        /*adminModel.editClient(clientToEdit,
                tf_clint_name.getText().trim(),
                tf_clint_email.getText().trim(),
                tf_clint_$perhour.getText().trim());*/
    }

    @FXML
    private void handel_client_delete(ActionEvent event) {
        //adminModel.removeClientFromDB(clientToDelete)
                
    }

    @FXML
    private void handel_project_add(ActionEvent event) {
       /* adminModel.addNewProjectToDB(
                tf_pj_name.getText().trim(),
                cb_pj_clint.getSelectionModel().getSelectedItem(),
                tf_pj_nr.getText().trim(),
                tf_pj_$perhour.getText().trim());*/
                
    }

    @FXML
    private void handel_project_eddit(ActionEvent event) {
       /* adminModel.editProject(
                editedProject,
                tf_pj_name.getText().trim(),
                cb_pj_clint.getSelectionModel().getSelectedItem(),
                tf_pj_nr.getText().trim(),
                tf_pj_$perhour.getText().trim());*/
    }

    @FXML
    private void handel_project_delete(ActionEvent event) {
        /*adminModel.removeProjectFromDB(Tbv_pj.getSelectionModel().getSelectedItems());*/
    }

    @FXML
    private void handel_task_add(ActionEvent event) {
        /*adminModel.addNewTaskToDB(
                task_name.getText().trim(),
                cb_task_project.getSelectionModel().getSelectedItem(),
                task_$perhour.getText().trim());*/
    }

    @FXML
    private void handel_task_eddit(ActionEvent event) {
        /*adminModel.editTask(editedTask,
                task_name.getText().trim(),
                cb_task_project.getSelectionModel().getSelectedItem(),
                task_$perhour.getText().trim());*/
    }

    @FXML
    private void handel_task_delete(ActionEvent event) {
        /*adminModel.removeTaskFromDB(taskToDelete);*/
    }

    @FXML
    private void handel_user_add(ActionEvent event) {
        /*adminModel.addNewUserToDB(
                tf_user_name.getText().trim(),
                tf_user_email.getText().trim(),
                tf_user_password.getText().trim(),
                tf_user_$perhour.getText().trim(),// add convter to flote
                cb_user_admin.getSelectionModel().getSelectedItem());*/
    }

    @FXML
    private void handel_user_eddit(ActionEvent event) {
        /*adminModel.editUser(userToEdit,
                tf_user_name.getText().trim(),
                cb_user_admin.getSelectionModel().getSelectedItem(),
                tf_user_$perhour.getText().trim());*/
    }

    @FXML
    private void handel_user_delete(ActionEvent event) {
       /*adminModel.removeUserFromDB(userToDelete);*/
    }

    @FXML
    private void handle_filepath(ActionEvent event) {
    }

    @FXML
    private void handel_session_edit(ActionEvent event) {
    }

    @FXML
    private void handel_session_delete(ActionEvent event) {
    }

    @FXML
    private void handle_start_stop(ActionEvent event) {
        
        if(isStarted == false)
            {
            isStarted = true;
             bn_start_stop.setText("Stop");
            LocalDateTime LDTnow = LocalDateTime.now();
            
            startTime = adminModel.localDateTimeToString(LDTnow);
            }
                else{
             isStarted = false;
                bn_start_stop.setText("Start");
            
             int lu = 1;
            
             LocalDateTime LDTnow = LocalDateTime.now();
             String StopTime = adminModel.localDateTimeToString(LDTnow);
             adminModel.addNewSessionToDB(lu, selectTask.getTaskID(), startTime, StopTime);
            ;
        }
        
    }

    @FXML
    private void handel_addTaskminview(ActionEvent event) {
        addTask();
    }
}
