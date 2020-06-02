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
import com.jfoenix.controls.JFXTreeView;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JFrame;
import mustc.be.Client;
import mustc.be.LoggedInUser;
import mustc.be.Project;
import mustc.be.Report;
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
    private Button tb_toggle;
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
    private JFXComboBox<Project> cb_task_project;
    @FXML
    private JFXButton bn_task_add;
    @FXML
    private JFXButton bn_task_eddit;
    @FXML
    private JFXButton bn_task_delete;
    @FXML
    private Tab tab_stat;
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
    private TableView<Session> TBV_Session;
    @FXML
    private TableColumn<Session, String> col_sesion_taskname;
    
    @FXML
    private TableColumn<Session, String> col_sesion_start;
    @FXML
    private TableColumn<Session, String> col_sesion_stop;
    
    private ScrollPane Sp_last3;
    
    @FXML
    private JFXButton bn_task1;
   
    @FXML
    private ImageView img_task1;
    @FXML
    private JFXButton bn_task2;
   
    @FXML
    private ImageView img_task2;
    @FXML
    private JFXButton bn_task3;
    
    @FXML
    private ImageView img_task3;
   
    private UserViewModel userModel;
    
    int MaxWidth;
    boolean min;
   
    boolean isStarted;
    Task selectTask;
    String startTime;
    String currentTab = "project";
    private LoggedInUser liu;
    
    Session SessionToedit;
    Task taskToedit;
    Task runningTask;
    boolean timeState = true;
    
    static int msec = 0;
    static int sec = 0;
    static int mins = 0;
    static int hours = 0;
    
    boolean bb = true;
    boolean bbm = true;
    boolean onTop = false;
    
    @FXML
    private Text lb_task;
    @FXML
    private Text tb_project;
    private TableColumn<Session, String> col_sesion_tn1;
    private JFXTextField tf_session_name;
    private JFXTextField tf_session_dev;
    @FXML
    private JFXTextField tf_session_start;
    @FXML
    private JFXTextField tf_session_stop;
    @FXML
    private JFXButton bn_session_edit;
    @FXML
    private JFXButton bn_session_delete;
    @FXML
    private JFXButton tb_task_billable;
    @FXML
    private JFXComboBox<Client> cb_stat_clint;
    @FXML
    private JFXButton bn_report;
    @FXML
    private TableView<Report> Tbv_Report;
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
    private TableColumn<Report, String> Report_duration;
    @FXML
    private JFXButton tb_smallview_billable;
    @FXML
    private JFXButton bn_searchClear;
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
    @FXML
    private JFXTreeView<String> tv_project_task;
    @FXML
    private JFXTextField tap_search;
    @FXML
    private Label lb_session_name;
    @FXML
    private Label lb_session_dev;
    @FXML
    private Label lb_progress;
    @FXML
    private JFXButton bn_t_confirm;
    @FXML
    private JFXButton bn_t_cancel;
    @FXML
    private JFXButton bn_s_confirm;
    @FXML
    private JFXButton bn_s_cancel;
    
    
    
    
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userModel = new UserViewModel();
        
        setProject();
        setTask();
        setSession();
                
        set3LatesTask();
        
        cb_project.setItems(userModel.getAllProjectsIDsAndNames());
        
        /*Image image1 = new Image(userModel.taskImg1());
        Image image2 = new Image(userModel.taskImg2());
        Image image3 = new Image(userModel.taskImg3());
        img_task1.setImage(image1);
        img_task2.setImage(image2);
        img_task3.setImage(image3);*/
        
        cb_task_project.setItems(userModel.getAllProjectsIDsAndNames());
        
        cb_stat_clint.setItems(userModel.getAllClientNameAndId());
        cb_stat_project.setItems(userModel.getAllProjectsIDsAndNamesForReport());

        lb_loginuser.setText(liu.getName());
        tv_project_task.setVisible(false);
        lb_progress.setVisible(false);
        
         bn_t_confirm.setVisible(false);
         bn_t_cancel.setVisible(false);
         bn_s_confirm.setVisible(false);
         bn_s_cancel.setVisible(false);
    }    

    public UserViewController() {
        MaxWidth = 260;
        min = true;
        isStarted = false;
        liu = LoggedInUser.getInstance();
    }
    public void set3LatesTask(){
    //recent task 1
        lb_t1task.setText(userModel.get1().get(0).getTaskName());
        lb_t1project.setText(userModel.get1().get(0).getProjectName());
        //resent task 2
        lb_t2task.setText(userModel.get2().get(0).getTaskName());
        lb_t2project.setText(userModel.get2().get(0).getProjectName());
        //recent task 3
        lb_t3task.setText(userModel.get3().get(0).getTaskName());
        lb_t3project.setText(userModel.get3().get(0).getProjectName());
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
                Stage stage = (Stage) fp_last3task.getScene().getWindow();
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
            Stage stage = (Stage) fp_last3task.getScene().getWindow();
            stage.setMaxHeight(248);
            stage.setMaxWidth(255);
            stage.setMinHeight(248);
            stage.setMinWidth(255);
        }
    }
    
    
    public void addTask(){
        userModel.addNewTaskToDB(tf_newtask.getText(),cb_project.getSelectionModel().getSelectedItem().getProjectID(), bbm);
        tb_project.setText(cb_project.getSelectionModel().getSelectedItem().toString());
        lb_task.setText(tf_newtask.getText().toString());
    }
    
    @FXML
    private void handle_view(ActionEvent event) {
        sizeExpantion();
    }

    @FXML
    private void toggel_size(ActionEvent event) {
        ToggelSize();
    }

    public void setTreeView(){
        
       
       String clientInSearch = search.getText();
       
       
        TreeItem<String> root = new TreeItem<>(clientInSearch);
        root.expandedProperty();
        int x =  userModel.oListProject().size();
        int y =  userModel.oListTask().size();
        String cid = clientInSearch.toLowerCase();
        for (int i = 0; i < x ; i++) {
                
                String pname = userModel.oListProject().get(i).getClientName().toLowerCase();
                if(cid.equals(pname)){
                
                TreeItem<String> item = new TreeItem<String>();
                item.setValue(new String(userModel.oListProject().get(i).toString()));
                root.getChildren().add(item);
                
                int id = userModel.oListProject().get(i).getProjectID();
                 
                for (int c = 0; c < y ; c++) {
                    int tid = userModel.oListTask().get(c).getAssociatedProjectID();
                if(id == tid ){
                TreeItem<String> it = new TreeItem<String>();
                it.setValue(new String(userModel.oListTask().get(c).toString()));
                item.getChildren().add(it);
                };
            }
                }
            }
     
        tv_project_task.setRoot(root);
    }
    
    public void setProject(){
    Col_pj_name.setCellValueFactory(new PropertyValueFactory<Project, String>("projectName"));
        Col_pj_clint.setCellValueFactory(new PropertyValueFactory<Project, String>("clientName"));
        Col_pj_contact.setCellValueFactory(new PropertyValueFactory<Project, String>("phoneNr"));
        Col_pj_nroftask.setCellValueFactory(new PropertyValueFactory<Project, String>("noOfTasks"));
        Col_pj_myhours.setCellValueFactory(new PropertyValueFactory<Project, String>("usersProjectMinutes"));
       
        
                Tbv_pj.setItems(userModel.getAllProject());
    }

    public void setTask(){
        Col_task_taskname.setCellValueFactory(new PropertyValueFactory<Task, String>("taskName"));
        Col_task_project.setCellValueFactory(new PropertyValueFactory<Task, String>("projectName"));
        Col_task_devs.setCellValueFactory(new PropertyValueFactory<Task, String>("developers"));
        Col_task_myhours.setCellValueFactory(new PropertyValueFactory<Task, String>("usersTaskMinutes"));
       
                 tbv_task.setItems(userModel.getAllTask());
    }
    
    public void setSession(){
        col_sesion_taskname.setCellValueFactory(new PropertyValueFactory<Session, String>("associatedTaskName"));
        col_sesion_start.setCellValueFactory(new PropertyValueFactory<Session, String>("startTime"));
        col_sesion_stop.setCellValueFactory(new PropertyValueFactory<Session, String>("finishTime"));
        
        TBV_Session.setItems(userModel.getAllSession());
    }
    
    public void setReport(){
        
        Report_client.setCellValueFactory(new PropertyValueFactory<Report, String>("clientName"));
        Report_project.setCellValueFactory(new PropertyValueFactory<Report, String>("projectName"));
        Report_task.setCellValueFactory(new PropertyValueFactory<Report, String>("taskName"));
        Report_user.setCellValueFactory(new PropertyValueFactory<Report, String>("loggedInUser"));
        Report_startTime.setCellValueFactory(new PropertyValueFactory<Report, String>("startTime"));
        Report_finishTime.setCellValueFactory(new PropertyValueFactory<Report, String>("finishTime"));
        Report_duration.setCellValueFactory(new PropertyValueFactory<Report, String>("minutes"));
        
        
        Tbv_Report.setItems(userModel.oReport());
        System.out.println(""+userModel.oReport().size());
    }
    
    // Taps
    @FXML
    private void tap_handel_project(Event event) {
      tap_search.clear();
        if(currentTab != "project" ){
            Tbv_pj.setItems(userModel.oListProject());
        }
        currentTab ="project";    
      
       
    }

    @FXML
    private void tap_handel_task(Event event) {
       currentTab ="task";
       tap_search.clear();
       tbv_task.setItems(userModel.oListTask());
    }

    @FXML
    private void tap_handel_stats(Event event) {
        currentTab ="stats";
        tap_search.clear();
    }

    @FXML
    private void tap_handel_sesion(Event event) {
        currentTab ="sessions";
        tap_search.clear();
        TBV_Session.setItems(userModel.oListSession());
        
    }

    @FXML
    private void handel_task1(ActionEvent event) { //*
        selectTask = userModel.get1().get(0);
            lb_task.setText(userModel.get1().get(0).getTaskName());
            tb_project.setText(userModel.get1().get(0).getProjectName());
                
    }

    @FXML
    private void handle_task2(ActionEvent event) {//*
        selectTask = userModel.get2().get(0);
            lb_task.setText(userModel.get2().get(0).getTaskName());
            tb_project.setText(userModel.get2().get(0).getProjectName());
    }

    @FXML
    private void handle_task3(ActionEvent event) {//*
       selectTask = userModel.get3().get(0);
            lb_task.setText(userModel.get3().get(0).getTaskName());
            tb_project.setText(userModel.get3().get(0).getProjectName());
    }

    @FXML
    private void handel_addTaskminview(ActionEvent event) {
         Thread t = new Thread()
        {
            public void run()
            {
        addTask();
        
        
        tbv_task.setItems(userModel.oListTask());
        
        sessionStartNStop();
        
       String T = tf_newtask.getText();
       
       int y =  userModel.oListTask().size();
       
        String tpj = cb_project.getSelectionModel().getSelectedItem().getProjectName();
       
       for (int c = 0; c < y ; c++) {
                    String tn = userModel.oListTask().get(c).getTaskName();
                    //String tn = "TreeItem [ value: "+adminModel.oListTask().get(c).getTaskName()+" ]";
                    String tp = userModel.oListTask().get(c).getProjectName();
                    
          if(       T.equals(tn)){
              if(tpj.equals(tp)){
            selectTask = userModel.oListTask().get(c);
              }}}
                 }
        
        };
                 t.start();
        
    }
        
    

    @FXML
    private void handel_start_stop(ActionEvent event) {
        sessionStartNStop();
    }

   

    @FXML
    private void handel_pick_task(MouseEvent event) { //*
       try{
        taskToedit = tbv_task.getSelectionModel().getSelectedItem();
        task_name.setText(taskToedit.getTaskName());
        cb_task_project.setPromptText(taskToedit.getProjectName());
       
        selectTask = tbv_task.getSelectionModel().getSelectedItem();
        lb_task.setText(tbv_task.getSelectionModel().getSelectedItem().getTaskName());
        tb_project.setText(tbv_task.getSelectionModel().getSelectedItem().getProjectName());
       }
       catch(Exception e){
            System.out.println("Field has no value"+e);
        }
    }
    @FXML
    private void handel_pick_session(MouseEvent event) {
        try{
        SessionToedit = TBV_Session.getSelectionModel().getSelectedItem();
        lb_session_name.setText(SessionToedit.getAssociatedTaskName());
        tf_session_start.setText(SessionToedit.getStartTime());
        tf_session_stop.setText(SessionToedit.getFinishTime());
        lb_session_dev.setText(liu.getName());
        }
        catch(Exception e){
            System.out.println("Field has no value"+e);
        }
    }
    
    @FXML
    private void handel_pick_treeview(MouseEvent event) {
        try{
        String T = tv_project_task.getSelectionModel().getSelectedItem().getValue();
       int y =  userModel.oListTask().size();
       String tpj = tv_project_task.getSelectionModel().getSelectedItem().getParent().getValue();
       for (int c = 0; c < y ; c++) {
                    String tn = userModel.oListTask().get(c).getTaskName();
                    //String tn = "TreeItem [ value: "+adminModel.oListTask().get(c).getTaskName()+" ]";
                    String tp = userModel.oListTask().get(c).getProjectName();
                    
          if(       T.equals(tn)){
              if(tpj.equals(tp)){
            selectTask = userModel.oListTask().get(c);
            tb_project.setText(selectTask.getProjectName());
            lb_task.setText(selectTask.getTaskName());
          }
          }
    }}catch(Exception e){
            System.out.println("Field is not the Task"+e);
        }
    
        
    }

    public void taskTapAddTask(){
    userModel.addNewTaskToDB(
                task_name.getText().trim(),              
                cb_task_project.getSelectionModel().getSelectedItem().getProjectID(),
                bb);
    
        
    }
    
  

    @FXML
    private void handel_edit_task(ActionEvent event) {//*
        userModel.editTask(taskToedit,
                task_name.getText().trim(),
                taskToedit.getAssociatedProjectID(),
                bb);
                tbv_task.refresh();
                task_name.clear();
    }

    private void handel_delete_task(ActionEvent event) {//*
         bn_t_confirm.setVisible(true);
        bn_t_cancel.setVisible(true);
    }
    
     @FXML
    private void handel_session_edit(ActionEvent event) {//*
         userModel.editSession(SessionToedit,
                SessionToedit.getAssociatedUserID(),
                SessionToedit.getAssociatedTaskID(),
                tf_session_start.getText(),
                tf_session_stop.getText());
                
                TBV_Session.refresh();
                lb_session_name.setText("Name");
                tf_session_start.clear();
                tf_session_stop.clear();
                lb_session_dev.setText("Developers");
    }

    @FXML
    private void handel_session_delete(ActionEvent event) {//*
        bn_s_confirm.setVisible(true);
        bn_s_cancel.setVisible(true);
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
    public void sessionStartNStop(){
     Thread t = new Thread()
        {
            public void run()
            {
        if(isStarted == false)
            {
            isStarted = true;
             Platform.runLater(()->{
                 bn_start_stop.setText("Stop");
             });
            LocalDateTime LDTnow = LocalDateTime.now();
            
            runningTask = selectTask;
            startTime = userModel.localDateTimeToString(LDTnow);
            timeState = true;
            clock();
            }
                else{
             isStarted = false;
             Platform.runLater(()->{   
             bn_start_stop.setText("Start");
             });
             int lu = liu.getId();
             System.out.println(""+ lu);
             LocalDateTime LDTnow = LocalDateTime.now();
             String StopTime = userModel.localDateTimeToString(LDTnow);
             userModel.addNewSessionToDB(lu, runningTask.getTaskID(),runningTask.getTaskName(), startTime, StopTime);
             timeState = false;
             
             
             userModel.getUsersThreeRecentTaskss(userModel.getUser(liu.getId()));
             
             Platform.runLater(()->{
             set3LatesTask();
             resetTime();
             TBV_Session.refresh();
             
             });
        }
    }
        }; t.start();
        
       }
    
     public void resetTime(){
    lb_tasktime.setText("00:00:00");
    msec = 0;
    sec = 0;
    mins = 0;
    hours = 0;
    
    }

   

    @FXML
    private void Handel_project(ActionEvent event) {
        try{
        userModel.getAllTaskIDsAndNamesOfAProject(cb_stat_project.getSelectionModel().getSelectedItem().getProjectID());
        ObservableList<Task> cp = userModel.projectTask();
        cb_stat_task.setItems(cp);}
       catch(Exception e){
           System.out.println(""+e);
       }
    }


    @FXML
    private void handel_selectClient(ActionEvent event) {
        ObservableList<Project> cp = userModel.getAllProjectIDsAndNamesOfAClient(cb_stat_clint.getSelectionModel().getSelectedItem().getClientId());
        cb_stat_project.setItems(cp);
    }

    @FXML
    private void handel_report(ActionEvent event) { //bn report gen
        lb_progress.setVisible(true);
        lb_progress.setText("Generating Report");
        Thread t = new Thread()
        {
            public void run()
            {
                
        LocalDate from;
        LocalDate to;
        int client =0;
        int project = 0;
        int task = 0;
        int user = liu.getId();
        //client
        if(cb_stat_clint.getSelectionModel().getSelectedItem() == null){
            client = -1;
        }else{
            client = cb_stat_clint.getSelectionModel().getSelectedItem().getClientId();
        }
        //project
        if(cb_stat_project.getSelectionModel().getSelectedItem() == null){
            project = -1;
        }else if(client != -1 & cb_stat_project.getSelectionModel().getSelectedItem()== null){
           project = -2;
        }else{
           project = cb_stat_project.getSelectionModel().getSelectedItem().getProjectID();
        }
        //Task
        if(cb_stat_task.getSelectionModel().getSelectedItem()== null){
            task = -1;
        }else if(project != -1 & cb_stat_task.getSelectionModel().getSelectedItem() == null){
            task = -2;
        }else{
            task = cb_stat_task.getSelectionModel().getSelectedItem().getTaskID();
        }
           
        
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
        
        userModel.generateReport(
                client,
                project,
                task,
                user,
                from,
                to
                );
        Platform.runLater(()->{
                 setReport();
                 lb_progress.setVisible(false);
             });
        
        
    }}; t.start();
    }
    

 
    @FXML
    private void handel_searchClear(ActionEvent event) {
        search.clear();
        tv_project_task.setVisible(false);
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
    private void Search(KeyEvent event) {
         if(currentTab == "task"){ 
       ObservableList<Task> taskInSearch;
       taskInSearch = userModel.searchTask(userModel.oListTask(), tap_search.getText());
       tbv_task.setItems(taskInSearch);} 
       else if(currentTab =="project"){
           ObservableList<Project> projectInSearch;
            projectInSearch = userModel.searchProject(userModel.oListProject(), tap_search.getText());
            Tbv_pj.setItems(projectInSearch);
       }
       else if (currentTab =="sessions"){
       ObservableList<Session> sessionInSearch;
            sessionInSearch = userModel.searchSession(userModel.oListSession(), tap_search.getText());
            TBV_Session.setItems(sessionInSearch);
       }
       else if (currentTab =="stats"){
       
       }
       
    }
    
     @FXML
    private void handel_billable(ActionEvent event) {
        
        if(bb == true){
            bb = false;  
            tb_task_billable.setTextFill(Color.rgb(210,39,30));
            
            
        }
        else{
        bb = true;
        tb_task_billable.setTextFill(Color.rgb(21,117,84));
        }
        
    }

    @FXML
    private void handle_small_billable(ActionEvent event) {
        
        if(bbm == true){
            bbm = false;   
            tb_smallview_billable.setTextFill(Color.rgb(210,39,30));
        }
        else{
        bbm = true;
        tb_smallview_billable.setTextFill(Color.rgb(21,117,84));
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

    private void handel_add_task(ActionEvent event) {
        Thread t = new Thread()
        {
            public void run()
            {
        taskTapAddTask();
        
        
        tbv_task.setItems(userModel.oListTask());
        
        sessionStartNStop();
        
       String T = task_name.getText();
       
       int y =  userModel.oListTask().size();
       
        String tpj = cb_task_project.getSelectionModel().getSelectedItem().getProjectName();
       
       for (int c = 0; c < y ; c++) {
                    String tn = userModel.oListTask().get(c).getTaskName();
                    //String tn = "TreeItem [ value: "+adminModel.oListTask().get(c).getTaskName()+" ]";
                    String tp = userModel.oListTask().get(c).getProjectName();
                    
          if(       T.equals(tn)){
              if(tpj.equals(tp)){
            selectTask = userModel.oListTask().get(c);
            tb_project.setText(selectTask.getProjectName());
            lb_task.setText(selectTask.getTaskName());
              }}}
                 }
        
        };
                 t.start();
        /* adminModel.addNewTaskToDB(
                task_name.getText().trim(),              
                cb_task_project.getSelectionModel().getSelectedItem().getProjectID(),
                bb);
        tbv_task.refresh();*/
        
    }

    @FXML
    private void confirm_task_delete(ActionEvent event) {
        userModel.removeTaskFromDB(taskToedit);
        userModel.oListTask().remove(taskToedit);
        tbv_task.refresh();
        task_name.clear();
        bn_t_confirm.setVisible(false);
        bn_t_cancel.setVisible(false);
    }

    @FXML
    private void cancel_task_delete(ActionEvent event) {
        bn_t_confirm.setVisible(false);
        bn_t_cancel.setVisible(false);
    }

    @FXML
    private void confirm_session_delete(ActionEvent event) {
        userModel.removeSessionFromDB(SessionToedit);
        userModel.oListSession().remove(SessionToedit);
        TBV_Session.refresh();
         lb_session_name.setText("Name");
         tf_session_start.clear();
         tf_session_stop.clear();
         lb_session_dev.setText("Developers");
        bn_s_confirm.setVisible(false);
        bn_s_cancel.setVisible(false);
    }

    @FXML
    private void cancel_session_delete(ActionEvent event) {
        bn_s_confirm.setVisible(false);
        bn_s_cancel.setVisible(false);
    }

    @FXML
    private void handel_task_add(ActionEvent event) {
    }

    @FXML
    private void handel_task_delete(ActionEvent event) {
    }

    

}
    