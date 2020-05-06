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
import java.util.ArrayList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFrame;
import mustc.be.Project;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;
import mustc.gui.model.UserViewModel;



/**
 *
 * @author Trigger
 */
public class UserViewController extends JFrame implements Initializable {
    
    private Label label;
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
    private TableColumn<Project, String> Col_pj_myhours;
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
    private TableColumn<Task, String> Col_task_myhours;
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
    private Label lb_stat_taskhours;
    @FXML
    private Label lb_stat_totalhours;
    @FXML
    private Tab tab_sesion;
    @FXML
    private TableView<Session> TBV_Session;
    @FXML
    private TableColumn<Session, String> col_sesion_taskname;
    
    @FXML
    private TableColumn<Session, String> col_sesion_start;
    @FXML
    private TableColumn<Session, String> col_sesion_stop;
    
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
   
    private UserViewModel userModel;
    
    int MaxWidth;
    boolean min;
   
    boolean isStarted;
    Task selectTask;
    String startTime;
    
    @FXML
    private Text lb_task;
    @FXML
    private Text tb_project;
    private TableColumn<Session, String> col_sesion_tn1;
    
    
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = new UserViewModel();
        
        setProject();
        setTask();
        setSession();
                
        cb_task1.setItems(userModel.get1());// dont work
        cb_task1.setPromptText(cb_task1.getItems().get(0).getTaskName());
        bn_task1.setText(cb_task1.getItems().get(0).getProjectName());
        //resent task 2
        cb_task2.setItems(userModel.get2());
        cb_task2.setPromptText(cb_task2.getItems().get(0).getTaskName());
        bn_task2.setText(cb_task2.getItems().get(0).getProjectName());
        //recent task 3
        cb_task3.setItems(userModel.get3());
        cb_task3.setPromptText(cb_task3.getItems().get(0).getTaskName());
        bn_task3.setText(cb_task3.getItems().get(0).getProjectName());
        
        cb_project.setItems(userModel.getAllProjectList());
        /*Image image1 = new Image(userModel.taskImg1());
        Image image2 = new Image(userModel.taskImg2());
        Image image3 = new Image(userModel.taskImg3());
        img_task1.setImage(image1);
        img_task2.setImage(image2);
        img_task3.setImage(image3);*/
        
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
    
    
    public void addTask(){
        userModel.addNewTaskToDB(tf_newtask.getText(),"as",cb_project.getSelectionModel().getSelectedItem().getProjectID());
    }
    
    @FXML
    private void handle_view(ActionEvent event) {
        sizeExpantion();
    }

    @FXML
    private void toggel_size(ActionEvent event) {
        toggelSize();
    }


    
    public void setProject(){
    Col_pj_name.setCellValueFactory(new PropertyValueFactory<Project, String>("projectName"));
        Col_pj_clint.setCellValueFactory(new PropertyValueFactory<Project, String>("clientName"));
        Col_pj_contact.setCellValueFactory(new PropertyValueFactory<Project, String>("phoneNr"));
        Col_pj_nroftask.setCellValueFactory(new PropertyValueFactory<Project, String>("noOfTasks"));
        Col_pj_myhours.setCellValueFactory(new PropertyValueFactory<Project, String>("myProjectHours"));
       
        
                Tbv_pj.setItems(userModel.getAllProject());
    }

    public void setTask(){
        Col_task_taskname.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        Col_task_project.setCellValueFactory(new PropertyValueFactory<Task, String>("projectName"));
        Col_task_devs.setCellValueFactory(new PropertyValueFactory<Task, String>("developers"));
        Col_task_myhours.setCellValueFactory(new PropertyValueFactory<Task, String>("myTaskHours"));
       
                 tbv_task.setItems(userModel.getAllTask());
    }
    
    public void setSession(){
        col_sesion_taskname.setCellValueFactory(new PropertyValueFactory<Session, String>("associatedTaskName"));
        col_sesion_start.setCellValueFactory(new PropertyValueFactory<Session, String>("startTime"));
        col_sesion_stop.setCellValueFactory(new PropertyValueFactory<Session, String>("finishTime"));
        
        TBV_Session.setItems(userModel.getAllSession());
    }
    
    @FXML
    private void tap_handel_project(Event event) {
        /*Col_pj_name.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
        Col_pj_clint.setCellValueFactory(new PropertyValueFactory<Project, String>("clint"));
        Col_pj_contact.setCellValueFactory(new PropertyValueFactory<Project, String>("contact"));
        Col_pj_nroftask.setCellValueFactory(new PropertyValueFactory<Project, String>("nroftask"));
        Col_pj_myhours.setCellValueFactory(new PropertyValueFactory<Project, String>("myhours"));
        Tbv_pj.setItems(userModel.getAllProject());*/
    }

    @FXML
    private void tap_handel_task(Event event) {
       /* Col_task_taskname.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));
        Col_task_project.setCellValueFactory(new PropertyValueFactory<Task, String>("Project"));
        Col_task_devs.setCellValueFactory(new PropertyValueFactory<Task, String>("devs"));
        Col_task_myhours.setCellValueFactory(new PropertyValueFactory<Task, String>("myHours"));
        tbv_task.setItems(userModel.getAllTask());*/
    }

    @FXML
    private void tap_handel_stats(Event event) {
        
    }

    @FXML
    private void tap_handel_sesion(Event event) {
       /* col_sesion_taskname.setCellValueFactory(new PropertyValueFactory<Session, String>("taskName"));
        col_sesion_date.setCellValueFactory(new PropertyValueFactory<Session, String>("date"));
        col_sesion_start.setCellValueFactory(new PropertyValueFactory<Session, String>("start"));
        col_sesion_stop.setCellValueFactory(new PropertyValueFactory<Session, String>("stop"));
        col_sesion_myhours.setCellValueFactory(new PropertyValueFactory<Session, String>("myHours"));
        TBV_Session.setItems(userModel.getAllSession());*/
        
    }

    @FXML
    private void handel_task1(ActionEvent event) {
        
        selectTask = cb_task1.getSelectionModel().getSelectedItem();
        lb_task.setText(cb_task1.getSelectionModel().getSelectedItem().getTaskName());
            tb_project.setText(cb_task1.getSelectionModel().getSelectedItem().getProjectName());
                
    }

    @FXML
    private void handle_task2(ActionEvent event) {
        selectTask = cb_task1.getSelectionModel().getSelectedItem();
        lb_task.setText(cb_task1.getSelectionModel().getSelectedItem().getTaskName());
            tb_project.setText(cb_task1.getSelectionModel().getSelectedItem().getProjectName());
    }

    @FXML
    private void handle_task3(ActionEvent event) {
        selectTask = cb_task3.getSelectionModel().getSelectedItem();
        lb_task.setText(cb_task3.getSelectionModel().getSelectedItem().getTaskName());
            tb_project.setText(cb_task3.getSelectionModel().getSelectedItem().getProjectName());
    }

    @FXML
    private void handel_addTaskminview(ActionEvent event) {
        addTask();
    }

    @FXML
    private void handel_start_stop(ActionEvent event) {
        if(isStarted == false)
            {
            isStarted = true;
             bn_start_stop.setText("Stop");
            LocalDateTime LDTnow = LocalDateTime.now();
            
            startTime = userModel.localDateTimeToString(LDTnow);
            }
                else{
             isStarted = false;
                bn_start_stop.setText("Start");
            
             int lu = 1;
            
             LocalDateTime LDTnow = LocalDateTime.now();
             String StopTime = userModel.localDateTimeToString(LDTnow);
             userModel.addNewSessionToDB(lu, selectTask.getTaskID(), startTime, StopTime);
            ;
    }

    }
    

}
    