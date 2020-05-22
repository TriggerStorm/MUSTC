/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.bll;

import javafx.collections.ObservableList;
import mustc.be.Client;
import mustc.be.Project;
import mustc.be.Session;
import mustc.be.Task;
import mustc.be.User;

/**
 *
 * @author Trigger
 */
public class SearchUtilities {
    
    // method ida form prior project movecolltion ida for tuder "nedas" https://github.com/Metallist1/MovieAssigment/blob/master/src/mymoviesassigment/bll/util/searchMovie.java
    public ObservableList<Task> searchTask(ObservableList<Task> allTask, String text){
        return allTask.filtered((f) -> f.getTaskName().toLowerCase().startsWith(text.toLowerCase()));   
    }
    
    public ObservableList<Task> searchTaskpj(ObservableList<Task> allTask, String text){
        return allTask.filtered((f) -> f.getProjectName().toLowerCase().startsWith(text.toLowerCase()));   
    }
    
    public ObservableList<Client> searchClient(ObservableList<Client> allClient, String text){
        return allClient.filtered((f) -> f.getClientName().toLowerCase().startsWith(text.toLowerCase()));
    }
    
    public ObservableList<Project> searchProject(ObservableList<Project> allProject, String text){
        return allProject.filtered((f) -> f.getProjectName().toLowerCase().startsWith(text.toLowerCase()));   
    }
    
     public ObservableList<Session> searchSession(ObservableList<Session> allSession, String text){
        return allSession.filtered((f) -> f.getAssociatedTaskName().toLowerCase().startsWith(text.toLowerCase()));   
    }
     
     public ObservableList<User> searchUser(ObservableList<User> allUser, String text){
        return allUser.filtered((f) -> f.getUserName().toLowerCase().startsWith(text.toLowerCase()));   
    }
}
