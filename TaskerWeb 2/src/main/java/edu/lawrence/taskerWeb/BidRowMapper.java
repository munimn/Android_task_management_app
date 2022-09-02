
package edu.lawrence.taskerWeb;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class BidRowMapper implements RowMapper<Bid> {
    @Override
    public Bid mapRow(ResultSet row, int rowNum) throws SQLException {
        Bid b = new Bid();
        b.setIdbid(row.getInt("idbid"));
        b.setJob(row.getInt("job"));
        b.setWorker(row.getInt("worker"));
        
        return b;
    }
}
