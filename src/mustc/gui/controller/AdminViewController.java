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
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import mustc.be.Client;
import mustc.be.Project;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;
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
    private TextField pj_search;
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
    private TextField stat_search;
    @FXML
    private Tab tab_sesion;
    @FXML
    private TableView<Session> tbv_session;
    @FXML
    private TableColumn<Session, String> col_sesion_taskname;
    @FXML
    private TableColumn<Session, String> col_sesion_date;
    @FXML
    private TableColumn<Session, String> col_sesion_start;
    @FXML
    private TableColumn<Session, String> col_sesion_stop;
    @FXML
    private TableColumn<Session, String> col_sesion_myhours;
    @FXML
    private TextField sesion_search;
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
    private TableColumn<Client, String> Col_clint_totalprice;
    @FXML
    private TextField clint_search;
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
    private JFXTextField task_$perhour;
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
    private TableColumn<User, String> col_user_hoursthisweek;
    @FXML
    private TableColumn<User, String> col_user_$perhour;
    @FXML
    private TableColumn<User, String> col_user_admin;
    @FXML
    private TableColumn<User, String> col_user_startdate;
    @FXML
    private TextField user_search;
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
    
    private AdminModel adminModel;
    int MaxWidth;
    boolean min;
    
    
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        adminModel = new AdminModel();
    }    

    public AdminViewController() {
        MaxWidth = 260;
        min = true;
    }
    
    public void SS(){
        
        
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
 
    
    

    private void handle_view(ActionEvent event) {
        SS();
    }

    @FXML
    private void toggel_size(ActionEvent event) {
        ToggelSize();
    }

    

    @FXML
    private void handle_tap_clint(Event event) {
        Col_clint_name.setCellValueFactory(new PropertyValueFactory<Client, String>("clientName"));
        Col_clint_email.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
        Col_clint_nrofpj.setCellValueFactory(new PropertyValueFactory<Client, String>("nrOfProjects"));
        Col_clint_$perhour.setCellValueFactory(new PropertyValueFactory<Client, String>("standardRate"));
        Col_clint_totalhours.setCellValueFactory(new PropertyValueFactory<Client, String>("totalHours"));
        Col_clint_totalprice.setCellValueFactory(new PropertyValueFactory<Client, String>("totalPrice"));
        Tbv_Clint.setItems(adminModel.getAllClient());
    }

    @FXML
    private void handle_tap_project(Event event) {
        
        Col_pj_name.setCellValueFactory(new PropertyValueFactory<Project, String>("projectName"));
        Col_pj_clint.setCellValueFactory(new PropertyValueFactory<Project, String>("clintName"));
        Col_pj_contact.setCellValueFactory(new PropertyValueFactory<Project, String>("phoneNr"));
        Col_pj_nroftask.setCellValueFactory(new PropertyValueFactory<Project, String>("nrOfTask"));
        Col_pj_totalhours.setCellValueFactory(new PropertyValueFactory<Project, String>("totalHours"));
        Col_pj_totalprice.setCellValueFactory(new PropertyValueFactory<Project, String>("totalPrice"));
        //.setCellValueFactory(new PropertyValueFactory<Project, String>("projectRate"));
                Tbv_pj.setItems(adminModel.getAllProject());
    }

    @FXML
    private void handle_tap_task(Event event) {
       
        Col_task_taskname.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        Col_task_project.setCellValueFactory(new PropertyValueFactory<Task, String>("project"));
        Col_task_devs.setCellValueFactory(new PropertyValueFactory<Task, String>("devs"));
        Col_task_$perhour.setCellValueFactory(new PropertyValueFactory<Task, String>("taskRate"));
        Col_task_totalhours.setCellValueFactory(new PropertyValueFactory<Task, String>("totalHours"));
        Col_task_totalprice.setCellValueFactory(new PropertyValueFactory<Task, String>("totalPrice"));
                 tbv_task.setItems(adminModel.getAllTask());
    }

    @FXML
    private void handle_tap_stats(Event event) {
    }

    @FXML
    private void handle_tap_sessions(Event event) {
        
        col_sesion_taskname.setCellValueFactory(new PropertyValueFactory<Session, String>("taskName"));
        col_sesion_date.setCellValueFactory(new PropertyValueFactory<Session, String>("date"));
        col_sesion_start.setCellValueFactory(new PropertyValueFactory<Session, String>("start"));
        col_sesion_stop.setCellValueFactory(new PropertyValueFactory<Session, String>("stop"));
        col_sesion_myhours.setCellValueFactory(new PropertyValueFactory<Session, String>("myHours"));
            tbv_session.setItems(adminModel.getAllSessions());
    }

    @FXML
    private void handle_tap_user(Event event) {
        
        col_user_name.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        col_user_hoursthisweek.setCellValueFactory(new PropertyValueFactory<User, String>("hoursThisWeek"));
        col_user_$perhour.setCellValueFactory(new PropertyValueFactory<User, String>("salary"));
        col_user_admin.setCellValueFactory(new PropertyValueFactory<User, String>("admin"));
        col_user_startdate.setCellValueFactory(new PropertyValueFactory<User, String>("startDate"));
            tbv_user.setItems(adminModel.getAllUser());
    }
}
