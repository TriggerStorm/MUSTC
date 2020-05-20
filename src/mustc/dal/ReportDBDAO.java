/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mustc.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mustc.be.Project;
import mustc.be.ReportOutput;

/**
 *
 * @author Trigger and Alan
 */
public class ReportDBDAO {
    private DBConnection dbc;

    
    public ReportDBDAO() {
                dbc = new DBConnection();

    }
    
    
    
     
}
