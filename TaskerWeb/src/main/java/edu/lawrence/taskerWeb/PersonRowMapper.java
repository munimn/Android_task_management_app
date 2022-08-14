package edu.lawrence.taskerWeb;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet row, int rowNum) throws SQLException {
        Person p = new Person();
        p.setId(row.getInt("idpeople"));
        p.setUserName(row.getString("username"));
        p.setPassword(row.getString("password"));
        p.setName(row.getString("name"));
        p.setPhone(row.getString("phone"));
        p.setEmail(row.getString("email"));
        return p;
    }
}
