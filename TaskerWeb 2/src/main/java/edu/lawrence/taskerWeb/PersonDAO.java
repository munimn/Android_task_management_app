package edu.lawrence.taskerWeb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public Person findByUserName(String userName) {
	String sql = "SELECT * FROM people where username=?";
        RowMapper<Person> rowMapper = new PersonRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, userName);
    }
    
    public Person findById(int id) {
        String sql = "SELECT * FROM people where idpeople=?";
        RowMapper<Person> rowMapper = new PersonRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
