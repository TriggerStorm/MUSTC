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
public class User {
    private int id;  //  maybe
    private String name;
    private String email;
    private String password;
    private int salary;
    private boolean admin;  // or 0 = developer, 1 = admin, 2 = project owner.
 //   private List<Task> usersTasks;  //  may be faster to process if we have this.
}
