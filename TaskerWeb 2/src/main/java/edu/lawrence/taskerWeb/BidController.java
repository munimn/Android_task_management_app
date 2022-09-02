
package edu.lawrence.taskerWeb;


import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bids")
@CrossOrigin(origins="*")
public class BidController {
    private BidDAO bidDAO;
    
    public BidController(BidDAO dao) {
        bidDAO = dao;
    }
        @GetMapping(params={"job"})
    public List<Bid> findByIdbid(@RequestParam("job") int job) {
        return bidDAO.findByIdbid(job);
    }
    
 
    
    @PostMapping
    public int save(@RequestBody Bid message) {
        return bidDAO.save(message);
    }
}

