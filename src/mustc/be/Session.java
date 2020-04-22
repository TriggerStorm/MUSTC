/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.be;

/**
 *
 * @author Trigger, Filip, Cecillia and Alan
 */
public class Session {
    private int id;
    private User associatedUser;  // the person whom the task is assigned to.
    private Task associatedTask;  // the person whom the task is assigned to.
    private int startTime;
    private int finishTime;
//    private int sessionTime;  //  difference between start time and finish time ...maybe?
}
