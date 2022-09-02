/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.taskerWeb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PersonNewDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PersonNew findById(int id) {
        String sql = "SELECT * FROM peoplenew where idpeople=?";
        RowMapper<PersonNew> rowMapper = new PersonNewRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    
}

