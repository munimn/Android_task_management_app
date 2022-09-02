
package edu.lawrence.taskerWeb;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class WorkerRowMapper implements RowMapper<Worker> {
    @Override
    public Worker mapRow(ResultSet row, int rowNum) throws SQLException {
        Worker w = new Worker();
        w.setIdwork(row.getInt("idwork"));
        w.setJob(row.getInt("job"));
        w.setWorker(row.getInt("worker"));
        w.sethours(row.getInt("hours"));
        w.setRating(row.getInt("rating"));
        return w;
    }
}
