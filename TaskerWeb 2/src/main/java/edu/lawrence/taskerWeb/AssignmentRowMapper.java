/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.taskerWeb;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class AssignmentRowMapper implements RowMapper<Assignment> {
    @Override
    public Assignment mapRow(ResultSet row, int rowNum) throws SQLException {
        Assignment a = new Assignment();
        a.setidassignment(row.getInt("idassignments"));
        a.setJob(row.getInt("job"));
        a.setWorker(row.getInt("worker"));
        
        return a;
    }
}