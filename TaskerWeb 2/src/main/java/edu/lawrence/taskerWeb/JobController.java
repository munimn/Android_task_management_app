package edu.lawrence.taskerWeb;

import java.sql.SQLException;
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
@RequestMapping("/jobs")
@CrossOrigin(origins="*")
public class JobController {
    private JobDAO JobDAO;
    
    public JobController(JobDAO dao) {
        this.JobDAO = dao;
    }
    
   @PostMapping
    public String save(@RequestBody Job job) {
            JobDAO.save(job);
            return "0"; 
    }
    @GetMapping(params={"customer"})
    public List<Job> findByCustomer(@RequestParam("customer") int customer) {
        return JobDAO.findByCustomer(customer);
    }
    @GetMapping(params={"idjob"})
    public List<Job> findByIdjob(@RequestParam("idjob") int idjob) {
        return JobDAO.findByCustomer(idjob);
    }
   
    
}