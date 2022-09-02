/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.lawrence.taskerWeb;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PersonNewRowMapper implements RowMapper<PersonNew> {
    @Override
    public PersonNew mapRow(ResultSet row, int rowNum) throws SQLException {
        PersonNew p = new PersonNew();
        p.setId(row.getInt("idpeople"));
        p.setUserName(row.getString("username"));
        p.setPassword(row.getString("password"));
        p.setName(row.getString("name"));
        p.setPhone(row.getString("phone"));
        p.setEmail(row.getString("email"));
        p.setJobs(row.getInt("jobs"));
        p.setRating(row.getInt("rating"));
        return p;
    }
}
