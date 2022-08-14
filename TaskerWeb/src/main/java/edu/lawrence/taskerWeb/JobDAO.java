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
    public List<Job> findByIdjob(int id) {
	String sql = "SELECT idjob, description, date, location, customer,status FROM jobs WHERE idjob=?";
        RowMapper<Job> rowMapper = new JobRowMapper();
        return jdbcTemplate.query(sql, rowMapper, id);
    }

     public List<Job> availableJobs() {
        String sql = "select idjob, description, date, location, customer, status from jobs where status=0";
        RowMapper<Job> rowMapper = new JobRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

}
