/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

import java.util.List;

/**
 *
 * @author Trigger, Filip, Cecillia and Alan
 */
public class Task {
    private int id;
    private String name;
    private String description;
    private Project associatedProject;  // the person whom the task is assigned to.
//    private User associatedUser;  // the person to whom the task is assigned to.
//or  private List<User> associatedUsers;  // the people to whom the task is assigned to.

    private List<Session> sessions;  //time??
//    private int taskTime;  //  total time used on a task  ... maybe
}
