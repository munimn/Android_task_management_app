/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.taskerWeb;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AssignmentDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
   
  
    
        public int save(Assignment a) {
        String insertSQL = "INSERT INTO assignments (job,worker) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, a.getJob());
                    ps.setInt(2, a.getWorker());
                    
                    return ps;
                }, keyHolder);

        return keyHolder.getKey().intValue();
        
        
    }
       public Assignment findByjob(int job) {
	String sql = "SELECT idassignments, job,worker FROM assignments WHERE job=?";
        RowMapper<Assignment> rowMapper = new AssignmentRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, job);
    } 
}