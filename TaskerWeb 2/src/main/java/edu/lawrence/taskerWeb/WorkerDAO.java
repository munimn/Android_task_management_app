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
public class WorkerDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
   
  
    
        public int save(Worker work) {
        String insertSQL = "INSERT INTO work (job,worker,hours,rating) values (?, ?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, work.getJob());
                    ps.setInt(2, work.getWorker());
                    ps.setInt(3, work.gethours());
                    ps.setInt(4, work.getRating());
                    return ps;
                }, keyHolder);

        /** You have one small oversight here. When feedback gets posted for a job you should
        * also change the status of that job from 1 to 2. I added some code here to do that. **/
        String updateSQL = "update jobs set status=2 where idjob=?";
        jdbcTemplate.update(updateSQL,work.getJob());
        
        return keyHolder.getKey().intValue();
    }

    
}
