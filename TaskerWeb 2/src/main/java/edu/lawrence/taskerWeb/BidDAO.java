
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
public class BidDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
   
  
    
        public int save(Bid bid) {
        String insertSQL = "INSERT INTO bids (job,worker) values (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, bid.getJob());
                    ps.setInt(2, bid.getWorker());
                    
                    return ps;
                }, keyHolder);

        return keyHolder.getKey().intValue();
        
        
    }
        
        public List<Bid> findByIdbid(int job) {
	String sql = "SELECT idbid, job,worker FROM bids WHERE job=?";
        RowMapper<Bid> rowMapper = new BidRowMapper();
        return jdbcTemplate.query(sql, rowMapper, job);
    }

}
