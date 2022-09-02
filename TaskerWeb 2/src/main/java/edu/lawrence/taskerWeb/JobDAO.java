package edu.lawrence.taskerWeb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JobDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Job> findByCustomer(int customer) {
	String sql = "SELECT idjob, description, date, location, customer,status FROM jobs WHERE customer=?";
        RowMapper<Job> rowMapper = new JobRowMapper();
        return jdbcTemplate.query(sql, rowMapper, customer);
    }
    public void save(Job job) {
        String insertSQL = "INSERT INTO jobs (description, date, location, customer, status) values ( ?, ?,? ,? ,?)";
        jdbcTemplate.update(insertSQL, job.getDescription(),job.getDate(),job.getLocation(), job.getCustomer(), job.getStatus());   
    }
    public void Update(int idjob) {
        String insertSQL = "UPDATE jobs SET status=1 WHERE idjob=? ";
        jdbcTemplate.update(insertSQL, idjob);   
    }
    
    
    
    
    public List<Job> findByIdjob(int id) {
	String sql = "SELECT idjob, description, date, location, customer,status FROM jobs WHERE idjob=?";
        RowMapper<Job> rowMapper = new JobRowMapper();
        return jdbcTemplate.query(sql, rowMapper, id);
    }

     public List<Job> availableJobs(int customer) {
        String sql = "SELECT idjob, description, date, location, status,customer from jobs WHERE status=0 AND customer=?";
        RowMapper<Job> rowMapper = new JobRowMapper();
        return jdbcTemplate.query(sql, rowMapper,customer);
    }
      public List<Job> completedJobs(int customer) {
        String sql = "SELECT idjob, description, date, location, status,customer from jobs WHERE status=1 AND customer=?";
        RowMapper<Job> rowMapper = new JobRowMapper();
        return jdbcTemplate.query(sql, rowMapper,customer);
    }


}
