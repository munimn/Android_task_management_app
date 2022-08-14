package edu.lawrence.taskerWeb;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class JobRowMapper implements RowMapper<Job> {
    @Override
    public Job mapRow(ResultSet row, int rowNum) throws SQLException {
        Job j = new Job();
        j.setIdjob(row.getInt("idjob"));
        j.setSubject(row.getString("description"));
        j.setDate(row.getDate("date"));
        j.setLocation(row.getString("location"));
        j.setQuiz(row.getInt("customer"));
        j.setStatus(row.getInt("status"));
        return j;
    }
}
